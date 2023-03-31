import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Slf4j
public class Task1 {

    public static boolean isCurrentVersionHigherOrEqual(String agentVersion, String currentAgentVersion) {
        if (StringUtils.isBlank(currentAgentVersion) || StringUtils.isBlank(agentVersion)) {
            log.info("Versions is not in valid format");
            return false;
        }

        String[] currentVersionParts = currentAgentVersion.split("\\.");
        String[] agentVersionParts = agentVersion.split("\\.");

        if (currentVersionParts.length !=3 || agentVersionParts.length!=3){
            log.info("Versions is not in valid format");
            return false;
        }

        for (int i = 0; i < 3; i++) {
            int currentVersionPart = Integer.parseInt(currentVersionParts[i]);
            int agentVersionPart = Integer.parseInt(agentVersionParts[i]);

            if (currentVersionPart > agentVersionPart) {
                return true;
            } else if (currentVersionPart < agentVersionPart) {
                return false;
            }
        }
        //In case there are equal
        return true;
    }

}
