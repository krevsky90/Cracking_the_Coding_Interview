package solving_techniques.different;

/**
 * 161. One Edit Distance (medium)
 * https://leetcode.com/problems/one-edit-distance/ (blocked)
 * <p>
 * #Company: Yandex
 * <p>
 * info: https://leetcode.ca/all/161.html
 * <p>
 * Given two strings s and t, determine if they are both one edit distance apart.
 * Note:
 * There are 3 possiblities to satisfy one edit distance apart:
 * <p>
 * Insert a character into s to get t
 * Delete a character from s to get t
 * Replace a character of s to get t
 * <p>
 * Example 1:
 * Input: s = "ab", t = "acb"
 * Output: true
 * Explanation: We can insert 'c' into s to get t.
 * <p>
 * Example 2:
 * Input: s = "cab", t = "ad"
 * Output: false
 * Explanation: We cannot get t from s by only one step.
 * <p>
 * Example 3:
 * Input: s = "1203", t = "1213"
 * Output: true
 * Explanation: We can replace '0' with '1' to get t.
 */
public class OneEditDistance {
    public static void main(String[] args) {
        String s1 = "ab";
        String t1 = "acb";
        String s2 = "cab";
        String t2 = "ad";
        String s3 = "1203";
        String t3 = "1223";

        OneEditDistance obj = new OneEditDistance();
        System.out.println(obj.isOneDistance(s1, t1));      //expected true
        System.out.println(obj.isOneDistance(s2, t2));      //expected false
        System.out.println(obj.isOneDistance(s3, t3));      //expected true
    }

    /**
     * KREVSKY SOLUTION:
     * time to solve ~ 10 mins
     * time ~ O(max(s.length(), t.length())
     * <p>
     * 2 attempts:
     * - in case if the strings are the same, we should return false
     */
    public boolean isOneDistance(String s, String t) {
        if (s.length() == t.length()) {
            int diff = 0;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) != t.charAt(i)) {
                    diff++;
                    if (diff > 1) return false;
                }
            }
            return diff == 1;
        } else if (Math.abs(s.length() - t.length()) > 1) {
            return false;
        } else {
            if (s.length() > t.length()) {
                return isOneDistanceHelper(t, s);
            } else {
                return isOneDistanceHelper(s, t);
            }
        }
    }

    private boolean isOneDistanceHelper(String minS, String maxS) {
        int i = 0;
        int j = 0;
        int diff = 0;
        while (i < minS.length() && j < maxS.length()) {
            if (minS.charAt(i) != maxS.charAt(j)) {
                j++;
                diff++;
                if (diff > 1) return false;
            } else {
                i++;
                j++;
            }
        }

        return diff == 1;
    }

    /**
     * SOLUTION #2:
     * info: https://leetcode.ca/2016-05-09-161-One-Edit-Distance/
     * suppose sLen > tLen. if not => swap the parameters
     */
    public boolean isOneEditDistance2(String s, String t) {
        int sLen = s.length();
        int tLen = t.length();
        if (sLen < tLen) {
            return isOneEditDistance2(t, s);
        }

        if (sLen - tLen > 1) {
            return false;
        }

        for (int i = 0; i < tLen; ++i) {
            if (s.charAt(i) != t.charAt(i)) {
                if (sLen == tLen) {
                    return s.substring(i + 1).equals(t.substring(i + 1));
                }
                return s.substring(i + 1).equals(t.substring(i));
            }
        }
        //NOTE: if the strings have the same length and there was no case when s.charAt(i) != t.charAt(i)
        // => the strings the the same => return false
        return sLen == tLen + 1;
    }
}
