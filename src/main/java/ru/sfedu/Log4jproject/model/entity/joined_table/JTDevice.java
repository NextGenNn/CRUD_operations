package ru.sfedu.log4jproject.model.entity.joined_table;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "jt_Device")
@Inheritance(strategy = InheritanceType.JOINED)
public class JTDevice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String manufacturer;

    private long serialNumber;

    public JTDevice(String manufacturer, long serialNumber) {
        this.manufacturer = manufacturer;
        this.serialNumber = serialNumber;
    }
}
