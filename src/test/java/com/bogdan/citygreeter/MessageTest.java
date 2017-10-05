package com.bogdan.citygreeter;

import static org.junit.Assert.assertEquals;

import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;

import org.junit.Test;

public class MessageTest {
    
    final int MORNING_BEGIN = 6;
    final int MORNING_END = 9;
    final int DAY_BEGIN = 9;
    final int DAY_END = 19;
    final int EVENING_BEGIN = 19;
    final int EVENING_END = 23;
    final int NIGHT_BEGIN = 23;
    final int NIGHT_END = 6;
    
    final String DEFAULT_TIME_ZONE = "GMT";

    Message message = new Message();
    
    @Test
    public void shouldReturnCorrectCityZoneId() {
        assertEquals(ZoneId.of("America/New_York"), message.assignCityZoneId("America/New_York"));
        assertEquals(ZoneId.of("Africa/Addis_Ababa"), message.assignCityZoneId("Africa/Addis_Ababa"));
        assertEquals(ZoneId.of("Pacific/Apia"), message.assignCityZoneId("Pacific/Apia"));
        assertEquals(ZoneId.of("America/Puerto_Rico"), message.assignCityZoneId("America/Puerto_Rico"));
        assertEquals(ZoneId.of("Etc/GMT-11"), message.assignCityZoneId("Etc/GMT-11"));
        assertEquals(ZoneId.of("Etc/GMT-12"), message.assignCityZoneId("Etc/GMT-12"));
        assertEquals(ZoneId.of(DEFAULT_TIME_ZONE), message.assignCityZoneId("New_York"));
        assertEquals(ZoneId.of(DEFAULT_TIME_ZONE), message.assignCityZoneId("New_Yor"));
        assertEquals(ZoneId.of(DEFAULT_TIME_ZONE), message.assignCityZoneId("NewYork"));
        assertEquals(ZoneId.of(DEFAULT_TIME_ZONE), message.assignCityZoneId(" "));
        assertEquals(ZoneId.of(DEFAULT_TIME_ZONE), message.assignCityZoneId("/////"));
        assertEquals(ZoneId.of(DEFAULT_TIME_ZONE), message.assignCityZoneId("!@#$%^&*()"));
        assertEquals(ZoneId.of(DEFAULT_TIME_ZONE), message.assignCityZoneId("1234657890"));
        assertEquals(ZoneId.of(DEFAULT_TIME_ZONE), message.assignCityZoneId("1"));
        assertEquals(ZoneId.of("Asia/Shanghai"), message.assignCityZoneId("Asia/Shanghai"));
        assertEquals(ZoneId.of(DEFAULT_TIME_ZONE), message.assignCityZoneId("Asia_Shanghai"));
        assertEquals(ZoneId.of(DEFAULT_TIME_ZONE), message.assignCityZoneId("Asia"));
        
        Set<String> ZoneIds = ZoneId.getAvailableZoneIds();
        for (String zone : ZoneIds) {
            assertEquals(ZoneId.of(zone), message.assignCityZoneId(zone));
        }
        
    }
    
    @Test (expected = java.time.zone.ZoneRulesException.class) 
    public void shouldReturnZoneRulesException() {
        Set<String> ZoneIds = ZoneId.getAvailableZoneIds();
        for (String zone : ZoneIds) {
            assertEquals(ZoneId.of(DEFAULT_TIME_ZONE), ZoneId.of((message.assignCityZoneId(zone).toString() + "1")));
            assertEquals(ZoneId.of(DEFAULT_TIME_ZONE), ZoneId.of((message.assignCityZoneId(zone).toString() + ".")));
        }
        
    }
    
    @Test
    public void shouldReturnDayPartForLocaleRu() {
        Locale locale = new Locale("ru_RU");
        ResourceBundle dayPart = ResourceBundle.getBundle("DayPart", locale);
        assertEquals(dayPart.getString("night"), message.getDayPart(LocalTime.of(0, 00, 00), locale));
        assertEquals(dayPart.getString("night"), message.getDayPart(LocalTime.of(1, 00, 00), locale));
        assertEquals(dayPart.getString("night"), message.getDayPart(LocalTime.of(2, 00, 00), locale));
        assertEquals(dayPart.getString("night"), message.getDayPart(LocalTime.of(3, 00, 00), locale));
        assertEquals(dayPart.getString("night"), message.getDayPart(LocalTime.of(4, 00, 00), locale));
        assertEquals(dayPart.getString("night"), message.getDayPart(LocalTime.of(5, 00, 00), locale));
        assertEquals(dayPart.getString("morning"), message.getDayPart(LocalTime.of(6, 00, 00), locale));
        assertEquals(dayPart.getString("morning"), message.getDayPart(LocalTime.of(7, 00, 00), locale));
        assertEquals(dayPart.getString("morning"), message.getDayPart(LocalTime.of(8, 00, 00), locale));
        assertEquals(dayPart.getString("day"), message.getDayPart(LocalTime.of(9, 20, 00), locale));
        assertEquals(dayPart.getString("day"), message.getDayPart(LocalTime.of(10, 20, 00), locale));
        assertEquals(dayPart.getString("day"), message.getDayPart(LocalTime.of(11, 20, 00), locale));
        assertEquals(dayPart.getString("day"), message.getDayPart(LocalTime.of(12, 20, 00), locale));
        assertEquals(dayPart.getString("day"), message.getDayPart(LocalTime.of(13, 20, 00), locale));
        assertEquals(dayPart.getString("day"), message.getDayPart(LocalTime.of(14, 20, 00), locale));
        assertEquals(dayPart.getString("day"), message.getDayPart(LocalTime.of(15, 20, 00), locale));
        assertEquals(dayPart.getString("day"), message.getDayPart(LocalTime.of(16, 20, 00), locale));
        assertEquals(dayPart.getString("day"), message.getDayPart(LocalTime.of(16, 20, 00), locale));
        assertEquals(dayPart.getString("day"), message.getDayPart(LocalTime.of(17, 20, 00), locale));
        assertEquals(dayPart.getString("day"), message.getDayPart(LocalTime.of(18, 20, 00), locale));
        assertEquals(dayPart.getString("evening"), message.getDayPart(LocalTime.of(19, 00, 00), locale));
        assertEquals(dayPart.getString("evening"), message.getDayPart(LocalTime.of(20, 00, 00), locale));
        assertEquals(dayPart.getString("evening"), message.getDayPart(LocalTime.of(21, 00, 00), locale));
        assertEquals(dayPart.getString("evening"), message.getDayPart(LocalTime.of(22, 00, 00), locale));
        assertEquals(dayPart.getString("night"), message.getDayPart(LocalTime.of(23, 00, 00), locale));
        
        
    }
    
