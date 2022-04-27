package sahaj.farecalculator.data.model;

import java.time.LocalTime;
import sahaj.farecalculator.enums.DayType;

/**
 * Data model for Peak hours data
 */
public class PeakHours {
    private DayType dayType;
    private LocalTime fromTime;
    private LocalTime toTime;

    public PeakHours(DayType dayType, LocalTime
             fromTime, LocalTime toTime){
        this.dayType = dayType;
        this.fromTime = fromTime;
        this.toTime = toTime;
    }

    public DayType getDayType() {
        return dayType;
    }

    public LocalTime getFromTime() {
        return fromTime;
    }

    public LocalTime getToTime() {
        return toTime;
    }
}
