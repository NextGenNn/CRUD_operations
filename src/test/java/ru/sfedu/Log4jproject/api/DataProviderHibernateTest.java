package ru.sfedu.log4jproject.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import ru.sfedu.log4jproject.model.entity.TestEntity;
import ru.sfedu.log4jproject.model.entity.component_collection.CCBuilding;
import ru.sfedu.log4jproject.model.entity.component_collection.CMBuilding;
import ru.sfedu.log4jproject.model.entity.component_collection.Network;
import ru.sfedu.log4jproject.model.entity.joined_table.CommJTDevice;
import ru.sfedu.log4jproject.model.entity.list_collection.LCBuilding;
import ru.sfedu.log4jproject.model.entity.many_to_many.MTMDistributor;
import ru.sfedu.log4jproject.model.entity.many_to_many.MTMFacility;
import ru.sfedu.log4jproject.model.entity.map_collection.MCBuilding;
import ru.sfedu.log4jproject.model.entity.mapped_superclass.CommMSDevice;
import ru.sfedu.log4jproject.model.entity.one_to_many.Client;
import ru.sfedu.log4jproject.model.entity.one_to_many.Office;
import ru.sfedu.log4jproject.model.entity.one_to_one.*;
import ru.sfedu.log4jproject.model.entity.set_collection.SCBuilding;

import java.time.LocalDate;
import java.util.*;

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
            //sc_building
            Set<String> scNetworks = new HashSet<String>();
            scNetworks.add("Coworking network");
            scNetworks.add("Director network");
            scNetworks.add("Maintenance network");
            SCBuilding scBuilding = new SCBuilding("Office", scNetworks);
            //lc_building
            List<String> lcNetworks = new ArrayList<String>();
            lcNetworks.add("Transfer network");
            lcNetworks.add("Clerk network");
            lcNetworks.add("Security network");
            LCBuilding lcBuilding = new LCBuilding("Bank", lcNetworks);
            //mc_building
            Map<String, String> mcNetworks = new HashMap<String, String>();
            mcNetworks.put("255.255.154.96", "Client network");
            mcNetworks.put("255.255.154.92", "Distributor network");
            mcNetworks.put("255.255.155.31", "Director network");
            MCBuilding mcBuilding = new MCBuilding("Restaurant", mcNetworks);
            //cc_building
            Network neighborhoodNet = new Network("Dream House Corporation", "255.255.255.56", 1500, 488);
            CCBuilding ccBuilding = new CCBuilding();
            ccBuilding.setName("Lake-shore Estate");
            ccBuilding.getNetworks().add(neighborhoodNet);
            //cm_building
            Network workersNetwork = new Network("Vierra Softworks Group", "255.255.126.12", 175, 1024);
            CMBuilding cmBuilding = new CMBuilding();
            cmBuilding.setName("Vierra Softworks HQ");
            cmBuilding.getNetworks().put("coworking", workersNetwork);
            //one_to_many
            Office office = new Office("19th Avenue 2453");
            Client client1 = new Client(office, "Joshua Bernski", "+1(534)670-89-43");
            Client client2 = new Client(office, "Mike Sinnegale", "+1(700)112-55-56");
            office.getClients().add(client1);
            office.getClients().add(client2);
            //one_to_one shared foreign key
            SharedFKManager SFKManager = new SharedFKManager("Linus Pulowski", 3);
            SharedFKFacility SFKFacility = new SharedFKFacility(1,"Mill St. 782",SFKManager);
            //one_to_one generated foreign primary key
            GeneratedFKFacility GFKFacility = new GeneratedFKFacility("Principle St. 4120");
            GeneratedFKManager GFKManager = new GeneratedFKManager(GFKFacility, "Lewis Brown", 2);
            GFKFacility.setManager(GFKManager);
            //one_to_one foreign key
            FKManager FKmanager = new FKManager("Antonio Ferrera", 2);
            FKFacility FKfacility = new FKFacility(FKmanager, "Kalvin St. 1253");
            //many_to_many
            MTMDistributor electronics = new MTMDistributor("Electronics", "TrueWireless");
            MTMDistributor food = new MTMDistributor("Food", "Pepsi Co");
            MTMFacility sideOffice = new MTMFacility("7th St. 934");
            MTMFacility headQuarters = new MTMFacility("Ocean Ave. 1002");
            electronics.getFacilities().add(sideOffice);
            electronics.getFacilities().add(headQuarters);
            food.getFacilities().add(sideOffice);
            food.getFacilities().add(headQuarters);
            sideOffice.getDistributors().add(electronics);
            sideOffice.getDistributors().add(food);
            headQuarters.getDistributors().add(electronics);
            headQuarters.getDistributors().add(food);
            /* End of initialization */
            dataProvider.save(electronics);
            dataProvider.save(food);
            dataProvider.save(sideOffice);
            dataProvider.save(headQuarters);

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
            dataProvider.get(GeneratedFKFacility.class, 36);
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