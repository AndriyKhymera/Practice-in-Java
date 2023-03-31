package task45;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import task45.Agent;
import task45.ModuleType;

import java.util.List;

@Repository
public interface ModuleRepository extends JpaRepository<Module, Long> {

    List<Module> findByAgent(Agent agent);

    List<Module> findByAgentAndType(Agent agent, ModuleType type);

}
