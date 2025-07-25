package solving_techniques.p26_backtracking;

import java.util.*;

/**
 * 247. Strobogrammatic Number II (medium) (locked)
 * https://leetcode.com/problems/strobogrammatic-number-ii
 * <p>
 * #Company (25.07.2025): 0 - 3 months Meta 5 6 months ago Google 7 Amazon 2
 * <p>
 * Given an integer n, return all the strobogrammatic numbers that are of length n. You may return the answer in any order.
 * <p>
 * A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
 * <p>
 * Example 1:
 * Input: n = 2
 * Output: ["11","69","88","96"]
 * <p>
 * Example 2:
 * Input: n = 1
 * Output: ["0","1","8"]
 * <p>
 * Constraints:
 * 1 <= n <= 14
 */
public class StrobogrammaticNumber2 {
    /**
     * KREVSKY SOLUTION:
     * time to solve ~ 25 mins
     * <p>
     * idea:
     * 1) use recursion and backtracking + mirroring sb's content
     * 2) if n is odd => insert 0 1 or 8 to the middle
     * 3) [0]'s element can not be 0
     *
     * time ~ O(N*5^(N/2)) - NOT sure!
     * space ~ O(N*5^(N/2))
     * <p>
     * 3 attempts:
     * - changes sb instead to form new string
     * - did not skip case tempResult[0] = '0', but had to do this
     * <p>
     * BEATS ~ 17%
     */
    private List<Character> arr018 = Arrays.asList('0', '1', '8');

    public List<String> findStrobogrammatic(int n) {
        Map<Character, Character> map = new HashMap<>();
        map.put('1', '1');
        map.put('6', '9');
        map.put('8', '8');
        map.put('9', '6');
        map.put('0', '0');

        StringBuilder sb = new StringBuilder();
        List<String> result = new ArrayList<>();

        helper(map, sb, result, n / 2, n % 2 == 1);

        return result;
    }

    private void helper(Map<Character, Character> map, StringBuilder sb, List<String> result, int k, boolean isOdd) {
        if (k == 0) {
            if (isOdd) {
                for (char c : arr018) {
                    result.add(sb.toString() + c + reverse(sb, map));
                }
            } else {
                result.add(sb.toString() + reverse(sb, map));
            }
            return;
        }

        for (char c : map.keySet()) {
            // # idea #2
            if (sb.isEmpty() && c == '0') continue;

            sb.append(c);
            helper(map, sb, result, k - 1, isOdd);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    private String reverse(StringBuilder sb, Map<Character, Character> map) {
        int len = sb.length();
        char[] res = new char[len];
        for (int i = 0; i < len; i++) {
            res[len - i - 1] = map.get(sb.charAt(i));
        }

        return String.valueOf(res);
    }

    /**
     * Official solution: uses BFS
     *
     * implemented in 10 mins
     *
     * BEATS ~ 59%
     */
    public char[][] reversiblePairs = {
            {'0', '0'}, {'1', '1'},
            {'6', '9'}, {'8', '8'}, {'9', '6'}
    };

    public List<String> findStrobogrammaticOfficial(int n) {
        Queue<String> q = new LinkedList<>();
        int curLength = 0;

        if (n % 2 == 0) {
            q.add("");
        } else {
            q.add("0");
            q.add("1");
            q.add("8");
            curLength = 1;
        }

        while (curLength < n) {
            int size = q.size();
            curLength += 2;
            for (int i = 0; i < size; i++) {
                String s = q.poll();

                for (char[] pair : reversiblePairs) {
                    //NOTE: we do not append '0' in the beginning if we form the final string
                    if (curLength < n || pair[0] != '0') {
                        q.add(pair[0] + s + pair[1]);
                    }
                }
            }
        }

        List<String> result = new ArrayList<>(q.size());
        while (!q.isEmpty()) {
            result.add(q.poll());
        }

        return result;
    }


}
