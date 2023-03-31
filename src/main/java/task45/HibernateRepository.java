package task45;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class HibernateRepository {

    @PersistenceContext
    private EntityManager entityManager;


    /**
     * Returns all modules for agent which are included in a complete route.
     * A complete route contains at least 1 INPUT module and at least 1 OUTPUT module.
     */
    public Set<Module> getModulesIncludedInCompleteRoutes(Long agentId) {
        Set<Module> modules = new LinkedHashSet<>();
        String hql = "SELECT DISTINCT modules FROM ModuleRoute moduleRoutes " +
                "JOIN moduleRoutes.module modules " +
                "JOIN moduleRoutes.route routes " +
                "WHERE routes.agent.id = :agentId " +
                "AND routes.id IN ( " +
                "   SELECT DISTINCT routesInner.id FROM Route routesInner " +
                "   JOIN routesInner.moduleRoutes moduleRoutesInner " +
                "   JOIN moduleRoutesInner.module modulesInner " +
                "   WHERE routesInner.agent.id = :agentId " +
                "   AND modulesInner.type = :inputType " +
                ") " +
                "AND routes.id IN ( " +
                "   SELECT DISTINCT routesInner.id FROM Route routesInner" +
                "   JOIN routesInner.moduleRoutes moduleRoutesInner " +
                "   JOIN moduleRoutesInner.module modulesInner " +
                "   WHERE routesInner.agent.id = :agentId " +
                "   AND modulesInner.type = :outputType " +
                ")";
        Query<Module> query = entityManager.unwrap(Session.class).createQuery(hql, Module.class);
        query.setParameter("agentId", agentId);
        query.setParameter("inputType", ModuleType.INPUT);
        query.setParameter("outputType", ModuleType.OUTPUT);

        List<Module> resultList = query.getResultList();
        modules.addAll(resultList);
        return modules;
    }

}
