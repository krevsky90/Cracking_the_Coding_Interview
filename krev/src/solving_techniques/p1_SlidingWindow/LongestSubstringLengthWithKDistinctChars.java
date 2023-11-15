package solving_techniques.p1_SlidingWindow;

import java.util.HashMap;
import java.util.Map;

/**
 * https://youtu.be/MK-NZ4hN7rs?t=1979
 *
 * Example with auxiliary data structure
 */
public class LongestSubstringLengthWithKDistinctChars {
    public static void main(String[] args) {
        String s = "AAAHHIBC";
        int k = 2;
        int result = findLongestSubstringLengthWithKDistinctChars(s, k);
        System.out.println(result);
    }

    public static int findLongestSubstringLengthWithKDistinctChars(String s, int k) {
        Map<Character, Integer> map = new HashMap<>();

        int start = 0;
        int maxLength = Integer.MIN_VALUE;

        char[] arr = s.toCharArray();

        for (int end = 0; end < s.length(); end++) {
            int amount = map.getOrDefault(arr[end], 0);
            map.put(arr[end], amount + 1);

            while (map.size() > k) {
                //shrink from left side
                int shrinkAmount = map.get(arr[start]);
                if (shrinkAmount > 1) {
                    map.put(arr[start], shrinkAmount - 1);
                } else {
                    map.remove(arr[start]);
                }

                start++;
            }

            maxLength = Math.max(maxLength, end - start + 1);
        }

        return maxLength;
    }
}
