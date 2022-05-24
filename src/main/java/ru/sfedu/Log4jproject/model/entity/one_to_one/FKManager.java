package ru.sfedu.log4jproject.model.entity.one_to_one;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "FK_MANAGER")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class FKManager {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long id;

    @NotNull
    protected String name;
    @NotNull
    protected int accessLevel;

    public FKManager(String name, int accessLevel) {
        this.name = name;
        this.accessLevel = accessLevel;
    }
}
