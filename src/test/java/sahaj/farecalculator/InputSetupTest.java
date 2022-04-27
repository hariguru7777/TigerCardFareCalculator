package sahaj.farecalculator;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sahaj.farecalculator.data.access.DataService;
import sahaj.farecalculator.data.access.impl.DataServiceImpl;
import sahaj.farecalculator.data.model.Journey;
import sahaj.farecalculator.data.model.Zone;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

class InputSetupTest {

    private InputSetup inputSetup;
    private DataService dataAccessService = DataServiceImpl.getInstance();

    @BeforeEach
    void setup(){
        inputSetup = new InputSetup();
    }

    @Test
    @DisplayName("PREREQUISITE_DATA_SETUP")
    void preRequisiteDataSetUp() {
        assertEquals(inputSetup.preRequisiteDataSetUp(),true,"Prerequisite data Setup test");
    }

    @Test
    @DisplayName("JOURNEY_DATA_SETUP")
    void inputJourneyDataSetUp() {
        inputSetup.preRequisiteDataSetUp();
        assertEquals(inputSetup.inputJourneyDataSetUp().size(),36,"Journey data Setup test");
    }
}
