package sahaj.farecalculator.data.model;

public class TripFare {
    private Integer fare;
    private Integer dailyCap;
    private Integer weeklyCap;

    public TripFare(Integer fare, Integer dailyCap, Integer weeklyCap) {
        this.fare = fare;
        this.dailyCap = dailyCap;
        this.weeklyCap = weeklyCap;
    }

    public Integer getFare() {
        return fare;
    }

    public Integer getDailyCap() {
        return dailyCap;
    }

    public Integer getWeeklyCap() {
        return weeklyCap;
    }
}
