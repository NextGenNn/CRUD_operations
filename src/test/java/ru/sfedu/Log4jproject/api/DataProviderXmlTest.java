package ru.sfedu.log4jproject.api;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import ru.sfedu.log4jproject.model.entity.User;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class DataProviderXmlTest {

    @Test
    void testAppendUserSuccess() {
        try {
            log.info("appendUser test Success");
            User user = new User(2, "Bean");
            DataProviderXml dataProviderXml = new DataProviderXml();
            dataProviderXml.appendUser(user);
            assertTrue(true);
        } catch (Exception ex){
            log.error("appendUser test Success - Failure");
            fail(ex.getMessage());
        }
    }

    @Test
    void testAppendUserFail(){
        try{
            log.info("appendUser test Fail");
            User user = new User();
            DataProviderXml dataProviderXml = new DataProviderXml();
            dataProviderXml.appendUser(user);
            assertTrue(true);
        } catch (Exception ex){
            log.error("appendUser test Fail - Failure");
            fail(ex.getMessage());
        }
    }

    @Test
    void testGetUserByIdSuccess() {
        try {
            log.info("getUserById test Success");
            DataProviderXml dataProviderXml = new DataProviderXml();
            dataProviderXml.getUserById(2);
            assertTrue(true);
        } catch (Exception ex){
            log.error("getUserById test Success - Failure");
            fail(ex.getMessage());
        }
    }

    @Test
    void testGetUserByIdFail() {
        try {
            log.info("getUserById test Fail");
            DataProviderXml dataProviderXml = new DataProviderXml();
            dataProviderXml.getUserById(1);
            assertTrue(true);
        } catch (Exception ex){
            log.error("getUserById test Fail - Failure");
            fail(ex.getMessage());
        }
    }

    @Test
    void testDeleteUserByIdSuccess() {
        try{
            log.info("deleteUserById test Success");
            DataProviderXml dataProviderXml = new DataProviderXml();
            dataProviderXml.deleteUserById(3);
            assertTrue(true);
        } catch(Exception ex){
            log.error("deleteUserById test Success - Failure");
            fail(ex.getMessage());
        }
    }

    @Test
    void testDeleteUserByIdFail() {
        try{
            log.info("deleteUserById test Fail");
            DataProviderXml dataProviderXml = new DataProviderXml();
            dataProviderXml.deleteUserById(14);
            assertTrue(true);
        } catch(Exception ex){
            log.error("deleteUserById test Fail - Failure");
            fail(ex.getMessage());
        }
    }

    @Test
    void updateUserSuccess() {
        try{
            log.info("updateUser test Success");
            DataProviderXml dataProviderXml = new DataProviderXml();
            User user = new User(5, "Helen");
            dataProviderXml.updateUser(user);
            assertTrue(true);
        } catch(Exception ex){
            log.error("updateUser test Success - Failure");
            fail(ex.getMessage());
        }
    }

    @Test
    void updateUserFail() {
        try{
            log.info("updateUser test Fail");
            DataProviderXml dataProviderXml = new DataProviderXml();
            User user = new User();
            dataProviderXml.updateUser(user);
            assertTrue(true);
        } catch(Exception ex){
            log.error("updateUser test Fail - Failure");
            fail(ex.getMessage());
        }
    }
}