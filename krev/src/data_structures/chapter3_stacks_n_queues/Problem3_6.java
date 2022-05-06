package data_structures.chapter3_stacks_n_queues;

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
public class Problem3_6 {
    public static void main(String[] args) {
        Problem3_6_DataStructureKrev data = new Problem3_6_DataStructureKrev();
        System.out.println(data.dequeueCat());
        System.out.println(data.dequeueDog());
        System.out.println(data.dequeueAny());
        data.enqueue(new Problem3_6_DataStructureKrev.MyNode(Problem3_6_DataStructureKrev.CAT_TYPE, 1));
        data.enqueue(new Problem3_6_DataStructureKrev.MyNode(Problem3_6_DataStructureKrev.CAT_TYPE, 2));
        System.out.println(data.dequeueDog());
        System.out.println(data.dequeueAny());
        System.out.println(data.dequeueAny());
        data.enqueue(new Problem3_6_DataStructureKrev.MyNode(Problem3_6_DataStructureKrev.CAT_TYPE, 1));
        data.enqueue(new Problem3_6_DataStructureKrev.MyNode(Problem3_6_DataStructureKrev.CAT_TYPE, 2));
        data.enqueue(new Problem3_6_DataStructureKrev.MyNode(Problem3_6_DataStructureKrev.DOG_TYPE, 3));
        data.enqueue(new Problem3_6_DataStructureKrev.MyNode(Problem3_6_DataStructureKrev.CAT_TYPE, 4));
        data.enqueue(new Problem3_6_DataStructureKrev.MyNode(Problem3_6_DataStructureKrev.DOG_TYPE, 5));
        System.out.println(data.dequeueDog());

        System.out.println("--------------------------------------");
        Problem3_6_AnimalQueue aq = new Problem3_6_AnimalQueue();
        aq.enqueue(new Problem3_6_AnimalQueue.Dog("d1"));
        aq.enqueue(new Problem3_6_AnimalQueue.Dog("d2"));
        aq.enqueue(new Problem3_6_AnimalQueue.Cat("c3"));
        aq.enqueue(new Problem3_6_AnimalQueue.Dog("d4"));
        aq.enqueue(new Problem3_6_AnimalQueue.Cat("c5"));

        System.out.println(aq.dequeueCats());
        System.out.println(aq.dequeueDogs());

    }
}
