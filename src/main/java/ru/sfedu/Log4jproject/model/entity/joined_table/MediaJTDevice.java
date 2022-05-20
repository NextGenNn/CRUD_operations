package ru.sfedu.log4jproject.model.entity.joined_table;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "jt_MediaDevice")
@PrimaryKeyJoinColumn(name = "device_id")
public class MediaJTDevice extends JTDevice {
    private boolean familyMode;

    public MediaJTDevice(String manufacturer, long serialNumber, boolean familyMode) {
        super(manufacturer, serialNumber);
        this.familyMode = familyMode;
    }

    @Override
    public String toString() {
        return "MediaJTDevice{" +
                "id=" + getId() +
                ", manufacturer=" + getManufacturer() +
                ", serialNumber=" + getSerialNumber() +
                ", familyMode=" + familyMode +
                '}';
    }
}
