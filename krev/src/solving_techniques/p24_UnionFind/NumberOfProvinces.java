package solving_techniques.p24_UnionFind;

import java.util.HashSet;
import java.util.Set;

/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/65439264365c9326b84111b4
 * OR
 * 547. Number of Provinces
 * https://leetcode.com/problems/number-of-provinces
 * <p>
 * There are n cities. Some of them are connected, while some are not.
 * If city a is connected directly with city b, and city b is connected directly with city c,
 * then city a is connected indirectly with city c.
 * <p>
 * A province is a group of directly or indirectly connected cities and no other cities outside of the group.
 * You are given an n x n matrix isConnected
 * where isConnected[i][j] = 1 if the ith city and the jth city are directly connected,
 * and isConnected[i][j] = 0 otherwise.
 * <p>
 * Return the total number of provinces.
 * <p>
 * Example 1:
 * Input: isConnected = [[1,1,0],[1,1,0],[0,0,1]]
 * Output: 2
 * <p>
 * Example 2:
 * Input: isConnected = [[1,0,0],[0,1,0],[0,0,1]]
 * Output: 3
 * <p>
 * Constraints:
 * 1 <= n <= 200
 * n == isConnected.length
 * n == isConnected[i].length
 * isConnected[i][j] is 1 or 0.
 * isConnected[i][i] == 1
 * isConnected[i][j] == isConnected[j][i]
 */
public class NumberOfProvinces {
    // 1 1 0
    // 1 1 0
    // 0 0 1

    //parent  0 0 2
    //arrSize 2 1 1

    /**
     * KREVSKY SOLUTION:
     * use find with compression AND union by Size
     * <p>
     * time to solve ~ 19 mins
     * <p>
     * time ~ O(n) + O(n*n) + O(n*a(n)) ~ O(n*n), since a(n) = 1/Ackerman(n) <= 4 for n < 10^600
     * space ~ O(n)
     * <p>
     * 1 attempt
     * <p>
     * BEATS = 54%
     */
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        //0. initialization
        int[] parent = new int[n];  //for 0..n-1
        int[] arrSize = new int[n];    //initially has 0...0
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            arrSize[i] = 1;
        }

        //1. union that is based on data of isConnected. check only triangle since matrix is symmetrical
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (isConnected[i][j] == 1) {
                    unionBySize(i, j, parent, arrSize);
                }
            }
        }

        //2. count provinces
        Set<Integer> representatives = new HashSet<>();
        for (int i = 0; i < n; i++) {
            representatives.add(find(i, parent));
        }

        return representatives.size();
    }

    private int find(int x, int[] parent) {
        if (x != parent[x]) {
            int res = find(parent[x], parent);
            parent[x] = res;    //cache, i.e. implement find with compression
        }
        return parent[x];
    }

    private void unionBySize(int x, int y, int[] parent, int[] arrSize) {
        int xrep = find(x, parent); //i.e. x-representative of the group which contains x
        int yrep = find(y, parent);

        if (xrep == yrep) return;//do nothing since x and y belongs to the same group => already connected somehow

        if (arrSize[xrep] < arrSize[yrep]) {
            parent[xrep] = yrep;
            arrSize[yrep] += arrSize[xrep];
        } else {
            parent[yrep] = xrep;
            arrSize[xrep] += arrSize[yrep];
        }
    }

    /**
     * SOLUTION #2:
     * use DFS
     * info: https://leetcode.com/problems/number-of-provinces/solutions/4748358/beats-100-dfs-easy-java-solution/
     *
     * BEATS = 100%
     */
    public int findCircleNum2(int[][] isConnected) {
        int n = isConnected.length;
        int ans = 0;
        boolean[] visit = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!visit[i]) {
                ans++;
                dfs(i, isConnected, visit);
            }
        }
        return ans;
    }

    private void dfs(int node1, int[][] isConnected, boolean[] visit) {
        visit[node1] = true;
        //NOTE: node2 = 0, since if we write node2 = node1 or node1 + 1, we will fail the case:
        //vertices 0,1,2,3 and 1 - 2 - 3 - 0, since we will not find 0 - 3 edge
        for (int node2 = 0; node2 < isConnected.length; node2++) {
            if (!visit[node2] && isConnected[node1][node2] == 1) {
                dfs(node2, isConnected, visit);
            }
        }
    }

}
