package ru.sfedu.log4jproject.model.entity.mapped_superclass;

import lombok.*;

import javax.persistence.Entity;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "ms_CommDevice")
public class CommMSDevice extends MSDevice {
    private long frequency;

    public CommMSDevice(String manufacturer, long serialNumber, long frequency) {
        super(manufacturer, serialNumber);
        this.frequency = frequency;
    }

    @Override
    public String toString() {
        return "CommMSDevice{" +
                "id=" + getId() +
                ", manufacturer=" + getManufacturer() +
                ", serialNumber=" + getSerialNumber() +
                ", frequency=" + frequency +
                '}';
    }
}
