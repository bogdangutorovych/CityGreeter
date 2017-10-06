package com.bogdan.citygreeter;

import java.time.ZoneId;
import java.util.Set;

public class Test {

    public static void main(String[] args) {
 
        System.out.println(assignCityZoneId("Kiev"));

    }

    public static ZoneId assignCityZoneId(String enteredTimeZone) {
        Set<String> ZoneIds = ZoneId.getAvailableZoneIds();
        for (String zone : ZoneIds) {
            System.out.println(zone);
            if (zone.equals(enteredTimeZone) || zone.indexOf(enteredTimeZone) != -1) {
                return ZoneId.of(zone);
            }
        }
        return getDefaultCityZoneId();
    }
    
    public static ZoneId getDefaultCityZoneId() {
        return ZoneId.of("GMT");
    }
    
}
