package java_learning.patterns.creational.builder.example1;

public class HouseBuilder implements Builder {
    private House house;

    public HouseBuilder() {
        createDraft();
    }

    public Builder createDraft() {
        this.house = new House();
        return this;    //i.e. return builder object
    }

    @Override
    public Builder setAddress(String address) {
        house.setAddress(address);
        return this;
    }

    @Override
    public Builder setHouseType(HouseTypes type) {
        house.setType(type);
        return this;
    }

    @Override
    public Builder setNumberOfFlats(int num) {
        house.setNumberOfFlats(num);
        return this;
    }

    @Override
    public Builder setHasGarden(boolean hasGarden) {
        house.setHasGarden(hasGarden);
        return this;
    }

    @Override
    public Builder setHasPool(boolean hasPool) {
        house.setHasPool(hasPool);
        return this;
    }

    //NOTE: this is NOT overriden method, since different builders potentially might return different objects!
    public House getHouse() {
        return house;
    }
}
