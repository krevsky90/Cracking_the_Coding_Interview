https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/6388cb65765bb2154037ccb9

Many coding interview problems involve traversing 2D arrays (aka matrix or grid).
The Island pattern describes all the efficient ways to traverse a matrix.
This pattern will go through many problems to explain matrix traversal
using Depth First Search (DFS) and Breadth First Search (BFS) approaches and their variants.

Theory:
see 3 solutions of NumberOfIslands:
1) DFS + mark visited cells by changing the original matrix
2) BFS (queue that store unvisited cells) + mark visited cells by changing the original matrix
3) BFS (...) + keep separate matrix to mark a cell visited

HINT (best practice):
use 2D array for the storing the horizontal and vertical directions. (Up, left, down, right}
    static int[][] dirs = {
        { 0, -1 },
        { -1, 0 },
        { 0, 1 },
        { 1, 0 }
    };

    use it as shown below:
    for (int k = 0; k < 4; k++) {
        dfs(grid, x0, y0, i + dirs[k][0], j + dirs[k][1], v);
    }

Sequence of problems:
1) Number of Islands (easy) - done
2) Biggest Island (easy) - done
3) Flood Fill (easy) - done
4) Number of Closed Islands (easy) - done
5) Problem Challenge 1 (easy) - done
6) Problem Challenge 2 Count Distinct Islands (medium) - done
7) Problem Challenge 3 (medium) - todo
8) https://leetcode.com/problems/making-a-large-island (hard) - done
