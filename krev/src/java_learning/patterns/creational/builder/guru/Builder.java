package java_learning.patterns.creational.builder.guru;

public interface Builder {
    void createDraft();
    void setSeats(int n);
    void setEngine(String engine);
    void setTripComputer();
    void setGPS();
}
