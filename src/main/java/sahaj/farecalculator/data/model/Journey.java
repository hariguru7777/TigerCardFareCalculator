package sahaj.farecalculator.data.model;

import java.time.OffsetDateTime;

/**
 * Data model for Journey details
 */
public class Journey {
    private OffsetDateTime timestamp;
    private Zone fromZone;
    private Zone toZone;

    public Journey(OffsetDateTime timestamp, Zone fromZone, Zone toZone) {
        this.timestamp = timestamp;
        this.fromZone = fromZone;
        this.toZone = toZone;
    }

    public OffsetDateTime getTimestamp() {
        return timestamp;
    }

    public Zone getFromZone() {
        return fromZone;
    }

    public Zone getToZone() {
        return toZone;
    }
}
