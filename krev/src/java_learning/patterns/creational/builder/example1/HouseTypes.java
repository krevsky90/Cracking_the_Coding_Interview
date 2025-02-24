package java_learning.patterns.creational.builder.example1;

public enum HouseTypes {
    TYPE_1("type1"),
    TYPE_2("type2"),
    TYPE_3("type3");

    private final String type;

    HouseTypes(String type) {
        this.type = type;
    }
}
