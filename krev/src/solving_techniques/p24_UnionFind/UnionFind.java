package solving_techniques.p24_UnionFind;

/**
 * info:
 * https://www.geeksforgeeks.org/introduction-to-disjoint-set-data-structure-or-union-find-algorithm
 * <p>
 * For each set/group we can select representative element of this group. This can be each element of the group,
 * but usually we select element with the biggest index.
 * <p>
 * Problem #1: to define if x and y belongs to the same group
 * Solution: we can use the fact that all elements of the same group have the SAME representative
 * <p>
 * Problem #2: to join(union) the groups
 * Solution: we can join their representative elements
 * <p>
 * Data Structures:
 * parent[] array
 * its size = amount of given elements
 * parent[i] contains the representative element for i-th element of given array.
 * <p>
 * Initial condition:
 * before applying relations, each element is separate group/set and is representative element of this group
 */
public class UnionFind {
    public static void main(String[] args) {
        UnionFind uf = new UnionFind(5);
        uf.union(0, 1);
        uf.union(1, 2);
        uf.union(2, 3);
        uf.union(3, 4);
        //currently uf.parent[] = 1,2,3,4,4

        UnionFind uf2 = new UnionFind(5);
        uf2.unionWithCompression(0, 1);
        uf2.unionWithCompression(1, 2);
        uf2.unionWithCompression(2, 3);
        uf2.unionWithCompression(3, 4);
        //currently uf2.parent[] = 1,2,3,4,4
        uf2.find(0);
        //currently uf2.parent[] = 1,2,3,4,4
        uf2.findWithCompression(0, uf2.parent);
        //currently uf2.parent[] = 4,2,3,4,4 !!!

    }

    public int[] parent;

    public UnionFind(int size) {
        parent = new int[size];

        for (int i = 0; i < size; i++) {
            parent[i] = i;
        }
    }

    /**
     * time ~ O(N)
     */
    public int find(int x) {
        if (parent[x] == x) {
            // If i is the parent of itself, then i is the representative of this set
            return x;
        } else {
            // Else if i is not the parent of itself, then i is not the representative of his set.
            // So we recursively call Find on its parent
            return find(parent[x]);
        }
    }

    /**
     * time ~ O(N), since it calls find(..) twice
     * <p>
     * Let there be 4 elements 0, 1, 2, 3
     * <p>
     * Initially, all elements are single element subsets.
     * 0 1 2 3
     * <p>
     * Do Union(0, 1)
     *   1   2   3
     *  /
     * 0
     * <p>
     * Do Union(1, 2)
     *     2   3
     *    /
     *   1
     *  /
     * 0
     * <p>
     * Do Union(2, 3)
     *       3
     *      /
     *     2
     *    /
     *   1
     *  /
     * 0
     */
    public void union(int x, int y) {
        int xrep = find(x);
        int yrep = find(y);

        //nothing do join since x and y belongs to the same set/group/tree
        if (xrep == yrep) return;

        //no matter parent(x) = y or parent[y] = x. the main idea is to join the representatives
        parent[xrep] = yrep;
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
     *             9
     *          /  |  \
     *         4   5   6
     *       /        / \
     *      0        7   8
     *     /
     *    3
     *  /  \
     * 1    2
     * When find() is called for 3, we traverse up and find 9 as representative
     * of this subset. With path compression, we also make 3 and 0 as the child of 9 so
     * that when find() is called next time for 0, 1, 2 or 3, the path to root is reduced.
     * <p>
     *      --------9-------
     *    /   /    /  \      \
     *   0   4    5    6       3
     *               /  \    /  \
     *              7    8  1    2
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

    public void unionWithCompression(int x, int y) {
        int xrep = findWithCompression(x, parent);
        int yrep = findWithCompression(y, parent);

        //nothing do join since x and y belongs to the same set/group/tree
        if (xrep == yrep) return;

        //no matter parent(x) = y or parent[y] = x. the main idea is to join the representatives
        parent[xrep] = yrep;
    }
}