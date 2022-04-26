package ru.sfedu.log4jproject.model.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Message {
    //@Column(name = "message")
    private String msg;
}
