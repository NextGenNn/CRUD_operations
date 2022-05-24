package ru.sfedu.log4jproject.model.entity.component_collection;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Network implements Serializable {
    @Column(nullable = false)
    protected String owner;

    @Column(nullable = false)
    protected String ip;

    protected int range;
    protected int freq;

    public Network(String owner, String ip, int range, int freq) {
        this.owner = owner;
        this.ip = ip;
        this.range = range;
        this.freq = freq;
    }
}
