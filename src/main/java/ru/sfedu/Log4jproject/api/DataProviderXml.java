package ru.sfedu.log4jproject.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import ru.sfedu.log4jproject.model.CodeType;
import ru.sfedu.log4jproject.model.Result;
import ru.sfedu.log4jproject.model.beans.User;
import ru.sfedu.log4jproject.model.Wrapper;
import ru.sfedu.log4jproject.utils.IdGenerator;
import ru.sfedu.log4jproject.utils.Validator;
import ru.sfedu.log4jproject.utils.XmlUtil;

import java.io.File;
import java.util.List;
import java.util.Optional;

public class DataProviderXml implements IDataProvider{

    private final String FILE_NAME = "xml_name";
    private final String PATH = "xml_path";
    private final String FILE_EXTENSION = "xml";
    private static final Logger log = LogManager.getLogger(DataProviderXml.class);

    @Override
    public Result<User> appendUser(User userToAppend) {
        try {
            log.info("appendUser[1]: writing user {}", userToAppend);
            XmlUtil xmlUtil = new XmlUtil(PATH, FILE_EXTENSION, FILE_NAME);
            Validator.validateUser(userToAppend);
            userToAppend.setId(IdGenerator.generateUserXmlId());
            File file = xmlUtil.getFile();
            Serializer serializer = new Persister();
            Wrapper document = xmlUtil.getWrapper(userToAppend);
            serializer.write(document, file);
            log.info("appendUser[2]: user successfully written");
            return new Result<User>("", null, CodeType.SUCCESS);
        } catch (Exception ex) {
            log.error("appendUser[2]: exception received {}", ex.getMessage());
            ex.printStackTrace();
            return new Result<User>(ex.getMessage(), null, CodeType.ERROR);
        }
    }

    @Override
    public Result<User> getUserById(long id){
        try {
            log.info("getUserById[1]: searching for id={}", id);
            XmlUtil xmlUtil = new XmlUtil(PATH, FILE_EXTENSION, FILE_NAME);
            File file = xmlUtil.getFile();
            Serializer serializer = new Persister();
            Wrapper wrapper = serializer.read(Wrapper.class, file);
            List<User> userList = wrapper.getUserList();
            Optional<User> result = xmlUtil.findUser(userList, id);
            log.info("getUserById[2]: searching result {}", result);
            return new Result<User>("", result.get(), CodeType.SUCCESS);
        } catch (Exception ex){
            log.error("getUserById[2]: Exception received {}", ex.getMessage());
            return new Result<User>(ex.getMessage(), null, CodeType.ERROR);
        }
    }

    @Override
    public Result<User> deleteUserById(long id) {
        try{
            log.info("deleteUserById[1]: deleting user by id={}", id);
            XmlUtil xmlUtil = new XmlUtil(PATH, FILE_EXTENSION, FILE_NAME);
            File file = xmlUtil.getFile();
            Serializer serializer = new Persister();
            Wrapper wrapper = serializer.read(Wrapper.class, file);
            wrapper = xmlUtil.deleteWrapperEntry(wrapper, id);
            serializer.write(wrapper, file);
            log.info("deleteUserById[2]: entry deleted");
            return new Result<User>(null, null, CodeType.SUCCESS);
        } catch(Exception ex){
            log.error("deleteUserById[2]: exception received {}", ex.getMessage());
            return new Result<User>(ex.getMessage(), null, CodeType.ERROR);
        }
    }

    @Override
    public Result<User> updateUser(User userToUpdate){
        try{
            log.info("updateUser[1]: updating user{}", userToUpdate);
            Validator.validateUser(userToUpdate);
            deleteUserById(userToUpdate.getId());
            appendUser(userToUpdate);
            log.info("updateUser[2]: user updated");
            return new Result<User>(null, null, CodeType.SUCCESS);
        } catch(Exception ex){
            log.error("updateUser[2]: exception received {}", ex.getMessage());
            return new Result<User>(ex.getMessage(), null, CodeType.ERROR);
        }
    }
}
