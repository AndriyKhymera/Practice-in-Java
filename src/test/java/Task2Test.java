import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Slf4j
public class Task2Test extends Task2 {

    @Test
    public void test() throws IOException {
        Path tempFile = Files.createTempFile("prefix-", ".suffix");
        FileOutputStream fileOutputStream = new FileOutputStream(tempFile.toFile());
        System.setOut(new PrintStream(fileOutputStream));


        Task2 synchroProcessor = new Task2();
        Thread t1 = new Thread(synchroProcessor.new Executor());
        Thread t2 = new Thread(synchroProcessor.new Initializer());

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String[] expectedLinesInOrder = {"Initializer: initializing...",
                "Processor: initialization is done.",
                "Executor: processing..",
                "Initializer: initializing..."};
        List<String> lines = Files.readAllLines(tempFile);
        String[] filteredLines = lines.stream().map(line -> line.split("--")[1])
                .map(String::trim).toArray(String[]::new);

        for (int i = 0; i < expectedLinesInOrder.length; i++){
            Assertions.assertEquals(expectedLinesInOrder[0], filteredLines[0]);
        }
    }

}
