package java_learning.patterns.creational.builder.guru;

public class CarBuilder implements Builder {
    private Car car;

    @Override
    public void createDraft() {
        car = new Car();
    }

    @Override
    public void setSeats(int n) {
        car.setSeats(n);
    }

    @Override
    public void setEngine(String engine) {
        car.setEngine(engine);
    }

    @Override
    public void setTripComputer() {
        car.setHasTripComputer(true);
    }

    @Override
    public void setGPS() {
        car.setHasGPS(true);
    }

    public Car getResult() {
        return this.car;
    }
}
