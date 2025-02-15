package nestedClasses.static_nested_class;

public class TestStaticClass {
    public static void main(String[] args) {
        //NOTE: we can create object of nested static class WITHOUT creation of object of external (Car) class!
        Car.Engine engine = new Car.Engine(10);
        Car car = new Car("type1", 4, engine);
    }
}
