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

2) Adjacency list - is a data structure used to represent a graph where each node in the graph stores a list of its neighboring vertices.
    https://www.geeksforgeeks.org/adjacency-list-meaning-definition-in-dsa/
    How to build an Adjacency List?
    1) Create an array of linked lists of size N, where N is the number of vertices in the graph.
    2) Create a linked list of adjacent vertices for each vertex in the graph.
    3) For each edge (u, v) in the graph, add v to the linked list of u, and add u to the linked list of v if the graph is undirected
        otherwise add v to the list of u if it is directed from u to v. (In case of weighted graphs store the weight along with the connections).

3) Graph Traversal:
    A graph consists of vertices (nodes) connected by edges (lines).
    Graph traversal involves visiting all the graph nodes following a specific strategy or order.
    During traversal, each node is typically marked as visited to avoid revisiting the same node multiple times
        and to prevent infinite loops in cyclic graphs.

    Depth First Search(DFS):
        - recursive approach
        - iterative approach (stack)
    Breadth First Search (BFS):
        - iterative approach (queue of unvisited vertices)
        Use case: for finding the shortest path in unweighted graphs and for systematically exploring graphs

1) read https://www.geeksforgeeks.org/adjacency-matrix-meaning-and-definition-in-dsa/ and related articles
2) write examples of transforming matrix picture to Adjacency matrix
3) write examples of transforming matrix picture to Adjacency list
3) Dikstra'a algorithm:
    conditions: algorithm works with directed weighted (only allow non-negative weights) graph
    target: to find shortest lowest cost path from starting vertex to destination vertex

    theory (from vertex to neighbours): https://www.youtube.com/watch?v=K_1urzWrzLs
    theory (from the nearest vertex) + implementation O(V^2): https://www.geeksforgeeks.org/java-program-for-dijkstras-shortest-path-algorithm-greedy-algo-7/
    theory (from the nearest vertex) + implementation O(V + E*logV): https://www.geeksforgeeks.org/dijkstras-shortest-path-algorithm-in-java-using-priorityqueue/
    implementation: src/solving_techniques/p29_Graphs/theory/DijkstrasShortestPathAlgorithm.java

    practice: src/solving_techniques/p29_Graphs/NetworkDelayTime.java - see https://www.youtube.com/watch?v=EaphyqKU4PQ
4) Kruskal algorithm:
    theory https://www.youtube.com/watch?v=qOv8K-AJ7o0
    theory https://www.geeksforgeeks.org/kruskals-minimum-spanning-tree-algorithm-greedy-algo-2
    implementation: src/solving_techniques/p29_Graphs/theory/KruskalAlgorithm.java
5) Prim algorithm
    theory https://www.youtube.com/watch?v=mJcZjjKzeqk
    theory https://www.geeksforgeeks.org/prims-minimum-spanning-tree-mst-greedy-algo-5/
    theory https://www.youtube.com/watch?v=K_1urzWrzLs (starting from ~ 12 min)
    implementation: src/solving_techniques/p29_Graphs/theory/PrimAlgorithm.java

Common ideas:
0) if we use adjacent matrix, then set matrix[u][v] = INFINITY if u and v are not connected directly
1) convert input data to appropriate data structure (adj matrix, adj list, or ATTENTION !! some map: vertex -> list of pairs "vertex -> length_of_edge")
2) apply the algorithm

Sequence of problems:
1) Problem 1: Find if Path Exists in Graph(easy) - todo
2) Problem 2: Number of Provinces (medium) - todo
3) Problem 3: Minimum Number of Vertices to Reach All Nodes(medium) - todo