package ru.sfedu.log4jproject.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.sfedu.log4jproject.Constants;
import ru.sfedu.log4jproject.model.CodeType;
import ru.sfedu.log4jproject.model.Result;
import ru.sfedu.log4jproject.model.entity.TestEntity;
import ru.sfedu.log4jproject.model.entity.one_to_many.Office;
import ru.sfedu.log4jproject.utils.HibernateUtil;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
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
            List list =  session.createSQLQuery(Constants.POST_COUNT_TEST).list();
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
        try{ //Как правильнее ставить try-catch?
            log.info("saveTestEntity[1]: adding entity {}", entity);
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(entity);
            session.getTransaction().commit();
            session.close();
            log.info("saveTestEntity[2]: entity is successfully saved");
            return new Result<TestEntity>("Success", null, CodeType.SUCCESS);
        } catch (Exception ex){
            log.error("saveTestEntity[3]: {}", ex.getMessage());
            return new Result<TestEntity>(ex.getMessage(), null, CodeType.ERROR);
        }
    }

    public Result<TestEntity> getTestEntityById(long id){
        log.info("getTestEntityById[1]: getting entry by id {}", id);
        Session session = sessionFactory.openSession();
            try{ //Или вот так?
                session.beginTransaction();
                TestEntity entityToReturn = session.get(TestEntity.class, id);
                session.getTransaction().commit();
                session.close();
                log.info("getTestEntity[2]: got entry {}", entityToReturn);
                return new Result<TestEntity>("Success", entityToReturn, CodeType.SUCCESS);
            } catch (Exception ex){
                session.close();
                log.error("getTestEntityById[3]: {}", ex.getMessage());
                return new Result<TestEntity>(ex.getMessage(), null, CodeType.ERROR);
            }
    }

    public Result<TestEntity> updateTestEntity(TestEntity entityToUpdate){
        log.info("updateTestEntity[1]: updating entity {}", entityToUpdate);
        Session session = sessionFactory.openSession();
        try{
            session.beginTransaction();
            session.update(entityToUpdate);
            session.getTransaction().commit();
            session.close();
            log.info("updateTestEntity[2]: entry successfully updated");
            return new Result<TestEntity>("Success", null, CodeType.SUCCESS);
        } catch(Exception ex) {
            log.error("updateTestEntity[3]: {}", ex.getMessage());
            return new Result<TestEntity>(ex.getMessage(), null, CodeType.ERROR);
        }
    }

    public Result<TestEntity> deleteTestEntity(TestEntity entityToDelete){
        log.info("deleteTestEntity[1]: deleting entry by id {}", entityToDelete.getId());
        Session session = sessionFactory.openSession();
        try{
            session.beginTransaction();
            session.delete(entityToDelete);
            session.getTransaction().commit();
            session.close();
            log.info("deleteTestEntity[2]: entry successfully deleted");
            return new Result<TestEntity>("Success", null, CodeType.SUCCESS);
        } catch(Exception ex){
            log.error("deleteTestEntity[3]: {}", ex.getMessage());
            return new Result<TestEntity>(ex.getMessage(), null, CodeType.ERROR);
        }
    }

    public Result<Object> save(Object entity){
        log.info("save[1]: saving entity {}", entity);
        Session session = sessionFactory.openSession();
        try{
            session.beginTransaction();
            session.save(entity);
            session.getTransaction().commit();
            session.close();
            log.info("saveTestEntity[2]: entity is successfully saved");
            return new Result<Object>("Success", null, CodeType.SUCCESS);
        } catch (Exception ex){
            session.getTransaction().rollback();
            session.close();
            log.error("saveTestEntity[3]: {}", ex.getMessage());
            return new Result<Object>(ex.getMessage(), null, CodeType.ERROR);
        }
    }

    public <T> Result<Object> get(Class<T> clazz, long id){
        log.info("get[1]: getting entry by id {}", id);
        Session session = sessionFactory.openSession();
        try{
            session.beginTransaction();
            Object entityToReturn = session.get(clazz, id);
            session.getTransaction().commit();
            session.close();
            log.info("get[2]: got entry {}", entityToReturn);
            return new Result<Object>("Success", entityToReturn, CodeType.SUCCESS);
        } catch (Exception ex){
            session.getTransaction().rollback();
            session.close();
            log.error("get[3]: {}", ex.getMessage());
            return new Result<Object>(ex.getMessage(), null, CodeType.ERROR);
        }
    }

    public <T>List<T> getViaCriteria(Class<T> clazz){
        log.info("getViaCriteria[1]: getting entries {}", clazz);
        Session session = sessionFactory.openSession();
        try{
            session.beginTransaction();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<T> query = criteriaBuilder.createQuery(clazz);
            query.from(clazz);
            List<T> result = session.createQuery(query).getResultList();
            session.getTransaction().commit();
            session.close();
            log.info("getViaCriteria[2]: got list {}", result);
            return result;
        } catch (Exception ex){
            session.getTransaction().rollback();
            session.close();
            log.error("getViaCriteria[3]: {}", ex.getMessage());
            return new ArrayList<>();
        }
    }

    public <T> List<T> getViaSql(Class<T> clazz){
        log.info("getViaSql[1]: getting entries {}", clazz);
        Session session = sessionFactory.openSession();
        try{
            session.beginTransaction();
            String name = session.getMetamodel().entity(clazz).getName();
            List<T> result = session.createSQLQuery(Constants.POST_GET.concat(name)).list();
            session.getTransaction().commit();
            session.close();
            log.info("getViaSql[2]: got result {}", result);
            return result;
        } catch(Exception ex){
            session.getTransaction().rollback();
            session.close();
            log.error("getViaSql[3]: {}", ex.getMessage());
            return new ArrayList<>();
        }
    }

    public List getOfficesViaHql(){
        log.info("getViaHql[1]: getting offices");
        Session session = sessionFactory.openSession();
        try{
            session.beginTransaction();
            Query query = session.createQuery("from "+ Office.class.getName());
            List result = query.getResultList();
            session.getTransaction().commit();
            session.close();
            log.info("getViaHql[2]: got result {}", result);
            return result;
        } catch(Exception ex){
            session.getTransaction().rollback();
            session.close();
            log.error("getViaHql[3]: {}", ex.getMessage());
            return new ArrayList<>();
        }
    }

    public Result<Object> update(Object entityToUpdate){
        log.info("update[1]: updating entity {}", entityToUpdate);
        Session session = sessionFactory.openSession();
        try{
            session.beginTransaction();
            session.update(entityToUpdate);
            session.getTransaction().commit();
            session.close();
            log.info("update[2]: entry successfully updated");
            return new Result<Object>("Success", null, CodeType.SUCCESS);
        } catch(Exception ex) {
            session.getTransaction().rollback();
            session.close();
            log.error("update[3]: {}", ex.getMessage());
            return new Result<Object>(ex.getMessage(), null, CodeType.ERROR);
        }
    }

    public Result<Object> delete(Object entityToDelete){
        log.info("delete[1]: deleting entry {}", entityToDelete);
        Session session = sessionFactory.openSession();
        try{
            session.beginTransaction();
            session.delete(entityToDelete);
            session.getTransaction().commit();
            session.close();
            log.info("delete[2]: entry successfully deleted");
            return new Result<Object>("Success", null, CodeType.SUCCESS);
        } catch(Exception ex){
            session.getTransaction().rollback();
            session.close();
            log.error("delete[3]: {}", ex.getMessage());
            return new Result<Object>(ex.getMessage(), null, CodeType.ERROR);
        }
    }
}
