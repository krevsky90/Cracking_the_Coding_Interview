package solving_techniques.p20_TopologicalSort;

import java.util.*;

/**
 * 444. Sequence Reconstruction (medium)
 * https://leetcode.com/problems/sequence-reconstruction/
 * <p>
 * #Company (20.06.2025): 0 - 6 months Google 4
 * <p>
 * You are given an integer array nums of length n where nums is a permutation of the integers in the range [1, n]. You are also given a 2D integer array sequences where sequences[i] is a subsequence of nums.
 * <p>
 * Check if nums is the shortest possible and the only supersequence. The shortest supersequence is a sequence with the shortest length and has all sequences[i] as subsequences. There could be multiple valid supersequences for the given array sequences.
 * <p>
 * For example, for sequences = [[1,2],[1,3]], there are two shortest supersequences, [1,2,3] and [1,3,2].
 * While for sequences = [[1,2],[1,3],[1,2,3]], the only shortest supersequence possible is [1,2,3]. [1,2,3,4] is a possible supersequence but not the shortest.
 * Return true if nums is the only shortest supersequence for sequences, or false otherwise.
 * <p>
 * A subsequence is a sequence that can be derived from another sequence by deleting some or no elements without changing the order of the remaining elements.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,3], sequences = [[1,2],[1,3]]
 * Output: false
 * Explanation: There are two possible supersequences: [1,2,3] and [1,3,2].
 * The sequence [1,2] is a subsequence of both: [1,2,3] and [1,3,2].
 * The sequence [1,3] is a subsequence of both: [1,2,3] and [1,3,2].
 * Since nums is not the only shortest supersequence, we return false.
 * Example 2:
 * <p>
 * Input: nums = [1,2,3], sequences = [[1,2]]
 * Output: false
 * Explanation: The shortest possible supersequence is [1,2].
 * The sequence [1,2] is a subsequence of it: [1,2].
 * Since nums is not the shortest supersequence, we return false.
 * Example 3:
 * <p>
 * Input: nums = [1,2,3], sequences = [[1,2],[1,3],[2,3]]
 * Output: true
 * Explanation: The shortest possible supersequence is [1,2,3].
 * The sequence [1,2] is a subsequence of it: [1,2,3].
 * The sequence [1,3] is a subsequence of it: [1,2,3].
 * The sequence [2,3] is a subsequence of it: [1,2,3].
 * Since nums is the only shortest supersequence, we return true.
 * <p>
 * <p>
 * Constraints:
 * n == nums.length
 * 1 <= n <= 104
 * nums is a permutation of all the integers in the range [1, n].
 * 1 <= sequences.length <= 104
 * 1 <= sequences[i].length <= 104
 * 1 <= sum(sequences[i].length) <= 105
 * 1 <= sequences[i][j] <= n
 * All the arrays of sequences are unique.
 * sequences[i] is a subsequence of nums.
 */
public class SequenceReconstruction {
    /**
     * KREVSKY SOLUTION:
     * idea: Khan's algorithm for topological sort
     * <p>
     * time to solve ~ 30 mins
     *
     * time ~ O(V+N).
     *      since in step ‘d’, each number can become a source only once and each edge (a rule) will be accessed and removed once.
     *      Therefore, the time complexity of the above algorithm will be O(V+E),
     *      where ‘V’ is the count of distinct numbers and ‘E’ is the total number of the rules.
     *      Since, at most, each pair of numbers can give us one rule, we can conclude that the upper bound for the rules is O(N)
     *      where ‘N’ is the count of numbers in all sequences. So, we can say that the time complexity of our algorithm is O(V+N)
     *
     * space  ~ O(V+N).
     * 2 attempts:
     * - placed 'if (q.size() > 1) return false;' incorrectly (only before while-loop)
     *
     * BEATS ~ 16%
     */
    public boolean sequenceReconstruction(int[] nums, List<List<Integer>> sequences) {
        Set<List<Integer>> edges = new HashSet<>();
        Set<Integer> vertices = new HashSet<>();
        for (List<Integer> seq : sequences) {
            vertices.add(seq.get(0));
            for (int i = 1; i < seq.size(); i++) {
                edges.add(Arrays.asList(seq.get(i - 1), seq.get(i)));
                vertices.add(seq.get(i));
            }
        }

        if (vertices.size() < nums.length) return false;

        Map<Integer, Integer> indegrees = new HashMap<>();
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i : vertices) {
            map.put(i, new ArrayList<>());
            indegrees.put(i, 0);
        }

        for (List<Integer> edge : edges) {
            map.get(edge.get(0)).add(edge.get(1));
            indegrees.put(edge.get(1), indegrees.get(edge.get(1)) + 1);
        }

        Queue<Integer> q = new LinkedList<>();
        for (int key : indegrees.keySet()) {
            if (indegrees.get(key) == 0) {
                q.add(key);
            }
        }

        List<Integer> result = new ArrayList<>();
        while (!q.isEmpty()) {
            if (q.size() > 1) return false;
            int el = q.poll();
            result.add(el);

            for (int child : map.get(el)) {
                indegrees.put(child, indegrees.get(child) - 1);
                if (indegrees.get(child) == 0) {
                    q.add(child);
                }
            }
        }

        //here result.size() must be = nums.length (otherwise we would return 'false' earlier)
        for (int i = 0; i < nums.length; i++) {
            if (result.get(i) != nums[i]) return false;
        }

        return true;
    }

    /**
     * optimization: compare nums[idx] = el
     */
    public boolean sequenceReconstruction2(int[] nums, List<List<Integer>> sequences) {
        Set<List<Integer>> edges = new HashSet<>();
        Set<Integer> vertices = new HashSet<>();
        int n = nums.length;
        for (List<Integer> seq : sequences) {
            if (seq.get(0) < 1 || seq.get(0) > n) return false; //might speed up
            vertices.add(seq.get(0));
            for (int i = 1; i < seq.size(); i++) {
                if (seq.get(i) < 1 || seq.get(i) > n) return false; //might speed up
                edges.add(Arrays.asList(seq.get(i-1), seq.get(i)));
                vertices.add(seq.get(i));
            }
        }

        if (vertices.size() < nums.length) return false;

        Map<Integer, Integer> indegrees = new HashMap<>();
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i : vertices) {
            map.put(i, new ArrayList<>());
            indegrees.put(i, 0);
        }

        for (List<Integer> edge : edges) {
            map.get(edge.get(0)).add(edge.get(1));
            indegrees.put(edge.get(1), indegrees.get(edge.get(1)) + 1);
        }

        Queue<Integer> q = new LinkedList<>();
        for (int key : indegrees.keySet()) {
            if (indegrees.get(key) == 0) {
                q.add(key);
            }
        }

        int idx = 0;
        while (!q.isEmpty()) {
            if (q.size() > 1) return false;
            int el = q.poll();
            if (nums[idx] != el) return false;
            idx++;

            for (int child : map.get(el)) {
                indegrees.put(child, indegrees.get(child) - 1);
                if (indegrees.get(child) == 0) {
                    q.add(child);
                }
            }
        }

        return idx == n;
    }
}


