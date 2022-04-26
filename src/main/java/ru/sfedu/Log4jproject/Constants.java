package ru.sfedu.log4jproject;

public class Constants {
    public final static String KEY_ID = "KEY";
    public final static String KEY_USER_ID = "ID";
    public final static String KEY_NAME = "Name";

    public final static String INSERT_USER = "INSERT INTO USERS VALUES (%d, '%s');";
    public final static String GET_USERS = "SELECT * FROM USERS";
    public final static String DELETE_USER = "DELETE FROM USERS WHERE ID = %d";
    public final static String UPDATE_USER = "UPDATE USERS SET NAME = '%s' WHERE ID = %d";
    public final static String GET_ID = "SELECT ID FROM USERS";
    public final static String TABLE_NAME = "users";
    public final static String SQL_CREATE_TABLE = "CREATE TABLE IF NOT EXISTS ".concat(TABLE_NAME).concat("(").concat(KEY_ID).concat(" INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,").concat(KEY_USER_ID).concat(" BIGINT NOT NULL,").concat(KEY_NAME).concat(" CHAR NOT NULL)");

    public final static String POST_BDSIZE = "SELECT pg_size_pretty(pg_database_size('Users'))";
    public final static String POST_BDVERSION = "SELECT VERSION()";
    public final static String POST_COUNT_TEST = "SELECT count(*) FROM test";
    public final static String POST_TABLES = """
            SELECT table_name FROM information_schema.tables
            WHERE table_schema NOT IN ('information_schema', 'pg_catalog')
            AND table_schema IN('public', 'Tables');""";

    public final static String MONGO_FIELD_TIME = "time";
    public static final String MONGO_FIELD_COMMAND = "command";
    public static final String MONGO_FIELD_REPOSITORY = "repository";
    public static final String MONGO_FIELD_OBJECT = "item";
    public static final String MONGO_HOST = "localhost";
    public static final String MONGO_PORT = "27017";
    public static final String MONGO_LOGIN = "root";
    public static final String MONGO_PASSWORD = "root";
    public static final String MONGO_DB_NAME = "admin";

    public static final String PROP_HOST = "host";
    public static final String PROP_PORT = "port";
    public static final String PROP_DBNAME = "dbname";
    public static final String PROP_LOGIN = "login";
    public static final String PROP_PASSWORD = "password";
    public static final String PROP_TABLE = "table";
    public static final String PROP_COLLECTION = "Users";

    public static final String CLI_HELP = "h";
    public static final String CLI_ENVIRONMENT = "env";
    public static final String CLI_HIBERNATE = "hib";
    public static final String CLI_LOG = "log";
    public static final String CLI_DATA_TYPE = "dt";
    public static final String CLI_ADD_USER = "add";
    public static final String CLI_DELETE_USER = "dlt";
    public static final String CLI_UPDATE_USER = "upd";
    public static final String CLI_GET_USER = "get";
    public static final String CLI_GET_ALL_USERS = "gta";

    public static final String CLI_NAME_HELP = "help";
    public static final String CLI_NAME_ENVIRONMENT = "filePath";
    public static final String CLI_NAME_HIBERNATE = "filePath";
    public static final String CLI_NAME_LOG = "filePath";
    public static final String CLI_NAME_DATA_TYPE = "data_type";
    public static final String CLI_NAME_ADD_USER = "appendUser";
    public static final String CLI_NAME_DELETE_USER = "deleteUserById";
    public static final String CLI_NAME_UPDATE_USER = "updateUser";
    public static final String CLI_NAME_GET_USER = "getUserById";
    public static final String CLI_NAME_GET_ALL_USERS = "getAllUsers";

    public static final String CLI_DESC_HELP = "Show information about console commands";
    public static final String CLI_DESC_ENVIRONMENT = "Path to the environment.properties file";
    public static final String CLI_DESC_HIBERNATE = "Path to the hibernate.cfg.xml file";
    public static final String CLI_DESC_LOG = "Path to the log4j2 configuration file";
    public static final String CLI_DESC_DATA_TYPE = "Select type of data - CSV, XML, H2 (default: h2)";
    public static final String CLI_DESC_ADD_USER = "Creating new user";
    public static final String CLI_DESC_DELETE_USER = "Deleting user by selected id";
    public static final String CLI_DESC_UPDATE_USER = "Updating selected user or creating new";
    public static final String CLI_DESC_GET_USER = "Get user by selected id";
    public static final String CLI_DESC_GET_ALL_USERS = "Get all users in current data source";
}
