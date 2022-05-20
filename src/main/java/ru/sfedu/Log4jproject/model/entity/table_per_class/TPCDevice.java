package ru.sfedu.log4jproject.model.entity.table_per_class;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "tpc_Device")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class TPCDevice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String manufacturer;

    private long serialNumber;

    public TPCDevice(String manufacturer, long serialNumber) {
        this.manufacturer = manufacturer;
        this.serialNumber = serialNumber;
    }
}
