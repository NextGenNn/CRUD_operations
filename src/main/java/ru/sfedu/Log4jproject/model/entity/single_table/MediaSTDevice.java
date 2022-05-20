package ru.sfedu.log4jproject.model.entity.single_table;

import lombok.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity(name = "st_MediaDevice")
@Getter
@Setter
@NoArgsConstructor
@DiscriminatorValue("Media")
public class MediaSTDevice extends STDevice {
    private boolean familyMode;

    public MediaSTDevice(String manufacturer, long serialNumber, boolean familyMode) {
        super(manufacturer, serialNumber);
        this.familyMode = familyMode;
    }

    @Override
    public String toString() {
        return "MediaSTDevice{" +
                "id=" + getId() +
                ", manufacturer=" + getManufacturer() +
                ", serialNumber=" + getSerialNumber() +
                ", familyMode=" + familyMode +
                '}';
    }
}
