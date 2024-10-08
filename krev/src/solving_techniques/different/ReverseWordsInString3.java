package solving_techniques.different;

/**
 * 557. Reverse Words in a String III (easy(
 * https://leetcode.com/problems/reverse-words-in-a-string-iii
 * <p>
 * #Company: Adobe Amazon Apple eBay Electronic Arts Facebook Google Microsoft Nutanix Twitch Uber VMware Walmart Labs Yandex
 * <p>
 * Given a string s, reverse the order of characters in each word within a sentence
 * while still preserving whitespace and initial word order.
 * <p>
 * Example 1:
 * Input: s = "Let's take LeetCode contest"
 * Output: "s'teL ekat edoCteeL tsetnoc"
 * <p>
 * Example 2:
 * Input: s = "Mr Ding"
 * Output: "rM gniD"
 * <p>
 * Constraints:
 * 1 <= s.length <= 5 * 10^4
 * s contains printable ASCII characters.
 * s does not contain any leading or trailing spaces.
 * There is at least one word in s.
 * All the words in s are separated by a single space.
 */
public class ReverseWordsInString3 {
    public static void main(String[] args) {
        String s = "Let's take LeetCode contest";

        ReverseWordsInString3 obj = new ReverseWordsInString3();
        System.out.println(obj.reverseWords(s));
    }

    /**
     * KREVSKY SOLUTION:
     * idea:
     * 1) use StringBuilder
     * 2) use split
     * <p>
     * time ~ O(n)
     * space ~ O(n)
     * <p>
     * 1 attempt
     * <p>
     * BEATS ~ 48%
     */
    public String reverseWords(String s) {
        StringBuilder sb = new StringBuilder();
        String[] words = s.split(" ");
        for (String w : words) {
            for (int i = w.length() - 1; i >= 0; i--) {
                sb.append(w.charAt(i));
            }
            sb.append(" ");
        }

        sb.setLength(sb.length() - 1);
        return sb.toString();
    }

    /**
     * SOLUTION #2
     * BEATS ~ 87%
     *
     * extra idea: use StringBuilder to reverse
     */
    public String reverseWords2(String s) {
        String arr[] = s.split(" ");

        int n = arr.length;

        StringBuilder ans = new StringBuilder("");

        for (int i = 0; i < n; i++) {
            StringBuilder x = new StringBuilder(arr[i]);
            ans.append(x.reverse());

            if (i != n - 1) ans.append(" ");
        }
        return ans.toString();
    }
}
