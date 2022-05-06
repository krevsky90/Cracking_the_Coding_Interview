package data_structures.chapter4_trees_n_graphs.extra;

import java.util.*;

/**
 * https://www.youtube.com/watch?v=AOoZsQFdwm8&list=PLNmW52ef0uwupxKyYvhvF7yG4nQGvO15b&index=4
 * Byte by Byte: How to Solve Ordering Problems Using Topological Sort
 * <p>
 * You are given a list of strings WORDS from the alien language's dictionary,
 * where the strings in WORDS are sorted lexicographically by the rules of this new language.
 * Return a string of the unique letters in the new alien language sorted in lexicographically increasing order by the new language's rules.
 * If there is no solution, return "". If there are multiple solutions, return any of them.
 * <p>
 * A string s is lexicographically smaller than a string t if at the first letter where they differ,
 * the letter in s comes before the letter in t in the alien language.
 * If the first min(s.length, t.length) letters are the same, then s is smaller if and only if s.length < t.length
 * <p>
 * Example 1:
 * words: ["wrt", "wrf", "er", "ett", "rftt"]
 * Output: "wertf"
 * <p>
 * Example 2:
 * words: ["z", "x"]
 * Output: "zx"
 */

//
public class AlienDictionary {
    public static void main(String[] args) {
        List<String> words = Arrays.asList("wrt", "wrf", "er", "ett", "rftt");
        String alienDictionary = alienDictionary(words);
        System.out.println(alienDictionary);
    }

    /**
     * We use topological sort
     * the same thing is applied to data_structures/chapter4_trees_n_graphs/Problem4_7.java
     */
    public static String alienDictionary(List<String> words) {
        Map<Character, Set<Character>> graph = buildGraph(words);
        int length = graph.size();

        LinkedList<Character> noDependencies = new LinkedList<>();
        for (Character c : graph.keySet()) {
            if (graph.get(c).isEmpty()) {
                noDependencies.add(c);
            }
        }

        List<Character> result = new ArrayList<>(length);
        while (!noDependencies.isEmpty()) {
            Character letter = noDependencies.pop();
            result.add(letter);
            //remove dependencies that points to 'c'
            for (Character c : graph.keySet()) {
                if (result.contains(c)) {   //otherwise var c will be added to noDependencies again and again!
                    continue;
                }
                graph.get(c).remove(letter);
                if (graph.get(c).isEmpty()) {
                    noDependencies.add(c);
                }
            }
        }

        //check that our result is valid
        if (result.size() != length) {
            return "";
        }

        //convert to String
        StringBuilder sb = new StringBuilder();
        for (Character c : result) {
            sb.append(c);
        }
        return sb.toString();
    }

    public static Map<Character, Set<Character>> buildGraph(List<String> words) {
        Map<Character, Set<Character>> graph = new HashMap<>();

        //fill map
        for (String w : words) {
            char[] symbolArr = w.toCharArray();
            for (Character c : symbolArr) {
                if (graph.get(c) == null) {
                    graph.put(c, new HashSet<Character>());
                }
            }
        }

        for (int i = 0; i < words.size() - 1; i++) {
            //compare word.get(i) and word.get(i+1)
            //find the first different symbol
            int j = 0;
            int minLength = Math.min(words.get(i).length(), words.get(i + 1).length());
            while (j < minLength) {
                if (words.get(i).charAt(j) != words.get(i + 1).charAt(j)) {
                    graph.get(words.get(i + 1).charAt(j)).add(words.get(i).charAt(j));
                    break;
                }
                j++;
            }
        }

        return graph;
    }
}
