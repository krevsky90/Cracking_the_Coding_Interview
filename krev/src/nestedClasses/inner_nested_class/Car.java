package nestedClasses.inner_nested_class;

public class Car {
    String type;
    private int doorCount;
    Engine engine;

    private static int a;

    public Car(String type, int doorCount, int horsePower) {
        this.type = type;
        this.doorCount = doorCount;
        this.engine = this.new Engine(horsePower);  //or new Engine(horsePower)

        //NOTE: external class has access to private elements of object of inner class (after we create object of inner class)
        int hp = engine.horsePower;
        engine.privateInnerMethod();
    }

    public Car(String type, int doorCount) {
        this.type = type;
        this.doorCount = doorCount;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public String toString() {
        return "Car: " + type + ", doorCount = " + doorCount + ", engine = " + engine;
    }

    public class Engine {
//        private static int counter;         //NOTE: inner classes can NOT contain static elements (fields/methods)!
        private static final int counter = 4;   //but if static field is final => ok
        private int horsePower;


        public Engine(int horsePower) {
            this.horsePower = horsePower;
            //inner class has access even to private static and non-static elements of external class
            int b = a;
            int cc = doorCount;
        }

        public String toString() {
            return "MyEngine: (" + horsePower + ")";
        }

        private void privateInnerMethod() {}
    }

    private class Z extends Engine {

        public Z(int horsePower) {
            super(horsePower);
        }
    }
}
