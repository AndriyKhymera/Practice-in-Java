import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import task45.HibernateRepository;
import task45.Module;
import task45.ModuleType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
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
        Module module1 = new Module();
        module1.setType(ModuleType.INPUT);
        module1.setName("module1");
        Module module2 = new Module();
        module2.setType(ModuleType.OUTPUT);
        module2.setName("module3");
        Module module3 = new Module();
        module3.setType(ModuleType.EXTENSION);
        module3.setName("module3");
        expectedModules.addAll(List.of(module1, module2, module3));

        Set<Module> actualModules = hibernateRepository.getModulesIncludedInCompleteRoutes(agentId);

        assertEquals(expectedModules, actualModules);
    }
}
