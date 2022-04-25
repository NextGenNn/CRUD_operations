package ru.sfedu.log4jproject;

import org.apache.commons.cli.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import ru.sfedu.log4jproject.api.DataProviderCsv;
import ru.sfedu.log4jproject.api.DataProviderH2;
import ru.sfedu.log4jproject.api.DataProviderXml;
import ru.sfedu.log4jproject.api.IDataProvider;
import ru.sfedu.log4jproject.model.Result;
import ru.sfedu.log4jproject.model.beans.User;
import ru.sfedu.log4jproject.utils.ConfigurationUtil;
import ru.sfedu.log4jproject.utils.HibernateUtil;

import java.io.File;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;

public class Main {
    private static final Logger log = LogManager.getLogger(Main.class);

    public static void main(String[] args){
        CommandLineParser parser = new DefaultParser();
        try{
            CommandLine cmd = parser.parse(getOptions(), args);
            if(cmd.hasOption(Constants.CLI_HELP)){
                printHelp(
                        getOptions(), // опции по которым составляем help
                        120, // ширина строки вывода
                        "Options", // строка предшествующая выводу
                        "-- HELP --", // строка следующая за выводом
                        3, // число пробелов перед выводом опции
                        5, // число пробелов перед выводом опцисания опции
                        true, // выводить ли в строке usage список команд
                        System.out // куда производить вывод
                );
            }
            if(cmd.hasOption(Constants.CLI_ENVIRONMENT)){
                String argument = cmd.getOptionValue(Constants.CLI_ENVIRONMENT);
                ConfigurationUtil.setCustomConfigPath(argument);
            }
            if(cmd.hasOption(Constants.CLI_HIBERNATE)){
                String argument = cmd.getOptionValue(Constants.CLI_HIBERNATE);
                HibernateUtil.setCustomConfigPath(argument);
            }
            if (cmd.hasOption(Constants.CLI_LOG)) {
                String argument = cmd.getOptionValue(Constants.CLI_LOG);
                File file = new File(argument);
                LoggerContext context = (LoggerContext) LogManager.getContext(false);
                context.setConfigLocation(file.toURI());
            }
            IDataProvider dataProvider = new DataProviderH2();
            if (cmd.hasOption(Constants.CLI_DATA_TYPE)) {
                String argument = cmd.getOptionValue(Constants.CLI_DATA_TYPE);
                switch (argument) {
                    case "XML" -> dataProvider = new DataProviderXml();
                    case "CSV" -> dataProvider = new DataProviderCsv();
                    case "H2" -> dataProvider = new DataProviderH2();
                    default -> log.info("Invalid data type");
                }
                log.info("Data provider {} is installed", dataProvider.getClass().getName());
            }
            if(cmd.hasOption(Constants.CLI_ADD_USER)){
                String[] arguments = cmd.getOptionValues(Constants.CLI_ADD_USER);
                User user = new User(Long.parseLong(arguments[0]), arguments[1]);
                log.info("Appending user {}", user);
                Result<User> result = dataProvider.appendUser(user);
                log.info("Appending finished with result {}:{}", result.getCode(), result.getMessage());
            }
            if(cmd.hasOption(Constants.CLI_DELETE_USER)){
                String argument = cmd.getOptionValue(Constants.CLI_DELETE_USER);
                log.info("Deleting user by id {}", argument);
                Result<User> result = dataProvider.deleteUserById(Long.parseLong(argument));
                log.info("Deleting finished with result {}:{}", result.getCode(), result.getMessage());
            }
            if(cmd.hasOption(Constants.CLI_UPDATE_USER)){
                String[] arguments = cmd.getOptionValues(Constants.CLI_UPDATE_USER);
                log.info(Arrays.toString(arguments));
                User user = new User(Long.parseLong(arguments[0]), arguments[1]);
                log.info("Updating user {}", user);
                Result<User> result = dataProvider.updateUser(user);
                log.info("Updating finished with result {}:{}", result.getCode(), result.getMessage());
            }
            if(cmd.hasOption(Constants.CLI_GET_USER)){
                String argument = cmd.getOptionValue(Constants.CLI_GET_USER);
                log.info("Getting user by id {}", argument);
                Result<User> result = dataProvider.getUserById(Long.parseLong(argument));
                log.info("Getting finished with result {}", result);
            }
        } catch(ParseException ex) {
            log.error("Exception received {}", ex.getMessage());
        }
    }

