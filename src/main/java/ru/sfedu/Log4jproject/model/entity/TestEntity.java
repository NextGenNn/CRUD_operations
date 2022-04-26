package ru.sfedu.log4jproject.model.entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "test", schema = "public")
public class TestEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "test_id", nullable = false)
    private long id;

    @Column(name = "test_name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String desc;

    @Column(name = "creationdate", nullable = false)
    private LocalDate dateCreated;

    @Column(name = "checked", nullable = false)
    private boolean check;

    @Embeddable
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class Address implements Serializable {
        @NotNull
        private String city;
        @NotNull
        private String street;
    }

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "city", column = @Column(name = "home_city")),
            @AttributeOverride(name = "street", column = @Column(name = "home_street"))
    })
    private Address homeAddress;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "city", column = @Column(name = "work_city")),
            @AttributeOverride(name = "street", column = @Column(name = "work_street"))
    })
    private Address workAddress;

    @Override
    public String toString() {
        return "TestEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", dateCreated=" + dateCreated +
                ", check=" + check +
                ", homeAddress=" + homeAddress +
                ", workAddress=" + workAddress +
                '}';
    }
}
