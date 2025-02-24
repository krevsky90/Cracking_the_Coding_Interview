package java_learning.patterns.creational.builder.guru;

public class Car {
    private int seats;
    private String engine;
    private boolean hasTripComputer;
    private boolean hasGPS;

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

    public boolean isHasTripComputer() {
        return hasTripComputer;
    }

    public void setHasTripComputer(boolean hasTripComputer) {
        this.hasTripComputer = hasTripComputer;
    }

    public boolean isHasGPS() {
        return hasGPS;
    }

    public void setHasGPS(boolean hasGPS) {
        this.hasGPS = hasGPS;
    }

    @Override
    public String toString() {
        return "Car{" +
                "seats=" + seats +
                ", engine='" + engine + '\'' +
                ", hasTripComputer=" + hasTripComputer +
                ", hasGPS=" + hasGPS +
                '}';
    }
}