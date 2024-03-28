package solving_techniques.p24_UnionFind;

// OPTIMIZATION: Union By Size //

/**
 * info:
 * https://www.geeksforgeeks.org/introduction-to-disjoint-set-data-structure-or-union-find-algorithm/
 * similar to 'union by rank', but here we store size[] array.
 * if i is a representative of a set, size[i] is the number of the elements in the tree representing the set
 * <p>
 * Initially size[i] = 1 for each i
 * The logic of joining is similar to 'union by rank':
 * set with less size is joined to the set with bigger size, total size of bigger set += size of less set
 * if sets have the same size, no matter who is attached, but size of parent should be increased by the size of attached set
 */
public class UnionFindBySize {
    public int[] sizeArr;
    public int[] parent;

    public UnionFindBySize(int size) {
        parent = new int[size];
        sizeArr = new int[size];

        for (int i = 0; i < size; i++) {
            parent[i] = i;
            sizeArr[i] = 1;
        }
    }

    /**
     * provides minimum 'height' or rank => we have fast 'find' method
     */
    public void unionBySize(int x, int y) {
        int xrep = findWithCompression(x, parent);
        int yrep = findWithCompression(y, parent);

        if (xrep == yrep) return;

        int xrank = sizeArr[x];
        int yrank = sizeArr[y];

        if (xrank < yrank) {
            parent[xrep] = yrep;
            sizeArr[yrep] += sizeArr[xrep];
        } else {
            parent[yrep] = xrep;
            sizeArr[yrep] += sizeArr[xrep];
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
}
