import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import task45.Agent;
import task45.AgentType;
import task45.HibernateRepository;
import task45.ModuleType;
import task45.Module;

@DataJpaTest
@Transactional
@Sql({"/schema.sql", "/data.sql"})
public class HibernateRepositoryTest {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private HibernateRepository hibernateRepository;

    @Test
    public void testGetModulesIncludedInCompleteRoutes() {
        Long agentId = 1L;
        Set<Module> expectedModules = new HashSet<>();
        expectedModules.add(new Module(1L, new Agent(1L, "agent1", AgentType.TYPE1, "tp", ""), "module1", ModuleType.INPUT));
        expectedModules.add(new Module(2L, new Agent(1L, "agent1", AgentType.TYPE2, ""), "module2", ModuleType.OUTPUT));
        Module module = new Module();
        module.setType(ModuleType.PROCESSOR);
        expectedModules.add(new Module(3L, new Agent(), "module3", ));

        Set<Module> actualModules = hibernateRepository.getModulesIncludedInCompleteRoutes(agentId);

        assertEquals(expectedModules, actualModules);
    }
}
