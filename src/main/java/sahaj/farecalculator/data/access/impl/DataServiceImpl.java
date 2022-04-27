package sahaj.farecalculator.data.access.impl;

import sahaj.farecalculator.data.access.DataService;
import sahaj.farecalculator.data.model.Fare;
import sahaj.farecalculator.data.model.PeakHours;
import sahaj.farecalculator.data.model.Zone;
import sahaj.farecalculator.enums.BusyHoursType;
import sahaj.farecalculator.enums.DayType;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class DataServiceImpl implements DataService {

    private static final Logger logger = Logger.getLogger(DataServiceImpl.class.getName());
    private static final DataService dataAccessService = new DataServiceImpl();
    private List<Zone> zones;
    private List<Fare> fares;
    private List<PeakHours> peakHours;

    private DataServiceImpl(){

    }

    /**
     * Return Singleton object
     *
     * @return Singleton object
     */
    public static DataService getInstance() {
        return dataAccessService;
    }

    public List<PeakHours> getPeakHours() {
        return peakHours;
    }

    public void setPeakHours(List<PeakHours> peakHours) {
        this.peakHours = peakHours;
    }

    public List<Fare> getFares() {
        return fares;
    }

    public void setFares(List<Fare> fares) {
        this.fares = fares;
    }

    /**
     * Method to find if a day of the week is a weekend or weekday.
     *
     * @param day - Day of the week
     * @return DayType or null
     */
    @Override
    public DayType getDayType(DayOfWeek day) {
        if (day == null) {
            logger.severe("NULL Input");
            return null;
        }
        if (day.equals(DayOfWeek.SATURDAY) || day.equals(DayOfWeek.SUNDAY)) {
            return DayType.WEEKEND;
        }
        return DayType.WEEKDAY;
    }

    /**
     * Calculate the fare for a journey from fromZone to toZone during peak/off-peak hours.
     *
     * @param fromZone  - start zone
     * @param toZone    - end zone
     * @param hoursType - Peak/off-peak hours
     * @return null or Fare amount for the journey is returned
     */
    @Override
    public Integer getFareForJourney(Zone fromZone, Zone toZone, BusyHoursType hoursType) {
        if (fromZone == null || toZone == null || hoursType == null) {
            logger.severe("NULL Input parameters");
            return null;
        }
        List<Fare> fareRecord = getFares().stream()
                .filter(x -> x.getFromZone().equals(fromZone) && x.getToZone().equals(toZone))
                .collect(Collectors.toList());
        Integer fare = fareRecord.get(0).getFare().get(hoursType);
        return fare;
    }

    /**
     * Calculate the dailycap for a journey between two zones
     *
     * @param fromZone - Start zone
     * @param toZone   - End zone
     * @return null or DailyCap for the journey is returned.
     */
    @Override
    public Integer getDailyCapForZone(Zone fromZone, Zone toZone) {
        if (fromZone == null || toZone == null) {
            logger.severe("NULL Input parameters");
            return null;
        }
        List<Fare> fareRecord = getFares().stream()
                .filter(x -> x.getFromZone().equals(fromZone) && x.getToZone().equals(toZone))
                .collect(Collectors.toList());
        Integer dailyCap = fareRecord.get(0).getDailyCap();
        return dailyCap;
    }

    /**
     * Calculates the weeklycap for a journey between two zones
     *
     * @param fromZone - Start zone
     * @param toZone   - End zone
     * @return null or Weekly cap for the journey is returned.
     */
    @Override
    public Integer getWeeklyCapForZone(Zone fromZone, Zone toZone) {
        if (fromZone == null || toZone == null) {
            logger.severe("NULL Input parameters");
            return null;
        }
        List<Fare> fareRecord = getFares().stream()
                .filter(x -> x.getFromZone().equals(fromZone) && x.getToZone().equals(toZone))
                .collect(Collectors.toList());
        Integer weeklyCap = fareRecord.get(0).getWeeklyCap();
        return weeklyCap;
    }

    /**
     * Find out if it is the peek/off-peak hours for a given day and time
     * @param dayType - Day type - WEEKDAY/WEEKEND
     * @param time - Time of travel
     * @return null or BusyHoursType
     */
    @Override
    public BusyHoursType getBusyPeakHoursType(DayType dayType, LocalTime time){
        if(dayType == null || time == null){
            logger.severe("NULL Input parameters");
            return null;
        }
        long count = getPeakHours().stream().filter(x -> x.getDayType().equals(dayType)
                        && x.getFromTime().minusNanos(1).isBefore(time)
                        && x.getToTime().plusNanos(1).isAfter(time))
                .count();
        if(count == 0){
            return BusyHoursType.OFF_PEAK_HOURS;
        }
        return BusyHoursType.PEAK_HOURS;
    }

    /**
     * Get the list of zones
     * @return List of zones
     */
    @Override
    public List<Zone> getZones() {
        return zones;
    }

    /**
     * Set the list of zones
     * @param zones - Set the list of zones
     */
    @Override
    public void setZones(List<Zone> zones) {
        this.zones = zones;
    }
}
