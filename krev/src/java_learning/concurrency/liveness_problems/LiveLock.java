package java_learning.concurrency.liveness_problems;

public class LiveLock {
    static class Spoon {
        private Friend owner;

        public Spoon(Friend owner) {
            this.owner = owner;
        }

        public Friend getOwner() {
//            System.out.println("Thread: " + Thread.currentThread().getName() + ": Spoon#getF: will return " + f.name);
            return owner;
        }

        public void setOwner(Friend owner) {
//            System.out.println("Thread: " + Thread.currentThread().getName() + ": Spoon#setF: new value = " + f.name);
            this.owner = owner;
        }

        public void use() {
            System.out.println("Friend " + owner.name + " is using the spoon");
        }
    }

    static class Friend {
        private boolean isHungry = true;
        private String name;

        public Friend(String name) {
            this.name = name;
        }

        public synchronized void eatWithFriend(Friend otherFriend, Spoon spoon) {
            while (isHungry) {
                if (spoon.getOwner() == this) {
                    if (otherFriend.isHungry) {
                        System.out.println(name + ": please take the spoon, " + otherFriend.name);
                        spoon.setOwner(otherFriend);
                    } else {
                        spoon.use();
                        isHungry = false;
                        System.out.println("I'm not hungry");
                        spoon.setOwner(otherFriend);
                    }
                } else {
                    //NOTE: without this part we see only one line:
                    //Bob: please take the spoon, Alice
                    //and after that the program is hanging...
                    //todo: why?

                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Friend bob = new Friend("Bob");
        Friend alice = new Friend("Alice");

        Spoon spoon = new Spoon(bob);

        Thread bobThread = new Thread(() -> {
            bob.eatWithFriend(alice, spoon);
        }, "bobThread");

        Thread aliceThread = new Thread(() -> {
            alice.eatWithFriend(bob, spoon);
        }, "aliceThread");

        bobThread.start();
        aliceThread.start();

        bobThread.join();
        aliceThread.join();

        System.out.println("Main FINISH");
    }
}
