package java_learning.patterns.creational.builder.guru;

public class Main {
    public static void main(String[] args) {
        CarBuilder carBuilder = new CarBuilder();
        CarManualBuilder carManualBuilder = new CarManualBuilder();

        Director director = new Director(carBuilder);
        director.build();
        Car car = carBuilder.getResult();
        System.out.println(car);

        director.setBuilder(carManualBuilder);
        director.build();
        CarManual carManual = carManualBuilder.getResult();
        System.out.println(carManual);
    }
}
