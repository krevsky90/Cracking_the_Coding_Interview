package data_structures.chapter4_trees_n_graphs.extra;

import java.util.*;

/**
 * 269. Alien Dictionary (hard) (locked)
 * https://leetcode.com/problems/alien-dictionary
 * OR
 * https://leetcode.ca/all/269.html
 *
 * #Company: Airbnb Amazon Apple Bloomberg Cohesity Facebook Flipkart Google Microsoft Oracle Pinterest Pocket Gems Snapchat Square Twitter Uber VMware
 *
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
public class AlienDictionary {
    public static void main(String[] args) {
        List<String> words = Arrays.asList("wrt", "wrf", "er", "ett", "rftt");
        System.out.println(alienDictionary(words));
    }

    /**
     * KREVSKY #2
     * info: https://www.youtube.com/watch?v=0nI8_RbVeb4&list=PLUPSMCjQ-7odm9bh0iW6WOCfS6wiJETnt&index=18
     * idea:
     * 1) build list of letters
     * 2) build adjMap: letter -> list of 'further' letters (since it is alphabet, it is expected that the list will contain 0 or 1 element if data is correct
     * 3) use topological sort DFS function:
     * - keep visited letters
     * - keep cycle letters
     * - keep sb to store the final letter, i.e. we processed its children
     *
     * edge cases:
     * 1) if given words[] is not lexicographically sorted (i.e. incorrectness is like apple, app), then return ""
     *
     * N - words.length, S - max length of the words
     * time ~ O(N*M) + O(N*M) + O(V + E), where V - letters.length(), E = min(V^2, N - 1),
     *      since it is impossible to have more than V^2 edges between V nodes
     *      also we check N-1 pairs of words. each of them gives at most 1 edge
     *
     *      so O(N*M + V + min(V^2, N)) ~ O(N*M + min(V^2, N))
     *          if V^2 > N:
     *              since N*M > N => O(N*M + N) ~ O(N*M)
     *          else (i.e. V^2 < N)
     *              since V^2 < N < N*M => O(N*M + V^2) ~ O(N*M)
     *
     * space ~ O(E + V). since V <= 26 and E < V^2 <= 26^2 = 576 => space ~ O(1)
     *
     * 3 attempts:
     * - not all letters are in keySet => need to traverse through the whole set of letters
     * - did not consider edge case #1
     *
     * BEATS ~ 96%
     *
     */
    public String alienOrder(String[] words) {
        Map<Character, Set<Character>> adjMap = new HashMap<>();
        Set<Character> letters = new HashSet<>();
        for (int i = 0; i < words.length; i++) {
            for (char c : words[i].toCharArray()) {
                letters.add(c);
            }
        }

        for (int i = 0; i < words.length - 1; i++) {
            String word1 = words[i];
            String word2 = words[i + 1];

            boolean hasSamePrefix = true; //if word1 and word2 have the same prefix which length =  Math.min(word1.length(), word2.length())
            for (int j = 0; j < Math.min(word1.length(), word2.length()); j++) {
                if (word1.charAt(j) != word2.charAt(j)) {
                    adjMap.putIfAbsent(word1.charAt(j), new HashSet<>());
                    adjMap.get(word1.charAt(j)).add(word2.charAt(j));
                    hasSamePrefix = false;
                    break;
                }
            }

            //edge case:the words are not sorted lexicographically
            //apple
            //app
            if (hasSamePrefix && word1.length() > word2.length()) return "";
        }

        StringBuilder sb = new StringBuilder();

        Set<Character> visited = new HashSet<>();
        Set<Character> cycle = new HashSet<>();

        for (Character c : letters) {
            if (!visited.contains(c)) {
                if (!topologicalSortDfs(adjMap, c, visited, cycle, sb)) {
                    return "";
                }
            }
        }

        return sb.reverse().toString();
    }
    //    t -> f
    //    w -> e
    //    r- > t
    //    e -> r

    // cycle =
    // visited = f, t, r e w
    // sb = ftrew

    private boolean topologicalSortDfs(Map<Character, Set<Character>> adjMap, Character c, Set<Character> visited, Set<Character> cycle, StringBuilder sb) {
        if (visited.contains(c)) return true;

        if (cycle.contains(c)) return false;

        cycle.add(c);

        for (Character temp : adjMap.getOrDefault(c, new HashSet<>())) {
            if (!topologicalSortDfs(adjMap, temp, visited, cycle, sb)) {
                return false;
            }
        }

        cycle.remove(c);
        visited.add(c);
        sb.append(c);

        return true;
    }

    /**
     * We use topological sort
     * the same thing is applied to data_structures/chapter4_trees_n_graphs/Problem4_7.java
     *
     * info: https://www.youtube.com/watch?v=AOoZsQFdwm8&list=PLNmW52ef0uwupxKyYvhvF7yG4nQGvO15b&index=4
     *
     * idea:
     * 1) build graph based on:
     * since the words are in lexicographical order, then we compare i-th word with i+1-th and the first difference gives us relation between the letters
     * 1.2) store graph: letter -> list of previous letters
     *
     * 2) add letters which don't have previous letters to the Queue, poll them, add to the result
     * 2.2) remove such letters from the Sets of the other letters in the graph and check if updated Set is empty. If yes - add its letter to the queue
     *
     * 3) if the initial graph.size() != result.size() => we have loop => return ""
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
