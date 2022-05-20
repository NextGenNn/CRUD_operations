package ru.sfedu.log4jproject.model.entity.table_per_class;

import lombok.*;
import ru.sfedu.log4jproject.model.entity.mapped_superclass.MSDevice;
import javax.persistence.Entity;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "tpc_MediaDevice")
public class MediaTPCDevice extends MSDevice {
    private boolean familyMode;

    public MediaTPCDevice(String manufacturer, long serialNumber, boolean familyMode) {
        super(manufacturer, serialNumber);
        this.familyMode = familyMode;
    }

    @Override
    public String toString() {
        return "MediaTPCDevice{" +
                "id=" + getId() +
                ", manufacturer=" + getManufacturer() +
                ", serialNumber=" + getSerialNumber() +
                ", familyMode=" + familyMode +
                '}';
    }
}
