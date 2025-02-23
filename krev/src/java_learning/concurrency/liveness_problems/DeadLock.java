package java_learning.concurrency.liveness_problems;

public class DeadLock {
    /**
     * idea:
     * there are 2 locks: instance of class A and instance of class B
     * t1 takes 'a' lock  when calls a.method1A
     *      t1 is trying to take 'b' lock to call b.methodHelperB() inside method1A, BUT 'b' is already taken by t2
     * t2 takes 'b' lock when calls b.method1B
     *      t2 is trying to take 'a' lock to call a.methodHelperA() inside method1AB, BUT 'a' is already taken by t1
     *
     */
    public static void main(String[] args) throws InterruptedException {
        A a = new A();
        B b = new B();

        Thread t1 = new Thread(() -> a.method1A(b));
        Thread t2 = new Thread(() -> b.method1B(a));

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("FINISH");
    }
    static class A {
        public synchronized void method1A(B b) {
            System.out.println("method1A");
            b.methodHelperB();
        }

        public synchronized void methodHelperA() {
            System.out.println("methodHelperA");
        }

    }
    static class B {
        public synchronized void method1B(A a) {
            System.out.println("method1B");
            a.methodHelperA();
        }

        public synchronized void methodHelperB() {
            System.out.println("methodHelperB");
        }
    }
}
