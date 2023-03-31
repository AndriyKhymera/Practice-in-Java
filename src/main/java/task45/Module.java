package task45;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

import static javax.persistence.CascadeType.DETACH;

@Entity
@Getter
@Setter
public class Module extends AbstractEntity {

    @NaturalId
    @ManyToOne(cascade = DETACH)
    @JoinColumn(name = "agent_id")
    private Agent agent;
    @NaturalId
    @Column(name = "name", length = 128)
    private String name;
    @Column(nullable = false, length = 31)
    @Enumerated(EnumType.STRING)
    private ModuleType type;

    public Module() {
    }

    //getters and setters skipped for briefity.
}
