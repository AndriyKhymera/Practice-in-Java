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


//    public Set<Module> getModulesIncludedInCompleteRoutes(Long agentId) {
//        CriteriaBuilder cb = session.getCriteriaBuilder();
//        CriteriaQuery<Module> query = cb.createQuery(Module.class);
//        Root<Module> module = query.from(Module.class);
//        Join<Module, ModuleRoute> moduleRoute = module.join("moduleRoutes", JoinType.INNER);
//        Join<ModuleRoute, Route> route = moduleRoute.join("route", JoinType.INNER);
//        Predicate agentPredicate = cb.equal(route.get("agent").get("id"), agentId);
//        Predicate inputPredicate = cb.equal(module.get("type"), ModuleType.n INPUT);
//        Predicate outputPredicate = cb.equal(module.get("type"), ModuleType.OUTPUT);
//        Predicate completeRoutePredicate = cb.and(cb.exists(
//                        query.select(moduleRoute).from(ModuleRoute.class)
//                                .join("module", JoinType.INNER)
//                                .where(cb.and(cb.equal(moduleRoute.get("route"), route),
//                                        inputPredicate))),
//                cb.exists(
//                        query.select(moduleRoute).from(ModuleRoute.class)
//                                .join("module", JoinType.INNER)
//                                .where(cb.and(cb.equal(moduleRoute.get("route"), route),
//                                        outputPredicate))));
//        query.where(cb.and(agentPredicate, completeRoutePredicate));
//        query.select(module);
//        return new LinkedHashSet<>(session.createQuery(query).getResultList());

}
