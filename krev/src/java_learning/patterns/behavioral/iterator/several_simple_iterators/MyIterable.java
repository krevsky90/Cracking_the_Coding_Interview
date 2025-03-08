package java_learning.patterns.behavioral.iterator.several_simple_iterators;

import java.util.Iterator;

/**
 * Instead of usual Iterable, to support several iterators
 */
public interface MyIterable<T> {
    Iterator<T> getOddIterator();

    Iterator<T> getReverseIterator();
}
