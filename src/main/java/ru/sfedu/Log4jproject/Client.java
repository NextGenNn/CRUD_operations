package ru.sfedu.log4jproject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Client {
    private static Logger log = LogManager.getLogger(Client.class.getName());

    Client(){
        log.debug("<Your constructor name>[0]: starting application.........");
    }

    public void logBasicSystemInfo() {
        log.info("Launching the application...");
        /*log.info(
                "Operating System: " + System.getProperty("os.name") + " "
                        + System.getProperty("os.version")
        );
        log.info("JRE: " + System.getProperty("java.version"));
        log.info("Java Launched From: " + System.getProperty("java.home"));
        log.info("Class Path: " + System.getProperty("java.class.path"));
        log.info("Library Path: " + System.getProperty("java.library.path"));
        log.info("User Home Directory: " + System.getProperty("user.home"));
        log.info("User Working Directory: " + System.getProperty("user.dir"));
        log.info("Test INFO logging.");*/
        log.debug("Debugging");
        log.error("ERROR");
    }
}
