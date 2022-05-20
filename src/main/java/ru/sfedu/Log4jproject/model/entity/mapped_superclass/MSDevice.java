package ru.sfedu.log4jproject.model.entity.mapped_superclass;

import lombok.*;

import javax.persistence.*;

@MappedSuperclass
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MSDevice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String manufacturer;

    private long serialNumber;

    public MSDevice(String manufacturer, long serialNumber) {
        this.manufacturer = manufacturer;
        this.serialNumber = serialNumber;
    }
}
