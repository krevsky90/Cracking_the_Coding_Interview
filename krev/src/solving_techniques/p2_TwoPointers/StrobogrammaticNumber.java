package solving_techniques.p2_TwoPointers;

import java.util.HashMap;
import java.util.Map;

/**
 * 246. Strobogrammatic Number (easy)  (locked)
 * https://leetcode.com/problems/strobogrammatic-number
 * <p>
 * #Company (20.02.2025): 0 - 6 months Meta 7 6 months ago Google 4 Uber 3
 * <p>
 * Given a string num which represents an integer, return true if num is a strobogrammatic number.
 * <p>
 * A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
 * <p>
 * Example 1:
 * Input: num = "69"
 * Output: true
 * <p>
 * Example 2:
 * Input: num = "88"
 * Output: true
 * <p>
 * Example 3:
 * Input: num = "962"
 * Output: false
 * <p>
 * Constraints:
 * 1 <= num.length <= 50
 * num consists of only digits.
 * num does not contain any leading zeros except for zero itself.
 */
public class StrobogrammaticNumber {
    /**
     * KREVSKY SOLUTION:
     * idea: 2 pointers + map of symmetric symbols
     * time to solve ~ 13 mins
     * <p>
     * 2 attempts:
     * - did not think that '1' is also symmetric like 8 or 0
     * <p>
     * BEATS ~ 100%
     */
    public boolean isStrobogrammatic(String num) {
        //0 -> 0
        //6 -> 9
        //9 -> 6
        //8 -> 8
        //60809
        //6889

        //time ~ O(n), n - num.length()
        //space ~ O(1)
        Map<Character, Character> map = new HashMap<>();
        map.put('0', '0');
        map.put('1', '1');
        map.put('6', '9');
        map.put('9', '6');
        map.put('8', '8');

        int left = 0;
        int right = num.length() - 1;
        while (left < right) {
            if (num.charAt(right) != map.getOrDefault(num.charAt(left), 'x')) return false;

            left++;
            right--;
        }

        //if num has odd length => left = right when while loop is broken
        if (left == right) {
            return num.charAt(left) == '0' || num.charAt(left) == '8' || num.charAt(left) == '1';
        } else {
            //i.e. left > right
            return true;
        }
    }
}
