package sahaj.farecalculator;

import sahaj.farecalculator.data.access.DataService;
import sahaj.farecalculator.data.access.impl.DataServiceImpl;
import sahaj.farecalculator.data.model.Journey;
import sahaj.farecalculator.data.model.Zone;
import sahaj.farecalculator.core.FareCalculator;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Application {

    private static final Logger logger = Logger.getLogger(Application.class.getName());

    /**
     * Starting point of the application
     *
     * @param args - null or One argument - input test case file
     */

    public static void main(String[] args) {
        // Setup input
        InputSetup inputSetup = new InputSetup();
        inputSetup.preRequisiteDataSetUp();
        FareCalculator fareCalculator = FareCalculator.getInstance();
        DataService dataAccessServiceImpl = DataServiceImpl.getInstance();


        // No input file is provided. So take the journey file in resources folder as input
        if (args.length == 0) {
            List<Journey> journeys = inputSetup.inputJourneyDataSetUp();
            Integer totalFare = fareCalculator.calculateFare(journeys);
            System.out.println("Total Fare :" + totalFare);
            return;
        }

        // Input is provided in a separate file
        String inputFile = args[0];
        List<Zone> zones = dataAccessServiceImpl.getZones();

        try (InputStream inputStream = new FileInputStream(inputFile);
                InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
                BufferedReader reader = new BufferedReader(streamReader)) {

            int noOfTestCases = Integer.parseInt(reader.readLine());
            for (int i = 0; i < noOfTestCases; i++) {
                int noOfJourneys = Integer.parseInt(reader.readLine());
                List<Journey> journeys = new ArrayList<>();
                for (int j = 0; j < noOfJourneys; j++) {
                    String journeyEntry = reader.readLine();
                    String[] tokens = journeyEntry.split(" ");
                    OffsetDateTime timeStamp = OffsetDateTime.parse(tokens[0]);
                    int fromZone = Integer.parseInt(tokens[1]) - 1;
                    int toZone = Integer.parseInt(tokens[2]) - 1;
                    journeys.add(new Journey(timeStamp, zones.get(fromZone), zones.get(toZone)));
                }
                Integer totalFare = fareCalculator.calculateFare(journeys);
                System.out.println("Testcase:" + (i + 1) + "\tTotal Fare   :" + totalFare);
            }
        } catch (IOException e) {
            logger.severe("Exception while reading input file");
            e.printStackTrace();
        }
    }
}