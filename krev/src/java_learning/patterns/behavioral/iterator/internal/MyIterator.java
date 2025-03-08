package java_learning.patterns.behavioral.iterator.internal;

public interface MyIterator<T> {
    boolean hasNext();

    T next();
}
