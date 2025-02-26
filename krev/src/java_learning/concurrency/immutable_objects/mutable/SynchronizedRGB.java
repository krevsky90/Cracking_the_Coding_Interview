package java_learning.concurrency.immutable_objects.mutable;

public class SynchronizedRGB {
    // Values must be between 0 and 255.
    private int red;
    private int green;
    private int blue;
    private String name;
    private MutableClass obj;

    private void check(int red, int green, int blue) {
        if (red < 0 || red > 255 || green < 0 || green > 255 || blue < 0 || blue > 255) {
            throw new IllegalArgumentException();
        }
    }

    public SynchronizedRGB(int red, int green, int blue, String name, MutableClass obj) {
        check(red, green, blue);
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.name = name;
        this.obj = obj;
    }

    public void set(int red, int green, int blue, String name, MutableClass obj) {
        check(red, green, blue);
        synchronized (this) {
            this.red = red;
            this.green = green;
            this.blue = blue;
            this.name = name;
            this.obj = obj;
        }
    }

    public synchronized int getRGB() {
        return ((red << 16) | (green << 8) | blue);
    }

    public synchronized String getName() {
        return name;
    }

    public synchronized MutableClass getObj() {
        return obj;
    }

    public synchronized void invert() {
        red = 255 - red;
        green = 255 - green;
        blue = 255 - blue;
        name = "Inverse of " + name;
    }

    @Override
    public String toString() {
        return "SynchronizedRGB{" +
                "red=" + red +
                ", green=" + green +
                ", blue=" + blue +
                ", name='" + name + '\'' +
                ", obj=" + obj +
                '}';
    }

    public static void main(String[] args) {
        MutableClass obj = new MutableClass(100);
        //example 1:
        SynchronizedRGB color = new SynchronizedRGB(0, 0, 0, "Pitch Black", obj);
        System.out.println(color);  //will print object with color.counter = 100

        int myColorInt = color.getRGB();      //Statement 1
        //NOTE: it some thread calls set(//) method, then myColorInt and myColorName will be in inconsistent state!
        String myColorName = color.getName(); //Statement 2

        //example 2:
        //to avoid inconsistency, need to use sync block
        synchronized (color) {
            int myColorInt2 = color.getRGB();
            String myColorName2 = color.getName();
        }

        obj.setCounter(150);
        System.out.println(color);  //will print object with color.counter = 150
    }
}
