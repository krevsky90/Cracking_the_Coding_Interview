package solving_techniques.p11_ModifiedBinarySearch;

/**
 * https://www.tryexponent.com/practice/prepare/root-of-number
 * <p>
 * Many times, we need to re-implement basic functions without using any standard library functions already implemented.
 * For example, when designing a chip that requires very little memory space.
 * <p>
 * In this question we’ll implement a function root that calculates the n’th root of a number.
 * The function takes a nonnegative number x and a positive integer n,
 * and returns the positive n’th root of x within an error of 0.001
 * (i.e. suppose the real root is y, then the error is: |y-root(x,n)| and must satisfy |y-root(x,n)| < 0.001).
 * <p>
 * Don’t be intimidated by the question.
 * While there are many algorithms to calculate roots that require prior knowledge in numerical analysis (some of them are mentioned here),
 * there is also an elementary method which doesn’t require more than guessing-and-checking. Try to think more in terms of the latter.
 * <p>
 * Make sure your algorithm is efficient, and analyze its time and space complexities.
 * <p>
 * Examples:
 * input:  x = 7, n = 3
 * output: 1.913
 * <p>
 * input:  x = 9, n = 2
 * output: 3
 */
public class RootOfNumber {
    /**
     * ORIGINAL SOLUTION:
     * info: https://www.tryexponent.com/practice/prepare/root-of-number
     * <p>
     * idea:
     * 1) determine bounds where we will find the answer
     * 2) use binary search
     * <p>
     * time ~ O(log x)
     * space ~ O(1)
     */
    static double power(double base, int exponent) {
        double result = 1.0;
        for (int i = 0; i < exponent; i++) {
            result *= base;
        }
        return result;
    }

    static double root1(double x, int n) {
        if (x == 0) {
            return 0.0;
        }

        double lowerBound = 0.0;
        double upperBound = Math.max(1.0, x);
        double approxRoot = (upperBound + lowerBound) / 2.0;

        while ((upperBound - lowerBound) >= 0.001) {
            if (power(approxRoot, n) > x) {
                upperBound = approxRoot;
            } else if (power(approxRoot, n) < x) {
                lowerBound = approxRoot;
            } else {
                break;
            }

            approxRoot = (upperBound + lowerBound) / 2.0;
        }

        return approxRoot;
    }

    /**
     * KREVSKY SOLUTION #1
     * the same idea, but another condition
     */
    static double rootKrev1(double x, int n) {
        if (x == 0) return 0;
        // x < 1 => x < y < 1
        // x >= 1 > 1 <= y <= x
        double low = Math.min(x, 1);
        double high = Math.max(x, 1);

        double mid = 0.0;
        while (low < high) {
            mid = low + (high - low) / 2.0;
            if (Math.pow(mid, n) - x > 0.001) {
                high = mid;
            } else if (x - Math.pow(mid, n) > 0.001) {
                low = mid;
            } else {
                break;
            }
        }

        return mid;
    }

    /**
     * KREVSKY SOLUTION #2
     * the same idea, but another condition
     */
    static double rootKrev2(double x, int n) {
        if (x == 0) return 0;

        double low = Math.min(x, 1);
        double high = Math.max(x, 1);

        double mid = 0.0;
        while (Math.abs(Math.pow(mid, n) - x) >= 0.001) {
            mid = low + (high - low)/2.0;
            if (Math.pow(mid, n) - x > 0.001) {
                high = mid;
            } else if (x - Math.pow(mid, n) > 0.001) {
                low = mid;
            } else {
                break;
            }
        }

        return mid;
    }

}
