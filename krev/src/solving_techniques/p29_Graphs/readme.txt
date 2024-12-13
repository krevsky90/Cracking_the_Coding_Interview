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
    target: to find shortest/lowest cost path from starting vertex to destination vertex

    theory (from vertex to neighbours): https://www.youtube.com/watch?v=K_1urzWrzLs
    theory (from the nearest vertex) + implementation O(V^2): https://www.geeksforgeeks.org/java-program-for-dijkstras-shortest-path-algorithm-greedy-algo-7/
    theory (from the nearest vertex) + implementation O(V + E*logV): https://www.geeksforgeeks.org/dijkstras-shortest-path-algorithm-in-java-using-priorityqueue/
    implementation: src/solving_techniques/p29_Graphs/theory/DijkstrasShortestPathAlgorithm.java

    THE BEST practice: https://www.youtube.com/watch?v=XEb7_z5dG3c
    THE BEST practice: PathWithMaximumProbability # maxProbability3
4) Kruskal algorithm:
    theory https://www.youtube.com/watch?v=qOv8K-AJ7o0
    theory https://www.geeksforgeeks.org/kruskals-minimum-spanning-tree-algorithm-greedy-algo-2
    implementation: src/solving_techniques/p29_Graphs/theory/KruskalAlgorithm.java
5) Prim algorithm
    theory https://www.youtube.com/watch?v=mJcZjjKzeqk
    theory https://www.geeksforgeeks.org/prims-minimum-spanning-tree-mst-greedy-algo-5/
    theory https://www.youtube.com/watch?v=K_1urzWrzLs (starting from ~ 12 min)
    implementation: src/solving_techniques/p29_Graphs/theory/PrimAlgorithm.java
6) Eulerian Path/Circuit algorithm (Hierholzer's algorithm):
    theory https://www.youtube.com/watch?v=8MpoO2zA2l4
    NOTE:
        - Eulerian Path exists if there is node which (count_outcoming_edges - count_incoming_edges == 1) and node (count_incoming_edges - count_outcoming_edges == 1)
            OR all nodes have count_outcoming_edges = count_incoming_edges
        - start with node which count_outcoming_edges - count_incoming_edges == 1 (or count_outcoming_edges = count_incoming_edges otherwise)
        - use DFS. if node does not have unvisited outcoming edges, then it is added in the beginning of the path

    implementation: src/solving_techniques/p29_Graphs/ReconstructItinerary.java

Common ideas:
0) if we use adjacent matrix, then set matrix[u][v] = INFINITY if u and v are not connected directly
1) convert input data to appropriate data structure (adj matrix, adj list, or ATTENTION !! some map: vertex -> list of pairs "vertex -> length_of_edge")
2) apply the algorithm

Sequence of problems:
1) Problem 1: Find if Path Exists in Graph (easy) - done
2) Problem 2: Number of Provinces (medium) - todo
3) Problem 3: Minimum Number of Vertices to Reach All Nodes(medium) - done

todo: take problems from here https://neetcode.io/roadmap
4) https://leetcode.com/problems/clone-graph (medium) - done
5) https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph (medium) - done
6) https://leetcode.com/problems/find-number-of-coins-to-place-in-tree-nodes (hard) - done
7) https://leetcode.com/problems/find-distance-in-a-binary-tree (medium) - done

8) https://leetcode.com/problems/shortest-path-in-binary-matrix (medium) - done

9) https://leetcode.com/problems/accounts-merge (medium) - done
10) https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree (medium) - done
11) https://leetcode.com/problems/find-all-people-with-secret (hard) - done
12) https://leetcode.com/problems/word-ladder (hard) - done
13) https://leetcode.com/problems/find-the-celebrity (medium) - done
14) https://leetcode.com/problems/shortest-distance-from-all-buildings (hard) - done
15) https://leetcode.com/problems/jump-game-v/ (hard) - done
16) https://leetcode.com/problems/reconstruct-itinerary (hard) - done
17) https://leetcode.com/problems/shortest-distance-after-road-addition-queries-i (medium) - done
18) https://leetcode.com/problems/find-champion-ii (medium) - done
19) https://leetcode.com/problems/path-with-maximum-probability (medium) - done
20) https://leetcode.com/problems/cheapest-flights-within-k-stops (medium) - done
21) https://leetcode.com/problems/bus-routes (hard) - done