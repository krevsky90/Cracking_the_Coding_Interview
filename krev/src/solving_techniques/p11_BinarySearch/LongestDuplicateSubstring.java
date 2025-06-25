package solving_techniques.p11_BinarySearch;

import java.util.*;

/**
 * 1044. Longest Duplicate Substring (hard)
 * https://leetcode.com/problems/longest-duplicate-substring
 * <p>
 * #Company (8.03.2025):
 * <p>
 * Given a string s, consider all duplicated substrings:
 * (contiguous) substrings of s that occur 2 or more times. The occurrences may overlap.
 * <p>
 * Return any duplicated substring that has the longest possible length.
 * If s does not have a duplicated substring, the answer is "".
 * <p>
 * Example 1:
 * Input: s = "banana"
 * Output: "ana"
 * <p>
 * Example 2:
 * Input: s = "abcd"
 * Output: ""
 * <p>
 * Constraints:
 * 2 <= s.length <= 3 * 10^4
 * s consists of lowercase English letters.
 */
public class LongestDuplicateSubstring {
    /**
     * NOT SOLVED by myself (did not reach idea of binary search
     * time to implement ~ 6 mins
     * <p>
     * BUT it gives TLE, since findSubStringAtlength is not optimal!
     * to optimize - we need to calculate hash to all possible strings that has the length => use Rabin-Karp algorithm!
     */
    public String longestDupSubstring(String s) {
        int low = 1;
        int high = s.length() - 1;
        String result = "";
        while (low <= high) {
            int mid = low + (high - low) / 2;
            String tempRes = findSubStringAtlength(s, mid);
            if ("".equals(tempRes)) {
                high = mid - 1;
            } else {
                result = tempRes;
                low = mid + 1;
            }
        }

        return result;
    }

    private String findSubStringAtlength(String s, int length) {
        //use fixed sliding window
        Set<String> substrings = new HashSet<>();
        for (int i = 0; i < s.length() - length + 1; i++) {
            String temp = s.substring(i, i + length);
            if (substrings.contains(temp)) {
                return temp;
            } else {
                substrings.add(temp);
            }
        }

        return "";
    }

    /**
     * Optimized solution:
     * time to optimize ~ 40 mins
     *
     * BEATS ~ 33%
     */

    //simple idea - hash substring that has length = L by this way:
    //example: arr[0] * 26^(L-1) + arr[1] * 26^(L-2) + ... + arr[L-2] * 26^1 + arr[L-1] * 1
    //this is Rabin-Karp algorithm. We use base = 26, because string can contains only lowercase English letters.
    //But the problem is 26^(L-1) can we very huge => apply mod 10^9, for example.
    //It might give us collisions => let's keep map: hash -> list of substrings that has such hash
    //We should not have many collisions => it is cheap to compare current substring to the existing list (takes O(L) * list.size(0))

    //to recalculate hash fast, we use fixed sliding window to change hash
    //the hash is affected by start element and new element => to calculate new hash (which fits current window) takes ~ O(1)
    private static final int BASE = 26;
    private static final long MOD = 1000_000_007;   //it is better to use prime number

    private String findSubStringAtLengthOptimized(String s, int length) {
        //calc hash for [0, length] substring
        long h = 0;
        for (int i = 0; i < length; i++) {
            h = (h * BASE + (s.charAt(i) - 'a')) % MOD;
        }

        long BASE_LEN_MINUS_1 = 1;
        for (int i = 1; i < length; i++) {
            BASE_LEN_MINUS_1 = (BASE_LEN_MINUS_1 * BASE) % MOD;
        }

        //keep map: hash -> list of start indices of the words that has length = length and hash = hash
        Map<Long, List<Integer>> map = new HashMap<>();
        map.put(h, new ArrayList<>());
        map.get(h).add(0);

        //use fixed sliding window
        for (int i = length; i < s.length(); i++) {
            //recal hash for [i - length + 1, i] substring
            //1) remove impact of i-length-th element:
            h = (h - BASE_LEN_MINUS_1 * (s.charAt(i - length) - 'a') % MOD + MOD) % MOD;
            //2) shift the rest + add i-th element
            h = (h * BASE + (s.charAt(i) - 'a')) % MOD;

            //try to find substring with the same hash and check if it is the same substring as [i - length + 1, i] or just with equal hash
            List<Integer> hits = map.get(h);
            if (hits != null) {
                String curSubString = s.substring(i - length + 1, i + 1);
                for (int start : hits) {
                    String candidate = s.substring(start, start + length);
                    if (candidate.equals(curSubString)) {
                        return candidate;
                    }
                }
            }

            map.put(h, new ArrayList<>());
            map.get(h).add(i - length + 1);
        }

        return "";
    }
}
