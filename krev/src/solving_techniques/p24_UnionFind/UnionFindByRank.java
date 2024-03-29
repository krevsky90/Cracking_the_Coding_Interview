package solving_techniques.p24_UnionFind;

// OPTIMIZATION: Union By Rank //
/**
 * https://www.geeksforgeeks.org/union-by-rank-and-path-compression-in-union-find-algorithm/
 * Idea:
 * we need a new array of integers called rank[]. The size of this array is the same as the parent array Parent[]
 * If i is a representative of a set, rank[i] is the height of the tree representing the set.
 * <p>
 * We want to minimize the height of the resulting tree.
 * To do this we always attach a smaller depth tree under the root of the deeper tree.
 * If the ranks are equal, it doesn?t matter which tree goes under the other,
 * but the rank of the result will always be one greater than the rank of the trees.
 * The term rank is preferred instead of height because if the path compression technique is used,
 * then the rank is not always equal to height.
 * Also, the size (in place of height) of trees can also be used as rank.
 * <p>
 * time ~ O(LogN)
 */
public class UnionFindByRank {
    public static void main(String[] args) {
        UnionFindByRank ufk = new UnionFindByRank(5);
        ufk.unionByRank(0,1);
        ufk.unionByRank(1,2);
        ufk.unionByRank(2,3);
        ufk.unionByRank(3,4);
        //currently rank = 0 1 0 1 0
        //currently parent = 1 3 1 3 3


    }

    public int[] rank;
    public int[] parent;

    public UnionFindByRank(int size) {
        parent = new int[size];
        rank = new int[size];   //0...0

        for (int i = 0; i < size; i++) {
            parent[i] = i;
        }
    }

    // OPTIMIZATION: Path Compression //

    /**
     * info: https://www.geeksforgeeks.org/union-by-rank-and-path-compression-in-union-find-algorithm/
     * idea:
     * to flatten the tree when find() is called.
     * When find() is called for an element x, root of the tree is returned.
     * The find() operation traverses up from x to find root.
     * The idea of path compression is to make the found root as parent of x
     * so that we don?t have to traverse all intermediate nodes again.
     * and save parent[x] value directly to x index by this way.
     * <p>
     * time ~ O(LogN) on average per call
     * <p>
     * Let the subset {0, 1, .. 9} be represented as below and find() is called
     * for element 3.
     *            9
     *         /  |   \
     *        4   5    6
     *      /         /  \
     *     0         7    8
     *   /
     *  3
     * / \
     * 1   2
     * When find() is called for 3, we traverse up and find 9 as representative
     * of this subset. With path compression, we also make 3 and 0 as the child of 9 so
     * that when find() is called next time for 0, 1, 2 or 3, the path to root is reduced.
     * <p>
     *      --------9-------
     *    /   /    /  \      \
     *   0   4    5    6       3
     *  /  \    /  \
     * 7    8   1   2
     */
    public int findWithCompression(int x, int[] parent) {
        if (parent[x] == x) {
            // If i is the parent of itself, then i is the representative of this set
            return x;
        } else {
            // Else if i is not the parent of itself, then i is not the representative of his set.
            // So we recursively call Find on its parent
            parent[x] = findWithCompression(parent[x], parent);
            return parent[x];
        }
    }

    /**
     * provides minimum 'height' or rank => we have fast 'find' method
     */
    public void unionByRank(int x, int y) {
        int xrep = findWithCompression(x, parent);
        int yrep = findWithCompression(y, parent);

        if (xrep == yrep) return;

        int xrank = rank[xrep];
        int yrank = rank[yrep];

        if (xrank < yrank) {
            parent[xrep] = yrep;
        } else if (xrank > yrank) {
            parent[yrep] = xrep;
        } else {
            //no matter who will be attached
            parent[xrep] = yrep;
            //but we need increment the result tree's rank by 1
            rank[yrep]++;
        }
    }
}
