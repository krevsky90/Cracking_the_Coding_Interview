package data_structures.chapter8_recursion_and_dynamic_programming;

import java.util.Arrays;

/**
 * p.146
 * 8.1 Triple Step:
 * A child is running up a staircase with n steps and can hop either 1 step, 2 steps, or 3
 * steps at a time. Implement a method to count how many possible ways the child can run up the
 * stairs.
 * Hints: #152, #178, #217, #237, #262, #359
 * <p>
 * ASSUMPTION/VALIDATION:
 */
public class Problem8_1 {
    public static void main(String[] args) {
        System.out.println(countPaths(5));
    }

    /**
     * KREVSKY SOLUTION: memorization
     * time ~ O(n)
     * мой вариант более экономный, т.к. юзает только 3 переменных - т.е. O(1). ведь весь массив не нужен
     */
    public static int countPathsOptimized(int n) {
        if (n <= 0) return 0;
        if (n == 1) return 1;
        if (n == 2) return 2;
        if (n == 3) return 4;

        int countNminus1 = 4;
        int countNminus2 = 2;
        int countNminus3 = 1;

        int i = 4;
        while (i < n) {
            int newCount = countNminus1 + countNminus2 + countNminus3;    //7
            countNminus3 = countNminus2;    //2
            countNminus2 = countNminus1;    //4
            countNminus1 = newCount;        //7
            i++;
        }

        //return countPaths(n-1) + countPaths(n-2) + countPaths(n-3);
        return countNminus1 + countNminus2 + countNminus3;
    }

    /**
     * KREVSKY SOLUTION
     * time ~ O(3^n)
     */
    public static int countPaths(int n) {
        if (n <= 0) return 0;
        if (n == 1) return 1;
        if (n == 2) return 2;
        if (n == 3) return 4;

        return countPaths(n - 1) + countPaths(n - 2) + countPaths(n - 3);
    }

    /**
     * ORIGINAL SOLUTION
     * time ~ O(3^n)
     */
    public static int countWays(int n) {
        if (n < 0) {
            return 0;
        } else if (n == 0) {
            return 1;   //todo: это просто волевое решение! хотя если n = 0, то я бы сказал, что ответ д б = 0
        } else {
            return countWays(n - 1) + countWays(n - 2) + countWays(n - 3);
        }
    }

    /**
     * ORIGINAL SOLUTION - memorization
     * time ~ O(n)
     * Typically we use a HashMap<Integer, Integer> for a cache. In this case, the keys will be exactly 1
     * through n.lt's more compact to use an integer array.
     */
    public static int countWaysMemorization(int n) {
        int memo[] = new int[n + 1];
        Arrays.fill(memo, -1);
        return countWaysMemorization(n, memo);
    }

    private static int countWaysMemorization(int n, int memo[]) {
        if (n < 0) {
            return 0;
        } else if (n == 0) {
            return 1;   //todo: это просто волевое решение! хотя если n = 0, то я бы сказал, что ответ д б = 0
        } else if (memo[n] > -1) {
            return memo[n];
        } else {
            memo[n] = countWaysMemorization(n - 1, memo) + countWaysMemorization(n - 2, memo) + countWaysMemorization(n - 3, memo);
            return memo[n];
        }
    }
}
