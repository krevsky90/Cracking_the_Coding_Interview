package java_learning.patterns.creational.builder.example1;

//OR it can be abstract class
public interface Builder {
    //OR we can return void, but if we return Builder, we will be able to use method chaining!
    Builder createDraft();
    Builder setAddress(String address);
    Builder setHouseType(HouseTypes type);
    Builder setNumberOfFlats(int num);
    Builder setHasGarden(boolean hasGarden);
    Builder setHasPool(boolean hasPool);
}
