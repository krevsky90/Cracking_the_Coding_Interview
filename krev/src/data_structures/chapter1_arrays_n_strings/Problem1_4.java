package data_structures.chapter1_arrays_n_strings;

/**
 * p.103
 *
 * 1.4. Palindrome Permutation: Given a string, write a function to check if it is a permutation of a palindrome.
 * A palindrome is a word or phrase that is the same forwards and backwards. A permutation
 * is a rearrangement of letters. The palindrome does not need to be limited to just dictionary words.
 * EXAMPLE
 * Input: Tact Coa
 * Output: True (permutations: "taco cat". "atco cta". etc.)
 * Hints: #106, #121, #134, #136
 *
 * ASSUMPTION:
 * 1) spaces doesn't affect palindrome rule
 * 2) case insensitive
 * 3) phrase consists of lower letters only
 *
 */
public class Problem1_4 {
    public static void main(String[] args) {
        String s = "tact coa";
        System.out.println(isPalindromePermutation2(s));
    }

    /**
     * SOLUTION p.207:
     * We need to have an even number of almost all characters, so that half can be on one side and half can be on the other side.
     * At most one character (the middle character) can have an odd count.
     *
     * We use a hash table to count how many times each character appears.
     * We iterate through the hash table (by separate loop or using the loop for filling the hash table as in isPalindromePermutation2)
     * and ensure that no more than one character has an odd count.
     *
     */
    public static boolean isPalindromePermutation2(String s) {
        int[] table = new int[(int)'z' - (int)'a' + 1];
        char[] chars = s.toCharArray();
        int odd = 0;

        for(char c : chars) {
            int charNumber = getCharNumber(c);
            if (charNumber != -1) {
                table[charNumber]++;
                if (table[charNumber] % 2 == 1) {
                    odd++;
                } else {
                    odd--;
                }
            }
        }

        return odd <= 1;
    }

    private static int getCharNumber(char c) {
        if ('a' <= c && c <= 'z') {
            return 'z' - c;
        }
        return -1;
    }
}