    private static Options getOptions(){
        Options options = new Options();

        Option help = new Option(Constants.CLI_HELP, false, Constants.CLI_DESC_HELP);
        help.setArgName(Constants.CLI_NAME_HELP);
        help.setOptionalArg(true);

        Option envProperties = new Option(Constants.CLI_ENVIRONMENT, true, Constants.CLI_DESC_ENVIRONMENT);
        envProperties.setArgName(Constants.CLI_NAME_ENVIRONMENT);
        envProperties.setArgs(1);
        envProperties.setOptionalArg(true);

        Option hibernateConfig = new Option(Constants.CLI_HIBERNATE, true, Constants.CLI_DESC_HIBERNATE);
        hibernateConfig.setArgName(Constants.CLI_NAME_HIBERNATE);
        hibernateConfig.setArgs(1);
        hibernateConfig.setOptionalArg(true);

        Option logConfig = new Option(Constants.CLI_LOG, true, Constants.CLI_DESC_LOG);
        logConfig.setArgName(Constants.CLI_NAME_LOG);
        logConfig.setArgs(1);
        logConfig.setOptionalArg(true);

        Option dataProvider = new Option(Constants.CLI_DATA_TYPE, true, Constants.CLI_DESC_DATA_TYPE);
        dataProvider.setArgName(Constants.CLI_NAME_DATA_TYPE);
        dataProvider.setArgs(1);
        dataProvider.setOptionalArg(true);

        Option addUser = new Option(Constants.CLI_ADD_USER, true, Constants.CLI_DESC_ADD_USER);
        addUser.setArgName(Constants.CLI_NAME_ADD_USER);
        addUser.setArgs(2);
        addUser.setOptionalArg(true);

        Option deleteUser = new Option(Constants.CLI_DELETE_USER, true, Constants.CLI_DESC_DELETE_USER);
        deleteUser.setArgName(Constants.CLI_NAME_DELETE_USER);
        deleteUser.setArgs(1);
        deleteUser.setOptionalArg(true);

        Option updateUser = new Option(Constants.CLI_UPDATE_USER, true, Constants.CLI_DESC_UPDATE_USER);
        updateUser.setArgName(Constants.CLI_NAME_UPDATE_USER);
        updateUser.setArgs(2);
        updateUser.setOptionalArg(true);

        Option getUser = new Option(Constants.CLI_GET_USER, true, Constants.CLI_DESC_GET_USER);
        getUser.setArgName(Constants.CLI_NAME_GET_USER);
        getUser.setArgs(1);
        getUser.setOptionalArg(true);

        options.addOption(help);
        options.addOption(envProperties);
        options.addOption(hibernateConfig);
        options.addOption(logConfig);
        options.addOption(dataProvider);
        options.addOption(addUser);
        options.addOption(deleteUser);
        options.addOption(updateUser);
        options.addOption(getUser);
        return options;
    }

    public static void printHelp(
            final Options options,
            final int printedRowWidth,
            final String header,
            final String footer,
            final int spacesBeforeOption,
            final int spacesBeforeOptionDescription,
            final boolean displayUsage,
            final OutputStream out) {
        final String commandLineSyntax = "java -jar Log4jproject.jar";
        final PrintWriter writer = new PrintWriter(out);
        final HelpFormatter helpFormatter = new HelpFormatter();
        helpFormatter.printHelp(
                writer,
                printedRowWidth,
                commandLineSyntax,
                header,
                options,
                spacesBeforeOption,
                spacesBeforeOptionDescription,
                footer,
                displayUsage);
        writer.flush();
    }
}
