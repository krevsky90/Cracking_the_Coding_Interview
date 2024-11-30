package solving_techniques.different;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 824. Goat Latin (easy)
 * https://leetcode.com/problems/goat-latin
 *
 * #Company: Meta
 *
 * You are given a string sentence that consist of words separated by spaces. Each word consists of lowercase and uppercase letters only.
 *
 * We would like to convert the sentence to "Goat Latin" (a made-up language similar to Pig Latin.) The rules of Goat Latin are as follows:
 *
 * If a word begins with a vowel ('a', 'e', 'i', 'o', or 'u'), append "ma" to the end of the word.
 * For example, the word "apple" becomes "applema".
 * If a word begins with a consonant (i.e., not a vowel), remove the first letter and append it to the end, then add "ma".
 * For example, the word "goat" becomes "oatgma".
 * Add one letter 'a' to the end of each word per its word index in the sentence, starting with 1.
 * For example, the first word gets "a" added to the end, the second word gets "aa" added to the end, and so on.
 * Return the final sentence representing the conversion from sentence to Goat Latin.
 *
 * Example 1:
 * Input: sentence = "I speak Goat Latin"
 * Output: "Imaa peaksmaaa oatGmaaaa atinLmaaaaa"
 *
 * Example 2:
 * Input: sentence = "The quick brown fox jumped over the lazy dog"
 * Output: "heTmaa uickqmaaa rownbmaaaa oxfmaaaaa umpedjmaaaaaa overmaaaaaaa hetmaaaaaaaa azylmaaaaaaaaa ogdmaaaaaaaaaa"
 *
 * Constraints:
 * 1 <= sentence.length <= 150
 * sentence consists of English letters and spaces.
 * sentence has no leading or trailing spaces.
 * All the words in sentence are separated by a single space.
 */
public class GoatLatin {
    /**
     * KREVSKY SOLUTION:
     * time to solve ~ 12 mins
     * time ~ O(n)
     * space ~ O(n)
     *
     * 2 attempts:
     * - forgot NOT to add " " if the word is the latest
     *
     * BEATS ~ 82%
     */
    public String toGoatLatin(String sentence) {
        Set<Character> vowel = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));
        String ma = "ma";
        String[] words = sentence.split(" ");
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < words.length; i++) {
            char firstChar = words[i].charAt(0);
            if (vowel.contains(Character.toLowerCase(firstChar))) {
                sb.append(words[i]);
            } else {
                sb.append(words[i].substring(1)).append(firstChar);
            }
            sb.append(ma);

            for (int j = 0; j < i + 1; j++) {
                sb.append("a");
            }

            //forgot this condition => incorrect answer!
            if (i < words.length - 1) {
                sb.append(" ");
            }
        }

        return sb.toString();
    }
}
