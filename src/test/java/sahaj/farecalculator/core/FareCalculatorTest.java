package sahaj.farecalculator.core;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sahaj.farecalculator.InputSetup;
import sahaj.farecalculator.data.model.Journey;

import java.util.List;

class FareCalculatorTest {

    private InputSetup inputSetup = new InputSetup();
    private FareCalculator fareCalculator = FareCalculator.getInstance();
    List<Journey> journeyList;

    @BeforeEach
    void setUp() {
        inputSetup.preRequisiteDataSetUp();
        journeyList = inputSetup.inputJourneyDataSetUp();
    }

    @Test
    @DisplayName("CALCULATE_FARE_TEST")
    void calculateFare() {
        assertEquals(fareCalculator.calculateFare(journeyList),690, "Total fare for the customer");
    }

    @Test
    @DisplayName("TOTAL_FARE_JOURNEY_NULL")
    void calculateFareForNullJourney() {
        assertEquals(fareCalculator
                        .calculateFare(null)
                , 0,"TOTAL_FARE_JOURNEY_NULL");
    }
}
