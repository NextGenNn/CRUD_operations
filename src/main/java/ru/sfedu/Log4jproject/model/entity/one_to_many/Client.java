package ru.sfedu.log4jproject.model.entity.one_to_many;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Client implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "office_id", nullable = false)
    protected Office office;

    protected String name;
    protected String phone;

    public Client(Office office, String name, String phone) {
        this.office = office;
        this.name = name;
        this.phone = phone;
    }
}
