package ru.sfedu.log4jproject.model.entity.table_per_class;

import lombok.*;
import ru.sfedu.log4jproject.model.entity.mapped_superclass.MSDevice;
import javax.persistence.Entity;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "tpc_CommDevice")
public class CommTPCDevice extends TPCDevice {
    private long frequency;

    public CommTPCDevice(String manufacturer, long serialNumber, long frequency) {
        super(manufacturer, serialNumber);
        this.frequency = frequency;
    }

    @Override
    public String toString() {
        return "CommTPCDevice{" +
                "id=" + getId() +
                ", manufacturer=" + getManufacturer() +
                ", serialNumber=" + getSerialNumber() +
                ", frequency=" + frequency +
                '}';
    }
}
