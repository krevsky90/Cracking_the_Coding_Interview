package solving_techniques.p17_FibonacciNumbers;

/**
 * https://www.designgurus.io/course-play/grokking-dynamic-programming/doc/637f466266312fdb1061848d
 *
 * Write a function to calculate the nth Fibonacci number.
 *
 * Fibonacci numbers are a series of numbers in which each number is the sum of the two preceding numbers.
 * First few Fibonacci numbers are: 0, 1, 1, 2, 3, 5, 8, ...
 *
 */
public class FibonacciNumbers {
    public static void main(String[] args) {
        int n = 10;
        System.out.println(findNthFibonacciNumberRecursively(n));
        System.out.println(findNthFibonacciNumberIteratively(n));

    }

    /**
     * KREVSKY SOLUTION:
     * time to solve 2 min
     * time ~ O(n)
     * space ~ O(1), if we do not consider java memory stack, O(n) - if consider
     * 1 attempt
     * NOTE: can be optimized by memoization (memo[] that has all previous Fib numbers OR only two last numbers (better)
     */
    public static int findNthFibonacciNumberRecursively(int n) {
        if (n == 0) {
            return 0;
        } else if (n <= 2) {
            return 1;
        } else {
            return findNthFibonacciNumberRecursively(n - 1) + findNthFibonacciNumberRecursively(n - 2);
        }
    }

    /**
     * KREVSKY SOLUTION:
     * time to solve 10 mins
     * time ~ O(n)
     * space ~ O(1)
     * 1 attempt
     */
    public static int findNthFibonacciNumberIteratively(int n) {
        if (n == 0) {
            return 0;
        } else if (n <= 2) {
            return 1;
        } else {
            int k1 = 1;
            int k2 = 1;
            int result = 0;
            for (int i = 3; i <= n; i++) {
                result = k1 + k2;
                k2 = k1;
                k1 = result;
            }
            return result;
        }
    }
}
