package ru.sfedu.log4jproject.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class Result <T>{

    public String message;
    public T entity;
    public CodeType code;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getEntity() {
        return entity;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }

    public CodeType getCode() {
        return code;
    }

    public void setCode(CodeType code) {
        this.code = code;
    }

    public boolean isEmpty(){return (this.entity == null);}

    @Override
    public String toString() {
        return "Result{" +
                "message='" + message + '\'' +
                ", entity=" + entity +
                ", code=" + code +
                '}';
    }
}
