package java_learning.patterns.creational.builder.guru;

public class CarManualBuilder implements Builder {
    private CarManual carManual;

    @Override
    public void createDraft() {
        carManual = new CarManual();
    }

    @Override
    public void setSeats(int n) {
        carManual.setSeats(n);
    }

    @Override
    public void setEngine(String engine) {
        carManual.setEngine(engine);
    }

    @Override
    public void setTripComputer() {
        carManual.setTripComputerDescription("Some description of trip computer");
    }

    @Override
    public void setGPS() {
        carManual.setGpsDescription("Some description of GPS");
    }

    public CarManual getResult() {
        return this.carManual;
    }
}
