package solving_techniques.different;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 249. Group Shifted Strings (medium) (locked)
 * https://leetcode.com/problems/group-shifted-strings
 * <p>
 * #Company (20.02.2025): 0 - 3 months Meta 19 Google 2 6 months ago Wix 2 Uber 2
 * <p>
 * Perform the following shift operations on a string:
 * <p>
 * Right shift: Replace every letter with the successive letter of the English alphabet, where 'z' is replaced by 'a'. For example, "abc" can be right-shifted to "bcd" or "xyz" can be right-shifted to "yza".
 * Left shift: Replace every letter with the preceding letter of the English alphabet, where 'a' is replaced by 'z'. For example, "bcd" can be left-shifted to "abc" or "yza" can be left-shifted to "xyz".
 * We can keep shifting the string in both directions to form an endless shifting sequence.
 * <p>
 * For example, shift "abc" to form the sequence: ... <-> "abc" <-> "bcd" <-> ... <-> "xyz" <-> "yza" <-> .... <-> "zab" <-> "abc" <-> ...
 * You are given an array of strings strings, group together all strings[i] that belong to the same shifting sequence. You may return the answer in any order.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: strings = ["abc","bcd","acef","xyz","az","ba","a","z"]
 * <p>
 * Output: [["acef"],["a","z"],["abc","bcd","xyz"],["az","ba"]]
 * <p>
 * Example 2:
 * <p>
 * Input: strings = ["a"]
 * <p>
 * Output: [["a"]]
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= strings.length <= 200
 * 1 <= strings[i].length <= 50
 * strings[i] consists of lowercase English letters.
 */
public class GroupShiftedStrings {
    /**
     * KREVSKY SOLUTION:
     * time to solve ~ 20 mins
     * <p>
     * idea:
     * 1) keep map: key -> list of words that can be formed by right/left shifts
     * 2) to find if w1 can be transformed to w2, we need to check for each their chars if delta between codes of these chars is the same
     * <p>
     * BEATS ~ 26%
     */
    public List<List<String>> groupStrings(String[] strings) {
        Map<String, List<String>> map = new HashMap<>();

        for (int i = 0; i < strings.length; i++) {
            boolean isFound = false;
            for (String key : map.keySet()) {
                if (isSame(key, strings[i])) {
                    map.get(key).add(strings[i]);
                    isFound = true;
                    break;
                }
            }

            if (!isFound) {
                map.put(strings[i], new ArrayList<>());
                map.get(strings[i]).add(strings[i]);
            }
        }

        return map.values().stream().collect(Collectors.toList());

//        List<List<String>> result = new ArrayList<>();
//        for (List<String> list : map.values()) {
//            result.add(list);
//        }
//
//        return result;
    }

    private boolean isSame(String w1, String w2) {
        if (w1 == null || w2 == null || w1.length() != w2.length()) return false;

        int delta = (w1.charAt(0) - w2.charAt(0) + 26) % 26;
        for (int i = 1; i < w1.length(); i++) {
            int temp = (w1.charAt(i) - w2.charAt(i) + 26) % 26;
            if (temp != delta) return false;
        }

        return true;
    }

    /**
     * Editorial
     * idea: the same + use hash as key, not some word from the group
     * hash = <number of shifts that is required to transform temp char to 'a'> + 'a' char
     */
    private String getHash(String s) {
        char[] chars = s.toCharArray();
        StringBuilder hashKey = new StringBuilder();

        for (int i = 1; i < chars.length; i++) {
            hashKey.append((char) ((chars[i] - chars[i - 1] + 26) % 26 + 'a'));
        }

        return hashKey.toString();
    }

    /**
     * Time complexity ~ O(N*K). N - strings.length, K - max length of string
     * <p>
     * Space complexity ~ O(N*K)
     */
    public List<List<String>> groupStringsOfficial(String[] strings) {
        Map<String, List<String>> mapHashToList = new HashMap<>();

        // Create a hash_value (hashKey) for each string and append the string
        // to the list of hash values i.e. mapHashToList["cd"] = ["acf", "gil", "xzc"]
        for (String str : strings) {
            String hashKey = getHash(str);
            if (mapHashToList.get(hashKey) == null) {
                mapHashToList.put(hashKey, new ArrayList<>());
            }
            mapHashToList.get(hashKey).add(str);
        }

        // Iterate over the map, and add the values to groups
        List<List<String>> groups = new ArrayList<>();
        for (List<String> group : mapHashToList.values()) {
            groups.add(group);
        }

        // Return a list of all of the grouped strings
        return groups;
    }
}
