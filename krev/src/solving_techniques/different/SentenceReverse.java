package solving_techniques.different;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * https://www.tryexponent.com/practice/prepare/sentence-reverse
 * <p>
 * You are given an array of characters arr that consists of sequences of characters separated by space characters.
 * Each space-delimited sequence of characters defines a word.
 * <p>
 * Implement a function reverseWords that reverses the order of the words in the array in the most efficient manner.
 * <p>
 * Explain your solution and analyze its time and space complexities.
 * <p>
 * Example:
 * input:  arr = [ 'p', 'e', 'r', 'f', 'e', 'c', 't', ' ',
 * 'm', 'a', 'k', 'e', 's', ' ',
 * 'p', 'r', 'a', 'c', 't', 'i', 'c', 'e' ]
 * <p>
 * output: [ 'p', 'r', 'a', 'c', 't', 'i', 'c', 'e', ' ',
 * 'm', 'a', 'k', 'e', 's', ' ',
 * 'p', 'e', 'r', 'f', 'e', 'c', 't' ]
 */
public class SentenceReverse {
    /**
     * NOTE! it is similar to https://leetcode.com/problems/reverse-words-in-a-string-ii
     * but a little bit more complex because of leading spaces and extra spaed between words
     */

    /**
     * KREVSKY SOLUTION
     * idea: use Deque to reverse the words
     * time to solve ~ 25 mins
     * time ~ O(n)
     * space ~ O(n)
     * <p>
     * 3 attempts:
     * - used pollLast instead of pollFirst
     */
    char[] reverseWords(char[] arr) {
        // your code goes here
        Deque<Character> q = new LinkedList<>();
        List<Character> resultList = new ArrayList<>();

        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] != ' ') {
                q.addFirst(arr[i]);
            } else {
                while (!q.isEmpty()) {
                    resultList.add(q.pollFirst());
                }
                resultList.add(' ');
            }
        }

        while (!q.isEmpty()) {
            resultList.add(q.pollFirst());
        }

        char[] result = new char[arr.length];
        for (int i = 0; i < arr.length; i++) {
            result[i] = resultList.get(i);
        }

        return result;
    }

    /**
     * Official solution
     * idea:
     * 1) reverse the string
     * 2) reverse the words
     * time ~ O(n)
     * space ~ O(1)
     */
    char[] reverseWordsOfficial(char[] arr) {
        // Reverse all characters
        int n = arr.length;
        mirrorReverse(arr, 0, n - 1);

        // Reverse each word
        Integer wordStart = null;
        for (int i = 0; i < n; i++) {
            if (arr[i] == ' ') {
                if (wordStart != null) {
                    mirrorReverse(arr, wordStart, i - 1);
                    wordStart = null;
                }
            } else if (i == n - 1) {
                if (wordStart != null) {
                    mirrorReverse(arr, wordStart, i);
                }
            } else {
                if (wordStart == null) {
                    wordStart = i;
                }
            }
        }

        return arr;
    }

    // Helper function - reverses the order of items in arr
    // Please note that this is language dependent:
    // If arrays are sent by value, reversing should be done in place
    void mirrorReverse(char[] arr, int start, int end) {
        while (start < end) {
            char tmp = arr[start];
            arr[start] = arr[end];
            arr[end] = tmp;
            start++;
            end--;
        }
    }

}

