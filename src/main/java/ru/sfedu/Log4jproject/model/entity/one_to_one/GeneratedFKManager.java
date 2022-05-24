package ru.sfedu.log4jproject.model.entity.one_to_one;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

@Entity(name = "GFK_MANAGER")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class GeneratedFKManager {
    @Id
    @GeneratedValue(generator = "managerKeyGenerator")
    @GenericGenerator(
            name = "managerKeyGenerator",
            strategy = "foreign",
            parameters =
                    @Parameter(
                            name = "property",
                            value = "facility"
                    )
    )
    protected long id;

    @OneToOne(optional = false)
    @PrimaryKeyJoinColumn
    protected GeneratedFKFacility facility;

    protected String name;
    protected int accessLevel;

    public GeneratedFKManager(GeneratedFKFacility facility) {
        this.facility = facility;
    }

    public GeneratedFKManager(GeneratedFKFacility facility, String name, int accessLevel) {
        this.facility = facility;
        this.name = name;
        this.accessLevel = accessLevel;
    }
}
