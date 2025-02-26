package java_learning.concurrency.immutable_objects.mutable;

public class MutableClass {
    private int counter = 0;

    public MutableClass(int c) {
        this.counter = c;
    }

    public MutableClass(MutableClass o) {
        this(o.counter);
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    @Override
    public String toString() {
        return "MutableClass{" +
                "counter=" + counter +
                '}';
    }
}
