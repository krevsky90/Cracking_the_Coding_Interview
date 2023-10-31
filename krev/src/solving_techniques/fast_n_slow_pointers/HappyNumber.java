package solving_techniques.fast_n_slow_pointers;

import java.util.HashSet;

/**
 * https://interviewnoodle.com/grokking-leetcode-a-smarter-way-to-prepare-for-coding-interviews-e86d5c9fe4e1
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/63911273cf5aba4f2f70b5fe
 * OR
 * 202. Happy Number
 * https://leetcode.com/problems/happy-number/description/
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

        //if we reach this row of code => we escaped from while loop => a cycle of numbers exists => slow canNOT be = 1 => the number is not a happy numbe
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
