package data_structures.chapter1_arrays_n_strings.amazon_igotanoffer.medium_strings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 17. Letter Combinations of a Phone Number (medium)
 * https://leetcode.com/problems/letter-combinations-of-a-phone-number/
 * OR
 * https://igotanoffer.com/blogs/tech/string-interview-questions
 *
 * #Company: Airbnb Amazon Apple Atlassian Bloomberg Dropbox eBay Facebook Google caMorgan Lyft Microsoft Morgan Stanley Nutanix Oracle Pinterest Quip (Salesforce) Roblox Salesforce Uber VMware Walmart Labs Yahoo
 *
 * <p>
 * Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent. Return the answer in any order.
 * <p>
 * A mapping of digits to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.
 * <p>
 * Example 1:
 * Input: digits = "23"
 * Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
 * <p>
 * Example 2:
 * Input: digits = ""
 * Output: []
 * <p>
 * Example 3:
 * Input: digits = "2"
 * Output: ["a","b","c"]
 * <p>
 * Constraints:
 * 0 <= digits.length <= 4
 * digits[i] is a digit in the range ['2', '9'].
 */
public class Problem2_4_LetterCombinationsOfPhoneNumber {
    /**
     * KREVSKY SOLUTION
     * idea ~ recursion
     * OR
     * we can use backtracking and StringBuilder instead of 'prefix' string as described here
     *      https://www.youtube.com/watch?v=HEFawmFMuIY&list=PLUPSMCjQ-7od5IVz8ug6D-apxFLkDTsoy&index=25
     *
     * time to solve ~ 20 mins of thinking + 18 with debugging
     *
     * time ~ O(N * 4^N). where N - number of different digits (i.e. 2,3,..9 => 8)
     * we have 'N*' since we build prefix for each combination adding N digits to it
     *
     * space ~ O(N) if we don't count stack space. otherwise - N*4^N
     *
     * 3 attempts (typos)
     *
     * BEATS ~ 37%
     */
    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits == null || digits.isEmpty()) return result;

        Map<Character, String> map = new HashMap<>();
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");

        String prefix = "";
        int pos = 0;
        letterCombinations(map, result, prefix, pos, digits);

        return result;
    }

    //digits = 23
    //digits.size() = 2
    //prefix = ""
    //curNum = '2'
    //letters = "abc"
    //pos = 0
    //curL = 'a'
    public void letterCombinations(Map<Character, String> map, List<String> result, String prefix, int pos, String digits) {
        if (pos >= digits.length()) {
            result.add(prefix);
            return;
        }
        Character curNum = digits.charAt(pos);
        char[] letters = map.get(curNum).toCharArray();
        for (char curL : letters) {
            letterCombinations(map, result, prefix + curL, pos + 1, digits);
        }
    }
}
