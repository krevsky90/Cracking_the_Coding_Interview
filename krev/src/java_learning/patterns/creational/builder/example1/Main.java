package java_learning.patterns.creational.builder.example1;

public class Main {
    public static void main(String[] args) {
        HouseBuilder houseBuilder = new HouseBuilder();
        Director director = new Director(houseBuilder);

        director.createHouseWithPool();
        House houseWithPool = houseBuilder.getHouse();
        System.out.println(houseWithPool);

        director.create3floorsHouseWithGarden();
        House house3floorsWithGarden = houseBuilder.getHouse();
        System.out.println(house3floorsWithGarden);
    }
}
