package java_learning.patterns.creational.builder.guru;

public class Director {
    private Builder builder;

    public Director(Builder builder) {
        this.builder = builder;
    }

    public void setBuilder(Builder builder) {
        this.builder = builder;
    }

    public void build() {
        builder.createDraft();
        builder.setSeats(2);
        builder.setEngine("100 HP");
        builder.setGPS();
        builder.setTripComputer();
    }
}
