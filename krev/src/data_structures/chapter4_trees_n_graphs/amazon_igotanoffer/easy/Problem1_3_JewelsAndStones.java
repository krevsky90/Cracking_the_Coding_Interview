package data_structures.chapter4_trees_n_graphs.amazon_igotanoffer.easy;

import java.util.HashSet;

/**
 * https://igotanoffer.com/blogs/tech/string-interview-questions
 * https://leetcode.com/problems/jewels-and-stones/solutions/113553/c-java-python-set-solution-o-j-s/
 *
 * You're given strings jewels representing the types of stones that are jewels, and stones representing the stones you have. Each character in stones is a type of stone you have. You want to know how many of the stones you have are also jewels.
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
     * O(J*S)
     */
    public int numJewelsInStonesKREV(String jewels, String stones) {
        char[] arr = jewels.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            stones = stones.replaceAll("" + arr[i], "1");
        }

        int result = 0;
        for(int j = 0; j < stones.length(); j++) {
            if (stones.charAt(j) == '1') {
                result++;
            }
        }

        return result;
    }

    /**
     * O(J+S)
     * The IDEA: search through HashSet is O(1) -> let's search letters from stones string in hashSet created from jewels
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
