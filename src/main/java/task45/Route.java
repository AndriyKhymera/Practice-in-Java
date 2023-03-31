package task45;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.NaturalId;

import jakarta.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
public class Route extends AbstractEntity {

    @NaturalId
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "agent_id")
    private Agent agent;
    @NaturalId
    @Column(name = "name", length = 128)
    private String name;
    @Column(name = "priority")
    private Integer priority;
    @OneToMany(mappedBy = "route", cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(FetchMode.SUBSELECT)
    @OrderBy("id")
    private Set<ModuleRoute> moduleRoutes = new LinkedHashSet<ModuleRoute>();

    public String toString() {
        Agent var10000 = this.getAgent();
        return "Route(agent=" + var10000 + ", name=" + this.getName() + ", priority=" + this.getPriority() + ")";
    }

    //getters and setters skipped for briefity.
}

