package java_learning.patterns.behavioral.iterator.several_simple_iterators;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Node> nodes = List.of(new Node("n1"), new Node("n2"), new Node("n3"), new Node("n4"), new Node("n5"));
        NodeCollection collection = new NodeCollection(nodes);

        AbstractNodeIterator oddIterator = collection.getOddIterator();
        AbstractNodeIterator reverseIterator = collection.getReverseIterator();

        while (oddIterator.hasNext()) {
            System.out.println(oddIterator.next());
        }

        System.out.println("------------------------");

        while (reverseIterator.hasNext()) {
            System.out.println(reverseIterator.next());
        }
    }
}
