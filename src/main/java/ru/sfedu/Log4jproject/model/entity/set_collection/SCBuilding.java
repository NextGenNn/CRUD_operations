package ru.sfedu.log4jproject.model.entity.set_collection;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity (name = "SC_BUILDING")
@NoArgsConstructor
@ToString
@Getter
@Setter
public class SCBuilding {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "SC_NETWORKS",
            joinColumns = @JoinColumn(name = "BUILDING_ID"))
    @Column(name = "NETNAME")
    protected Set<String> networks = new HashSet<String>();

    private String name;

    public SCBuilding(String name, Set<String> networks){
        this.name = name;
        this.networks = networks;
    }
}
