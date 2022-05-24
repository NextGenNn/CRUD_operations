package ru.sfedu.log4jproject.model.entity.one_to_many;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Office implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private long id;

    @OneToMany(
            mappedBy = "office",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            orphanRemoval = true
    )
    @ToString.Exclude
    protected Set<Client> clients = new HashSet<Client>();

    protected String address;

    public Office(Set<Client> clients, String address) {
        this.clients = clients;
        this.address = address;
    }

    public Office(String address) {
        this.address = address;
    }
}
