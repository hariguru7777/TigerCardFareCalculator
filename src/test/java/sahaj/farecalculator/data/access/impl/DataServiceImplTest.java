package sahaj.farecalculator.data.access.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sahaj.farecalculator.InputSetup;
import sahaj.farecalculator.data.access.DataService;
import sahaj.farecalculator.data.model.Zone;
import sahaj.farecalculator.enums.BusyHoursType;
import sahaj.farecalculator.enums.DayType;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DataServiceImplTest {

    private InputSetup inputSetup = new InputSetup();
    private DataService dataService= DataServiceImpl.getInstance();
    private List<Zone> zones;
    
    @BeforeEach
    void setUp() {
        inputSetup.preRequisiteDataSetUp();
        zones = dataService.getZones();
    }
    
    @Test
    @DisplayName("TEST_INPUT_NULL")
    void testDayTypeForNull() {
        assertNull(dataService.getDayType(null)
                , "Should return null");
    }

    @Test
    @DisplayName("DayType for MONDAY")
    void testDayTypeForMonday() {
        assertEquals(dataService.getDayType(DayOfWeek.MONDAY), DayType.WEEKDAY
                , "Should return WEEKDAY");
    }

    @Test
    @DisplayName("DayType for TUESDAY")
    void testDayTypeForTuesday() {
        assertEquals(dataService.getDayType(DayOfWeek.TUESDAY), DayType.WEEKDAY
                , "Should return WEEKDAY");
    }

    @Test
    @DisplayName("DayType for WEDNESDAY")
    void testDayTypeForWednesday() {
        assertEquals(dataService.getDayType(DayOfWeek.WEDNESDAY), DayType.WEEKDAY
                , "Should return WEEKDAY");
    }

    @Test
    @DisplayName("DayType for THURSDAY")
    void testDayTypeForThursday() {
        assertEquals(dataService.getDayType(DayOfWeek.THURSDAY), DayType.WEEKDAY
                , "Should return WEEKDAY");
    }

    @Test
    @DisplayName("DayType for FRIDAY")
    void testDayTypeForFriday() {
        assertEquals(dataService.getDayType(DayOfWeek.FRIDAY), DayType.WEEKDAY
                , "Should return WEEKDAY");
    }

    @Test
    @DisplayName("DayType for SATURDAY")
    void testDayTypeForSaturday() {
        assertEquals(dataService.getDayType(DayOfWeek.SATURDAY), DayType.WEEKEND
                , "Should return WEEKEND");
    }

    @Test
    @DisplayName("DayType for SUNDAY")
    void testDayTypeForSunday() {
        assertEquals(dataService.getDayType(DayOfWeek.SUNDAY), DayType.WEEKEND
                , "Should return WEEKEND");
    }

    @Test
    @DisplayName("FARE_FOR_JOURNEY_PEAK_HOURS")
    void getFareForJourneyPeakHours() {
        assertEquals(dataService.getFareForJourney(zones.get(0),zones.get(1), BusyHoursType.PEAK_HOURS)
                , 35, "FARE_FOR_JOURNEY_PEAK_HOURS");
    }

    @Test
    @DisplayName("FARE_FOR_JOURNEY_OFF_PEAK_HOURS")
    void getFareForJourneyOffPeakHours() {
        assertEquals(dataService.getFareForJourney(zones.get(0),zones.get(1), BusyHoursType.OFF_PEAK_HOURS)
                , 30, "FARE_FOR_JOURNEY_OFF_PEAK_HOURS");
    }



    @Test
    @DisplayName("NULL_INPUT_TEST_PEAK_HOURS")
    void getFareForPeakHoursNull() {
        assertNull(dataService.getFareForJourney(zones.get(0),zones.get(1), null)
                , "NULL_INPUT_TEST_PEAK_HOURS");
    }

    @Test
    @DisplayName("DAILY_CAP_FOR_SAME_ZONE")
    void getDailyCapForSameZone() {
        assertEquals(dataService.getDailyCapForZone(zones.get(0),zones.get(0))
                ,100, "DAILY_CAP_FOR_SAME_ZONE");
    }

    @Test
    @DisplayName("WEEKLY_CAP_FOR_SAME_ZONE")
    void getWeeklyCapForSameZone() {
        assertEquals(dataService.getWeeklyCapForZone(zones.get(0),zones.get(0))
                ,500, "WEEKLY_CAP_FOR_SAME_ZONE");
    }

    @Test
    @DisplayName("WEEKLY_CAP_FOR_DIFFERENT_ZONE")
    void getWeeklyCapForDifferentZones() {
        assertEquals(dataService.getWeeklyCapForZone(zones.get(0),zones.get(1))
                ,600, "WEEKLY_CAP_FOR_DIFFERENT_ZONE");
    }
    
    // NULL parameter testcases
    @Test
    @DisplayName("TEST_INPUT_NULL")
    void getFareForNull() {
        assertNull(dataService.getFareForJourney(null,null, null)
                , "TEST_INPUT_NULL");
    }

    @Test
    @DisplayName("TEST_INPUT_NULL_FROM_ZONE")
    void getFareForFromZoneNull() {
        assertNull(dataService.getFareForJourney(null,zones.get(1), BusyHoursType.PEAK_HOURS)
                , "TEST_INPUT_NULL_FROM_ZONE");
    }

    @Test
    @DisplayName("TEST_INPUT_NULL_TO_ZONE")
    void getFareForToZoneNull() {
        assertNull(dataService.getFareForJourney(zones.get(0),null, BusyHoursType.PEAK_HOURS)
                , "TEST_INPUT_NULL_TO_ZONE");
    }
    @Test
    @DisplayName("TEST_INPUT_NULL")
    void getDailyCapForNull() {
        assertNull(dataService.getDailyCapForZone(null,null)
                , "TEST_INPUT_NULL");
    }

    @Test
    @DisplayName("TEST_INPUT_NULL_FROM_ZONE")
    void getDailyCapForNullFromZone() {
        assertNull(dataService.getDailyCapForZone(null,zones.get(0))
                , "TEST_INPUT_NULL_FROM_ZONE");
    }

    @Test
    @DisplayName("TEST_INPUT_NULL_TO_ZONE")
    void getDailyCapForNullToZone() {
        assertNull(dataService.getDailyCapForZone(zones.get(0),null)
                , "TEST_INPUT_NULL_TO_ZONE");
    }

    @Test
    @DisplayName("TEST_INPUT_NULL")
    void getWeeklyCapForNull() {
        assertNull(dataService.getDailyCapForZone(null,null)
                , "TEST_INPUT_NULL");
    }

    @Test
    @DisplayName("TEST_INPUT_NULL_FROM_ZONE")
    void getWeeklyCapForNullFromZone() {
        assertNull(dataService.getWeeklyCapForZone(null,zones.get(0))
                , "TEST_INPUT_NULL_FROM_ZONE");
    }

    @Test
    @DisplayName("TEST_INPUT_NULL_TO_ZONE")
    void getWeeklyCapForNullToZone() {
        assertNull(dataService.getWeeklyCapForZone(zones.get(0),null)
                , "TEST_INPUT_NULL_TO_ZONE");
    }

    @Test
    @DisplayName("TEST_NULL_INPUT")
    void getBusyPeakHoursTypeForNull() {
        assertNull(dataService.getBusyPeakHoursType(null, null)
                , "Both input DayType and Time are null");
    }

    @Test
    @DisplayName("TEST_NULL_INPUT_DAYTYPE")
    void getBusyPeakHoursTypeForDayTypeNull() {
        assertNull(dataService.getBusyPeakHoursType(null, LocalTime.parse("08:00"))
                , "Input DayType is null");
    }

    @Test
    @DisplayName("TEST_NULL_INPUT_TIME")
    void getBusyPeakHoursTypeForTimeNull() {
        assertNull(dataService.getBusyPeakHoursType(DayType.WEEKDAY, null)
                , "Input time is null");
    }

    @Test
    @DisplayName("TEST_PEAK_HOURS")
    void getBusyPeakHoursTypeForPeakHours() {
        assertEquals(dataService.getBusyPeakHoursType(DayType.WEEKDAY, LocalTime.parse("08:00"))
                , BusyHoursType.PEAK_HOURS, "PEAK_HOURS_TEST");
    }

    @Test
    @DisplayName("TEST_OFF_PEAK_HOURS")
    void getBusyPeakHoursTypeForOffPeakHours() {
        assertEquals(dataService.getBusyPeakHoursType(DayType.WEEKDAY, LocalTime.parse("11:00"))
                , BusyHoursType.OFF_PEAK_HOURS, "OFF_PEAK_HOURS_TEST");
    }

    @Test
    @DisplayName("TEST_BOUNDARY_CONDITION_START_TIME")
    void getBusyPeakHoursTypeBoundaryCondition() {
        assertEquals(dataService.getBusyPeakHoursType(DayType.WEEKDAY, LocalTime.parse("07:00"))
                , BusyHoursType.PEAK_HOURS, "Boundary Condition test: Start time");
    }

    @Test
    @DisplayName("TEST_BOUNDARY_CONDITION_START_TIME_BEFORE")
    void getBusyPeakHoursTypeBoundaryConditionBefore() {
        assertEquals(dataService.getBusyPeakHoursType(DayType.WEEKDAY, LocalTime.parse("06:59"))
                , BusyHoursType.OFF_PEAK_HOURS, "Boundary Condition test: Before Start time");
    }

    @Test
    @DisplayName("TEST_BOUNDARY_CONDITION_START_TIME_AFTER")
    void getBusyPeakHoursTypeBoundaryConditionAfter() {
        assertEquals(dataService.getBusyPeakHoursType(DayType.WEEKDAY, LocalTime.parse("07:01"))
                , BusyHoursType.PEAK_HOURS, "Boundary Condition test: After Start time");
    }

    @Test
    @DisplayName("TEST_BOUNDARY_CONDITION_END_TIME")
    void getBusyPeakHoursTypeEndBoundaryCondition() {
        assertEquals(dataService.getBusyPeakHoursType(DayType.WEEKDAY, LocalTime.parse("10:30"))
                , BusyHoursType.PEAK_HOURS, "Boundary Condition test: End time");
    }

    @Test
    @DisplayName("TEST_BOUNDARY_CONDITION_END_TIME_BEFORE")
    void getBusyPeakHoursTypeEndBoundaryConditionBefore() {
        assertEquals(dataService.getBusyPeakHoursType(DayType.WEEKDAY, LocalTime.parse("10:29"))
                , BusyHoursType.PEAK_HOURS, "Boundary Condition test: End time");
    }

    @Test
    @DisplayName("TEST_BOUNDARY_CONDITION_END_TIME_AFTER")
    void getBusyPeakHoursTypeEndBoundaryConditionAfter() {
        assertEquals(dataService.getBusyPeakHoursType(DayType.WEEKDAY, LocalTime.parse("10:31"))
                , BusyHoursType.OFF_PEAK_HOURS, "Boundary Condition test: End time");
    }

    @Test
    @DisplayName("TEST_TIME_FORMAT_HH_MM_SS")
    void getBusyPeakHoursTypeForPeakHoursTimeFormat() {
        assertEquals(dataService.getBusyPeakHoursType(DayType.WEEKDAY, LocalTime.parse("10:30:50"))
                , BusyHoursType.OFF_PEAK_HOURS, "OFF_PEAK_HOURS_TEST");
    }

    @Test
    @DisplayName("TEST_TIME_FORMAT_EXCEPTION")
    void getBusyPeakHoursTypeForPeakHoursTimeFormatException() {
        assertThrows(DateTimeParseException.class,
                () -> dataService.getBusyPeakHoursType(DayType.WEEKDAY, LocalTime.parse("10:3050")));
    }
}