package live_coding.ood.designingParkingLot.grokking;

public class ParkingRate {
    private double hours;
    private double rate;

    public ParkingRate(double hours, double rate) {
        this.hours = hours;
        this.rate = rate;
    }

    public double calculate() {
        return hours*rate;  //for example
    }
}
