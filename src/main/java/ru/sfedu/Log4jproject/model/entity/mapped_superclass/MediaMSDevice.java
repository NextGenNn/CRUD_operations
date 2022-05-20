package ru.sfedu.log4jproject.model.entity.mapped_superclass;

import lombok.*;

import javax.persistence.Entity;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "ms_MediaDevice")
public class MediaMSDevice extends MSDevice {
    private boolean familyMode;

    public MediaMSDevice(String manufacturer, long serialNumber, boolean familyMode) {
        super(manufacturer, serialNumber);
        this.familyMode = familyMode;
    }

    @Override
    public String toString() {
        return "MediaMSDevice{" +
                "id=" + getId() +
                ", manufacturer=" + getManufacturer() +
                ", serialNumber=" + getSerialNumber() +
                ", familyMode=" + familyMode +
                '}';
    }
}
