package solving_techniques.p29_Graphs.bidirectionalBFS;

import java.util.*;

/**
 * 752. Open the Lock (medium)
 * https://leetcode.com/problems/open-the-lock
 *
 * #Company (18.06.2025): 0 - 3 months ZIP 3 Google 2 Amazon 2 eBay 2 0 - 6 months Uber 3 6 months ago TikTok 4 Goldman Sachs 4 Meta 3 Microsoft 3 Flipkart 3 CARS24 3 J.P. Morgan 2 ByteDance 2 DE Shaw 2 Zepto 2
 *
 * You have a lock in front of you with 4 circular wheels. Each wheel has 10 slots: '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'.
 * The wheels can rotate freely and wrap around: for example we can turn '9' to be '0', or '0' to be '9'.
 * Each move consists of turning one wheel one slot.
 * The lock initially starts at '0000', a string representing the state of the 4 wheels.
 * You are given a list of deadends dead ends, meaning if the lock displays any of these codes,
 *      the wheels of the lock will stop turning and you will be unable to open it.
 * Given a target representing the value of the wheels that will unlock the lock,
 *      return the minimum total number of turns required to open the lock, or -1 if it is impossible.
 *
 * Example 1:
 * Input: deadends = ["0201","0101","0102","1212","2002"], target = "0202"
 * Output: 6
 * Explanation:
 * A sequence of valid moves would be "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202".
 * Note that a sequence like "0000" -> "0001" -> "0002" -> "0102" -> "0202" would be invalid,
 * because the wheels of the lock become stuck after the display becomes the dead end "0102".
 *
 * Example 2:
 * Input: deadends = ["8888"], target = "0009"
 * Output: 1
 * Explanation: We can turn the last wheel in reverse to move from "0000" -> "0009".
 *
 * Example 3:
 * Input: deadends = ["8887","8889","8878","8898","8788","8988","7888","9888"], target = "8888"
 * Output: -1
 * Explanation: We cannot reach the target without getting stuck.
 *
 * Constraints:
 * 1 <= deadends.length <= 500
 * deadends[i].length == 4
 * target.length == 4
 * target will not be in the list deadends.
 * target and deadends[i] consist of digits only.
 */
public class OpenTheLock {
    /**
     * Unidirectional BFS (as usual)
     *
     * Let's n=10 is the number of slots on a wheel, w=4 is the number of wheels, and d is the number of elements in the deadends array.
     *
     * To initialize visited ~ O(w*d)
     *
     * In the worst case, we might iterate on all n^w unique combinations,
     *      and for each combination, we perform 2w turns. Thus, it will take O(n^w * 2w)
     *
     * total time ~ (w*d + n^w * w) ~ O(w*(d + n^d)) ~ O(4*(d + 10^4))
     * space - the same
     *
     * BEATS ~ 30%
     */
    public int openLock(String[] deadends, String target) {
        // combine deadend and visited combinations in one set
        Set<String> visited = new HashSet<>(Arrays.asList(deadends));

        String start = "0000";
        if (visited.contains(start)) return -1;
        if (start.equals(target)) return 0;

        Queue<String> begin = new LinkedList<>();
        begin.add(start);

        int lvl = 0;
        while (!begin.isEmpty()) {
            int size = begin.size();
            Set<String> newLevelSet = new HashSet<>();
            for (int i = 0; i < size; i++) {
                String node = begin.poll();
                if (node.equals(target)) return lvl;

                for (String adj : getAdjNodes(node)) {
                    if (!visited.contains(adj)) {
                        begin.add(adj);
                        visited.add(adj);
                    }
                }
            }
            lvl++;
        }

        return -1;
    }

    /**
     * BIdirectional BFS (optimized)
     *
     * optimization is like we have time ~ O(b^(dist/2) + b^(dist/2)) ~ O(b^(dist/2))
     * instead of unidirectional BFS which gives us O(b^(dist))
     * where b ~ branching factor, dist - min distance between start and end
     *
     * BEATS ~ 96%
     */
    public int openLockBiBFS(String[] deadends, String target) {
        // combine deadend and visited sets
        Set<String> visited = new HashSet<>(Arrays.asList(deadends));

        String start = "0000";
        if (visited.contains(start)) return -1;
        if (start.equals(target)) return 0;

        Set<String> beginSet = new HashSet<>();
        beginSet.add(start);

        Set<String> endSet = new HashSet<>();
        endSet.add(target);

        int lvl = 1;
        while (!beginSet.isEmpty() && !endSet.isEmpty()) {
            //take the minimal set to optimize solution
            if (beginSet.size() > endSet.size()) {
                Set<String> tmp = beginSet;
                beginSet = endSet;
                endSet = tmp;
            }

            //use BFS to minimal set
            Set<String> newLevelSet = new HashSet<>();
            for (String node : beginSet) {
                for (String adj : getAdjNodes(node)) {
                    //NOTE: here is the mistake of https://vladisov.notion.site/Open-the-Lock-1f2a255c61184cb0aca20efaecd94b90
                    //suppose we have start - (16 adj nodes) - target
                    //after 1st while loop, all 16 adj nodes are in beginSet, BUT also in visited!
                    //and when we swapped beginSet and endSet, it occurs, that all adj nodes of target are already in visited set => there is no solution!
                    //To fix it, we need to check 'endSet.contains(adj)' BEFORE checking '!visited.contains(adj)'
                    if (endSet.contains(adj)) {
                        return lvl;
                    }

                    if (!visited.contains(adj)) {
                        newLevelSet.add(adj);
                        visited.add(adj);
                    }
                }
            }

            beginSet = newLevelSet;
            lvl++;

        }

        return -1;
    }

    private List<String> getAdjNodes(String node) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < node.length(); i++) {
            int val = node.charAt(i) - '0';
            result.add(node.substring(0, i) + (val == 0 ? 9 : val - 1) + node.substring(i + 1));
            result.add(node.substring(0, i) + (val == 9 ? 0 : val + 1) + node.substring(i + 1));
        }

        return result;
    }
}
