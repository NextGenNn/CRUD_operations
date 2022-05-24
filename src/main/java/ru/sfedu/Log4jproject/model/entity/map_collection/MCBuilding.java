package ru.sfedu.log4jproject.model.entity.map_collection;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity(name = "MC_BUILDING")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class MCBuilding {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "MC_NETWORKS")
    @MapKeyColumn(name = "IP")
    @Column(name = "NETNAME")
    protected Map<String, String> networks = new HashMap<>();

    private String name;

    public MCBuilding(String name, Map<String, String> networks){
        this.name = name;
        this.networks = networks;
    }
}
