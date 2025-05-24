package solving_techniques.p29_Graphs;

import java.util.*;

/**
 * https://www.tryexponent.com/practice/prepare/sentence-similarity
 * <p>
 * Determine if two sentences are similar. Two sentences are similar if they have the same length and each pair of corresponding words in the two sentences is similar. The similarity between words is defined by the provided list of similar word pairs. A word is always similar to itself.
 * <p>
 * For example, if we have the list of similar word pairs as [("great", "good"), ("acting","drama"), ("skills","talent")], then the sentences "You have great acting skills" and "You have good drama talent" are similar.
 * <p>
 * Examples:
 * sentence1 = ["Let's", "code", "in", "Python"]
 * sentence2 = ["Let's", "program", "in", "Python"]
 * similarPairs = [
 * ("code", "program"),
 * ]
 * output: true
 * <p>
 * sentence1 = ["I", "love", "to", "play", "football"]
 * sentence2 = ["I", "love", "playing", "soccer"]
 * similarPairs = [("play", "playing"), ("football", "soccer")]
 * output: false, different sentence lengths
 * <p>
 * sentence1 = ["Do", "you", "like", "coffee"]
 * sentence2 = ["Do", "you", "love", "coffee"]
 * similarPairs = [
 * ("like", "enjoy"),
 * ("coffee", "tea"),
 * ]
 * output: false, "like" is not similar to "love" based on the given pairs.
 * <p>
 * sentence1 = ["I", "really", "love", "leetcode", "and", "apples"]
 * sentence2 = ["I", "so", "like", "codesignal", "and", "oranges"]
 * similarPairs = [
 * ("very", "so"),
 * ("love", "adore"),
 * ("really", "very"),
 * ("leetcode", "codesignal"),
 * ("apples", "oranges"),
 * ("like", "adore"),
 * ]
 * output: true, "like" is similar to "love", because both are similar to "adore".
 */
public class SentenceSimilarity {
    //Time Complexity:
    // Let n be the maximum number of words in the sentences, and m be the number of similar word pairs.
    // Constructing the graph takes O(m) time.
    // Then, for each word pair, the DFS takes O(n) time in the worst case, resulting in a total time complexity of O(n + m). ???
    //
    //Space Complexity:
    // In the worst case, the graph can contain O(m) edges.
    // Additionally, in the DFS, the space complexity is O(n) due to the recursion stack or auxiliary data structures to track visited nodes.
    // Therefore, the overall space complexity is O(n + m).
    public static boolean areSentencesSimilar(String[] sentence1, String[] sentence2, String[][] similarPairs) {
        if (sentence1 == null && sentence2 == null) return true;
        if (sentence1 == null && sentence2 != null) return false;
        if (sentence1 != null && sentence2 == null) return false;

        if (sentence1.length != sentence2.length) return false;
        int n = sentence1.length;

        Map<String, Set<String>> map = new HashMap<>();
        for (String[] pair : similarPairs) {
            map.putIfAbsent(pair[0], new HashSet<>());
            map.putIfAbsent(pair[1], new HashSet<>());
        }

        for (String[] pair : similarPairs) {
            map.get(pair[0]).add(pair[1]);
            map.get(pair[1]).add(pair[0]);
        }

        for (int i = 0; i < n; i++) {
            String temp1 = sentence1[i];
            String temp2 = sentence2[i];
            if (!checkSimilarWordsBFS(temp1, temp2, map)) {
                System.out.println(temp1);
                return false;
            }
        }

        return true;
    }

    private static boolean checkSimilarWordsBFS(String w1, String w2, Map<String, Set<String>> map) {
        Queue<String> q = new LinkedList<>();
        q.add(w1);
        Set<String> visited = new HashSet<>();
        visited.add(w1);

        while (!q.isEmpty()) {
            String temp = q.poll();
            if (temp.equals(w2)) return true;

            for (String adjNode : map.getOrDefault(temp, new HashSet<>())) {
                if (!visited.contains(adjNode)) {
                    q.add(adjNode);
                    visited.add(adjNode);
                }
            }
        }

        return false;
    }

    //if we use DFS
    private static boolean checkSimilarWordsDFS(String w1, String w2, Map<String, Set<String>> map, Set<String> visited) {
        if (w1.equals(w2)) return true;

        if (visited.contains(w1)) return false;

        visited.add(w1);

        for (String temp : map.getOrDefault(w1, new HashSet<>())) {
            if (!visited.contains(temp)) {
                if (checkSimilarWordsDFS(temp, w2, map, visited)) {
                    return true;
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        String[] sentence1 = {"i", "really", "love", "leetcode", "and", "apples"};
        String[] sentence2 = {"i", "so", "like", "codesignal", "and", "oranges"};
        String[][] similarPairs = {
                {"very", "so"},
                {"love", "adore"},
                {"really", "very"},
                {"leetcode", "codesignal"},
                {"apples", "oranges"},
                {"like", "adore"},
        };

        boolean result = areSentencesSimilar(sentence1, sentence2, similarPairs);
        System.out.println(result); // Expected output: true
    }
}
