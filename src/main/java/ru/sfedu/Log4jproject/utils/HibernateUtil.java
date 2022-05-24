package ru.sfedu.log4jproject.utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import ru.sfedu.log4jproject.model.entity.TestEntity;
import ru.sfedu.log4jproject.model.entity.component_collection.CCBuilding;
import ru.sfedu.log4jproject.model.entity.component_collection.CMBuilding;
import ru.sfedu.log4jproject.model.entity.joined_table.CommJTDevice;
import ru.sfedu.log4jproject.model.entity.joined_table.JTDevice;
import ru.sfedu.log4jproject.model.entity.joined_table.MediaJTDevice;
import ru.sfedu.log4jproject.model.entity.list_collection.LCBuilding;
import ru.sfedu.log4jproject.model.entity.many_to_many.MTMDistributor;
import ru.sfedu.log4jproject.model.entity.many_to_many.MTMFacility;
import ru.sfedu.log4jproject.model.entity.map_collection.MCBuilding;
import ru.sfedu.log4jproject.model.entity.mapped_superclass.CommMSDevice;
import ru.sfedu.log4jproject.model.entity.mapped_superclass.MSDevice;
import ru.sfedu.log4jproject.model.entity.mapped_superclass.MediaMSDevice;
import ru.sfedu.log4jproject.model.entity.one_to_many.Client;
import ru.sfedu.log4jproject.model.entity.one_to_many.Office;
import ru.sfedu.log4jproject.model.entity.one_to_one.*;
import ru.sfedu.log4jproject.model.entity.set_collection.SCBuilding;
import ru.sfedu.log4jproject.model.entity.single_table.CommSTDevice;
import ru.sfedu.log4jproject.model.entity.single_table.MediaSTDevice;
import ru.sfedu.log4jproject.model.entity.single_table.STDevice;
import ru.sfedu.log4jproject.model.entity.table_per_class.CommTPCDevice;
import ru.sfedu.log4jproject.model.entity.table_per_class.MediaTPCDevice;
import ru.sfedu.log4jproject.model.entity.table_per_class.TPCDevice;

import java.io.File;

public class HibernateUtil {
    private static SessionFactory sessionFactory;
    private static final String DEFAULT_CONFIG_PATH = "src/main/resources/hibernate.cfg.xml";
    private static String CUSTOM_CONFIG_PATH = "";
    /**
     * Создание фабрики
     */

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            // loads configuration and mappings
            //Configuration configuration = new Configuration().configure();
            //File configFile = new File(DEFAULT_CONFIG_PATH);
            Configuration configuration = new Configuration().configure(getConfigFile());
            ServiceRegistry serviceRegistry
                    = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();


            MetadataSources metadataSources =
                    new MetadataSources(serviceRegistry);
            metadataSources.addAnnotatedClass(TestEntity.class);// Аннотированная сущность
            metadataSources.addAnnotatedClass(STDevice.class);
            metadataSources.addAnnotatedClass(CommSTDevice.class);
            metadataSources.addAnnotatedClass(MediaSTDevice.class);
            metadataSources.addAnnotatedClass(CommMSDevice.class);
            metadataSources.addAnnotatedClass(MediaMSDevice.class);
            metadataSources.addAnnotatedClass(MSDevice.class);
            metadataSources.addAnnotatedClass(CommJTDevice.class);
            metadataSources.addAnnotatedClass(JTDevice.class);
            metadataSources.addAnnotatedClass(MediaJTDevice.class);
            metadataSources.addAnnotatedClass(TPCDevice.class);
            metadataSources.addAnnotatedClass(CommTPCDevice.class);
            metadataSources.addAnnotatedClass(MediaTPCDevice.class);
            metadataSources.addAnnotatedClass(SCBuilding.class);
            metadataSources.addAnnotatedClass(LCBuilding.class);
            metadataSources.addAnnotatedClass(MCBuilding.class);
            metadataSources.addAnnotatedClass(CCBuilding.class);
            metadataSources.addAnnotatedClass(CMBuilding.class);
            metadataSources.addAnnotatedClass(Client.class);
            metadataSources.addAnnotatedClass(Office.class);
            metadataSources.addAnnotatedClass(SharedFKFacility.class);
            metadataSources.addAnnotatedClass(SharedFKManager.class);
            metadataSources.addAnnotatedClass(GeneratedFKManager.class);
            metadataSources.addAnnotatedClass(GeneratedFKFacility.class);
            metadataSources.addAnnotatedClass(FKFacility.class);
            metadataSources.addAnnotatedClass(FKManager.class);
            metadataSources.addAnnotatedClass(MTMDistributor.class);
            metadataSources.addAnnotatedClass(MTMFacility.class);

            //metadataSources.addResource("named-queries.hbm.xml");// Именованные запросы
            sessionFactory = metadataSources.buildMetadata().buildSessionFactory();
        }
        return sessionFactory;
    }

    public static void setCustomConfigPath(String path){
        CUSTOM_CONFIG_PATH = path;
    }

    private static File getConfigFile(){
        if(!CUSTOM_CONFIG_PATH.isBlank()){
            return new File(CUSTOM_CONFIG_PATH);
        }
        return new File(DEFAULT_CONFIG_PATH);
    }

}
