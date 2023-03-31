package task45;

import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class DataInitializer {

    @Autowired
    private AgentRepository agentRepository;
    @Autowired
    private RouteRepository routeRepository;
    @Autowired
    private ModuleRouteRepository moduleRouteRepository;
    @Autowired
    private ModuleRepository moduleRepository;
    private static final Random random = new Random();

    public void initAgents(int count) {
        List<Agent> agents = new ArrayList<>();
        Faker faker = new Faker();
        for (int i = 0; i < count; i++) {
            Agent agent = new Agent();
            agent.setName(faker.name().fullName());
            agent.setType(AgentType.values()[random.nextInt(AgentType.values().length)]);
            agent.setGlobalConfig(faker.lorem().paragraph());
            agents.add(agent);
        }
        agentRepository.saveAllAndFlush(agents);
        // Save agents to database using your Hibernate repository
    }

    public void initRoutes(int count, List<Agent> agents) {
        List<Route> routes = new ArrayList<>();
        Faker faker = new Faker();
        for (int i = 0; i < count; i++) {
            Route route = new Route();
            route.setAgent(agents.get(random.nextInt(agents.size())));
            route.setName(faker.commerce().productName());
            route.setPriority(random.nextInt(10));
            routes.add(route);
        }
        routeRepository.saveAllAndFlush(routes);
    }

    public void initModules(int count, List<Agent> agents) {
        List<Module> modules = new ArrayList<>();
        Faker faker = new Faker();
        for (int i = 0; i < count; i++) {
            Module module = new Module();
            module.setAgent(agents.get(random.nextInt(agents.size())));
            module.setName(faker.commerce().productName());
            module.setType(ModuleType.values()[random.nextInt(ModuleType.values().length)]);
            modules.add(module);
        }
        moduleRepository.saveAllAndFlush(modules);
    }

    public void initModuleRoutes(int count, List<Module> modules, List<Route> routes) {
        List<ModuleRoute> moduleRoutes = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            ModuleRoute moduleRoute = new ModuleRoute();
            moduleRoute.setModule(modules.get(random.nextInt(modules.size())));
            moduleRoute.setRoute(routes.get(random.nextInt(routes.size())));
            moduleRoutes.add(moduleRoute);
        }
        moduleRouteRepository.saveAllAndFlush(moduleRoutes);
    }
}
