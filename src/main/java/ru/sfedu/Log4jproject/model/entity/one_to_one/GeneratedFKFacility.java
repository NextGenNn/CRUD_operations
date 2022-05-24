package ru.sfedu.log4jproject.model.entity.one_to_one;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

import static javax.persistence.GenerationType.AUTO;
import static javax.persistence.GenerationType.IDENTITY;

@Entity(name = "GFK_Facility")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class GeneratedFKFacility {
    @Id
    @GeneratedValue(strategy = AUTO)
    protected long id;

    protected String address;

    @OneToOne(
            mappedBy = "facility",
            cascade = CascadeType.PERSIST
    )
    protected GeneratedFKManager manager;

    public GeneratedFKFacility(String address, GeneratedFKManager manager) {
        this.address = address;
        this.manager = manager;
    }

    public GeneratedFKFacility(String address) {
        this.address = address;
    }
}
