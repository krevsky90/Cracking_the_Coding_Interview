package solving_techniques.p15_0or1_Knapsack;

import java.util.Arrays;

/**
 * 2578. Split With Minimum Sum
 * https://leetcode.com/problems/split-with-minimum-sum
 *
 * Given a positive integer num, split it into two non-negative integers num1 and num2 such that:
 *
 * The concatenation of num1 and num2 is a permutation of num.
 * In other words, the sum of the number of occurrences of each digit in num1 and num2 is equal to the number of occurrences of that digit in num.
 * num1 and num2 can contain leading zeros.
 * Return the minimum possible sum of num1 and num2.
 *
 * Notes:
 * It is guaranteed that num does not contain any leading zeros.
 * The order of occurrence of the digits in num1 and num2 may differ from the order of occurrence of num.
 *
 * Example 1:
 * Input: num = 4325
 * Output: 59
 * Explanation: We can split 4325 so that num1 is 24 and num2 is 35, giving a sum of 59. We can prove that 59 is indeed the minimal possible sum.
 *
 * Example 2:
 * Input: num = 687
 * Output: 75
 * Explanation: We can split 687 so that num1 is 68 and num2 is 7, which would give an optimal sum of 75.
 *
 * Constraints:
 * 10 <= num <= 10^9
 */
public class SplitWithMinimumSum {
    /**
     * KREVSKY SOLUTION:
     * idea:
     * 1) sort digits
     * 2) skip '0'
     * 3) distribute digits evenly into two buckets (i.e. SBs), starting from the minimum digit (to have min num1 and num2)
     *
     * time to solve ~ 10 mins
     * time ~ O(n*logn), where n - the length of num
     * space ~ O(n)
     *
     * 2 attempts:
     * - wrote "i < numChars" instead of "i < numChars.length"
     */
    public int splitNum(int num) {
        char[] numChars = Integer.toString(num).toCharArray();
        Arrays.sort(numChars);

        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();

        for (int i = 0; i < numChars.length; i++) {
            if (numChars[i] == 0) continue;

            if (i % 2 == 1) {
                sb1.append(numChars[i]);
            } else {
                sb2.append(numChars[i]);
            }
        }

        Integer num1 = new Integer(sb1.toString());
        Integer num2 = new Integer(sb2.toString());
        return num1 + num2;
    }
}
