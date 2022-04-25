package ru.sfedu.log4jproject.model.beans;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "test", schema = "public")
public class TestEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String desc;

    @Column(name = "creationdate", nullable = false)
    private LocalDate dateCreated;

    @Column(name = "checked", nullable = false)
    private boolean check;

    @Override
    public int hashCode() {
        return Objects.hash(id, name, desc, dateCreated, check);
    }

    @Override
    public String toString() {
        return "TestEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", dateCreated=" + dateCreated +
                ", check=" + check +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestEntity that = (TestEntity) o;
        return id == that.id && check == that.check && name.equals(that.name) && desc.equals(that.desc) && dateCreated.equals(that.dateCreated);
    }
}
