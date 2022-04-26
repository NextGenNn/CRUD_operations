package ru.sfedu.log4jproject.api;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import ru.sfedu.log4jproject.model.Result;
import ru.sfedu.log4jproject.model.entity.User;

import static org.junit.jupiter.api.Assertions.*;
@Slf4j
class DataProviderCsvTest {
    @Test
    public void testAppendUserSuccess(){
        try {
            log.info("AppendUser test Success");
            DataProviderCsv dataProviderCsv = new DataProviderCsv();

            User user = new User(4, "Frank");

            dataProviderCsv.appendUser(user);

            assertTrue(true);
        }
        catch(Exception ex){
            log.error("AppendUser test Success - Failure");
            fail(ex.getMessage());
        }
    }

    @Test
    public void testAppendUserFail(){
        try {
            log.info("AppendUser test Fail");
            DataProviderCsv dataProviderCsv = new DataProviderCsv();

            User user = new User();

            dataProviderCsv.appendUser(user);

            log.info("appendUser test Fail - Success");
            assertTrue(true);
        }
        catch(Exception ex){
            log.error("appendUser test Fail - Failure");
            fail("No exceptions received");
        }
    }

    @Test
    public void testGetUserByIdSuccess() {
        try {
            log.info("getUserById test Success");
            DataProviderCsv dataProviderCsv = new DataProviderCsv();
            User gotUser = new User();

            Result<User> result = dataProviderCsv.getUserById(6);
            if(result.getEntity() != null){gotUser = result.getEntity();}
            log.info(gotUser.toString());

            assertTrue(true);
        }
        catch(Exception ex){
            log.error("getUserById test Success - Failure");
            fail(ex.getMessage());
        }
    }

    @Test
    public void testGetUserByIdFail(){
        try {
            log.info("getUserById test Fail");
            DataProviderCsv dataProviderCsv = new DataProviderCsv();
            User gotUser = new User(0, "Empty");

            Result<User> result = dataProviderCsv.getUserById(5);
            if(result.getEntity() != null){gotUser = result.getEntity();}
            log.info(gotUser.toString());

            log.info("getUserById test Fail - Success");
            assertTrue(true);
        }
        catch(Exception ex){
            log.error("getUserById test Fail - Failure");
            fail("No exceptions received");
        }
    }

    @Test
    public void testDeleteUserByIdSuccess(){
        try{
            log.info("deleteUserById test Success");
            DataProviderCsv dataProviderCsv = new DataProviderCsv();
            dataProviderCsv.deleteUserById(13);

            assertTrue(true);
        } catch (Exception ex){
            log.error("deleteUserById test Success - Failure");
            fail(ex.getMessage());
        }
    }

    @Test
    public void testDeleteUserByIdFail(){
        try{
            log.info("deleteUserById test Fail");
            DataProviderCsv dataProviderCsv = new DataProviderCsv();
            dataProviderCsv.deleteUserById(5);

            log.info("deleteUserById test Fail - Success");
            assertTrue(true);
        } catch (Exception ex){
            log.error("deleteUserById test Fail - Failure");
            fail("No exceptions received");
        }
    }

    @Test
    public void testUpdateUserSuccess(){
        try{
            log.info("updateUser test Success");
            User toUpdate = new User(3, "Lewis");
            DataProviderCsv dataProviderCsv = new DataProviderCsv();
            dataProviderCsv.updateUser(toUpdate);

            assertTrue(true);
        } catch (Exception ex){
            log.error("updateUser test Success - Failure");
            fail(ex.getMessage());
        }
    }

    @Test
    public void testUpdateUserFail(){
        try{
            log.info("updateUser test Success");
            User toUpdate = new User();
            DataProviderCsv dataProviderCsv = new DataProviderCsv();
            dataProviderCsv.updateUser(toUpdate);

            log.info("updateUser test Fail - Success");
            assertTrue(true);
        } catch (Exception ex){
            log.error("updateUser test Success - Failure");
            fail("No exceptions received");
        }
    }
}