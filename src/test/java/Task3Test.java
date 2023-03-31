import org.junit.jupiter.api.Test;
import task3.AgentInfoBean;
import task3.Task3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class Task3Test {

    @Test
    public void testFilterByStatus() {
        Task3 task3Spy = spy(Task3.class);
        List<AgentInfoBean> input = new LinkedList<>();

        List<AgentInfoBean> list = new ArrayList<>(3);
        input.add(new AgentInfoBean(Task3.STATES.get(0)));
        input.add(new AgentInfoBean(Task3.STATES.get(1)));
        input.add(new AgentInfoBean(Task3.STATES.get(2)));
        input.add(new AgentInfoBean(""));
        input.add(new AgentInfoBean());

        doReturn(input).when(task3Spy).fetchItems();

        List<AgentInfoBean> expectedOutput = List.of(input.get(0), input.get(1), input.get(2));
        assertEquals(expectedOutput, task3Spy.filterByStatus());
    }
}
