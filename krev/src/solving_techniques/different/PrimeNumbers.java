package solving_techniques.different;

import java.util.*;

/**
 * https://www.tryexponent.com/practice/prepare/prime-numbers
 */
public class PrimeNumbers {
    /**
     * KREVSKY SOLUTION:
     * time to solve ~ 10+ mins
     * <p>
     * time ~ O(n^3/2)
     */
    public static List<Integer> findPrimes(int n) {
        if (n == 1) return new ArrayList<>();
        if (n == 2) return Arrays.asList(2);

        List<Integer> res = new ArrayList<>();
        for (int c = 2; c <= n; c++) {
            if (isPrime(c)) {
                res.add(c);
            }
        }


        return res;
    }

    private static boolean isPrime(int c) {
        for (int d = 2; d <= Math.sqrt(c); d++) {
            if (c % d == 0) {
                return false;
            }
        }

        return true;
    }

    /**
     * Optimized solution: idea of interviewer
     */
    public static List<Integer> findPrimesOptimized(int n) {
        List<Integer> res = new ArrayList<>();
        for (int c = 2; c <= n; c++) {
            //idea: if c is dividable by some of stored prime number (which is obviously < c) => c is NOT prime
            boolean isPrime = true;
            for (int prime : res) {
                if (c % prime == 0) {
                    isPrime = false;
                    break;
                }
            }

            if (isPrime) {
                res.add(c);
            }
        }

        return res;
    }

    /**
     * Like Prime Sieve of Erastosthenes.
     */
    public static List<Integer> findPrimesErastosthenes(int n) {
        Set<Integer> nums = new HashSet<>();
        for (int i = 2; i <= n; i++) {
            nums.add(i);
        }

        for (int d = 2; d * d <= n; d++) {
            for (int j = d * d; j <= n; j += d) {
                nums.remove(j);
            }
        }

        return new ArrayList<Integer>(nums);
    }
}
