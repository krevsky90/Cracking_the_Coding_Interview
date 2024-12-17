package solving_techniques.p8_TreeDepthFirstSearch;

/**
 * 427. Construct Quad Tree (medium)
 * https://leetcode.com/problems/construct-quad-tree
 * <p>
 * #Company: 0 - 3 months Uber 3 Palantir Technologies 2 6 months ago Google 8 Amazon 3 Microsoft 2
 * <p>
 * Given a n * n matrix grid of 0's and 1's only. We want to represent grid with a Quad-Tree.
 * Return the root of the Quad-Tree representing grid.
 * <p>
 * A Quad-Tree is a tree data structure in which each internal node has exactly four children. Besides, each node has two attributes:
 * <p>
 * val: True if the node represents a grid of 1's or False if the node represents a grid of 0's.
 * Notice that you can assign the val to True or False when isLeaf is False, and both are accepted in the answer.
 * isLeaf: True if the node is a leaf node on the tree or False if the node has four children.
 * class Node {
 * public boolean val;
 * public boolean isLeaf;
 * public Node topLeft;
 * public Node topRight;
 * public Node bottomLeft;
 * public Node bottomRight;
 * }
 * We can construct a Quad-Tree from a two-dimensional area using the following steps:
 * <p>
 * If the current grid has the same value (i.e all 1's or all 0's) set isLeaf True and set val to the value of the grid and set the four children to Null and stop.
 * If the current grid has different values, set isLeaf to False and set val to any value and divide the current grid into four sub-grids as shown in the photo.
 * Recurse for each of the children with the proper sub-grid.
 */
public class ConstructQuadTree {
    class Node {
        public boolean val;
        public boolean isLeaf;
        public Node topLeft;
        public Node topRight;
        public Node bottomLeft;
        public Node bottomRight;


        public Node() {
            this.val = false;
            this.isLeaf = false;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean val, boolean isLeaf) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean val, boolean isLeaf, Node topLeft, Node topRight, Node bottomLeft, Node bottomRight) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = topLeft;
            this.topRight = topRight;
            this.bottomLeft = bottomLeft;
            this.bottomRight = bottomRight;
        }
    }

    /**
     * KREVSKY SOLUTION:
     * time to solve ~ 31 mins
     * <p>
     * time ~ O(N*N*logN) since we have callstack tree like
     * N*N = N^2 cells
     * 4*(N/2)^2 = N^2 cells
     * ...
     * and the number of such levels is logN
     * <p>
     * space ~ O(logN) - just to keep recursion stack in memory
     * <p>
     * <p>
     * 3 attempts:
     * - different stupid compilation errors
     * - mixed arguments when build node.topRight object
     * <p>
     * BEATS ~ 100%
     */
    public Node construct(int[][] grid) {
        int n = grid.length;
        return build(grid, 0, n, 0, n);
    }

    private Node build(int[][] grid, int left, int right, int top, int bottom) {
        Node node = new Node();
        if (right - left == 1 && bottom - top == 1) {
            //node contains only one cell
            node.isLeaf = true;
            node.val = grid[top][left] == 1 ? true : false;
            return node;
        }

        int initialValue = grid[top][left];
        boolean isLeaf = true;
        for (int r = top; r < bottom; r++) {
            for (int c = left; c < right; c++) {
                if (grid[r][c] != initialValue) {
                    isLeaf = false;
                    break;
                }
            }

            if (!isLeaf) {
                break;
            }
        }

        node.isLeaf = isLeaf;
        node.val = initialValue == 1 ? true : false;

        if (!isLeaf) {
            node.topLeft = build(grid, left, (right + left) / 2, top, (top + bottom) / 2);
            node.topRight = build(grid, (right + left) / 2, right, top, (top + bottom) / 2);
            node.bottomLeft = build(grid, left, (right + left) / 2, (top + bottom) / 2, bottom);
            node.bottomRight = build(grid, (right + left) / 2, right, (top + bottom) / 2, bottom);
        }

        return node;
    }

    /**
     * Optimized solution:
     * Previously we first iterate over all the cells in the matrix and then decide if this should be a leaf or not and have four child nodes.
     * In case we decide to have four child nodes, we recursively move to the four sub-matrices and follow the same process.
     * The redundant part in this approach is when we will iterate over the cells in the sub-matrices
     * that would have already been iterated for the root node.
     * These redundant operations can be avoided if we simply make a recursive call to the four sub-matrices instead of first checking all the values.
     * Once all four recursive calls are returned, we will decide whether to let these as child nodes of the root node
     * or should be combined them into one as the root node.
     * <p>
     * time ~ O(N^2)
     * space ~ O(logN)
     */
    private Node solve(int[][] grid, int x1, int y1, int length) {
        // Return a leaf node if the matrix size is one.
        if (length == 1) {
            return new Node(grid[x1][y1] == 1, true);
        }

        // Recursive calls to the four sub-matrices.
        Node topLeft = solve(grid, x1, y1, length / 2);
        Node topRight = solve(grid, x1, y1 + length / 2, length / 2);
        Node bottomLeft = solve(grid, x1 + length / 2, y1, length / 2);
        Node bottomRight = solve(grid, x1 + length / 2, y1 + length / 2, length / 2);

        // If the four returned nodes are leaf and have the same values
        // Return a leaf node with the same value.
        if (topLeft.isLeaf && topRight.isLeaf && bottomLeft.isLeaf && bottomRight.isLeaf
                && topLeft.val == topRight.val && topRight.val == bottomLeft.val
                && bottomLeft.val == bottomRight.val) {
            return new Node(topLeft.val, true);
        }

        // If the four nodes aren't identical, return a non-leaf node with corresponding child pointers.
        return new Node(false, false, topLeft, topRight, bottomLeft, bottomRight);
    }

    public Node construct2(int[][] grid) {
        return solve(grid, 0, 0, grid.length);
    }
}
