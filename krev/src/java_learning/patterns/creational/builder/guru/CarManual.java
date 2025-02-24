package java_learning.patterns.creational.builder.guru;

public class CarManual {
    private int seats;
    private String engine;
    private String tripComputerDescription;
    private String gpsDescription;

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getTripComputerDescription() {
        return tripComputerDescription;
    }

    public void setTripComputerDescription(String tripComputerDescription) {
        this.tripComputerDescription = tripComputerDescription;
    }

    public String getGpsDescription() {
        return gpsDescription;
    }

    public void setGpsDescription(String gpsDescription) {
        this.gpsDescription = gpsDescription;
    }

    @Override
    public String toString() {
        return "CarManual{" +
                "seats=" + seats +
                ", engine='" + engine + '\'' +
                ", tripComputerDescription='" + tripComputerDescription + '\'' +
                ", gpsDescription='" + gpsDescription + '\'' +
                '}';
    }
}
