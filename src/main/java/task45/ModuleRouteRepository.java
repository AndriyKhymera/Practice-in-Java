package task45;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModuleRouteRepository extends JpaRepository<ModuleRoute, Long> {

    List<ModuleRoute> findByRoute(Route route);

    List<ModuleRoute> findByModule(Module module);

}
