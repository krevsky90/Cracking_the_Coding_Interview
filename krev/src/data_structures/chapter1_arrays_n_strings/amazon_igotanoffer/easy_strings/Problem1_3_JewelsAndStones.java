package data_structures.chapter1_arrays_n_strings.amazon_igotanoffer.easy_strings;

import java.util.HashSet;

/**
 * 771. Jewels and Stones (easy)
 * https://leetcode.com/problems/jewels-and-stones
 * OR
 * https://igotanoffer.com/blogs/tech/string-interview-questions
 *
 * #Company: Adobe Alibaba Amazon Apple Baidu Facebook Google Yahoo Yandex
 *
 * You're given strings jewels representing the types of stones that are jewels,
 *      and stones representing the stones you have. Each character in stones is a type of stone you have.
 * You want to know how many of the stones you have are also jewels.
 *
 * Letters are case sensitive, so "a" is considered a different type of stone from "A".
 *
 * Example 1:
 * Input: jewels = "aA", stones = "aAAbbbb"
 * Output: 3
 *
 * Example 2:
 * Input: jewels = "z", stones = "ZZ"
 * Output: 0
 *
 * Constraints:
 * 1 <= jewels.length, stones.length <= 50
 * jewels and stones consist of only English letters.
 * All the characters of jewels are unique.
 */
public class Problem1_3_JewelsAndStones {
    /**
     * KREVSKY SOLUTION
     * time to solve ~ 8 mins
     * idea:
     * 1) traverse through jewels and form HashSet
     * 2) traverse through stones and for each element check (using HashSet -> O(1)) if it is jewel or not
     * time ~ O(J+S)
     * space ~ O(1)
     * 1 attempt
     *
     * BEATS ~ 58%
     */
    public int numJewelsInStones(String jewels, String stones) {
        HashSet setJ = new HashSet();
        for(char j : jewels.toCharArray()) {
            setJ.add(j);
        }

        int result = 0;
        for (char s : stones.toCharArray()) {
            if (setJ.contains(s)) {
                result++;
            }
        }

        return result;
    }
}
