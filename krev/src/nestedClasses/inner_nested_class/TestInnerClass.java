package nestedClasses.inner_nested_class;

public class TestInnerClass {
    public static void main(String[] args) {
        Car car = new Car("type1", 4);
        System.out.println(car);
        //NOTE: we can NOT create object of nested inner class without creation of object of external (Car) class!
        Car.Engine engine = car.new Engine(3);

        car.setEngine(engine);
        System.out.println(car);

        //NOTE: this expression does NOT mean that created Engine object belongs to created Car object!
        Car.Engine engine2 = new Car("t2", 2).new Engine(3);
    }
}
