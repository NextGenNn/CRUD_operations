package ru.sfedu.log4jproject.api;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ru.sfedu.log4jproject.model.ActionType;
import ru.sfedu.log4jproject.model.RepositoryType;
import ru.sfedu.log4jproject.Constants;

import java.net.UnknownHostException;
import java.util.Date;
import java.util.Properties;

public class MongoProvider {
    private static final Logger log = LogManager.getLogger(MongoProvider.class);
    private MongoClient mongoClient;
    private DB db;
    private boolean authenticate;
    private static DBCollection table;

    public MongoProvider(Properties prop){
        try {
            log.info("workWithMongo[1]: working with {}", prop);
            mongoClient = new MongoClient(prop.getProperty(Constants.PROP_HOST), Integer.parseInt(prop.getProperty(Constants.PROP_PORT)));
            db = mongoClient.getDB(prop.getProperty(Constants.PROP_DBNAME));
            authenticate = db.authenticate(prop.getProperty(Constants.PROP_LOGIN), prop.getProperty(Constants.PROP_PASSWORD).toCharArray());
            table = db.getCollection(prop.getProperty(Constants.PROP_TABLE));
        } catch(UnknownHostException ex){
            log.info("workWithMongo[]: exception received{}", ex.getMessage());
        }
    }

    public MongoProvider(){
        try {
            Properties prop = setUpDefaultProperties();
            log.info("workWithMongo[1]: working with {}", prop);
            mongoClient = new MongoClient(prop.getProperty(Constants.PROP_HOST), Integer.parseInt(prop.getProperty(Constants.PROP_PORT)));
            db = mongoClient.getDB(prop.getProperty(Constants.PROP_DBNAME));
            authenticate = db.authenticate(prop.getProperty(Constants.PROP_LOGIN), prop.getProperty(Constants.PROP_PASSWORD).toCharArray());
            table = db.getCollection(prop.getProperty(Constants.PROP_TABLE));
        } catch(UnknownHostException ex){
            log.info("workWithMongo[]: exception received{}", ex.getMessage());
        }
    }

    private Properties setUpDefaultProperties(){
        Properties prop = new Properties();
        prop.setProperty(Constants.PROP_HOST, Constants.MONGO_HOST);
        prop.setProperty(Constants.PROP_PORT, Constants.MONGO_PORT);
        prop.setProperty(Constants.PROP_DBNAME, Constants.MONGO_DB_NAME);
        prop.setProperty(Constants.PROP_LOGIN, Constants.MONGO_LOGIN);
        prop.setProperty(Constants.PROP_PASSWORD, Constants.MONGO_PASSWORD);
        prop.setProperty(Constants.PROP_TABLE, Constants.PROP_COLLECTION);
        log.info("PROPERTIES {}", prop);
        return prop;
    }

    public <T> void save(ActionType action, RepositoryType repositoryType, T obj) {
        log.info("save[1]: command = {}, type = {}, object = {}", action, repositoryType, obj);
        try {
            BasicDBObject document = new BasicDBObject();
            document.put(Constants.MONGO_FIELD_TIME, new Date());
            document.put(Constants.MONGO_FIELD_COMMAND, action.toString());
            document.put(Constants.MONGO_FIELD_REPOSITORY, repositoryType.toString());
            document.put(Constants.MONGO_FIELD_OBJECT, obj.toString());
            table.insert(document);
        } catch (Exception e) {
            log.error("save[2]: Exception received {}", e.getMessage());
        }
    }

}
