package java_learning.patterns.creational.builder.example1;

public class House {
    private String address;
    private HouseTypes type;
    private int numberOfFlats;
    private boolean hasGarden;
    private boolean hasPool;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public HouseTypes getType() {
        return type;
    }

    public void setType(HouseTypes type) {
        this.type = type;
    }

    public int getNumberOfFlats() {
        return numberOfFlats;
    }

    public void setNumberOfFlats(int numberOfFlats) {
        this.numberOfFlats = numberOfFlats;
    }

    public boolean isHasGarden() {
        return hasGarden;
    }

    public void setHasGarden(boolean hasGarden) {
        this.hasGarden = hasGarden;
    }

    public boolean isHasPool() {
        return hasPool;
    }

    public void setHasPool(boolean hasPool) {
        this.hasPool = hasPool;
    }

    @Override
    public String toString() {
        return "House{" +
                "address='" + address + '\'' +
                ", type=" + type +
                ", numberOfFlats=" + numberOfFlats +
                ", hasGarden=" + hasGarden +
                ", hasPool=" + hasPool +
                '}';
    }
}
