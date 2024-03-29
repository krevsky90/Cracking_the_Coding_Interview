package data_structures.chapter3_stacks_n_queues;

import java.util.LinkedList;
import java.util.List;

/**
 * p.111
 * Animal Shelter: An animal shelter, which holds only dogs and cats, operates on a strictly "first in, first
 * out" basis. People must adopt either the "oldest" (based on arrival time) of all animals at the shelter,
 * or they can select whether they would prefer a dog or a cat (and will receive the oldest animal of
 * that type). They cannot select which specific animal they would like. Create the data structures to
 * maintain this system and implement operations such as enqueue, dequeueAny, dequeueDog,
 * and dequeueCat. You may use the built-in Linked List data structure.
 * Hints: #22, #56, #63
 * <p>
 * ASSUMPTION/VALIDATION:
 */
public class Problem3_6_DataStructureKrev {
    public static final String DOG_TYPE = "dog";
    public static final String CAT_TYPE = "cat";
    private LinkedList<MyNode> list = new LinkedList<>();

    public void enqueue(MyNode node) {
        list.addLast(node);
    }

    public MyNode dequeueAny() {
        if (list.isEmpty()) {
            return null;
        }
        return list.remove();
    }

    public MyNode dequeueDog() {
        return dequeueByType(DOG_TYPE);
    }

    public MyNode dequeueCat() {
        return dequeueByType(CAT_TYPE);
    }

    private MyNode dequeueByType(String type) {
        for (MyNode node : list) {
            if (type.equals(node.type)) {
                list.remove(node);
                return node;
            }
        }
        //there are no objects with given type
        return null;
    }

    static class MyNode {
        private String type;
        private int id;

        public MyNode(String type, int id) {
            this.type = type;
            this.id = id;
        }

        public String toString() {
            return id + "(" + type + ")";
        }
    }
}