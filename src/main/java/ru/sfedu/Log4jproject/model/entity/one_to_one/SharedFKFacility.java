package ru.sfedu.log4jproject.model.entity.one_to_one;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity(name = "SFK_FACILITY")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class SharedFKFacility {
    @Id
    protected long id;

    protected String address;

    @OneToOne(
            optional = false,
            fetch = FetchType.LAZY
    )
    @PrimaryKeyJoinColumn
    @ToString.Exclude
    protected SharedFKManager manager;

    public SharedFKFacility(long id, String address, SharedFKManager manager) {
        this.id = id;
        this.address = address;
        this.manager = manager;
    }
}
