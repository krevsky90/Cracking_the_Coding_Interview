package data_structures.chapter1_arrays_n_strings.amazon_igotanoffer.medium_strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * https://igotanoffer.com/blogs/tech/string-interview-questions
 * OR
 * 49. Group Anagrams (medium)
 * https://leetcode.com/problems/group-anagrams
 * <p>
 * #Company: Adobe Affirm Alibaba Amazon Apple Bloomberg Booking.com Docusign eBay Facebook Goldman Sachs Google Hulu Intuit Mathworks Microsoft Nutanix Oracle Qualtrics Salesforce Snapchat Tesla Twilio Uber Visa VMware Walmart Labs Wish Yahoo Yandex Yelp Zulily
 * <p>
 * Given an array of strings strs, group the anagrams together. You can return the answer in any order.
 * <p>
 * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.
 * <p>
 * Example 1:
 * Input: strs = ["eat","tea","tan","ate","nat","bat"]
 * Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
 * <p>
 * Example 2:
 * Input: strs = [""]
 * Output: [[""]]
 * <p>
 * Example 3:
 * Input: strs = ["a"]
 * Output: [["a"]]
 * <p>
 * Constraints:
 * 1 <= strs.length <= 10000
 * 0 <= strs[i].length <= 100
 * strs[i] consists of lowercase English letters.
 */
public class Problem2_7_GroupAnagrams {
    /**
     * KREVSKY SOLUTION - not optimal or beauty
     * time to solve ~ 30 mins
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result = new ArrayList<>();

        if (strs == null || strs.length == 0) return result;

        List<String> group = new ArrayList<>();
        group.add(strs[0]);
        result.add(group);

        for (int i = 1; i < strs.length; i++) {
            String s = strs[i];
            boolean included = false;
            for (List g : result) {
                //group g can't be empty
                String gs = (String) g.get(0);
                if (isAnagram(gs, s)) {
                    g.add(s);
                    included = true;
                    break;
                }
            }

            if (!included) {
                List<String> newGroup = new ArrayList<>();
                newGroup.add(s);
                result.add(newGroup);
            }
        }

        return result;
    }

    //O(n), n - length of string
    private boolean isAnagram(String gs, String s) {
        if (gs.length() != s.length()) return false;

        char[] gsArr = gs.toCharArray();
        char[] sArr = s.toCharArray();

        int[] count = new int[256];
        for (int i = 0; i < gsArr.length; i++) {
            count[gsArr[i]]++;
            count[sArr[i]]--;
        }

        for (int i = 0; i < count.length; i++) {
            if (count[i] != 0) {
                return false;
            }
        }

        return true;
    }

    /**
     * ORIGINAL SOLUTION
     * idea - HashMap + creating of unique key (O(k), where k - number of string symbols)
     * time ~ O(N)*O(K) - where N = strs.length(), K - max length of the string from strs
     * space ~ O(N*K)
     */
    class Solution {
        public List<List<String>> groupAnagrams(String[] strs) {
            List<List<String>> result = new ArrayList<>();
            if (strs == null || strs.length == 0) {
                return result;
            }
            if (strs.length == 1) {
                result.add(Arrays.asList(strs));
                return result;
            }

            HashMap<String, List<String>> groups = new HashMap<>();
            for (String s : strs) {
                String signature = getSignature(s);
                groups.putIfAbsent(signature, new ArrayList<>());
                groups.get(signature).add(s);
            }

            return new ArrayList<>(groups.values());
        }

        //example of creation of unique key
        private String getSignature(String s) {
            int[] count = new int[26];
            for (int i = 0; i < s.length(); i++) {
                count[s.charAt(i) - 'a']++;
            }

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 26; i++) {
                if (count[i] != 0) {
                    sb.append((char) ('a' + i)).append(count[i]);
                }
            }
            return sb.toString();
        }
    }
}
