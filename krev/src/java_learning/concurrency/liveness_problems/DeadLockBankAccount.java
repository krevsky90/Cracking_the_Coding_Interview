package java_learning.concurrency.liveness_problems;

/**
 * info: https://stackoverflow.com/questions/13326861/avoid-deadlock-example
 */
public class DeadLockBankAccount {
    private static int numberOfTransaction = 0;

    public static class Account {
        private double balance;
        final int id;

        public Account(int id, double balance) {
            this.balance = balance;
            this.id = id;
        }

        public void withdraw(double amount) {
            balance -= amount;
        }

        public void deposit(double amount) {
            balance += amount;
        }
    }

    public static void main(String[] args) {
        final Account a = new Account(1, 1000);
        final Account b = new Account(2, 300);
        Thread t1 = new Thread() {
            public void run() {
                for (int i = 0; i < 1000000; i++) {
                    transferDeadlock(a, b, 200);
                }
            }
        };
        Thread t2 = new Thread() {
            public void run() {
                for (int i = 0; i < 1000000; i++) {
                    transferDeadlock(b, a, 300);
                }
            }
        };
        t1.start();
        t2.start();
    }

    //will cause deadlock in some cases,
    // since t1 takes A object to transfer from A to B
    // and t2 takes B object to transfer from B to A
    public static void transferDeadlock(Account from, Account to, double amount) {
        System.out.println(numberOfTransaction++);
        synchronized (from) {
            synchronized (to) {
                from.withdraw(amount);
                to.deposit(amount);
            }
        }
    }

    /**
     * SOLUTION: sort accounts before putting to synch/synch blocks
     */
    public static void transferNoDeadlock(Account from, Account to, double amount) {
        System.out.println(numberOfTransaction++);
        Account a1 = from;
        Account a2 = to;
        if (a1.id < a2.id) {
            a1 = to;
            a2 = from;
        }
        synchronized (a1) {
            synchronized (a2) {
                a1.withdraw(amount);
                a2.deposit(amount);
            }
        }
    }
}
