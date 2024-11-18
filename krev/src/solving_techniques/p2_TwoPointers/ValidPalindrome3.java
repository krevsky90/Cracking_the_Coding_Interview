package solving_techniques.p2_TwoPointers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 1216. Valid Palindrome III (hard)
 * https://leetcode.com/problems/valid-palindrome-iii (blocked)
 * OR
 * https://leetcode.ca/all/1216.html#google_vignette
 * <p>
 * Given a string s and an integer k, find out if the given string is a K-Palindrome or not.
 * <p>
 * A string is K-Palindrome if it can be transformed into a palindrome by removing at most k characters from it.
 * <p>
 * Example 1:
 * Input: s = "abcdeca", k = 2
 * Output: true
 * Explanation: Remove 'b' and 'e' characters.
 * <p>
 * Constraints:
 * 1 <= s.length <= 1000
 * s has only lowercase English letters.
 * 1 <= k <= s.length
 */
public class ValidPalindrome3 {
    public static void main(String[] args) {
        String s = "abcde";
        int k = 4;

        ValidPalindrome3 obj = new ValidPalindrome3();
        System.out.println(obj.isPalindrome(s, k));
    }

    /**
     * KREVSKY SOLUTION:
     * similar to https://www.youtube.com/watch?v=cJBT7Q106hg&list=PLUPSMCjQ-7od5IVz8ug6D-apxFLkDTsoy&index=114
     * but simpler.
     * Since the problem is locked on leetcode, can't test it properly,
     * but manual testing shows success
     *
     * idea:
     * 1) use 2 pointers and standard while (left < right)
     * 2) if left-char != right-char then result = one of 2 options:
     *  - opt#1: delete left char
     *  - opt#2: delete right char
     * 3) stop condition: if k < 0 => return false (or we can use k == 0 => return isPalindrome (usual method)
     * 4) use memoization for cases like s = "abcde" and k = 4, where key = (left, right, k)
     *
     * time to solve ~ 20 mins
     *
     * time ~ O((N-1)^2) ~ O(N^2) - due to res1 and res2 which might be calced each time if s = abcde, for example
     * space ~ O(N^2), since we need to store all possible combinations of (left, right)
     *
     * 2 attempts:
     * - forgot memo
     */
    public boolean isPalindrome(String s, int k) {
        char[] sArr = s.toCharArray();
        Map<List<Integer>, Boolean> mapMemo = new HashMap<>();

        return isPalindrome(sArr, 0, sArr.length - 1, k, mapMemo);
    }

    private boolean isPalindrome(char[] sArr, int left, int right, int k, Map<List<Integer>, Boolean> mapMemo) {
        if (k < 0) return false;

        List<Integer> key = Arrays.asList(left, right, k);

        if (mapMemo.containsKey(key)) {
            return mapMemo.get(key);
        }

        while (left < right) {
            if (sArr[left] == sArr[right]) {
                left++;
                right--;
            } else {
                boolean res1 = isPalindrome(sArr, left + 1, right, k - 1, mapMemo);
                boolean res2 = isPalindrome(sArr, left, right - 1, k - 1, mapMemo);

                mapMemo.put(key, res1 || res2);
                return mapMemo.get(key);
            }
        }

        mapMemo.put(key, true);
        return mapMemo.get(key);
    }
}
