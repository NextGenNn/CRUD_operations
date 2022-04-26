package ru.sfedu.log4jproject.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import ru.sfedu.log4jproject.model.entity.User;
import ru.sfedu.log4jproject.model.Wrapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class XmlUtil {

    public XmlUtil(String PATH, String FILE_EXTENSION, String FILE_NAME) throws IOException {
        fileName = ConfigurationUtil.getConfigurationEntry(FILE_NAME);
        path = ConfigurationUtil.getConfigurationEntry(PATH);
        fileExtension = ConfigurationUtil.getConfigurationEntry(FILE_EXTENSION);
    }

    public static final Logger log = LogManager.getLogger(XmlUtil.class);
    private static String path;
    private static String fileExtension;
    private static String fileName;

    public Wrapper getWrapper(User userToAppend) throws Exception {
        File file = getFile();
        Wrapper document = new Wrapper();
        Serializer serializer = new Persister();
        if(file.length()!=0) {
            document = serializer.read(Wrapper.class, file);
            List<User> userList = document.getUserList();
            userList.add(userToAppend);
            document.setUserList(userList);
        } else {
            List<User> userList = new ArrayList<User>();
            userList.add(userToAppend);
            document.setUserList(userList);
        }
        return document;
    }

    public Optional<User> findUser(List<User> userList, long id){
        Stream<User> userStream = userList.stream();
        User foundUser = new User();
        userStream.forEach(user -> {
            if (user.getId() == id) {
                foundUser.setId(user.getId());
                foundUser.setName(user.getName());
            }
        });
        userStream.close();
        return Optional.of(foundUser);
    }

    public Wrapper getWrapperFromList(List<User>userList){
        Wrapper wrapper = new Wrapper();
        wrapper.setUserList(userList);
        return wrapper;
    }

    public File getFile() throws IOException {
       return new File(path.concat(fileName).concat(fileExtension));
    }

    public Wrapper deleteWrapperEntry(Wrapper toDelete, long id){
        List<User> userList = toDelete.getUserList();
        Stream<User> userStream = userList.stream();
        List<User> newUserList = new ArrayList<User>();
        userStream.forEach(user ->{
            if(user.getId() != id){
                newUserList.add(user);
            }
        });
        userStream.close();
        return getWrapperFromList(newUserList);
    }
}
