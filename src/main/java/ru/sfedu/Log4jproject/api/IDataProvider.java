package ru.sfedu.log4jproject.api;
import ru.sfedu.log4jproject.model.Result;
import ru.sfedu.log4jproject.model.beans.User;


public interface IDataProvider {

    Result<User> appendUser(User userToAppend);
    Result<User> getUserById(long id);
    Result<User> deleteUserById(long id);
    Result<User> updateUser(User userToUpdate);

}
