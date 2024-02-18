package solving_techniques.p3_FastAndSlowPointers;

import java.util.HashSet;

/**
 * https://interviewnoodle.com/grokking-leetcode-a-smarter-way-to-prepare-for-coding-interviews-e86d5c9fe4e1
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/63911273cf5aba4f2f70b5fe
 * OR
 * 202. Happy Number
 * https://leetcode.com/problems/happy-number
 *
 * Write an algorithm to determine if a number n is happy.
 *
 * A happy number is a number defined by the following process:
 *
 * Starting with any positive integer, replace the number by the sum of the squares of its digits.
 * Repeat the process until the number equals 1 (where it will stay),
 * or it loops endlessly in a cycle which does not include 1.
 * Those numbers for which this process ends in 1 are happy.
 * Return true if n is a happy number, and false if not.
 *
 * Example 1:
 * Input: n = 19
 * Output: true
 * Explanation:
 * 1^2 + 9^2 = 82
 * 8^2 + 2^2 = 68
 * 6^2 + 8^2 = 100
 * 1^2 + 0^2 + 0^2 = 1
 * Example 2:
 *
 * Input: n = 2
 * Output: false
 *
 * Constraints:
 *
 * 1 <= n <= 2^31 - 1
 *
 */
public class HappyNumber {
    public static void main(String[] args) {
        int n1 = 19;
        boolean is1 = isHappy(n1);
        System.out.println(is1);
    }

    //Solution #1: using slow and fast pointers
    public static boolean isHappy(int n) {
        if (n == 1) return true;
        int slow = sumOfSquaredDigits(n);
        int fast = sumOfSquaredDigits(sumOfSquaredDigits(n));

        while (slow != fast) {
            slow = sumOfSquaredDigits(slow);
            fast = sumOfSquaredDigits(sumOfSquaredDigits(fast));
        }

        //if we reach this row of code => we escaped from while loop. We could do this in 2 cases:
        // 1) slow = fast = 1 ("happy" loop)
        // 2) slow = fast != 1 ("bad" loop)
        return slow == 1;
    }

    //Solution #2: this problem also can be solved by HashSet
    //https://leetcode.com/problems/happy-number/solutions/4226946/java-hashset-1ms-beats-83-13/
    public static boolean isHappyByHashSet(int n) {
        if (n == 1) return true;

        HashSet<Integer> hashSet = new HashSet<>();

        int temp = n;
        while(!hashSet.contains(temp)) {
            hashSet.add(temp);
            temp = sumOfSquaredDigits(temp);
            if (temp == 1) return true;
        }

        return false;
    }

    private static int sumOfSquaredDigits(int n) {
        int result = 0;
        while (n > 0) {
            int digit = n % 10;
            result += digit*digit;
            n = n / 10;
        }

        return result;
    }


}
