package task45;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.NaturalId;

import jakarta.persistence.*;

import static jakarta.persistence.CascadeType.DETACH;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
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

    //getters and setters skipped for briefity.
}
