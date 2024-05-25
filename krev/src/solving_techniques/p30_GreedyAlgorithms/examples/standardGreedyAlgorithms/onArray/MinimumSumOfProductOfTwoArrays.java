package solving_techniques.p30_GreedyAlgorithms.examples.standardGreedyAlgorithms.onArray;

/**
 * https://www.geeksforgeeks.org/minimum-sum-product-two-arrays/
 * OR
 * looks like https://leetcode.com/problems/minimize-product-sum-of-two-arrays (blocked)
 *
 * Find the minimum sum of Products of two arrays of the same size, given that k modifications are allowed on the first array.
 * In each modification, one array element of the first array can either be increased or decreased by 2.
 *
 * Examples:
 * Input : a[] = {1, 2, -3}
 *         b[]  = {-2, 3, -5}
 *            k = 5
 * Output : -31
 * Explanation:
 * Here n = 3 and k = 5.
 * So, we modified a[2], which is -3 and
 * increased it by 10 (as 5 modifications
 * are allowed).
 * Final sum will be :
 * (1 * -2) + (2 * 3) + (7 * -5)
 *    -2    +    6    -    35
 *              -31
 * (which is the minimum sum of the array
 * with given conditions)
 *
 * Input : a[] = {2, 3, 4, 5, 4}
 *         b[] = {3, 4, 2, 3, 2}
 *         k = 3
 * Output : 25
 * Explanation:
 * Here, total numbers are 5 and total
 * modifications allowed are 3. So, modify
 * a[1], which is 3 and decreased it by 6
 * (as 3 modifications are allowed).
 * Final sum will be :
 * (2 * 3) + (-3 * 4) + (4 * 2) + (5 * 3) + (4 * 2)
 *    6    –    12    +    8    +    15   +    8
 *                         25
 * (which is the minimum sum of the array with
 * given conditions)
 */
public class MinimumSumOfProductOfTwoArrays {
    public static void main(String[] args) {
        int[] a1 = {1, 2, -3};
        int[] b1 = {-2, 3, -5};
        int n1 = 3;
        int k1 = 5;
        System.out.println(minProduct(a1, b1, n1, k1));

        int[] a2 = {2, 3, 4, 5, 4};
        int[] b2 = {3, 4, 2, 3, 2};
        int n2 = 5;
        int k2 = 3;
        System.out.println(minProduct(a2, b2, n2, k2));
    }

    /**
     * KREVSKY SOLUTION:
     * differs from https://www.geeksforgeeks.org/minimum-sum-product-two-arrays/,
     * can't check my solution on leetcode, but at least it is correct for simple examples
     *
     * my idea:
     * 1) find the maximum Math.abs(b[i])
     * 2) consider that if we change a[i], then our influence will be the most valuable =>
     * we should apply all k operations to the same a[i]:
     * if b[i] < 0, then increase a[i] k times
     * if b[i] > 0, then decrease a[i] k times
     *
     * time to solve ~ 25 mins
     *
     * time ~ O(n)
     * space ~ O(1)
     *
     * 1 attempt
     */
    public static int minProduct(int a[], int b[], int n, int k) {
        int idx = -1;
        int maxAbs = 0;
        for (int i = 0; i < b.length; i++) {
            if (Math.abs(b[i]) > maxAbs) {
                maxAbs = Math.abs(b[i]);
                idx = i;
            }
        }

        if (b[idx] > 0) {
            a[idx] -= 2*k;
        } else {
            a[idx] += 2*k;
        }

        int result = 0;
        for (int i = 0; i < a.length; i++) {
            result += a[i]*b[i];
        }
        return result;
    }
}
