package solving_techniques.p12_Bitwise_XOR;

import java.util.HashMap;
import java.util.Map;

/**
 * 50. Pow(x, n) (medium)
 * https://leetcode.com/problems/powx-n
 * <p>
 * #Company: Adobe Amazon Apple Asana Bloomberg Facebook Goldman Sachs Google LinkedIn Microsoft Morgan Stanley Oracle Uber Walmart Labs
 * <p>
 * Implement pow(x, n), which calculates x raised to the power n (i.e., xn).
 * <p>
 * Example 1:
 * Input: x = 2.00000, n = 10
 * Output: 1024.00000
 * <p>
 * Example 2:
 * Input: x = 2.10000, n = 3
 * Output: 9.26100
 * <p>
 * Example 3:
 * Input: x = 2.00000, n = -2
 * Output: 0.25000
 * Explanation: 2-2 = 1/22 = 1/4 = 0.25
 * <p>
 * Constraints:
 * -100.0 < x < 100.0
 * -2^31 <= n <= 2^31-1
 * n is an integer.
 * Either x is not zero or n > 0.
 * -10^4 <= xn <= 10^4
 */
public class Pow_x_n {
    /**
     * NOT SOLVED by myself
     * info:
     * https://www.youtube.com/watch?v=hPlI-M5Iu6A&list=PLUPSMCjQ-7od5IVz8ug6D-apxFLkDTsoy&index=56
     * idea:
     * use memoization and divide the problem by 2 (like top-down approach)
     * <p>
     * time to implement ~ 15 mins
     * time ~ O(logN)
     * space ~ O(logN)
     * <p>
     * BEATS ~ 6%
     */
    public double myPow(double x, int n) {
        if (n < 0) {
            n = Math.abs(n);
            x = 1 / x;
        }

        Map<Integer, Double> memo = new HashMap<>();

        return myPow(x, n, memo);
    }

    private double myPow(double x, int n, Map<Integer, Double> memo) {
        if (n == 0) return 1;
        if (n == 1) return x;

        if (memo.containsKey(n)) return memo.get(n);

        double multiplicator = n % 2 == 0 ? 1 : x;  //it is for cases when n is odd
        //note: the first call of myPow(x, n/2, memo) will store the calculated value to memo[n/2] cell
        //the second call of  myPow(x, n/2, memo) will just return the value from memo array
        double val = myPow(x, n / 2, memo) * myPow(x, n / 2, memo) * multiplicator;
        memo.put(n, val);
        return val;
    }

    /**
     * SOLUTION #2:
     */
    public static void main(String[] args) {
        double x = 2;
        int n = 5;
        Pow_x_n obj = new Pow_x_n();

        System.out.println(obj.myPow2(x, n));
    }

    public double myPow2(double x, int n) {
        return n >= 0 ? qpow(x, n) : 1 / qpow(x, -(long) n);
    }

    private double qpow(double x, long n) {
        double res = 1;
        while (n > 0) {
            if ((n & 1) == 1) {
                res = res * x;
            }
            x = x * x;  //each time we need to do "a^2": see example below:
            //3 = 11
            //5 = 101
            //res = 3 + 3^4 => to get 3^4 during 2 iterations in for-loop, we need to a = a^2 each time
            n = n >> 1;
        }
        return res;
    }
}