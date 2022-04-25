package ru.sfedu.log4jproject.utils;

import com.opencsv.bean.CsvToBeanBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import ru.sfedu.log4jproject.Constants;
import ru.sfedu.log4jproject.model.beans.User;
import ru.sfedu.log4jproject.model.Wrapper;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.util.List;

public class IdGenerator {
    private static final Logger log = LogManager.getLogger(IdGenerator.class);
    private final static String CSV_PATH = "csv_path";
    private final static String CSV_FILE_EXT = "csv";
    private final static String CSV_FILE_NAME = "csv_name";
    private final static String XML_FILE_NAME = "xml_name";
    private final static String XML_PATH = "xml_path";
    private final static String XML_FILE_EXT = "xml";

    public static long generateForH2(String connector, String database, String user, String password) throws SQLException, IOException {
        log.info("generateForH2[1]: generating new id on {}", database);
        Connection con = DriverManager.getConnection(
                ConfigurationUtil.getConfigurationEntry(connector).concat(
                        ConfigurationUtil.getConfigurationEntry(database)),
                ConfigurationUtil.getConfigurationEntry(user),
                ConfigurationUtil.getConfigurationEntry(password));
        Statement stm = con.createStatement();
        ResultSet st = stm.executeQuery(Constants.GET_ID);
        long resId = 0;
        while (st.next()) {
            resId = st.getLong(1);
        }
        con.close();
        stm.close();
        log.info("generateForH2[2]: generated id {}", resId+1);
        return ++resId;
    }

    public static long generateUserCsvId() throws IOException {
        log.info("generateUserCsvId[1]: Generating id for file {}", ConfigurationUtil.getConfigurationEntry(CSV_FILE_NAME));
        CsvUtil readWriter = new CsvUtil(CSV_PATH, CSV_FILE_EXT, CSV_FILE_NAME);
        List<User> userList = new CsvToBeanBuilder<User>(readWriter.getCsvReader())
                .withType(User.class)
                .build()
                .parse();
        if(!userList.isEmpty()) {
            log.info("generateUserCsvId[2]: Generated id {}", userList.get(userList.size() - 1).getId() + 1);
            return userList.get(userList.size() - 1).getId() + 1;
        } else {
            log.info("generateUserCsvId[2]: Data source is empty, given id - 1");
            return 1;
        }
    }

    public static long generateUserXmlId(){
        try{
        log.info("generateUserXmlId[1]: Generating id for file {}", ConfigurationUtil.getConfigurationEntry(XML_FILE_NAME));
        XmlUtil xmlUtil = new XmlUtil(XML_PATH, XML_FILE_EXT, XML_FILE_NAME);
            File file = xmlUtil.getFile();
            Serializer serializer = new Persister();
            Wrapper wrapper = serializer.read(Wrapper.class, file);
            List<User> userList = wrapper.getUserList();
            if (!userList.isEmpty()) {
                log.info("generateUserXmlId[2]: Generated id {}", userList.get(userList.size() - 1).getId() + 1);
                return userList.get(userList.size() - 1).getId() + 1;
            } else {
                log.info("generateUserXmlId[2]: Data source is empty, given id - 1");
                return 1;
            }
        } catch (Exception ex){
            log.error("generateUserXmlId[2]: error appeared, file is considered to be empty, given id - 1");
            return 1;
        }
    }
}
