package ru.sfedu.log4jproject.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import ru.sfedu.log4jproject.model.entity.User;

import static org.junit.jupiter.api.Assertions.*;

class DataProviderH2Test {
    private final static Logger log = LogManager.getLogger(DataProviderH2.class);

    @Test
    void testAppendUserSuccess() {
        try {
            log.info("appendUser test success");
            DataProviderH2 dataProviderH2 = new DataProviderH2();
            User user = new User(51, "Leah");
            dataProviderH2.appendUser(user);
            assertTrue(true);
        } catch(Exception ex){
            log.error("appendUser test Success - Failure");
            fail(ex.getMessage());
        }
    }

    @Test
    void testAppendUserFail() {
        try {
            log.info("appendUser test Fail");
            DataProviderH2 dataProviderH2 = new DataProviderH2();
            User user = new User();
            dataProviderH2.appendUser(user);
            assertTrue(true);
        } catch(Exception ex){
            log.error("appendUser test Fail - Failure");
            fail(ex.getMessage());
        }
    }

    @Test
    void testGetUserByIdSuccess() {
        try {
            log.info("getUserById test success");
            DataProviderH2 dataProviderH2 = new DataProviderH2();
            dataProviderH2.getUserById(1);
            assertTrue(true);
        } catch(Exception ex){
            log.error("getUserById test Success - Failure");
            fail(ex.getMessage());
        }
    }

    @Test
    void testGetUserByIdFail() {
        try {
            log.info("getUserById test Fail");
            DataProviderH2 dataProviderH2 = new DataProviderH2();
            dataProviderH2.getUserById(580);
            assertTrue(true);
        } catch(Exception ex){
            log.error("getUserById test Fail - Failure");
            fail(ex.getMessage());
        }
    }

    @Test
    void testDeleteUserByIdSuccess() {
        try{
            log.info("deleteUserById test Success");
            DataProviderH2 dataProviderH2 = new DataProviderH2();
            dataProviderH2.deleteUserById(26);
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
            DataProviderH2 dataProviderH2 = new DataProviderH2();
            dataProviderH2.deleteUserById(35);
            assertTrue(true);
        } catch(Exception ex){
            log.error("deleteUserById test Fail - Failure");
            fail(ex.getMessage());
        }
    }

    @Test
    void testUpdateUserSuccess() {
        try{
            log.info("updateUser test Success");
            DataProviderH2 dataProviderH2 = new DataProviderH2();
            User user = new User(9, "Ada");
            dataProviderH2.updateUser(user);
            assertTrue(true);
        } catch(Exception ex){
            log.error("updateUser test Success - Failure");
            fail(ex.getMessage());
        }
    }

    @Test
    void testUpdateUserFail() {
        try{
            log.info("updateUser test Fail");
            DataProviderH2 dataProviderH2 = new DataProviderH2();
            User user = new User(56, "Tony");
            dataProviderH2.updateUser(user);
            assertTrue(true);
        } catch(Exception ex){
            log.error("updateUser test Fail - Failure");
            fail(ex.getMessage());
        }
    }
}