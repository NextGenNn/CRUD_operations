package ru.sfedu.log4jproject.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import ru.sfedu.log4jproject.model.entity.TestEntity;
import ru.sfedu.log4jproject.model.entity.joined_table.CommJTDevice;
import ru.sfedu.log4jproject.model.entity.joined_table.MediaJTDevice;
import ru.sfedu.log4jproject.model.entity.mapped_superclass.CommMSDevice;
import ru.sfedu.log4jproject.model.entity.mapped_superclass.MediaMSDevice;
import ru.sfedu.log4jproject.model.entity.single_table.CommSTDevice;
import ru.sfedu.log4jproject.model.entity.single_table.MediaSTDevice;
import ru.sfedu.log4jproject.model.entity.single_table.STDevice;
import ru.sfedu.log4jproject.model.entity.table_per_class.CommTPCDevice;
import ru.sfedu.log4jproject.model.entity.table_per_class.MediaTPCDevice;

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
            log.info("saveTestEntity test Success");
            DataProviderHibernate dataProvider = new DataProviderHibernate();
            TestEntity test = new TestEntity();
            test.setCheck(true);
            test.setDesc("Cashier");
            test.setName("Helen");
            test.setDateCreated(LocalDate.now());
            test.setHomeAddress(new TestEntity.Address("Detroit", "Lincoln Avenue"));
            test.setWorkAddress(new TestEntity.Address("Detroit", "6th Street"));
            dataProvider.saveTestEntity(test);
        } catch (Exception ex){
            log.error("saveTestEntity test Success - Failure");
            fail(ex.getMessage());
        }
    }

    @Test
    void saveTestEntityFail(){
        try{
            log.info("saveTestEntity test Fail");
            DataProviderHibernate dataProvider = new DataProviderHibernate();
            TestEntity test = new TestEntity();
            dataProvider.saveTestEntity(test);
        } catch (Exception ex){
            log.error("saveTestEntity test Fail - Failure");
            fail(ex.getMessage());
        }
    }

    @Test
    void getTestEntityByIdSuccess(){
        log.info("getTestEntityById test Success");
        try{
            DataProviderHibernate dataProvider = new DataProviderHibernate();
            dataProvider.getTestEntityById(6);
        } catch(Exception ex){
            log.error("getTestEntityById test Success - Failure");
            fail(ex.getMessage());
        }
    }

    @Test
    void getTestEntityByIdFail(){
        log.info("getTestEntityById test Fail");
        try{
            DataProviderHibernate dataProvider = new DataProviderHibernate();
            dataProvider.getTestEntityById(10);
        } catch(Exception ex){
            log.error("getTestEntityById test Fail - Failure");
            fail(ex.getMessage());
        }
    }

    @Test
    void updateTestEntitySuccess(){
        log.info("updateTestEntity test Success");
        try{
            DataProviderHibernate dataProvider = new DataProviderHibernate();
            TestEntity entity = dataProvider.getTestEntityById(1).getEntity();
            entity.setName("Vasily");
            dataProvider.updateTestEntity(entity);
        } catch(Exception ex){
            log.error("updateTestEntity test Success - Failure");
            fail(ex.getMessage());
        }
    }

    @Test
    void updateTestEntityFail(){
        log.info("updateTestEntity test Fail");
        try{
            DataProviderHibernate dataProvider = new DataProviderHibernate();
            TestEntity dummy = new TestEntity();
            dataProvider.updateTestEntity(dummy);
        } catch(Exception ex){
            log.error("updateTestEntity test Fail - Failure");
            fail(ex.getMessage());
        }
    }

    @Test
    void deleteTestEntitySuccess(){
        log.info("deleteTestEntity test Success");
        try{
            DataProviderHibernate dataProvider = new DataProviderHibernate();
            dataProvider.deleteTestEntity(dataProvider.getTestEntityById(6).getEntity());
        } catch(Exception ex){
            log.error("deleteTestEntity test Success - Failure");
            fail(ex.getMessage());
        }
    }

    @Test
    void deleteTestEntityFail(){
        log.info("deleteTestEntity test Fail");
        try{
            DataProviderHibernate dataProvider = new DataProviderHibernate();
            TestEntity dummy = new TestEntity();
            dataProvider.deleteTestEntity(dummy);
        } catch(Exception ex){
            log.error("deleteTestEntity test Fail - Failure");
            fail(ex.getMessage());
        }
    }

    @Test
    void saveSuccess(){
        try{
            log.info("save test Success");
            DataProviderHibernate dataProvider = new DataProviderHibernate();

            /* Entities initialization */
            //Mapped superclass
            MediaMSDevice mediaMS = new MediaMSDevice("Sony", 450903, true);
            CommMSDevice commMS = new CommMSDevice("LG", 251024, 450);

            //Joined table
            MediaJTDevice mediaJT = new MediaJTDevice("Samsung", 990998, false);
            CommJTDevice commJT = new CommJTDevice("Kinetic", 321775, 488);

            //Single table
            MediaSTDevice mediaST = new MediaSTDevice("Nokia", 111111, false);
            CommSTDevice commST = new CommSTDevice("Lizardman", 400302, 448);

            MediaTPCDevice mediaTPC = new MediaTPCDevice("Panasonic", 110133, true);
            CommTPCDevice commTPC = new CommTPCDevice("Tyrion", 835295,475);
            /* End of initialization */

            dataProvider.save(mediaST);
        } catch (Exception ex){
            log.error("save test Success - Failure");
            fail(ex.getMessage());
        }
    }

    @Test
    void getSuccess(){
        try{
            log.info("get test Success");
            DataProviderHibernate dataProvider = new DataProviderHibernate();
            dataProvider.get(CommSTDevice.class, 10);
        } catch (Exception ex){
            log.error("save test Success - Failure");
            fail(ex.getMessage());
        }
    }

    @Test
    void updateSuccess(){
        try{
            log.info("get test Success");
            DataProviderHibernate dataProvider = new DataProviderHibernate();
            CommJTDevice result = (CommJTDevice) dataProvider.get(CommJTDevice.class, 7).getEntity();
            result.setFrequency(486);
            dataProvider.update(result);
        } catch (Exception ex){
            log.error("save test Success - Failure");
            fail(ex.getMessage());
        }
    }

    @Test
    void deleteSuccess(){
        try{
            log.info("get test Success");
            DataProviderHibernate dataProvider = new DataProviderHibernate();
            dataProvider.delete(dataProvider.get(CommMSDevice.class, 0).getEntity());
        } catch (Exception ex){
            log.error("save test Success - Failure");
            fail(ex.getMessage());
        }
    }
}