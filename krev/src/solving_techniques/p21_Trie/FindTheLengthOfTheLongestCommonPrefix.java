package solving_techniques.p21_Trie;

import java.util.*;

/**
 * 3043. Find the Length of the Longest Common Prefix (medium)
 * https://leetcode.com/problems/find-the-length-of-the-longest-common-prefix/
 * <p>
 * #Company: 0 - 3 months Meta 5 Google 3 TikTok 2 Uber 2 0 - 6 months Capital One 3 ZipRecruiter 3 Microsoft 2 Databricks 2 Roblox 2 Coinbase 2 The Trade Desk 2 6 months ago Amazon 2
 * <p>
 * You are given two arrays with positive integers arr1 and arr2.
 * A prefix of a positive integer is an integer formed by one or more of its digits, starting from its leftmost digit. For example, 123 is a prefix of the integer 12345, while 234 is not.
 * A common prefix of two integers a and b is an integer c, such that c is a prefix of both a and b. For example, 5655359 and 56554 have common prefixes 565 and 5655 while 1223 and 43456 do not have a common prefix.
 * You need to find the length of the longest common prefix between all pairs of integers (x, y) such that x belongs to arr1 and y belongs to arr2.
 * Return the length of the longest common prefix among all pairs. If no common prefix exists among them, return 0.
 * <p>
 * Example 1:
 * Input: arr1 = [1,10,100], arr2 = [1000]
 * Output: 3
 * Explanation: There are 3 pairs (arr1[i], arr2[j]):
 * - The longest common prefix of (1, 1000) is 1.
 * - The longest common prefix of (10, 1000) is 10.
 * - The longest common prefix of (100, 1000) is 100.
 * The longest common prefix is 100 with a length of 3.
 * <p>
 * Example 2:
 * Input: arr1 = [1,2,3], arr2 = [4,4,4]
 * Output: 0
 * Explanation: There exists no common prefix for any pair (arr1[i], arr2[j]), hence we return 0.
 * Note that common prefixes between elements of the same array do not count.
 * <p>
 * Constraints:
 * 1 <= arr1.length, arr2.length <= 5 * 10^4
 * 1 <= arr1[i], arr2[i] <= 10^8
 */
public class FindTheLengthOfTheLongestCommonPrefix {
    /**
     * Official solution:
     * idea: use Trie
     *
     * time to implement ~ 9 mins
     * time ~ O(arr1.length * M1 + arr2.length * M2), where M1  -max length of the number in arr1, M2 - ...
     *      since arr1[i], arr2[i] <= 10^8 => M1 and M2 <= 8
     *      then O(arr1.length + arr2.length)
     *  space ~ O(arr1.length * M1) ~ O(arr1.length)
     *
     * BEATS ~ 27%
     */
    public int longestCommonPrefix2(int[] arr1, int[] arr2) {
        Trie trie = new Trie();
        for (int n : arr1) {
            trie.insert(n);
        }

        int maxDepth = 0;
        for (int n : arr2) {
            maxDepth = Math.max(trie.getDepth(n), maxDepth);
        }

        return maxDepth;
    }

    class Node {
        Map<Integer, Node> children = new HashMap<>();
    }

    class Trie {
        Node root = new Node();

        public void insert(int n) {
            char[] digits = ("" + n).toCharArray();
            Node cur = root;
            for (char c : digits) {
                int pos = c - '0';
                if (!cur.children.containsKey(pos)) {
                    cur.children.put(pos, new Node());
                }
                cur = cur.children.get(pos);
            }
        }

        public int getDepth(int n) {
            char[] digits = ("" + n).toCharArray();
            Node cur = root;
            int depth = 0;
            for (char c : digits) {
                int pos = c - '0';
                if (!cur.children.containsKey(pos)) {
                    return depth;
                } else {
                    depth++;
                    cur = cur.children.get(pos);
                }
            }

            return depth;
        }
    }


    //my example:
    //100, 106, 201, 4562, 9
    //10, 2,20, 4569, 94

    /**
     * KREVSKY SOLUTION:
     * idea:
     * 1) create sets of numbers
     * 2) convert to Sets of strings and save to lists
     * 3) sort lists of strings
     * 4) setup 2 pointers for each list
     * compare i-th and j-th words starting from the leftmost symbol increasing tempPrefix's value inside the loop
     * when loop ends we update maxPrefix
     * btw, if loop ends there are 4 potential cases why it happened:
     * 1) only part os both strings are common
     * - if k1-th char mean less digit that k2-th char => move i to check the next string from list1
     * - otherwise - move j
     * 2) w2 is substring of w1 => move j
     * 3) w1 is substring of w2 => move i
     * 4) w1 = w2 => move both i and j
     * <p>
     * time to solve ~ 34 mins
     * time to convert arr to sorted list: O(n) + O(n*logn) ~ O(n*logn) => O(arr1.length*log(arr1.length)) + O(arr2.length*log(arr2.length))
     * time to compare the words ~ O(total amount of letters in list1 + total amount of letters in list2)
     * space ~ O(arr1.length + arr2.length)
     * <p>
     * 2 attempts:
     * - forgot "else break"
     * <p>
     * BEATS ~ 22%
     */
    public int longestCommonPrefix(int[] arr1, int[] arr2) {
        List<String> list1 = getSortedList(arr1);
        List<String> list2 = getSortedList(arr2);

        int maxPrefix = 0;
        int i = 0;
        int j = 0;
        while (i < list1.size() && j < list2.size()) {
            int tempPrefix = 0;
            int k1 = 0;
            int k2 = 0;
            char[] w1 = list1.get(i).toCharArray();
            char[] w2 = list2.get(j).toCharArray();
            while (k1 < w1.length && k2 < w2.length) {
                if (w1[k1] == w2[k2]) {
                    tempPrefix++;
                    k1++;
                    k2++;
                } else {
                    break;
                }
            }

            maxPrefix = Math.max(maxPrefix, tempPrefix);

            if (k1 < w1.length && k2 < w2.length) {
                //no need convert char to int since the more char code number the more number it means
                if (w1[k1] < w2[k2]) {
                    i++;
                } else {
                    //w1[k1] > w2[k2]
                    j++;
                }
            } else if (k1 < w1.length && k2 == w2.length) {
                j++;
            } else if (k1 == w1.length && k2 < w2.length) {
                i++;
            } else if (k1 == w1.length && k2 == w2.length) {
                i++;
                j++;
            }
        }

        return maxPrefix;
    }

    private List<String> getSortedList(int[] arr) {
        Set<String> set = new HashSet<>();
        for (int a : arr) {
            set.add("" + a);
        }

        List<String> list = new ArrayList<>(set);
        Collections.sort(list, (a, b) -> a.compareTo(b));

        return list;
    }
}
