package data_structures.chapter3_stacks_n_queues;

import java.util.LinkedList;

public class Problem3_6_AnimalQueue {
    /**
     * SOLUTION:
     * use separate queues for dogs and
     * cats, and to place them within a wrapper class called Anima lQueue. We then store some sort of timestamp
     * to mark when each animal was enqueued. When we call dequeueAny, we peek at the heads of both the
     * dog and cat queue and return the oldest.
     */

    private LinkedList<Dog> dogs = new LinkedList<>();
    private LinkedList<Cat> cats = new LinkedList<>();
    private int order = 0;  //acts as timestamp

    public void enqueue(Animal animal) {
        animal.setOrder(order);
        order++;

        if (animal instanceof Dog) dogs.addLast((Dog) animal);
        else if (animal instanceof Cat) cats.addLast((Cat) animal);
    }

    public Animal dequeueAny() {
        if (dogs.isEmpty()) {
            return dequeueCats();
        } else if (cats.isEmpty()) {
            return dequeueDogs();
        }

        //if cat older than dog
        if (dogs.peek().getOrder() > cats.peek().getOrder()) {
            return dequeueCats();
        } else {
            return dequeueDogs();
        }
    }

    public Animal dequeueCats() {
        return cats.poll();
    }

    public Animal dequeueDogs() {
        return dogs.poll();
    }

    static abstract class Animal {
        private int order;
        protected String name;

        public Animal(String name) {
            this.name = name;
        }

        public void setOrder(int order) {
            this.order = order;
        }

        public int getOrder() {
            return this.order;
        }

        public String toString() {
            return name;
        }
    }

    static class Dog extends Animal {
        public Dog(String name) {
            super(name);
        }
    }

   static class Cat extends Animal {
        public Cat(String name) {
            super(name);
        }
    }
}
