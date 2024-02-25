package solving_techniques.p10_SubsetsAndPermutations;

import java.util.ArrayList;
import java.util.List;

/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/639cb0c8f9c10047d0529044
 * OR
 * 784. Letter Case Permutation
 * https://leetcode.com/problems/letter-case-permutation/
 * <p>
 * Problem Statement
 * Given a string, find all of its permutations preserving the character sequence but changing case.
 * <p>
 * Example 1:
 * Input: "ad52"
 * Output: "ad52", "Ad52", "aD52", "AD52"
 * <p>
 * Example 2:
 * Input: "ab7c"
 * Output: "ab7c", "Ab7c", "aB7c", "AB7c", "ab7C", "Ab7C", "aB7C", "AB7C"
 * <p>
 * Constraints:
 * 1 <= s.length <= 12
 * s consists of lowercase English letters, uppercase English letters, and digits.
 */
public class StringPermutationsByChangingCase {
    /**
     * KREVSKY SOLUTION
     * time to solve ~ 20 mins
     * time to debug ~ 11 mins
     *
     * time ~ O(2^n)
     * space ~ O(2^n)
     * <p>
     * 4 attempts: due to bad knowledge of syntax (Character class etc)
     */
    public List<String> letterCasePermutation(String s) {
        List<String> result = new ArrayList<>();
        letterCasePermutation(s.toCharArray(), result, new StringBuilder(), 0);
        return result;
    }


    // letterCasePermutation(a1b2, [], "", 0)
    //     i = 0
    //     sArr[i] = a
    //     tempStr = "a"
    //     letterCasePermutation(a1b2, [], "a", 1)
    //         i = 1
    //         sArr[i] = 1
    //         tempStr = "a1"
    //         letterCasePermutation(a1b2, [], "a1", 2)
    //             i = 2
    //             sArr[i] = b
    //             //lower case
    //             tempStr = "a1b"
    //             letterCasePermutation(a1b2, [], "a1b", 3)
    //                 i = 3
    //                 sArr[i] = 2
    //                 tempStr = "a1b2"
    //                 letterCasePermutation(a1b2, [], "a1b2", 4)
    //                     result = ["a1b2"]
    //                 tempStr = "a1b"
    //             tempStr = "a1"
    //             //upper case
    //             tempStr = "a1B"
    //             letterCasePermutation(a1b2, ["a1b2"], "a1B", 3)
    //                 i = 3
    //                 sArr[i] = 2
    //                 tempStr = "a1B2"
    //                 letterCasePermutation(a1b2, ["a1b2"], "a1B2", 4)
    //                     result = ["a1b2","a1B2"]
    //                 tempStr = "a1B"
    //  etc....


    private void letterCasePermutation(char[] sArr, List<String> result, StringBuilder tempStr, int start) {
        if (tempStr.length() == sArr.length) {
            result.add(tempStr.toString());
            return;
        }

        if (Character.isLetter(sArr[start])) {
            //lower case
            tempStr.append(Character.toLowerCase(sArr[start]));
            letterCasePermutation(sArr, result, tempStr, start + 1);
            tempStr.deleteCharAt(tempStr.length() - 1);

            //upper case
            tempStr.append(Character.toUpperCase(sArr[start]));
            letterCasePermutation(sArr, result, tempStr, start + 1);
            tempStr.deleteCharAt(tempStr.length() - 1);
        } else {//} if (Character.isDigit(sArr[start])) {
            tempStr.append(sArr[start]);
            letterCasePermutation(sArr, result, tempStr, start + 1);
            tempStr.deleteCharAt(tempStr.length() - 1);
        }
    }
}
