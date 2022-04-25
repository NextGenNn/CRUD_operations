package ru.sfedu.log4jproject.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.sfedu.log4jproject.Constants;
import ru.sfedu.log4jproject.model.CodeType;
import ru.sfedu.log4jproject.model.Result;
import ru.sfedu.log4jproject.model.beans.TestEntity;
import ru.sfedu.log4jproject.utils.HibernateUtil;
import java.util.List;

public class DataProviderHibernate {
    private static final Logger log = LogManager.getLogger(DataProviderHibernate.class);
    private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public String getDatabaseSize() throws Exception {
        try {
            log.info("getDatabaseSize[1]: getting database size");
            Session session = sessionFactory.openSession();
            List list =  session.createSQLQuery(Constants.POST_BDSIZE).list();
            log.info("getDatabaseSize[2]: got result {}", list.get(0));
            session.close();
            return list.get(0).toString();
        } catch (Exception ex){
            log.error("getDataBaseSize[ERR]: {}", ex.getMessage());
            throw new Exception(ex.getMessage());
        }
    }

    public String getDatabaseVersion() throws Exception {
        try {
            log.info("getDatabaseVersion[1]: getting database version");
            Session session = sessionFactory.openSession();
            List list =  session.createSQLQuery(Constants.POST_BDVERSION).list();
            log.info("getDatabaseVersion[2]: got result {}", list.get(0));
            session.close();
            return list.get(0).toString();
        } catch (Exception ex){
            log.error("getDataBaseVersion[ERR]: {}", ex.getMessage());
            throw new Exception(ex.getMessage());
        }
    }

    public String getEntriesCount() throws Exception {
        try {
            log.info("getEntriesCount[1]: getting entries count");
            Session session = sessionFactory.openSession();
            List list =  session.createSQLQuery(Constants.POST_COUNT).list();
            log.info("getEntriesCount[2]: got result {}", list.get(0));
            session.close();
            return list.get(0).toString();
        } catch (Exception ex){
            log.error("getEntriesCount[ERR]: {}", ex.getMessage());
            throw new Exception(ex.getMessage());
        }
    }

    public String getTables() throws Exception {
        try {
            log.info("getTables[1]: getting tables");
            Session session = sessionFactory.openSession();
            List list =  session.createSQLQuery(Constants.POST_TABLES).list();
            log.info("getTables[2]: got result {}", list.get(0));
            session.close();
            return list.get(0).toString();
        } catch (Exception ex){
            log.error("getTables[ERR]: {}", ex.getMessage());
            throw new Exception(ex.getMessage());
        }
    }

    public Result<TestEntity> saveTestEntity(TestEntity entity){
        try{
            log.info("saveTestEntity[1]: adding entity {}", entity);
            Session session = sessionFactory.openSession();
            session.save(entity);
            session.close();
            log.info("saveTestEntity[2]: entity is successfully saved");
            return new Result<TestEntity>("Success", null, CodeType.SUCCESS);
        } catch (Exception ex){
            log.error("saveTestEntity: {}", ex.getMessage());
            return new Result<TestEntity>(ex.getMessage(), null, CodeType.ERROR);
        }
    }
}
