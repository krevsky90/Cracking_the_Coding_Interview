package java_learning.patterns.behavioral.iterator.several_simple_iterators;

import java.util.Iterator;
import java.util.function.Consumer;

/**
 * Implements usual Iterator interface
 *
 * NOTE: I created this abstract class just to case T to Node
 * and return this AbstractNodeIterator in methods that create different iterators!
 */
public abstract class AbstractNodeIterator implements Iterator<Node> {
    @Override
    public void remove() {
        Iterator.super.remove();
    }

    @Override
    public void forEachRemaining(Consumer<? super Node> action) {
        Iterator.super.forEachRemaining(action);
    }
}
