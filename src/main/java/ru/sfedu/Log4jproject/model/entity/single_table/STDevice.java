package ru.sfedu.log4jproject.model.entity.single_table;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "st_Device")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "device_type", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("null")
public class STDevice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String manufacturer;

    private long serialNumber;

    public STDevice(String manufacturer, long serialNumber) {
        this.manufacturer = manufacturer;
        this.serialNumber = serialNumber;
    }
}
