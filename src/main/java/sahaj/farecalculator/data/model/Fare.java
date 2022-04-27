package sahaj.farecalculator.data.model;

import java.util.Map;
import sahaj.farecalculator.enums.BusyHoursType;

/**
 * Data model for Fare details
 */
public class Fare {
    private Zone fromZone;
    private Zone toZone;
    private Map<BusyHoursType, Integer> fare;
    private Integer dailyCap;
    private Integer weeklyCap;

    public Fare(Zone fromZone, Zone toZone, Map<BusyHoursType, Integer> fare, Integer dailyCap,
            Integer weeklyCap) {
        this.fromZone = fromZone;
        this.toZone = toZone;
        this.fare = fare;
        this.dailyCap = dailyCap;
        this.weeklyCap = weeklyCap;
    }

    public Zone getFromZone() {
        return fromZone;
    }

    public Zone getToZone() {
        return toZone;
    }

    public Map<BusyHoursType, Integer> getFare() {
        return fare;
    }

    public Integer getDailyCap() {
        return dailyCap;
    }

    public Integer getWeeklyCap() {
        return weeklyCap;
    }
}
