import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import task45.Module;
import task45.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = {HibernateRepository.class, Application.class, Agent.class})
public class HibernateRepositoryTest {

    @Autowired
    HibernateRepository hibernateRepository;

    @Test
//    @Sql("/data.sql")
    public void testGetModulesIncludedInCompleteRoutes() {
        Long agentId = 1L;
        Set<Module> expectedModules = new HashSet<>();
        Module module1 = new Module();
        module1.setType(ModuleType.INPUT);
        module1.setName("module1");
        Module module2 = new Module();
        module2.setType(ModuleType.OUTPUT);
        module2.setName("module2");
        expectedModules.addAll(List.of(module1, module2));

        Set<Module> actualModules = hibernateRepository.getModulesIncludedInCompleteRoutes(agentId);
        assertEquals(expectedModules, actualModules);
    }
}
