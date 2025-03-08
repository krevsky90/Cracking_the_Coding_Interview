package java_learning.patterns.behavioral.iterator.several_simple_iterators;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class NodeCollection implements MyIterable {
    private List<Node> nodes;

    public NodeCollection() {
        this.nodes = new ArrayList<>();
    }

    public NodeCollection(List<Node> nodes) {
        this.nodes = nodes;
    }

    public void addNodes(Node... nodes) {
        this.nodes.addAll(Arrays.asList(nodes));
    }

    public int getSize() {
        return nodes.size();
    }

    public Node getNodeByIndex(int i) {
        if (i < 0 || i >= getSize()) {
            throw new IllegalArgumentException("i can't be = " + i);
        }

        return nodes.get(i);
    }

    @Override
    public AbstractNodeIterator getOddIterator() {
        return new OddIterator(this);
    }

    @Override
    public AbstractNodeIterator getReverseIterator() {
        return new ReverseIterator(this);
    }
}
