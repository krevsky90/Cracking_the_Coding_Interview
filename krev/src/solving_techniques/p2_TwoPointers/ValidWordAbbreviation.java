package solving_techniques.p2_TwoPointers;

/**
 * 408 - Valid Word Abbreviation (easy) (locked)
 * https://leetcode.com/problems/valid-word-abbreviation
 * OR
 * https://leetcode.ca/all/408.html
 * <p>
 * #Company (18.02.2025): 0 - 3 months Meta 102 Google 2 0 - 6 months Datadog 5 Amazon 3 TikTok 2 6 months ago Apple 4 Disney 2
 * <p>
 * A string can be abbreviated by replacing any number of non-adjacent,
 * non-empty substrings with their lengths. The lengths should not have leading zeros.
 * <p>
 * For example, a string such as "substitution" could be abbreviated as (but not limited to):
 * "s10n" ("s ubstitutio n")
 * "sub4u4" ("sub stit u tion")
 * "12" ("substitution")
 * "su3i1u2on" ("su bst i t u ti on")
 * "substitution" (no substrings replaced)
 * <p>
 * The following are not valid abbreviations:
 * "s55n" ("s ubsti tutio n", the replaced substrings are adjacent)
 * "s010n" (has leading zeros)
 * "s0ubstitution" (replaces an empty substring)
 * Given a string word and an abbreviation abbr, return whether the string matches the given abbreviation.
 * <p>
 * A substring is a contiguous non-empty sequence of characters within a string.
 * <p>
 * Example 1:
 * Input: word = "internationalization", abbr = "i12iz4n"
 * Output: true
 * Explanation: The word "internationalization" can be abbreviated as "i12iz4n" ("i nternational iz atio n").
 * <p>
 * Example 2:
 * Input: word = "apple", abbr = "a2e"
 * Output: false
 * Explanation: The word "apple" cannot be abbreviated as "a2e".
 * <p>
 * Constraints:
 * 1 <= word.length <= 20
 * word consists of only lowercase English letters.
 * 1 <= abbr.length <= 10
 * abbr consists of lowercase English letters and digits.
 * All the integers in abbr will fit in a 32-bit integer.
 */
public class ValidWordAbbreviation {
    public static void main(String[] args) {
        String word = "substitution";
        String abbr1 = "s10n";
        String abbr2 = "sub4u4";
        String abbr3 = "12";
        String abbr4 = "su3i1u2on";
        String abbr5 = "substitution";

        String notAbbr1 = "s55n";
        String notAbbr2 = "s010n";
        String notAbbr3 = "s0ubstitution";

        ValidWordAbbreviation obj = new ValidWordAbbreviation();
        System.out.println(obj.validWordAbbreviation(word, abbr1));
        System.out.println(obj.validWordAbbreviation(word, abbr2));
        System.out.println(obj.validWordAbbreviation(word, abbr3));
        System.out.println(obj.validWordAbbreviation(word, abbr4));
        System.out.println(obj.validWordAbbreviation(word, abbr5));

        System.out.println(obj.validWordAbbreviation(word, notAbbr1));
        System.out.println(obj.validWordAbbreviation(word, notAbbr2));
        System.out.println(obj.validWordAbbreviation(word, notAbbr3));
    }

    /**
     * info:
     * https://leetcode.com/problems/valid-word-abbreviation/solutions/1491062/java-tc-o-abbr-sc-o-1-clean-concise-solution-using-two-pointers/?envType=company&envId=facebook&favoriteSlug=facebook-thirty-days
     */
    public boolean validWordAbbreviation2(String word, String abbr) {
        if (word == null || abbr == null) {
            throw new IllegalArgumentException("Input is null");
        }

        int wLen = word.length();
        int aLen = abbr.length();

        // length of abbreviation cannot be greater than word's length
        if (aLen > wLen) {
            return false;
        }

        if (wLen == 0) {
            return true;
        }

        int i = 0;
        int j = 0;

        while (i < wLen && j < aLen) {
            // It current characters in both word and abbr is same continue checking.
            if (word.charAt(i) == abbr.charAt(j)) {
                i++;
                j++;
                continue;
            }

            // Now current characters in word and abbr do not match. Thus current character
            // in abbr should be a valid starting digit 0 < x <= 9.
            if (abbr.charAt(j) == '0' || !Character.isDigit(abbr.charAt(j))) {
                return false;
            }

            // The num value
            int num = 0;
            while (j < aLen && Character.isDigit(abbr.charAt(j))) {
                num = 10 * num + (abbr.charAt(j) - '0');
                j++;
            }

            // Increment word pinter by num.
            i += num;
        }

        // If both i and j pointers are at end, then we have a valid word abbreviation
        return i == wLen && j == aLen;
    }

    /**
     * KREVSKY SOLUTION:
     * idea:
     * use two pointers approach:
     * pointer i - for word
     * pointer j - for abbr
     * number - substring of abbr that consists of digits
     * <p>
     * time to solve ~ 20+ mins
     * time ~ O(n)
     * space ~ O(1)
     * <p>
     * 3 attempts:
     * - extra "j++" inside for-loop
     * - incorrect final condition: wrote i == word.length(), but not i + number == word.length()
     */
    public boolean validWordAbbreviation(String word, String abbr) {
        int i = 0;    //for word
        int number = 0;

        char[] abbrChars = abbr.toCharArray();
        for (int j = 0; j < abbrChars.length; j++) {
            char c = abbrChars[j];
            if (Character.isDigit(c)) {
                //check is it is leading zero
                if (c == '0' && number == 0) return false;

                number = 10 * number + (c - '0');
            } else {
                //i.e. c is letter
                i += number;
                if (i >= word.length()) return false;

                if (c != word.charAt(i)) return false;

                i++;
                number = 0;
            }
        }

        //for case when abbr finishes with number we need to add number to i and compare to word.length()
        return i + number == word.length();
    }
}
