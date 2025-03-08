package java_learning.patterns.behavioral.iterator.several_simple_iterators;

import java.util.NoSuchElementException;

public class OddIterator extends AbstractNodeIterator {
    private final NodeCollection collection;
    private int pos = 0;

    public OddIterator(NodeCollection collection) {
        this.collection = collection;
    }

    @Override
    public boolean hasNext() {
        return pos < collection.getSize();
    }

    @Override
    public Node next() {
        if (hasNext()) {
            Node res = collection.getNodeByIndex(pos);
            pos += 2;
            return res;
        } else {
            throw new NoSuchElementException();
        }
    }
}
