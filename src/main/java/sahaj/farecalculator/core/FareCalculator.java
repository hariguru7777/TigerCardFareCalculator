package sahaj.farecalculator.core;

import sahaj.farecalculator.data.access.DataService;
import sahaj.farecalculator.data.access.impl.DataServiceImpl;
import sahaj.farecalculator.data.model.Journey;
import sahaj.farecalculator.data.model.TripFare;
import sahaj.farecalculator.data.model.Zone;
import sahaj.farecalculator.enums.BusyHoursType;
import sahaj.farecalculator.enums.DayType;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.logging.Logger;

/**
 * Service that has all the operations related to the fare calculations for the
 * list of journeys done by a customer
 */
public class FareCalculator {

    private static final Logger logger = Logger.getLogger(FareCalculator.class.getName());

    private static FareCalculator FareCalculator = new FareCalculator();
    private DataService dataAccessService = DataServiceImpl.getInstance();

    private FareCalculator() {
    }

    /**
     * Get an instance of the object
     * @return Singleton object
     */
    public static FareCalculator getInstance(){
        return FareCalculator;
    }
    public Integer calculateFare(List<Journey> journeyList) {
        if(journeyList == null || journeyList.isEmpty()){
            logger.fine("Total journeys is 0");
            return 0;
        }
        LocalDate prevDate = null;
        LocalDate currentDate = null;
        int weeklyCap = 0;
        int weeklyFare = 0;
        int totalFare = 0;
        // Iterate through all the trips
        for(int i = 0; i < journeyList.size();) {
            currentDate = journeyList.get(i).getTimestamp().toLocalDate();
            int dailyFare = 0;
            int dailyCap = 0;
            int weeklyCapForDay = 0;
            // Identify trips for the same day to calculate daily fare.
            while (i < journeyList.size()
                    && journeyList.get(i).getTimestamp().toLocalDate().equals(currentDate)){
                // if current day and previous day belongs to different weeks, calculate previous week fare.
                if (prevDate != null && !isDatesAreInTheSameWeek(prevDate,
                        journeyList.get(i).getTimestamp().toLocalDate())){
                    // update total fare
                    totalFare += Math.min(weeklyFare,weeklyCap);
                    weeklyCap = 0;
                    weeklyFare = 0;
                }
                TripFare tripFare = getTripFareForJourney(journeyList.get(i));
                dailyFare += tripFare.getFare();
                dailyCap = Math.max(tripFare.getDailyCap(),dailyCap);
                weeklyCapForDay = Math.max(tripFare.getWeeklyCap(),weeklyCapForDay);
                i++;
            }
            // update daily fare and weekly fare
            dailyFare = Math.min(dailyFare,dailyCap);
            weeklyFare += dailyFare;
            weeklyCap = Math.max(weeklyCap,weeklyCapForDay);
            prevDate = currentDate;
        }
        // calculate the last week fare and total fare
        weeklyFare = Math.min(weeklyFare,weeklyCap);
        totalFare += weeklyFare;
        return totalFare;
    }

    /**
     * Calculate the fare for a single trip
     * @param journey - Details of a single journey
     * @return tripFare
     */
    private TripFare getTripFareForJourney(Journey journey){
        LocalTime time = journey.getTimestamp().toLocalTime();
        Zone fromZone = journey.getFromZone();
        Zone toZone = journey.getToZone();
        DayType dayType = dataAccessService.getDayType(journey.getTimestamp().getDayOfWeek());
        BusyHoursType busyHoursType = dataAccessService.getBusyPeakHoursType(dayType, time);
        return new TripFare(dataAccessService.getFareForJourney(fromZone, toZone, busyHoursType),
                dataAccessService.getDailyCapForZone(fromZone, toZone),
                dataAccessService.getWeeklyCapForZone(fromZone, toZone));
    }

    /**
     * Check if the given two dates belongs to same week
     * @param date1 date1
     * @param date2 date2
     * @return true if dates are in the same week
     *         false otherwise
     */

    private boolean isDatesAreInTheSameWeek(LocalDate date1, LocalDate date2) {
        date1 = date1.minusDays(date1.getDayOfWeek().getValue());
        date2 = date2.minusDays(date2.getDayOfWeek().getValue());
        return  date1.equals(date2);
    }
}
