package data_structures.chapter8_recursion_and_dynamic_programming.Coderbyte_DP_course.resursive_approach;

/**
 * The function should return the n-th number of the Fibonacci sequence
 */
public class P1_FibMemoization {
    //recursive approach
    //time complexity ~ O(2^n)
    //space complexity ~ O(n)
    public int fib1(int n) {
        //base case
        if (n <= 2) return 1;

        return fib1(n - 1) + fib1(n-2);
    }

    //recursive approach + memoization
    //time complexity ~ O(n)
    //space complexity ~ O(n)
    public int fib2(int n) {
        int[] memo = new int[n+1];

        return fib2(n, memo);
    }

    public int fib2(int n, int[] memo) {
        //base case
        if (n <= 2) return 1;

        if (memo[n] > 0) {
            return memo[n];
        } else {
            //else - calculate the value, store to memo and return
            memo[n] = fib2(n - 1, memo) + fib2(n - 2, memo);
            return memo[n];
        }
    }
}
