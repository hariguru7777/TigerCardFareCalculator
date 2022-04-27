package sahaj.farecalculator;

import sahaj.farecalculator.data.access.DataService;
import sahaj.farecalculator.data.access.impl.DataServiceImpl;
import sahaj.farecalculator.data.model.Fare;
import sahaj.farecalculator.data.model.Journey;
import sahaj.farecalculator.data.model.PeakHours;
import sahaj.farecalculator.data.model.Zone;
import sahaj.farecalculator.enums.BusyHoursType;
import sahaj.farecalculator.enums.DayType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.util.*;
import java.util.logging.Logger;

/**
 * Class that will setup the all the pre-requisites for the application.
 * Read the inputs from file and populate zone, peakhours, fares and journeys.
 */
public class InputSetup {

    private static final Logger logger = Logger.getLogger(InputSetup.class.getName());
    public static final String PREREQUISITE_DATA = "PrerequisiteData.txt";
    public static final String INPUT_JOURNEY_DATA = "InputJourneyData.txt";
    private DataService dataAccessService = DataServiceImpl.getInstance();
    /**
     * Read the inputs from file and populate zone, peakhours, fares
     * @return true if success, false if some exceptions occur
     */
    public Boolean preRequisiteDataSetUp(){
        try (InputStream inputStream = getClass().getClassLoader()
                .getResourceAsStream(PREREQUISITE_DATA);
                InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
                BufferedReader reader = new BufferedReader(streamReader)) {

            // First line has no of zones
            String line = reader.readLine();
            int noOfZones = Integer.parseInt(line);
            List<Zone> zones = new ArrayList<>();
            for(int i = 0; i< noOfZones; i++){
                zones.add(new Zone(i+1));
            }
            dataAccessService.setZones(zones);

            // Second line has no of Fare entries
            line = reader.readLine();
            int noOfFareEntry = Integer.parseInt(line);
            List<Fare> fares = new ArrayList<>();
            for(int i = 0; i < noOfFareEntry; i++) {
                String fareEntry = reader.readLine();
                String[] tokens = fareEntry.split(" ");
                Zone fromZone = zones.get(Integer.parseInt(tokens[0])-1);
                Zone toZone = zones.get(Integer.parseInt(tokens[1])-1);
                Integer peakHoursFare = Integer.parseInt(tokens[2]);
                Integer offPeakHoursFare = Integer.parseInt(tokens[3]);
                Integer dailyCap = Integer.parseInt(tokens[4]);
                Integer weeklyCap = Integer.parseInt(tokens[5]);

                Map<BusyHoursType,Integer> hoursFare = new HashMap<>();
                hoursFare.put(BusyHoursType.PEAK_HOURS,peakHoursFare);
                hoursFare.put(BusyHoursType.OFF_PEAK_HOURS,offPeakHoursFare);

                fares.add(new Fare(fromZone,toZone,hoursFare,dailyCap,weeklyCap));
            }
            dataAccessService.setFares(fares);

            // Next line has no of peak hour entries
            line = reader.readLine();
            int noOfPeakHourEntry = Integer.parseInt(line);
            List<PeakHours> peakHours = new ArrayList<>();
            for(int i = 0; i < noOfPeakHourEntry; i++){
                String peakHourEntry = reader.readLine();
                String[] tokens = peakHourEntry.split(" ");
                DayType dayType = DayType.valueOf(tokens[0]);
                LocalTime fromTime = LocalTime.parse(tokens[1]);
                LocalTime toTime = LocalTime.parse(tokens[2]);
                peakHours.add(new PeakHours(dayType,fromTime,toTime));
            }
            dataAccessService.setPeakHours(peakHours);

        } catch (IOException e) {
            return false;
        }

        return true;
    }

    /**
     * Read the inputs from file and populate journeylist
     * @return true if success, false if some exceptions occur
     */
    public List<Journey> inputJourneyDataSetUp(){

        List<Zone> zones = dataAccessService.getZones();

        if(zones == null || zones.isEmpty()){
            return Collections.emptyList();
        }

        try (InputStream inputStream = getClass().getClassLoader()
                .getResourceAsStream(INPUT_JOURNEY_DATA);
                InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
                BufferedReader reader = new BufferedReader(streamReader)) {

            List<Journey> journeys = new ArrayList<>();
            String journeyEntry;
            while ((journeyEntry = reader.readLine()) != null) {
                String[] tokens = journeyEntry.split(" ");
                OffsetDateTime timeStamp = OffsetDateTime.parse(tokens[0]);
                int fromZone = Integer.parseInt(tokens[1]) - 1;
                int toZone = Integer.parseInt(tokens[2]) - 1;
                journeys.add(new Journey(timeStamp,zones.get(fromZone),zones.get(toZone)));

            }
            return journeys;
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}
