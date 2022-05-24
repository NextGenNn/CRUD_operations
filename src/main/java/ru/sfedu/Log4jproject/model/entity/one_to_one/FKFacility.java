package ru.sfedu.log4jproject.model.entity.one_to_one;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity(name = "FK_FACILITY")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class FKFacility {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long id;

    @OneToOne(
            fetch = FetchType.LAZY,
            optional = false,
            cascade = CascadeType.PERSIST
    )
    @JoinColumn(unique = true)
    @ToString.Exclude
    protected FKManager manager;

    protected String address;

    public FKFacility(FKManager manager, String address) {
        this.manager = manager;
        this.address = address;
    }
    public FKFacility(String address) {
        this.address = address;
    }
}
