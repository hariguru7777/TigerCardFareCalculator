package sahaj.farecalculator.data.access;

import sahaj.farecalculator.data.model.Fare;
import sahaj.farecalculator.data.model.PeakHours;
import sahaj.farecalculator.data.model.Zone;
import sahaj.farecalculator.enums.BusyHoursType;
import sahaj.farecalculator.enums.DayType;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

public interface DataService {

    /**
     * Method to find if a day of the week is a weekend or weekday.
     * @param day - Day of the week
     * @return DayType or null
     */
    DayType getDayType(DayOfWeek day);

    /**
     * Calculate the fare for a journey from fromZone to toZone during peak/off-peak hours.
     * @param fromZone - start zone
     * @param toZone - end zone
     * @param hoursType - Peak/off-peak hours
     * @return null or Fare amount for the journey is returned
     */
    Integer getFareForJourney(Zone fromZone, Zone toZone, BusyHoursType hoursType);

    /**
     * Calculate the dailycap for a journey between two zones
     * @param fromZone - Start zone
     * @param toZone - End zone
     * @return null or DailyCap for the journey is returned.
     */
    Integer getDailyCapForZone(Zone fromZone, Zone toZone);


    /**
     * Calculates the weeklycap for a journey between two zones
     * @param fromZone - Start zone
     * @param toZone - End zone
     * @return null or Weekly cap for the journey is returned.
     */
    Integer getWeeklyCapForZone(Zone fromZone, Zone toZone);

    /**
     * Method to set the Peak hour data
     * @param peakHours - peakHours data
     */
    void setPeakHours(List<PeakHours> peakHours);

    /**
     * Find out if it is the peek/off-peak hours for a given day and time
     * @param dayType - Day type - WEEKDAY/WEEKEND
     * @param time - Time of travel
     * @return null or BusyHoursType
     */
    BusyHoursType getBusyPeakHoursType(DayType dayType, LocalTime time);

    /**
     * Method to get the list of zones
     * @return - list of zones
     */
    List<Zone> getZones();

    /**
     * Method to set the list of zones
     * @param zones - list of zones
     */
    void setZones(List<Zone> zones);

    /**
     * Method to set the fare data
     */
    void setFares(List<Fare> fares);


}
