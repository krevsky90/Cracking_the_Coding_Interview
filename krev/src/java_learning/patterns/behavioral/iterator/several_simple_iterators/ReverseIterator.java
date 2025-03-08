package java_learning.patterns.behavioral.iterator.several_simple_iterators;

import java.util.NoSuchElementException;

public class ReverseIterator extends AbstractNodeIterator {
    private final NodeCollection collection;
    private int pos;

    public ReverseIterator(NodeCollection collection) {
        this.collection = collection;
        this.pos = collection.getSize() - 1;
    }

    @Override
    public boolean hasNext() {
        return pos >= 0;
    }

    @Override
    public Node next() {
        if (hasNext()) {
            Node res = collection.getNodeByIndex(pos);
            pos--;
            return res;
        } else {
            throw new NoSuchElementException();
        }
    }
}
