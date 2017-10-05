package com.bogdan.citygreeter;

import java.time.LocalTime;
import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Main {
    
    private static Logger log = LogManager.getLogger("org.apache.log4j.xml");

    public static void main(String[] args) {
        
        if ((args.length < 1) || (args.length > 2)) {
            log.error("Incorrect number of entered parameters! You should enter at least 1 parameter!");
            System.out.println("Incorrect number of entered parameters! You should enter at least 1 parameter!");
            System.exit(0);
        }
        
        Message message = new Message();
        String displayedMessage;
        
        if (args.length == 2) {
            log.info("2 parameters has been entered");
            displayedMessage = message.formMessage(args[0], LocalTime.now(message.assignCityZoneId(args[1])), Locale.getDefault());
            log.info("Final message - " + displayedMessage);
            System.out.println(displayedMessage);
        } else { 
            log.info("1 parameter has been entered");
            log.info("Time zone has not been entered!");
            displayedMessage = message.formMessage(args[0], LocalTime.now(message.getDefaultCityZoneId()), Locale.getDefault());
            log.info("Final message - " + displayedMessage);
            System.out.println(displayedMessage);
        }
        
    }

}
