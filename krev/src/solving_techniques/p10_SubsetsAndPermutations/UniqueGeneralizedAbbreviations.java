package solving_techniques.p10_SubsetsAndPermutations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/639dc70fef27e08651fb4a59
 * OR
 * https://leetcode.com/problems/generalized-abbreviation (medium) (locked)
 * <p>
 * Given a word, write a function to generate all of its unique generalized abbreviations.
 * <p>
 * A generalized abbreviation of a word can be generated by replacing each substring of the word with the count of characters in the substring.
 * Take the example of ?ab? which has four substrings: ??, ?a?, ?b?, and ?ab?.
 * After replacing these substrings in the actual word by the count of characters,
 * we get all the generalized abbreviations: ?ab?, ?1b?, ?a1?, and ?2?.
 * <p>
 * Note: All contiguous characters should be considered one substring
 */
public class UniqueGeneralizedAbbreviations {
    public static void main(String[] args) {
        String word = "word";
        //expected result
        //["4","3d","2r1","2rd","1o2","1o1d","1or1","1ord","w3","w2d","w1r1","w1rd","wo2","wo1d","wor1","word"]
        String[] res = new UniqueGeneralizedAbbreviations().generateAbbreviations(word);
        Arrays.stream(res).forEach(s -> System.out.print(s + " "));
    }

    /**
     * KREVSKY SOLUTION
     * idea: binary enumeration
     * time to solve + debug ~ 11 + 7 ~ 18 mins
     *
     * time ~ O(n*2^n)
     * space ~ O(n), where n = word.length()
     *
     * 1 attempt
     */
    public String[] generateAbbreviations(String word) {
        int cnt = (int) Math.pow(2, word.length());
        String[] result = new String[cnt];
        for (int i = 0; i < cnt; i++) {
            result[i] = generateWord(word, i);
        }

        return result;
    }

    //use the idea like Problem8_4 # getAllSetCombinatorial
    private String generateWord(String word, int mask) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        int temp = mask;
        int num = 0;
        while (temp > 0) {
            if ((temp & 1) == 1) {
                num++;
            } else {
                if (num != 0) sb.append(num);
                num = 0;
                sb.append(word.charAt(i));
            }

            temp = temp >> 1;
            i++;
        }

        if (num != 0) sb.append(num);
        sb.append(word.substring(i));

        return sb.toString();
    }

    /**
     * SOLUTION #1.2
     * the same logic but more beauty code
     * info: https://leetcode.ca/2016-10-15-320-Generalized-Abbreviation/
     */
    public List<String> generateAbbreviations2(String word) {
        int n = word.length();
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < 1 << n; ++i) {
            StringBuilder s = new StringBuilder();
            int cnt = 0;
            for (int j = 0; j < n; ++j) {
                if ((i >> j & 1) == 1) {
                    ++cnt;
                } else {
                    if (cnt > 0) {
                        s.append(cnt);
                        cnt = 0;
                    }
                    s.append(word.charAt(j));
                }
            }
            if (cnt > 0) {
                s.append(cnt);
            }
            ans.add(s.toString());
        }
        return ans;
    }
}
