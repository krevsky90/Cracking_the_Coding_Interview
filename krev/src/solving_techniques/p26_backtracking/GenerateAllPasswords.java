package solving_techniques.p26_backtracking;

import java.util.*;

/**
 * https://leetcode.com/discuss/interview-experience/6084864/Google-L4-interview-expereince
 *
 * Additional HC Round
 *
 * Generate brute force password attack list from "bad" keylogger and keyword list.
 * Imagine that you have a "bad" or "weak" keylogger which only has a counter (map)
 * of each key and the number of times it was pressed in the password, unordered.
 * For instance, it might be a camera pointed at the keyboard, and you can't quite
 * tell the order of the keys. So something like this:
 *
 *
 * {'a': 4, 'c': 1, 'e': 1, '1': 1}
 * Your goal, as an elite hacker, is to generate a list of possible passwords for a
 * brute force attack.
 */
public class GenerateAllPasswords {
    public static void main(String[] args) {
        Map<Character, Integer> map = new HashMap<>();
        map.put('a', 2);
        map.put('c', 1);
        map.put('e', 1);
        map.put('1', 1);

        GenerateAllPasswords obj = new GenerateAllPasswords();

        List<String> passwords = obj.generatePasswords(map);
        passwords.stream().sorted(String::compareTo).forEach(System.out::println);

        System.out.println(passwords.size());

    }

    /**
     * KREVSKY SOLUTION:
     * idea: backtracking
     *
     * time to solve ~ 10-15 mins
     *
     * 1 attempt
     *
     * NOT TESTED AUTOMATICALLY, but simple examples give correct answer
     */
    List<String> generatePasswords(Map<Character, Integer> map) {
        List<String> passwords = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        int capacity = 0;
        for (int i : map.values()) {
            capacity += i;
        }

        generatePasswords(map, sb, capacity, passwords);

        return passwords;
    }

    private void generatePasswords(Map<Character, Integer> map, StringBuilder sb, int capacity, List<String> passwords) {
        if (capacity == 0) {
            passwords.add(sb.toString());
            return;
        }

        for (char c : map.keySet()) {
            if (map.get(c) > 0) {
                sb.append(c);
                map.put(c, map.getOrDefault(c, 0) - 1);
                generatePasswords(map, sb, capacity - 1, passwords);
                //revert changes
                map.put(c, map.getOrDefault(c, 0) + 1);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }
}
