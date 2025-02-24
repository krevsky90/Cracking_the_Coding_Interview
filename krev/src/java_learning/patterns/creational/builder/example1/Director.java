package java_learning.patterns.creational.builder.example1;

/**
 * Director is optional class
 * potentially we can call builder directly from Client class,
 * but director can have methods to create different objects (with different list of characteristics)
 */
public class Director {
    Builder builder;

    public Director(Builder builder) {
        this.builder = builder;
    }

    public void createDefaultHouse() {
        builder.createDraft();
        builder.setAddress("addr2");
        builder.setNumberOfFlats(3);
        builder.setHouseType(HouseTypes.TYPE_1);
    }

    public void create3floorsHouseWithGarden() {
        builder.createDraft();
        builder.setAddress("addr2");
        builder.setNumberOfFlats(3);
        builder.setHouseType(HouseTypes.TYPE_1);
        builder.setHasGarden(true);
    }

    //can use method chaining
    public void createHouseWithPool() {
        builder.createDraft()
                .setAddress("addr1")
                .setNumberOfFlats(2)
                .setHouseType(HouseTypes.TYPE_3)
                .setHasPool(true);
    }
}
