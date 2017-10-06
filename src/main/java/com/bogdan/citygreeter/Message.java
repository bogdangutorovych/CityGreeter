package com.bogdan.citygreeter;

import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

class Message {
    
    private static Logger log = LogManager.getLogger("org.apache.log4j.xml");

    final int MORNING_BEGIN = 6;
    final int MORNING_END = 9;
    final int DAY_BEGIN = 9;
    final int DAY_END = 19;
    final int EVENING_BEGIN = 19;
    final int EVENING_END = 23;
    final int NIGHT_BEGIN = 23;
    final int NIGHT_END = 6;
    
    final String DEFAULT_TIME_ZONE = "GMT";

    public String formMessage(String cityName, LocalTime localTime, Locale locale) {
        log.info("Entered city name - " + cityName);
        return getDayPart(localTime, locale) + ", " + cityName + "!";
    }
    
    public String getDayPart(LocalTime cityLocalTime, Locale locale) {
        
        log.info("LocalTime - " + LocalTime.now());
        log.info("LocalTime in city's Time Zone - " + cityLocalTime.toString());
        ResourceBundle dayPart = ResourceBundle.getBundle("DayPart", locale);
        
        if (cityLocalTime.getHour() >= MORNING_BEGIN && cityLocalTime.getHour() < MORNING_END) {
            log.info("Current day part - MORNING");
            log.info("First part of Greeting will be - " + dayPart.getString("morning"));
            return dayPart.getString("morning");
            
        } else if (cityLocalTime.getHour() >= DAY_BEGIN && cityLocalTime.getHour() < DAY_END) {
            log.info("Current day part - DAY");
            log.info("First part of Greeting will be - " + dayPart.getString("day"));
            return dayPart.getString("day");
            
        } else if (cityLocalTime.getHour() >= EVENING_BEGIN && cityLocalTime.getHour() < EVENING_END) {
            log.info("Current day part - EVENING");
            log.info("First part of Greeting will be - " + dayPart.getString("evening"));
            return dayPart.getString("evening");
            
        } else if (cityLocalTime.getHour() >= NIGHT_BEGIN || cityLocalTime.getHour() < NIGHT_END) {
            log.info("Current day part - NIGHT");
            log.info("First part of Greeting will be - " + dayPart.getString("night"));
            return dayPart.getString("night");
        }
        return "Hello";
    }

    public ZoneId assignCityZoneId(String enteredTimeZone) {
        log.info("Entered argument for Time Zone - " + enteredTimeZone);
        Set<String> ZoneIds = ZoneId.getAvailableZoneIds();
        for (String zone : ZoneIds) {
            if (zone.equals(enteredTimeZone) || zone.indexOf(enteredTimeZone) != -1) {
                log.info("ZoneId is set to - " + ZoneId.of(zone));
                return ZoneId.of(zone);
            }
        }
        return getDefaultCityZoneId();
    }
    
    public ZoneId getDefaultCityZoneId() {
        log.info("ZoneId is set to default - " + ZoneId.of(DEFAULT_TIME_ZONE));
        return ZoneId.of(DEFAULT_TIME_ZONE);
    }
    
}
