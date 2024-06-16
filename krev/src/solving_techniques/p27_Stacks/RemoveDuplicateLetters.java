package solving_techniques.p27_Stacks;

import java.util.*;

/**
 * 316. Remove Duplicate Letters
 * https://leetcode.com/problems/remove-duplicate-letters (medium)
 * #Company: Apple, Oracle, Microsoft
 *
 * Given a string s, remove duplicate letters so that every letter appears once and only once.
 * You must make sure your result is the smallest in lexicographical order
 *  among all possible results.
 *
 * Example 1:
 * Input: s = "bcabc"
 * Output: "abc"
 *
 * Example 2:
 * Input: s = "cbacdcbc"
 * Output: "acdb"
 *
 * Constraints:
 * 1 <= s.length <= 10^4
 * s consists of lowercase English letters.
 */
public class RemoveDuplicateLetters {
    public static void main(String[] args) {
        String s1 = "bcabc";
        System.out.println(new RemoveDuplicateLetters().removeDuplicateLetters(s1));

        String s2 = "cbacdcbc";
        System.out.println(new RemoveDuplicateLetters().removeDuplicateLetters(s2));
    }
    /**
     * NOT SOLVED by myself
     * info: https://www.youtube.com/watch?v=GOwp3rctrFI&list=PLUPSMCjQ-7oeyhZZ7xjXPQmWEn1EQUiME&index=18
     * idea #1: store map: c -> max index of this char
     * idea #2: store stack + set of used letters
     * idea #3: for each letter:
     * - if it is already used - skip it
     * - if current letter < stack.peek() and we can take the letter 'stack.peek()' later => remove letter from stack and set
     * - add current letter to stack and set of used letters
     *
     * time to solve ~ 30 mins
     *
     * time ~ O(n)
     * space ~ O(n)
     *
     * 4 attempts:
     * - forgot while
     * - forgot sb.reverse()
     * - got infinite loop since checked 'i < map.get(stack.peek())' inside the loop
     *
     * BEATS time = 62%
     * BEATS space = 43%
     */
    public String removeDuplicateLetters(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int len = s.length();
        for (int i = len - 1; i >=0; i--) {
            if (!map.containsKey(s.charAt(i))) {
                map.put(s.charAt(i), i);
            }
        }

        Stack<Character> stack = new Stack<>();
        Set<Character> usedLetters = new HashSet<>();
        for (int i = 0; i < len; i++) {
            Character c = s.charAt(i);
            if (usedLetters.contains(c)) {
                continue;
            }

            while (!stack.isEmpty() && stack.peek() > c && i < map.get(stack.peek())) {
                usedLetters.remove(stack.pop());
            }

            stack.add(c);
            usedLetters.add(c);
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        return sb.reverse().toString();
    }
}
