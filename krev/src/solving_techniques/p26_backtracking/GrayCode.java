package solving_techniques.p26_backtracking;

import java.util.*;

/**
 * 89. Gray Code
 * https://leetcode.com/problems/gray-code
 * <p>
 * An n-bit gray code sequence is a sequence of 2^n integers where:
 * <p>
 * Every integer is in the inclusive range [0, 2^n - 1],
 * The first integer is 0,
 * An integer appears no more than once in the sequence,
 * The binary representation of every pair of adjacent integers differs by exactly one bit, and
 * The binary representation of the first and last integers differs by exactly one bit.
 * Given an integer n, return any valid n-bit gray code sequence.
 * <p>
 * Example 1:
 * Input: n = 2
 * Output: [0,1,3,2]
 * Explanation:
 * The binary representation of [0,1,3,2] is [00,01,11,10].
 * - 00 and 01 differ by one bit
 * - 01 and 11 differ by one bit
 * - 11 and 10 differ by one bit
 * - 10 and 00 differ by one bit
 * [0,2,3,1] is also a valid gray code sequence, whose binary representation is [00,10,11,01].
 * - 00 and 10 differ by one bit
 * - 10 and 11 differ by one bit
 * - 11 and 01 differ by one bit
 * - 01 and 00 differ by one bit
 * Example 2:
 * Input: n = 1
 * Output: [0,1]
 * <p>
 * Constraints:
 * <p>
 * 1 <= n <= 16
 */
public class GrayCode {
    public static void main(String[] args) {
        int n = 2;
        System.out.println(new GrayCode().grayCode(n));
    }

    /**
     * KREVSKY SOLUTION (#1):
     * time to solve ~ 30 mins
     * <p>
     * 2 attempts:
     * - need to store the sequence of added elements + need to check contains in O(1) => use LinkedHashSet
     * <p>
     * yes, it causes Time Limit Exceeded, but fast solution is not related to Backtracking - it is just about generating gray code numbers. See SOLUTION #2
     */
    public List<Integer> grayCode(int n) {
        LinkedHashSet<Integer> tempSet = new LinkedHashSet<>();
        tempSet.add(0);

        boolean res = helper(n, tempSet, 0);
        if (res) {
            return new ArrayList<>(tempSet);
        } else {
            return null;    //should not happen
        }
    }

    private boolean helper(int n, Set<Integer> tempSet, int lastInteger) {
        //stop condition:
        if (tempSet.size() == Math.pow(2, n)) {
            //The binary representation of the first and last integers differs by exactly one bit.
            return isOneBitDifferent(0, lastInteger);
        }

        for (int i = 1; i < Math.pow(2, n); i++) {
            //An integer appears no more than once in the sequence,
            //The binary representation of every pair of adjacent integers differs by exactly one bit
            if (!tempSet.contains(i) && isOneBitDifferent(lastInteger, i)) {
                tempSet.add(i);
                if (helper(n, tempSet, i)) {
                    return true;
                }
                tempSet.remove(i);
            }
        }

        return false;
    }

    private boolean isOneBitDifferent(int a, int b) {
        int xor = a ^ b;    //if it has only 1 bit, then it is power of 2
        while (xor > 1) {
            if (xor % 2 == 1) return false;
            xor = xor / 2;
        }
        return true;
    }

    /**
     * SOLUTION #2:
     * info:
     * https://leetcode.com/problems/gray-code/solutions/3433391/c-java-remember-this-formula/
     * <p>
     * Explanation
     * It is not very intuitive to come up with a solution for this problem in an interview if you haven't solved this already.
     * But there is a simple formula/ technique to remember to solve this one without much hassle !!
     * <p>
     * If n = 0 => {0}
     * If n = 1 => {0,1} {0 , 0 + pow(2,0)}
     * If n = 2 => {0,1,3,2} {0 , 1 , 1 + pow(2,1) , 0 + pow(2,1)}
     * If n = 3 => {0,1,3,2,6,7,5,4} {0 , 1 , 3 , 2 , 2 + pow(2,2) , 3 + pow(2,2) , 1 + pow(2,2) , 0 + pow(2,2)}
     * <p>
     * Here you can observe a pattern !!
     * <p>
     * At each step where we're supposed to calculate the gray code of i ,
     * Reverse the gray code list of i-1 and add the value pow(2,i-1) to all the elements of the reversed list.
     * Append the modified and reversed list to the (i-1) list.
     *
     * BEATS = 27%
     */
    public List<Integer> grayCode2(int n) {
        List<Integer> result = new ArrayList<>((int) Math.pow(2, n));
        result.add(0);
        for (int i = 1; i <= n; i++) {
            List<Integer> tempList = new ArrayList<>(result);
            Collections.reverse(tempList);
            for (int v : tempList) {
                int delta = (int) Math.pow(2, i - 1);
                result.add(v + delta);
            }
        }

        return result;
    }
}
