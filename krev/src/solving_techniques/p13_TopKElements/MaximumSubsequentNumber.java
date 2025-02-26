package solving_techniques.p13_TopKElements;

import java.util.*;

/**
 * https://leetcode.com/discuss/interview-experience/6084864/Google-L4-interview-expereince
 * <p>
 * Round 3:
 * Given a sequence S of N digits, find a subsequence of K digits such that the number formed by these K digits (in order) is the largest.
 * <p>
 * <p>
 * Get the largest number sequence
 * You have sequence of k digits
 * <p>
 * <p>
 * S = 4902, K = 2, answer = 92
 * S = 4902, K = 3, answer = 902
 * S = 142857, K = 1, answer = 8
 * S = 142857, K = 2, answer = 87
 * S = 142857, K = 4, answer = 4857
 */
public class MaximumSubsequentNumber {
    /**
     * KREVSKY SOLUTION:
     * idea:
     * DP: to take or not to take
     * time ~ 1h 50mins
     * ATTENTION!
     * NOT tested solution, but works properly at least for given examples
     */

    public int largestNumberSequence(int s, int k) {
        //	if (s = null || s.isEmpty() || k == 0) return 0;
        if (k < 0) throw new IllegalArgumentException("k is less than 0");

        List<Integer> digits = new ArrayList<>();
        while (s > 0) {
            digits.add(s % 10);
            s /= 10;
        }

        Collections.reverse(digits);

        String[][] memo = new String[digits.size() + 1][digits.size() + 1];
        //may be it is worth to use memoization based on pos and amount parameters

        String res = maxF(digits, 0, k);
        return Integer.valueOf(res);
    }

    private String maxF(List<Integer> digits, int pos, int amount) {
        if (pos == digits.size() && amount > 0) return "none";
        if (amount == 0) return "";

        String takeCase = maxF(digits, pos + 1, amount - 1);
        int takeResult = -1;
        if (!"none".equals(takeCase)) {
            takeCase = "" + digits.get(pos) + (takeCase.isEmpty() ? "" : takeCase);
            takeResult = Integer.valueOf(takeCase);
        }

        String notTakeCase = maxF(digits, pos + 1, amount);
        int notTakeResult = -1;
        if (!"none".equals(notTakeCase)) {
            notTakeResult = Integer.valueOf(notTakeCase.isEmpty() ? "0" : notTakeCase);
        }

        if (takeResult == -1 && notTakeResult == -1) {
            return "none";
        } else if (takeResult >= notTakeResult) {
            return takeCase;
        } else {
            return notTakeCase;
        }
    }

    public static void main(String[] args) {
        MaximumSubsequentNumber obj = new MaximumSubsequentNumber();
        int S1 = 4902, K1 = 2, answer1 = 92;
        int S2 = 4902, K2 = 3, answer2 = 902;
        int S3 = 142857, K3 = 1, answer3 = 8;
        int S4 = 142857, K4 = 2, answer4 = 87;
        int S5 = 142857, K5 = 4, answer5 = 4857;

        System.out.println(obj.largestNumberSequence(S1, K1));
        System.out.println(obj.largestNumberSequence(S2, K2));
        System.out.println(obj.largestNumberSequence(S3, K3));
        System.out.println(obj.largestNumberSequence(S4, K4));
        System.out.println(obj.largestNumberSequence(S5, K5));
    }
}
