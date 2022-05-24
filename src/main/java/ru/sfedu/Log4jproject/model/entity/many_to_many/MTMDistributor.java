package ru.sfedu.log4jproject.model.entity.many_to_many;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "MTM_DISTRIBUTOR")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class MTMDistributor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long id;

    @ManyToMany(mappedBy = "distributors")
    @ToString.Exclude
    protected Set<MTMFacility> facilities = new HashSet<MTMFacility>();

    protected String type;
    protected String name;

    public MTMDistributor(String type, String name) {
        this.type = type;
        this.name = name;
    }
}
