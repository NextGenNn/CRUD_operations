package ru.sfedu.log4jproject.model.entity.one_to_one;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "SFK_MANAGER")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class SharedFKManager {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long id;

    protected String name;
    protected int accessLevel;

    public SharedFKManager(String name, int accessLevel) {
        this.name = name;
        this.accessLevel = accessLevel;
    }
}
