https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/651a6ae21f722f7fca34a9a7

Graph Representations:
We primarily represent graphs using two ways:
1) Adjacency matrix:
    https://www.geeksforgeeks.org/adjacency-matrix-meaning-and-definition-in-dsa/
    How to build an Adjacency Matrix:
    1) Create an n x n matrix where n is the number of vertices in the graph.
    2) Initialize all elements to 0.
    3) For each edge (u, v) in the graph, if the graph is undirected mark a[u][v] and a[v][u] as 1,
        and if the edge is directed from u to v, mark a[u][v] as the 1.
        (Cells are filled with edge weight if the graph is weighted)

2) Adjacency list

3) Graph Traversal:
    A graph consists of vertices (nodes) connected by edges (lines).
    Graph traversal involves visiting all the graph nodes following a specific strategy or order.
    During traversal, each node is typically marked as visited to avoid revisiting the same node multiple times
        and to prevent infinite loops in cyclic graphs.

    Depth First Search(DFS) - todo
        uses recursion
    Breadth First Search (BFS)  - todo
        uses Queue of unvisited vertices
        Use case: for finding the shortest path in unweighted graphs and for systematically exploring graphs



todo:
1) read https://www.geeksforgeeks.org/adjacency-matrix-meaning-and-definition-in-dsa/ and related articles
2) write examples of transforming matrix picture to Adjacency matrix
3) write examples of transforming matrix picture to Adjacency list
3) Dikstra'a algorithm
    https://www.youtube.com/watch?v=K_1urzWrzLs
    implement by yourself
    then https://www.youtube.com/watch?v=EaphyqKU4PQ
4) Kruskal algorithm
5) Prim algorithm
    https://www.youtube.com/watch?v=K_1urzWrzLs (starting from ~ 12 min)
    + some other video
    implement by yourself
    + find fit problem

Sequence of problems:
1) Problem 1: Find if Path Exists in Graph(easy) - todo
2) Problem 2: Number of Provinces (medium) - todo
3) Problem 3: Minimum Number of Vertices to Reach All Nodes(medium) - todo