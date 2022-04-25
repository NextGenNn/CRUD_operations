package ru.sfedu.log4jproject.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.sfedu.log4jproject.Constants;
import ru.sfedu.log4jproject.model.ActionType;
import ru.sfedu.log4jproject.model.CodeType;
import ru.sfedu.log4jproject.model.RepositoryType;
import ru.sfedu.log4jproject.model.Result;
import ru.sfedu.log4jproject.model.beans.User;
import ru.sfedu.log4jproject.utils.CsvUtil;
import ru.sfedu.log4jproject.utils.IdGenerator;
import ru.sfedu.log4jproject.utils.StringUtil;
import ru.sfedu.log4jproject.utils.Validator;

import java.util.List;

public class DataProviderCsv implements IDataProvider{

    private final String FILE_NAME = "csv_name";
    private final String PATH = "csv_path";
    private final String FILE_EXTENSION = "csv";
    private final MongoProvider mongoProvider = new MongoProvider();
    private static final Logger log = LogManager.getLogger(DataProviderCsv.class);

    @Override
    public Result<User> appendUser(User userToAppend){
        try{
            log.info("appendUser[1]: appending user {}", userToAppend);
            Validator.validateUser(userToAppend);
            Result<User> currentUser = getUserById(userToAppend.getId());
            if(currentUser.getEntity() == null) {
                CsvUtil readWriter = new CsvUtil(PATH, FILE_EXTENSION, FILE_NAME);
                userToAppend.setId(IdGenerator.generateUserCsvId());
                readWriter.entryInput(userToAppend);
                log.info("appendUser[2]: appended {}", userToAppend);
            } else log.error("appendUser[2]: This user with this id already exists {}", currentUser);
            Result<User> result = new Result<User>(null, null, CodeType.SUCCESS);
            mongoProvider.save(ActionType.APPENDING, RepositoryType.CSV, result);
            return result;
        } catch (Exception ex){
            log.error("appendUser: {}", ex.getMessage());
            Result<User>result = new Result<User>(ex.getMessage(), null, CodeType.ERROR);
            mongoProvider.save(ActionType.APPENDING, RepositoryType.CSV, result);
            return result;
        }
    }

    @Override
    public Result<User> getUserById(long id) {
        try {
            log.info("getUserById[1]: id:{}", id);
            CsvUtil readWriter = new CsvUtil(PATH, FILE_EXTENSION, FILE_NAME);
            List<User> userList = readWriter.listOutput();
            User gotUser = readWriter.findEntryFromList(userList, id);
            log.info("getUserById[2]: Got user {}", gotUser);
            Result<User> result;
            if(!StringUtil.checkStringEmpty(gotUser.getName())){
                result = new Result<User>(null, gotUser, CodeType.SUCCESS);
            }
            else {
                log.info("getUserById[3]: No entry found");
                result = new Result<User>("No entry found", null, CodeType.ENTRY_NOT_FOUND);
            }
            mongoProvider.save(ActionType.GETTING, RepositoryType.CSV, result);
            return result;
        } catch (Exception ex){
            log.error("getUserById[2]: exception received {}", ex.getMessage());
            Result<User>result = new Result<User>(ex.getMessage(), null, CodeType.ERROR);
            mongoProvider.save(ActionType.GETTING, RepositoryType.CSV, result);
            return result;
        }
    }

    @Override
    public Result<User> deleteUserById(long id){
        try {
            log.info("deleteUserById[1]: id:{}", id);
            Result<User> result;
            CsvUtil readWriter = new CsvUtil(PATH, FILE_EXTENSION, FILE_NAME);
            readWriter.findDeleteEntry(id);
            readWriter.swapFiles();
            result = new Result<User>("Deleted entry by id ".concat(Long.valueOf(id).toString()), null, CodeType.SUCCESS);
            mongoProvider.save(ActionType.DELETING, RepositoryType.CSV, result);
            return result;
        } catch(Exception ex){
            log.error("deleteUserById {}", ex.getMessage());
            Result<User>result = new Result<User>(ex.getMessage(), null, CodeType.ERROR);
            mongoProvider.save(ActionType.DELETING, RepositoryType.CSV, result);
            return result;
        }
    }

    @Override
    public Result<User> updateUser(User userToUpdate) {
        try {
            log.info("updateUser[1]: user to update {}", userToUpdate);
            Result<User> result;
            Validator.validateUser(userToUpdate);
            deleteUserById(userToUpdate.getId());
            appendUser(userToUpdate);
            log.info("updateUser[2]: Entry successfully updated");
            result = new Result<User>(null, userToUpdate, CodeType.SUCCESS);
            mongoProvider.save(ActionType.UPDATING, RepositoryType.CSV, result);
            return result;
        } catch (Exception ex){
            log.error("updateUser: Exception received {}", ex.getMessage());
            Result<User>result = new Result<User>(ex.getMessage(), null, CodeType.ERROR);
            mongoProvider.save(ActionType.UPDATING, RepositoryType.CSV, result);
            return result;
        }
    }

}
