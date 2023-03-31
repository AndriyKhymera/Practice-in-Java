package task3;

import java.util.*;
import java.util.stream.Collectors;

public class Task3 {

    public static final List<String> STATES = List.of("state0", "state1", "state2");

    public List<AgentInfoBean> filterByStatus() {
        final List<AgentInfoBean> agentInfos = fetchItems();
        List<AgentInfoBean> filteredResults = new LinkedList<>();

        return agentInfos.stream()
                .filter(item -> Objects.nonNull(item.getStatus()))
                .filter(item -> STATES.contains(item.getStatus()))
                .collect(Collectors.toList());
    }

    public List<AgentInfoBean> fetchItems() {
        List<AgentInfoBean> items = new ArrayList<>();
        int listSize = new Random().nextInt();
        for (int i = 0; i < listSize; i++) {
            items.set(i, new AgentInfoBean());
        }
        return setRandomStatus(items);
    }

    public List<AgentInfoBean> setRandomStatus(List<AgentInfoBean> inputlist) {
        Random random = new Random();
        List<AgentInfoBean> resultList = List.copyOf(inputlist);
        int numberOfItemsToUpdate = random.nextInt(inputlist.size());
        for (int i = 0; i < numberOfItemsToUpdate; i++) {
            int statesIndex = random.nextInt(STATES.size());
            int itemIndex = random.nextInt(resultList.size());
            resultList.get(itemIndex).setStatus(STATES.get(statesIndex));
        }
        return resultList;
    }

}
