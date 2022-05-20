package ru.sfedu.log4jproject.model.entity.joined_table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "jt_CommDevice")
@PrimaryKeyJoinColumn(name = "device_id")
public class CommJTDevice extends JTDevice {
    private long frequency;

    public CommJTDevice(String manufacturer, long serialNumber, long frequency) {
        super(manufacturer, serialNumber);
        this.frequency = frequency;
    }

    @Override
    public String toString() {
        return "CommJTDevice{" +
                "id=" + getId() +
                ", manufacturer=" + getManufacturer() +
                ", serialNumber=" + getSerialNumber() +
                ", frequency=" + frequency +
                '}';
    }

}
