package solving_techniques.p1_SlidingWindow;

import java.util.HashMap;
import java.util.Map;

/**
 * 2516. Take K of Each Character From Left and Right
 * https://leetcode.com/problems/take-k-of-each-character-from-left-and-right/
 * <p>
 * You are given a string s consisting of the characters 'a', 'b', and 'c' and a non-negative integer k.
 * Each minute, you may take either the leftmost character of s, or the rightmost character of s.
 * <p>
 * Return the minimum number of minutes needed for you to take at least k of each character,
 * or return -1 if it is not possible to take k of each character.
 * <p>
 * Example 1:
 * Input: s = "aabaaaacaabc", k = 2
 * Output: 8
 * Explanation:
 * Take three characters from the left of s. You now have two 'a' characters, and one 'b' character.
 * Take five characters from the right of s. You now have four 'a' characters, two 'b' characters, and two 'c' characters.
 * A total of 3 + 5 = 8 minutes is needed.
 * It can be proven that 8 is the minimum number of minutes needed.
 * <p>
 * Example 2:
 * Input: s = "a", k = 1
 * Output: -1
 * Explanation: It is not possible to take one 'b' or 'c' so return -1.
 * <p>
 * Constraints:
 * 1 <= s.length <= 10^5
 * s consists of only the letters 'a', 'b', and 'c'.
 * 0 <= k <= s.length
 */
public class TakeKofEachCharacterFromLeftAndRight {
    /**
     * correct approach is Sliding Window
     * KREVSKY SOLUTION
     * time to solve ~ 45 mins
     *
     * 2 attempts:
     * - incorrect understanding of the problem => missed "AT LEAST" k letters
     */
    // aabaaaacaabc => [8, 2, 2]
    // k = 2
    // word <= [6,0,0]
    public int takeCharacters(String s, int k) {
        int[] arr = new int[3]; //0th element has amount of 'a' letters, 1st - of 'b' letters, 2nd - for 'c'
        char[] sArr = s.toCharArray();
        for (char c : sArr) {
            arr[c - 'a']++;
        }

        //cut k letters
        for (int i = 0; i < arr.length; i++) {
            arr[i] -= k;
            if (arr[i] < 0) return -1;
        }

        //if the problem can be solved (i.e. we have sufficient amount of each letter) then let's apply sliding window
        //to find minimum amount of elements to be removed,
        // we can find maximum length of the word that contains AT MOST the same amount of letters as it is in arr
        int maxLength = -1;
        int start = 0;

        for (int end = 0; end < sArr.length; end++) {
            arr[sArr[end] - 'a']--;

            while (hasNegativeElement(arr)) {
                arr[sArr[start] - 'a']++;
                start++;
            }

            maxLength = Math.max(maxLength, end - start + 1);
        }

        return s.length() - maxLength;
    }

    private boolean hasNegativeElement(int[] arr) {
        for (int a : arr) {
            if (a < 0) return true;
        }
        return false;
    }


    /**
     * KREVSKY SOLUTION:
     * not solved due to Time Limit Exceeded
     * could not implement memoization correctly (thought about memo[start][end]), so..
     * <p>
     * time to solve ~ 50-60 mins
     * a lot of attempts
     */
    // aabccbaacbc 222
    //     abccbaacbc 122
    //         bccbaacbc 022
    //            ccbaacbc 012
    //                 cbaacbc 011
    //                     baacbc 010
    //                         aacbc 000 -> return 0
    //                         baacb 01-1 -> return 1
    //                             aacb 00-1 -> return 0

    //                 ccbaacb 011
    //            bccbaacb 021

    //         abccbaacb 121
    //             bccbaacb 021
    //             abccbaac 111
    //     aabccbaacb 221
    public int takeCharactersKrevBacktracking(String s, int k) {
        Map<Character, Integer> letterToAmountMap = new HashMap<>();
        letterToAmountMap.put(new Character('a'), k);
        letterToAmountMap.put(new Character('b'), k);
        letterToAmountMap.put(new Character('c'), k);

        int start = 0;
        int end = s.length() - 1;

        int result = takeCharacters(s, start, end, letterToAmountMap);

        return result == Integer.MAX_VALUE ? -1 : result;
    }

    public int takeCharacters(String s, int start, int end, Map<Character, Integer> letterToAmountMap) {
        if (isMapEmpty(letterToAmountMap)) {
            return 0;
        } else if (start > end) {
            //it means that map is not empty
            return Integer.MAX_VALUE;
        } else {
            //backtracking for 2 cases:
            //1) take leftmost letter
            Character leftChar = s.charAt(start);
            letterToAmountMap.put(leftChar, letterToAmountMap.get(leftChar) - 1);
            int leftResult = takeCharacters(s, start + 1, end, letterToAmountMap);
            //revert the changes
            letterToAmountMap.put(leftChar, letterToAmountMap.get(leftChar) + 1);

            //2) take rightmost letter
            Character rightChar = s.charAt(end);
            letterToAmountMap.put(rightChar, letterToAmountMap.get(rightChar) - 1);
            int rightResult = takeCharacters(s, start, end - 1, letterToAmountMap);
            //revert the changes
            letterToAmountMap.put(rightChar, letterToAmountMap.get(rightChar) + 1);

            if (leftResult == Integer.MAX_VALUE && rightResult == Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            } else {
                return 1 + Math.min(leftResult, rightResult);
            }
        }
    }

    private boolean isMapEmpty(Map<Character, Integer> letterToAmountMap) {
        for (Map.Entry<Character, Integer> e : letterToAmountMap.entrySet()) {
            if (e.getValue() > 0) {
                return false;
            }
        }

        return true;
    }
}
