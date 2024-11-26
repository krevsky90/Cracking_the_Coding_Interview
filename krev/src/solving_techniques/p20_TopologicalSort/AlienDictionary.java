package solving_techniques.p20_TopologicalSort;

import java.util.*;

/**
 * 269. Alien Dictionary (hard)
 * https://leetcode.com/problems/alien-dictionary/
 * <p>
 * #Company: Airbnb Amazon Apple Bloomberg Cohesity Facebook Flipkart Google Microsoft Oracle Pinterest Pocket Gems Snapchat Square Twitter Uber VMware
 * <p>
 * There is a new alien language which uses the latin alphabet.
 * However, the order among letters are unknown to you.
 * You receive a list of non-empty words from the dictionary, where words are sorted lexicographically by the rules of this new language.
 * Derive the order of letters in this language.
 * <p>
 * Example 1:
 * Input:
 * [
 * "wrt",
 * "wrf",
 * "er",
 * "ett",
 * "rftt"
 * ]
 * <p>
 * Output: "wertf"
 * <p>
 * Example 2:
 * Input:
 * [
 * "z",
 * "x"
 * ]
 * <p>
 * Output: "zx"
 * <p>
 * Example 3:
 * Input:
 * [
 * "z",
 * "x",
 * "z"
 * ]
 * <p>
 * Output: ""
 * <p>
 * Explanation: The order is invalid, so return "".
 * Note:
 * You may assume all letters are in lowercase.
 * You may assume that if a is a prefix of b, then a must appear before b in the given dictionary.
 * If the order is invalid, return an empty string.
 * There may be multiple valid order of letters, return any one of them is fine.
 */
public class AlienDictionary {
    public static void main(String[] args) {
        String[] words1 = {"wrt", "wrf", "er", "ett", "rftt"};
        String[] words2 = {"z", "x"};
        String[] words3 = {"z", "x", "z"};

        AlienDictionary obj = new AlienDictionary();
        System.out.println(obj.dict(words1));
        System.out.println(obj.dict(words2));
        System.out.println(obj.dict(words3));
    }

    /**
     * KREVSKY SOLUTION:
     * idea:
     * 1) form set of letters and edges
     * 2) build adjMap (i.e. graph)
     * 3) use topological sort DFS (as in src/solving_techniques/p20_TopologicalSort/TopologicalSort.java)
     * <p>
     * time to solve ~ 20 (impl) + 7 (debug)
     *
     * time ~ O(C + V + E), C - total amount of letters in all words, V - amount of letters, E - amount of edges
     * space ~ O(V + E) - to store letters and edges
     *
     * 2 attempts:
     * - forgot j++;
     */
//      "wrt",
//              "wrf",
//              "er",
//              "ett",
//              "rftt"
//
//    edges = {{t,f},{w,e},{r,t},{e,r}}
//
//    adjMap =
//    t -> {f}
//    w -> {e}
//    r -> {t}
//    e -> {r}
//    f -> {}
//
//    letters = t f w e r
//
//            cycle =
//            visited = f,t,r,e,w
//    sb = ftrew
    public String dict(String[] words) {
        List<char[]> edges = new ArrayList<>();

        //1. form edges
        for (int i = 0; i < words.length - 1; i++) {
            int j = 0;
            while (j < Math.min(words[i].length(), words[i + 1].length())) {
                if (words[i].charAt(j) != words[i + 1].charAt(j)) {
                    edges.add(new char[]{words[i].charAt(j), words[i + 1].charAt(j)});
                    break;
                }
                j++;
            }
        }

        //2. form adjLists and set of letters
        Map<Character, List<Character>> adjMap = new HashMap<>();
        for (char[] edge : edges) {
            adjMap.putIfAbsent(edge[0], new ArrayList<>());
            adjMap.putIfAbsent(edge[1], new ArrayList<>());
            adjMap.get(edge[0]).add(edge[1]);
        }
        Set<Character> letters = adjMap.keySet();

        //3. topological sort DFS
        StringBuilder sb = new StringBuilder();
        Set<Character> visited = new HashSet<>();
        Set<Character> cycle = new HashSet<>();

        for (Character c : letters) {
            if (!visited.contains(c)) {
                if (!dfs(adjMap, c, sb, visited, cycle)) {
                    return "";    //since there is a cycle
                }
            }
        }

        return sb.reverse().toString();
    }

    private boolean dfs(Map<Character, List<Character>> adjMap, char c, StringBuilder sb, Set<Character> visited, Set<Character> cycle) {
        if (cycle.contains(c)) return false;    //cycle is found

        if (visited.contains(c)) return true;    //do nothing

        cycle.add(c);
        List<Character> children = adjMap.get(c);
        for (Character child : children) {
            if (!dfs(adjMap, child, sb, visited, cycle)) {
                //cycle
                return false;
            }
        }

        cycle.remove(c);
        visited.add(c);
        sb.append(c);

        return true;    //since there is no cycle
    }
}
