package solving_techniques.different;

import java.util.ArrayList;
import java.util.List;

/**
 * 68. Text Justification (hard)
 * https://leetcode.com/problems/text-justification
 *
 * #Company: (24.12.2024) 0 - 3 months Anyscale 11 Uber 8 Moveworks 5 Google 4 Roblox 4 Karat 4 Capital One 3 Amazon 2 Bloomberg 2 Airbnb 2 0 - 6 months Databricks 5 Sentry 5 Meta 3 Microsoft 3 Apple 3 Coursera 3 LinkedIn 2 Visa 2 Atlassian 2 Coinbase 2 6 months ago TikTok 9 Zoho 6 Adobe 5 Robinhood 3 Yahoo 3 Samsara 3 Oracle 2 Netflix 2 DevRev 2 Rippling 2
 *
 * Given an array of strings words and a width maxWidth, format the text such that each line has exactly maxWidth characters and is fully (left and right) justified.
 * You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces ' ' when necessary so that each line has exactly maxWidth characters.
 * Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line does not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.
 * For the last line of text, it should be left-justified, and no extra space is inserted between words.
 * Note:
 * A word is defined as a character sequence consisting of non-space characters only.
 * Each word's length is guaranteed to be greater than 0 and not exceed maxWidth.
 * The input array words contains at least one word.
 *
 * Example 1:
 * Input: words = ["This", "is", "an", "example", "of", "text", "justification."], maxWidth = 16
 * Output:
 * [
 *    "This    is    an",
 *    "example  of text",
 *    "justification.  "
 * ]
 *
 * Example 2:
 * Input: words = ["What","must","be","acknowledgment","shall","be"], maxWidth = 16
 * Output:
 * [
 *   "What   must   be",
 *   "acknowledgment  ",
 *   "shall be        "
 * ]
 * Explanation: Note that the last line is "shall be    " instead of "shall     be", because the last line must be left-justified instead of fully-justified.
 * Note that the second line is also left-justified because it contains only one word.
 *
 * Example 3:
 * Input: words = ["Science","is","what","we","understand","well","enough","to","explain","to","a","computer.","Art","is","everything","else","we","do"], maxWidth = 20
 * Output:
 * [
 *   "Science  is  what we",
 *   "understand      well",
 *   "enough to explain to",
 *   "a  computer.  Art is",
 *   "everything  else  we",
 *   "do                  "
 * ]
 *
 * Constraints:
 * 1 <= words.length <= 300
 * 1 <= words[i].length <= 20
 * words[i] consists of only English letters and symbols.
 * 1 <= maxWidth <= 100
 * words[i].length <= maxWidth
 */
public class TextJustification {
    public static void main(String[] args) {
        TextJustification obj = new TextJustification();
        String[] words = {"This", "is", "an", "example", "of", "text", "justification."};
        int maxWidth = 16;

        List<String> result = obj.fullJustify(words, maxWidth);
        System.out.println("");
    }

    /**
     * KREVSKY SOLUTION:
     * idea:
     * 1) group words where each group will form String of output list
     * 2.1) if group contains 1 word => line = word + spaces up ti the end
     * 2.2) else
     *      - calc min amount of spaces between the words
     *      - calc min amount of extra spaces
     *      - calc amount of remain extra spaces
     *      append words and (1 + min amount of extra spaces + (optionally) 1 remain extra space if their amount > 0)
     *
     * time to solve ~ 1h
     *
     * Let k - average length of the word, n = words.length
     * time for formLine ~ O(maxWidth) in the worst case
     * we will call formLine method n/(maxWidth/k) times = n*k/maxWidth
     * then total complexity ~ maxWidth * n*k/maxWidth = n*k
     *
     * space ~ O(maxWidth) if we don't consider space for the list (result)
     *
     * 3 attempts:
     * - did not handle case end = start
     * - incorrectly counted the amount of words in the line (had to look at the official solution)
     *
     * BEATS ~ 100%
     */
    public List<String> fullJustify(String[] words, int maxWidth) {
        int start = 0;
        int id = 0;
        List<String> result = new ArrayList<>();
        while (id < words.length) {
            int rowLength = 0;
            while (id < words.length && rowLength + words[id].length() <= maxWidth) {
                rowLength += words[id].length() + 1;
                id++;
            }

            boolean isLastLine = id == words.length;
            String tempResult = formLine(words, start, id - 1, isLastLine, maxWidth);
            result.add(tempResult);

            start = id;
        }

        return result;
    }

    private String formLine(String[] words, int start, int end, boolean isLastLine, int maxWidth) {
        StringBuilder sb = new StringBuilder();

        if (isLastLine) {
            for (int i = start; i < end; i++) {
                sb.append(words[i]).append(" ");
            }
            sb.append(words[end]);
            int numOfSpaces = maxWidth - sb.length();
            sb.append(repeat(numOfSpaces));
        } else {
            int totalWordLength = 0;
            for (int i = start; i <= end; i++) {
                totalWordLength += words[i].length();
            }

            int totalMinSpaces = end - start;
            if (totalMinSpaces > 0) {
                int totalExtraSpaces = maxWidth - totalWordLength - totalMinSpaces;
                int minExtraSpaces = totalExtraSpaces / totalMinSpaces;
                int remain = totalExtraSpaces % totalMinSpaces;


                for (int i = start; i < end; i++) {
                    sb.append(words[i]).append(repeat(1 + minExtraSpaces));
                    if (remain > 0) {
                        sb.append(" ");
                        remain--;
                    }
                }
                sb.append(words[end]);
            } else {
                //i.e. the only one word can be placed in the line
                sb.append(words[end]);
                int numOfSpaces = maxWidth - sb.length();
                sb.append(repeat(numOfSpaces));
            }
        }

        return sb.toString();
    }

    private String repeat(int k) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < k; i++) {
            sb.append(" ");
        }
        return sb.toString();
    }
}
