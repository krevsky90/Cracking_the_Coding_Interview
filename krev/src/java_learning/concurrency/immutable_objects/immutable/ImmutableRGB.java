package java_learning.concurrency.immutable_objects.immutable;

import java_learning.concurrency.immutable_objects.mutable.MutableClass;
import java_learning.concurrency.immutable_objects.mutable.SynchronizedRGB;

// idea #1: class should be final (or have private constructor)
public final class ImmutableRGB {
    // idea #2: fields should be private and final
    private final int red;
    private final int green;
    private final int blue;
    private final String name; //NOTE: this is reference to IMMUTABLE objects => no need to create copy of it in getName() method
    private final MutableClass obj; //add this field to show how to handle such cases (when immutable object contains reference to mutable object

    private void check(int red, int green, int blue) {
        if (red < 0 || red > 255 || green < 0 || green > 255 || blue < 0 || blue > 255) {
            throw new IllegalArgumentException();
        }
    }

    public ImmutableRGB(int red, int green, int blue, String name, MutableClass obj) {
        check(red, green, blue);
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.name = name;
        //idea #5: save copy of given mutable object
        this.obj = new MutableClass(obj);
    }

    //idea #3: remove set method
//    public void set(int red, int green, int blue, String name) {
//        check(red, green, blue);
//        synchronized (this) {
//            this.red = red;
//            this.green = green;
//            this.blue = blue;
//            this.name = name;
//        }
//    }

    //idea #4: remove synchronized word since the object is immutable
    public int getRGB() {
        return ((red << 16) | (green << 8) | blue);
    }

    public String getName() {
        return name;
    }

    //idea #5: return copy of mutable object
    public MutableClass getObj() {
        return new MutableClass(obj);
    }

    //idea #6: since the object is immutable, we need to return new inverted object
    public synchronized ImmutableRGB invert() {
        //idea #5.2: propagate copy of mutable object to constructor of immutable object
        return new ImmutableRGB(255 - red, 255 - green, 255 - blue, "Inverse of " + name, new MutableClass(obj));
    }

    @Override
    public String toString() {
        return "ImmutableRGB{" +
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
        ImmutableRGB color = new ImmutableRGB(0, 0, 0, "Pitch Black", obj);
        System.out.println(color);  //will print object with color.counter = 100

        obj.setCounter(150);
        System.out.println(color);  //will print object with color.counter = 100
    }
}
