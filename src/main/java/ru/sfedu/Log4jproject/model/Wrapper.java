package ru.sfedu.log4jproject.model;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;
import ru.sfedu.log4jproject.model.beans.User;

import java.util.List;
import java.util.Objects;

@Root
public class Wrapper {
    public Wrapper(){};

    @ElementList(inline = true, required = false)
    private List<User> userList;

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wrapper wrapper = (Wrapper) o;
        return Objects.equals(userList, wrapper.userList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userList);
    }

    @Override
    public String toString() {
        return "Wrapper{" +
                "userList=" + userList +
                '}';
    }
}
