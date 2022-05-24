package ru.sfedu.log4jproject.model.entity.component_collection;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "CC_BUILDING")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class CCBuilding {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "CC_NETWORKS")
    @AttributeOverride(
            name = "ip",
            column = @Column(name = "NET_IP", nullable = false)
    )
    protected Set<Network> networks = new HashSet<Network>();

    private String name;

    public CCBuilding(Set<Network> networks, String name) {
        this.networks = networks;
        this.name = name;
    }

}
