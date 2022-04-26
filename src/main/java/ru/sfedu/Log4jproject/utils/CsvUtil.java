package ru.sfedu.log4jproject.utils;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.sfedu.log4jproject.model.entity.User;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

public class CsvUtil {

    public CsvUtil(String PATH, String FILE_EXTENSION, String FILE_NAME) throws IOException {
        path = ConfigurationUtil.getConfigurationEntry(PATH);
        fileExtension = ConfigurationUtil.getConfigurationEntry(FILE_EXTENSION);
        fileName = ConfigurationUtil.getConfigurationEntry(FILE_NAME);
    }

    public static final Logger log = LogManager.getLogger(CsvUtil.class);
    private static String fileName;
    private static String path;
    private static String fileExtension;
    private static final String postfix = "New";

    public CSVReader getCsvReader() throws IOException {
        log.trace("getCsvReader[1]: Creating CSVReader for file {}", fileName);
        FileReader reader = new FileReader(path
                .concat(fileName)
                .concat(fileExtension));
        log.trace("getCsvReader[2]: CSVReader successfully created");
        return new CSVReader(reader);
    }

    public CSVWriter getCsvWriter(boolean append) throws IOException {
        log.trace("getCsvWriter[1]: Creating CSVWriter for file {} with append {}", fileName, append);
        FileWriter writer = new FileWriter(path
                .concat(fileName)
                .concat(fileExtension)
                ,append);
        log.trace("getCsvWriter[2]: CSVWriter successfully created");
        return new CSVWriter(writer, ',',
                CSVWriter.NO_QUOTE_CHARACTER,
                CSVWriter.NO_ESCAPE_CHARACTER,
                CSVWriter.DEFAULT_LINE_END);
    }

    public CSVWriter getCsvWriter(String fileName, boolean append) throws IOException {
        log.trace("getCsvWriter[1]: Creating CSVWriter for file {} with append {}", fileName, append);
        FileWriter writer = new FileWriter(path
                .concat(fileName)
                .concat(fileExtension)
                ,append);
        log.trace("getCsvWriter[2]: CSVWriter successfully created");
        return new CSVWriter(writer, ',',
                CSVWriter.NO_QUOTE_CHARACTER,
                CSVWriter.NO_ESCAPE_CHARACTER,
                CSVWriter.DEFAULT_LINE_END);
    }

    public File getFile() {
        log.trace("getFile[1]: Creating File for {}", fileName);
        return new File(path
                .concat(fileName)
                .concat(fileExtension));
    }

    public File getFile(String fileName) {
        log.trace("getFile[1]: Creating File for {}", fileName);
        return new File(path
                .concat(fileName)
                .concat(fileExtension));
    }

    public boolean entryInput(User userToAppend) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        log.trace("entryInput[1]: Writing user {} to file {}", userToAppend, fileName);
        CSVWriter csvWriter = getCsvWriter(true);
        StatefulBeanToCsv<User> beanToCsv = new StatefulBeanToCsvBuilder<User>(csvWriter)
                .withApplyQuotesToAll(false)
                .build();
        beanToCsv.write(userToAppend);
        csvWriter.close();
        log.trace("entryInput[2]: User successfully written");
        return true;
    }

    public List<User> listOutput() throws IOException {
        log.trace("listOutput[1]: Getting list from file {}", fileName);
        CSVReader csvReader = getCsvReader();
        CsvToBean<User> users = new CsvToBeanBuilder<User>(csvReader)
                .withType(User.class)
                .build();
        List<User> userList = users.parse();
        csvReader.close();
        log.trace("listOutput[2]: list received {}", userList);
        return userList;
    }

    public boolean findDeleteEntry(long id) {
        try {
            log.trace("findDeleteEntry[1]: Finding and deleting entry by id {} in file {}", id, fileName);
            CSVWriter writer = getCsvWriter(fileName.concat(postfix), false);
            CSVReader reader = getCsvReader();
            String[] strings;
            while ((strings = reader.readNext()) != null) {
                String[] userData = strings[0].split(String.valueOf(','));
                if (!userData[0].contains(Long.toString(id))) {
                    writer.writeNext(strings);
                }
            }
            reader.close();
            writer.close();
            log.trace("findDeleteEntry[2]: New file without selected entry has been created");
            return true;
        } catch(Exception ex){
            return false;
        }
    }

    public User findEntryFromList(List<User> users, long id){
        log.trace("findEntryFromList[1]: getting entry by id {} in list {}", id, users);
        User gotUser = new User();
        Stream<User> userStream = users.stream();
        userStream.forEach(user -> {
            if (user.getId() == id) {
                gotUser.setName(user.getName());
                gotUser.setId(user.getId());
            }
        });
        userStream.close();
        log.trace("findEntryFromList[2]: entry received {}", gotUser);
        return gotUser;
    }

    public boolean swapFiles(){
        try {
            log.info("swapFiles[1]: swapping for file {}", fileName);
            File oldFile = getFile();
            File newFile = getFile(fileName.concat(postfix));
            if(!oldFile.delete()) throw new Exception("File is not deleted");
            log.info("swapFiles[2]: {} has been deleted", oldFile);
            if(!newFile.renameTo(oldFile)) throw new Exception("File is not renamed");
            log.info("swapFiles[3]: {} has been written and renamed to {}", newFile, oldFile);
            return true;
        } catch (Exception ex){
            log.error("swapFiles: exception received {}", ex.getMessage());
            return false;
        }
    }
}
