package ru.sfedu.log4jproject.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.sfedu.log4jproject.model.CodeType;
import ru.sfedu.log4jproject.model.Result;
import ru.sfedu.log4jproject.model.beans.User;
import ru.sfedu.log4jproject.utils.ConfigurationUtil;
import ru.sfedu.log4jproject.Constants;
import ru.sfedu.log4jproject.utils.IdGenerator;
import ru.sfedu.log4jproject.utils.Validator;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;

public class DataProviderH2 implements IDataProvider {
    private static final Logger log = LogManager.getLogger(DataProviderH2.class);
    private static final String CONNECTOR = "connector";
    private static final String DATABASE = "database";
    private static final String USER = "user";
    private static final String PASSWORD = "password";
    private static final String H2_DRIVER = "driver";
    private static Connection con;

    public DataProviderH2(){
        try {
            Class.forName(ConfigurationUtil.getConfigurationEntry(H2_DRIVER)).getDeclaredConstructor().newInstance();
            createTable();
        } catch (Exception ex){
            log.error("DataProviderH2 Constructor: unable to create data provider. Exception received {}", ex.getMessage());
        }
    }

    private void createTable() throws SQLException, IOException {
        Statement stm = connectDB();
        stm.executeUpdate(Constants.SQL_CREATE_TABLE);
        closeCon(stm);
    }

    private Statement connectDB () throws IOException, SQLException {
        con = DriverManager.getConnection(
                ConfigurationUtil.getConfigurationEntry(CONNECTOR).concat(
                        ConfigurationUtil.getConfigurationEntry(DATABASE)),
                ConfigurationUtil.getConfigurationEntry(USER),
                ConfigurationUtil.getConfigurationEntry(PASSWORD));
        return con.createStatement();
    }

    private void closeCon (Statement stm) throws SQLException {
        stm.close();
        con.close();
    }

    //добавить проверку на наличие записи в бд
    //обернуть все методы в utils в try catch
    @Override
    public Result<User> appendUser(User userToAppend) {
        try {
            log.info("appendUser[1]: appending user {}", userToAppend);
            Validator.validateUser(userToAppend);
            userToAppend.setId(IdGenerator.generateForH2(CONNECTOR, DATABASE, USER, PASSWORD));
            Statement stm = connectDB();
            String query = String.format(Constants.INSERT_USER, userToAppend.getId(), userToAppend.getName());
            stm.executeUpdate(query);
            closeCon(stm);
            log.info("appendUser[2]: user appended");
            return new Result<User>(null, null, CodeType.SUCCESS);
        } catch (Exception ex) {
            log.info("appendUser[]: Exception received {}", ex.getMessage());
            return new Result<User>(ex.getMessage(), null, CodeType.ERROR);
        }
    }

    @Override
    public Result<User> getUserById(long id) {
        try {
            log.info("getUserById[1]: getting user by id {}", id);
            User gotUser = new User();
            Statement stm = connectDB();
            String query = Constants.GET_USERS;
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                if (rs.getLong(1) == id) {
                    gotUser.setId(rs.getLong(1));
                    gotUser.setName(rs.getString(2));
                }
            }
            Validator.validateUser(gotUser);
            log.info("getUserById[2]: received user {}", gotUser);
            closeCon(stm);
            return new Result<User>(null, gotUser, CodeType.SUCCESS);
        } catch (Exception ex) {
            log.info("getUserById[]: exception received {}", ex.getMessage());
            return new Result<User>(ex.getMessage(), null, CodeType.ERROR);
        }
    }

    @Override
    public Result<User> deleteUserById(long id) {
        try {
            log.info("deleteUserById[1]: deleting user by id {}", id);
            Statement stm = connectDB();
            String query = String.format(Constants.DELETE_USER, id);
            stm.executeUpdate(query);
            closeCon(stm);
            log.info("deleteUserById[2]: user deleted");
            return new Result<User>(null, null, CodeType.SUCCESS);
        } catch (Exception ex) {
            log.info("deleteUserById[]: exception received {}", ex.getMessage());
            return new Result<User>(ex.getMessage(), null, CodeType.ERROR);
        }
    }

    @Override
    public Result<User> updateUser(User userToUpdate) {
        try {
            log.info("updateUser[1]: updating user {}", userToUpdate);
            Validator.validateUser(userToUpdate);
            Statement stm = connectDB();
            Result<User> result = getUserById(userToUpdate.getId());
            if(result.getCode() == CodeType.SUCCESS){
                String query = String.format(Constants.UPDATE_USER, userToUpdate.getName(), userToUpdate.getId());
                stm.executeUpdate(query);
            } else appendUser(userToUpdate);
            closeCon(stm);
            log.info("updateUser[2]: user updated");
            return new Result<User>(null, null, CodeType.SUCCESS);
        } catch (Exception ex) {
            log.info("update[]: exception received {}", ex.getMessage());
            return new Result<User>(ex.getMessage(), null, CodeType.ERROR);
        }
    }
}
