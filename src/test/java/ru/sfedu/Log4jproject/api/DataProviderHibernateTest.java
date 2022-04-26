package ru.sfedu.log4jproject.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import ru.sfedu.log4jproject.model.entity.TestEntity;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DataProviderHibernateTest {
    private static final Logger log = LogManager.getLogger(DataProviderHibernateTest.class);

    @Test
    void getDatabaseSizeTestSuccess() {
        try{
            log.info("getDatabaseSize test Success");
            DataProviderHibernate dataProvider = new DataProviderHibernate();
            dataProvider.getDatabaseSize();
        } catch (Exception ex){
            log.error("getDatabaseSize test Success - Failure");
            fail(ex.getMessage());
        }
    }

    @Test
    void getDatabaseSizeVersionSuccess() {
        try{
            log.info("getDatabaseVersion test Success");
            DataProviderHibernate dataProvider = new DataProviderHibernate();
            dataProvider.getDatabaseVersion();
        } catch (Exception ex){
            log.error("getDatabaseVersion test Success - Failure");
            fail(ex.getMessage());
        }
    }

    @Test
    void getEntriesCountSuccess() {
        try{
            log.info("getEntriesCount test Success");
            DataProviderHibernate dataProvider = new DataProviderHibernate();
            dataProvider.getEntriesCount();
        } catch (Exception ex){
            log.error("getEntriesCount test Success - Failure");
            fail(ex.getMessage());
        }
    }

    @Test
    void getTablesSuccess() {
        try{
            log.info("getEntriesCount test Success");
            DataProviderHibernate dataProvider = new DataProviderHibernate();
            dataProvider.getTables();
        } catch (Exception ex){
            log.error("getEntriesCount test Success - Failure");
            fail(ex.getMessage());
        }
    }

    @Test
    void saveTestEntitySuccess(){
        try{
            log.info("saveTestEntitySuccess test Success");
            DataProviderHibernate dataProvider = new DataProviderHibernate();
            //TestEntity test = new TestEntity(0, "Joker", "Favorite card", LocalDate.now(), true);
            TestEntity test = new TestEntity();
            test.setCheck(true);
            test.setDesc("Favorite card");
            test.setName("Joker");
            test.setDateCreated(LocalDate.now());
            dataProvider.saveTestEntity(test);
        } catch (Exception ex){
            log.error("saveTestEntitySuccess test Success - Failure");
            fail(ex.getMessage());
        }
    }
}