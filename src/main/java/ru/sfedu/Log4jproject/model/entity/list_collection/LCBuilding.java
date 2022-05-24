package ru.sfedu.log4jproject.model.entity.list_collection;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "LC_BUILDING")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class LCBuilding {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "LC_NETWORKS")
    @OrderColumn
    @Column(name = "NETNAME")
    protected List<String> networks = new ArrayList<String>();

    private String name;

    public LCBuilding(String name, List<String> networks){
        this.name = name;
        this.networks = networks;
    }
}
