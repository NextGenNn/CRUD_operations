package ru.sfedu.log4jproject.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.sfedu.log4jproject.model.beans.User;



public class Validator {
    public static final Logger log = LogManager.getLogger(Validator.class);

    public static boolean validateUser(User userToValidate) throws Exception {
            log.trace("validateUser[1]: Validating user {}", userToValidate);
            boolean checkContents = (userToValidate.getName() != null);
            if (!checkContents) {
                log.error("Invalid user");
                throw new Exception("Invalid user");
            }
            log.trace("validateUser[2]: User successfully validated");
            return true;
    }
}
