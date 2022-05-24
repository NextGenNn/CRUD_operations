package ru.sfedu.log4jproject.model.entity.component_collection;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity(name = "CM_BUILDING")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class CMBuilding {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "CM_NETWORKS")
    @AttributeOverride(
            name = "ip",
            column = @Column(name = "NET_IP", nullable = false)
    )
    @AttributeOverride(
            name = "owner",
            column = @Column(nullable = true)
    )
    @AttributeOverride(
            name = "freq",
            column = @Column(nullable = true)
    )
    @AttributeOverride(
            name = "range",
            column = @Column(nullable = true)
    )
    protected Map<String, Network> networks = new HashMap<String, Network>();

    private String name;

    public CMBuilding(Map<String, Network> networks, String name) {
        this.networks = networks;
        this.name = name;
    }
}