    @Test
    public void shouldReturnDayPartForLocaleUS() {
        Locale locale = new Locale("en_US");
        ResourceBundle dayPart = ResourceBundle.getBundle("DayPart", locale);
        assertEquals(dayPart.getString("night"), message.getDayPart(LocalTime.of(0, 00, 00), locale));
        assertEquals(dayPart.getString("night"), message.getDayPart(LocalTime.of(1, 00, 00), locale));
        assertEquals(dayPart.getString("night"), message.getDayPart(LocalTime.of(2, 00, 00), locale));
        assertEquals(dayPart.getString("night"), message.getDayPart(LocalTime.of(3, 00, 00), locale));
        assertEquals(dayPart.getString("night"), message.getDayPart(LocalTime.of(4, 00, 00), locale));
        assertEquals(dayPart.getString("night"), message.getDayPart(LocalTime.of(5, 00, 00), locale));
        assertEquals(dayPart.getString("morning"), message.getDayPart(LocalTime.of(6, 00, 00), locale));
        assertEquals(dayPart.getString("morning"), message.getDayPart(LocalTime.of(7, 00, 00), locale));
        assertEquals(dayPart.getString("morning"), message.getDayPart(LocalTime.of(8, 00, 00), locale));
        assertEquals(dayPart.getString("day"), message.getDayPart(LocalTime.of(9, 20, 00), locale));
        assertEquals(dayPart.getString("day"), message.getDayPart(LocalTime.of(10, 20, 00), locale));
        assertEquals(dayPart.getString("day"), message.getDayPart(LocalTime.of(11, 20, 00), locale));
        assertEquals(dayPart.getString("day"), message.getDayPart(LocalTime.of(12, 20, 00), locale));
        assertEquals(dayPart.getString("day"), message.getDayPart(LocalTime.of(13, 20, 00), locale));
        assertEquals(dayPart.getString("day"), message.getDayPart(LocalTime.of(14, 20, 00), locale));
        assertEquals(dayPart.getString("day"), message.getDayPart(LocalTime.of(15, 20, 00), locale));
        assertEquals(dayPart.getString("day"), message.getDayPart(LocalTime.of(16, 20, 00), locale));
        assertEquals(dayPart.getString("day"), message.getDayPart(LocalTime.of(16, 20, 00), locale));
        assertEquals(dayPart.getString("day"), message.getDayPart(LocalTime.of(17, 20, 00), locale));
        assertEquals(dayPart.getString("day"), message.getDayPart(LocalTime.of(18, 20, 00), locale));
        assertEquals(dayPart.getString("evening"), message.getDayPart(LocalTime.of(19, 00, 00), locale));
        assertEquals(dayPart.getString("evening"), message.getDayPart(LocalTime.of(20, 00, 00), locale));
        assertEquals(dayPart.getString("evening"), message.getDayPart(LocalTime.of(21, 00, 00), locale));
        assertEquals(dayPart.getString("evening"), message.getDayPart(LocalTime.of(22, 00, 00), locale));
        assertEquals(dayPart.getString("night"), message.getDayPart(LocalTime.of(23, 00, 00), locale));
        
        
    }
    
    @Test
    public void shouldFormMessageWithLocaleUS() {
        Locale locale = new Locale("en_US");
        assertEquals("Good night, New York!", message.formMessage("New York", LocalTime.of(5, 00, 00), locale));
        assertEquals("Good morning, New York!", message.formMessage("New York", LocalTime.of(8, 00, 00), locale));
        assertEquals("Good day, New York!", message.formMessage("New York", LocalTime.of(12, 00, 00), locale));
        assertEquals("Good evening, New York!", message.formMessage("New York", LocalTime.of(19, 00, 00), locale));
        assertEquals("Good evening, New York!", message.formMessage("New York", LocalTime.of(20, 00, 00), locale));
        assertEquals("Good night, New York!", message.formMessage("New York", LocalTime.of(23, 30, 00), locale));
    }
    
    @Test
    public void shouldReturnDefaultCityZoneId() {
        assertEquals(ZoneId.of(DEFAULT_TIME_ZONE), message.getDefaultCityZoneId());
        
    }

}
