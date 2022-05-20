package ru.sfedu.log4jproject.model.entity.single_table;

import lombok.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "st_CommDevice")
@DiscriminatorValue("Comm")
public class CommSTDevice extends STDevice {
    private long frequency;

    public CommSTDevice(String manufacturer, long serialNumber, long frequency) {
        super(manufacturer, serialNumber);
        this.frequency = frequency;
    }

    @Override
    public String toString() {
        return "CommSTDevice{" +
                "id=" + getId() +
                ", manufacturer=" + getManufacturer() +
                ", serialNumber=" + getSerialNumber() +
                ", frequency=" + frequency +
                '}';
    }
}
