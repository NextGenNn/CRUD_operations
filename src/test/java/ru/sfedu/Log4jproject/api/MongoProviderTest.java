package ru.sfedu.log4jproject.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.sfedu.log4jproject.model.ActionType;
import ru.sfedu.log4jproject.model.CodeType;
import ru.sfedu.log4jproject.model.RepositoryType;
import ru.sfedu.log4jproject.model.Result;
import ru.sfedu.log4jproject.model.entity.User;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

class MongoProviderTest {

    private static final Logger log = LogManager.getLogger(MongoProviderTest.class);
    MongoProvider mongo;

    @BeforeEach
    void setUp(){
        mongo = new MongoProvider();
    }

    @Test
    void testSaveSuccess() {
        try {
            User user = new User(15, "TEST");
            Result<User> result = new Result<User>("Some message", user, CodeType.SUCCESS);
            mongo.save(ActionType.DELETING, RepositoryType.XML, result);
            assertTrue(true);
        } catch (Exception ex){
            log.error("save test Success - Failure");
            fail(ex.getMessage());
        }
    }

    @Test
    void testSaveFail() {
        try {
            User user = new User();
            mongo.save(ActionType.UPDATING, RepositoryType.XML, user);
            assertTrue(true);
        } catch (Exception ex){
            log.error("save test Success - Failure");
            fail(ex.getMessage());
        }
    }
}