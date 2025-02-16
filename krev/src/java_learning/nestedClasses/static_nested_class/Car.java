package java_learning.nestedClasses.static_nested_class;

public class Car {
    String type;
    private int doorCount;
    Engine engine;

    private static int a;

    public Car(String type, int doorCount, Engine engine) {
        this.type = type;
        this.doorCount = doorCount;
        this.engine = engine;

        //external class can use even private elements (fields/methods) of nested static class
        System.out.println(Engine.counter);
        Engine.m2();
        System.out.println(engine.horsePower);
        engine.m1();
    }

    private static void externalPrivateMethod() {}

    protected static class Engine {
        private static int counter;
        private int horsePower;

        public Engine(int horsePower) {
            counter++;
            this.horsePower = horsePower;
            System.out.println(a);              //ok, despite a is private
//            System.out.println(doorCount);    //NOT ok since doorCount is NOT static variable
        }

        public String toString() {
            return "MyEngine: (" + horsePower + ")";
        }

        private void m1() {
            //ok, despite externalPrivateMethod() is private
            externalPrivateMethod();
        }

        private static void m2() {
            //ok, despite externalPrivateMethod() is private
            externalPrivateMethod();
        }

        private static class Z {}
    }


}
