package data_structures.chapter1_arrays_n_strings;

/**
 * p.102
 *
 * 1.3. URLify: Write a method to replace all spaces in a string with '%20: You may assume that the string
 * has sufficient space at the end to hold the additional characters
 * (это значит, что (см пример) исходная строка имеет не менее 17 символов, т.к. финальная строка будет иметь 17 символов),
 * and that you are given the "true" length of the string. (Note: If implementing in Java,
 * please use a character array so that you can perform this operation in place)
 * EXAMPLE
 * Input:  "Mr John Smith    ", 13
 * Output: "Mr%20John%20Smith"
 * Hints: #53, #118
 *
 * ASSUMPTION: the string has sufficient space at the end to hold the additional characters
 *
 */
public class Problem1_3 {
    public static void main(String[] args) {
        String s = "Mr John Smith       ";  //20 characters
        char[] arr = s.toCharArray();
        replaceSpaces(arr, 14);
        System.out.println(new String(arr));
    }

    /**
     * SOLUTION p.206-207:
     * The algorithm employs a two-scan approach.
     * 1) In the first scan, we count the number of spaces. By tripling this number, we can compute how many extra characters we will have in the final string.
     * 2) In the second pass, which is done in reverse order, we actually edit the string:
     * when we see a space, we replace it with %20. If there is no space, then we copy the original character.
     *
     */
    public static void replaceSpaces(char[] str, int trueLength) {
        int spaceCount = 0;

        for (int i = 0; i < trueLength; i++) {
            if (str[i] == ' ') {
                spaceCount++;
            }
        }

        int index = trueLength + spaceCount * 2;

        //DO NOT UNDERSTAND THE REASON OF THIS 'IF' CODE!!!
        if (trueLength < str.length) {
            str[trueLength] = '\0'; //end array
        }

        for (int i = trueLength - 1; i >= 0; i--) {
            if (str[i] == ' ') {
                str[index-1] = '0';
                str[index-2] = '2';
                str[index-3] = '%';
                index -= 3;
            } else {
                str[index-1] = str[i];
                index--;
            }
        }
    }
}
