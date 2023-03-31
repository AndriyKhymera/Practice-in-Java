package task45;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import jakarta.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "AGENT")
@ToString
public class Agent extends AbstractEntity {

    @Column(name = "name", length = 127, nullable = false, unique = true)
    protected String name;
    @Column(nullable = false, length = 31)
    @Enumerated(EnumType.STRING)
    private AgentType type;
    @Column(name = "global_config", length = 32671)
    @Lob
    protected String globalConfig;

    @OneToMany(mappedBy = "agent", cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(FetchMode.SUBSELECT)
    @OrderBy("id")
    protected Set<Route> routes = new LinkedHashSet<>();
    @OneToMany(mappedBy = "agent", cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(FetchMode.SUBSELECT)
    protected Set<Module> modules = new LinkedHashSet<>();

    public String toString() {
        String var10000 = this.getName();
        return "Agent(name=" + var10000 + ", type=" + this.getType() + ", globalConfig=" + this.getGlobalConfig() + ")";
    }
    //getters and setters skipped for briefity.
}
