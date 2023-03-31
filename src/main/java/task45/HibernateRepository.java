package task45;

import org.hibernate.Session;

import javax.persistence.criteria.*;
import java.util.HashSet;
import java.util.Set;

public class HibernateRepository {

    private Session session;
    //private EntityManager em;

    /**
     * Returns all modules for agent which are included in a complete route.
     * A complete route contains at least 1 INPUT module and at least 1 OUTPUT module.
     * Returns all modules for agent which are included route which contains at least 1 INPUT module and at least 1 OUTPUT module.
     */

    public Set<Module> getModulesIncludedInCompleteRoutes(Long agentId) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Module> query = builder.createQuery(Module.class);
        Root<Route> route = query.from(Route.class);
        Join<Route, ModuleRoute> moduleRoute = route.join("moduleRoutes", JoinType.INNER);
        Join<ModuleRoute, Module> module = moduleRoute.join("module", JoinType.INNER);
        query.select(module);
        query.where(builder.equal(route.get("agent"), agentId));
        query.groupBy(module.get("id"));
        query.having(builder.equal(builder.countDistinct(route), builder.size(module.get("routes"))));

        return new HashSet<>(session.createQuery(query).getResultList());
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
