package ru.sfedu.log4jproject.model.entity.many_to_many;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "MTM_FACILITY")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class MTMFacility {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long id;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "FACILITY_DISTRIBUTOR",
            joinColumns = @JoinColumn(name = "FACILITY_ID"),
            inverseJoinColumns = @JoinColumn(name = "DISTRIBUTOR_ID")
    )
    @ToString.Exclude
    protected Set<MTMDistributor> distributors = new HashSet<MTMDistributor>();

    protected String address;

    public MTMFacility(String address) {
        this.address = address;
    }
}
