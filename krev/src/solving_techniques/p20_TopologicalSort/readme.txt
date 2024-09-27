https://interviewnoodle.com/grokking-leetcode-a-smarter-way-to-prepare-for-coding-interviews-e86d5c9fe4e1
https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/63a493063f9c1a4665882e74

Theory:
Topological Sort is used to find a linear ordering of elements that have dependencies on each other.
For example, if event B is dependent on event A, A comes before B in topological ordering.

Main Problem:
Topological Sort of a directed graph (a graph with unidirectional edges) is a linear ordering of its vertices such
that for every directed edge (U, V) from vertex U to vertex V, U comes before V in the ordering.
Given a directed graph, find the topological ordering of its vertices.

Approach 1:
Topological Sort + DFS:
https://www.youtube.com/watch?v=5lZ0iJMrUMk

Approach 2:
Topological Sort + BFS:
https://github.com/Chanda-Abdul/Several-Coding-Patterns-for-Solving-Data-Structures-and-Algorithms-Problems-during-Interviews/blob/main/%E2%9C%85%20Pattern%2016%3A%20%F0%9F%94%8E%20Topological%20Sort%20(Graph).md

Here are a few fundamental concepts related to topological sort:
Source: Any node that has no incoming edge and has only outgoing edges is called a source.
Sink: Any node that has only incoming edges and no outgoing edge is called a sink.
So, we can say that a topological ordering starts with one of the sources and ends at one of the sinks.
A topological ordering is possible only when the graph has no directed cycles, i.e. if the graph is a Directed Acyclic Graph (DAG).
If the graph has a cycle, some vertices will have cyclic dependencies which makes it impossible to find a linear ordering among vertices.

To find the topological sort of a graph we can traverse the graph in a Breadth First Search (BFS) way.
We will start with all the sources, and in a stepwise fashion, save all sources to a sorted list.
We will then remove all sources and their edges from the graph.
After the removal of the edges, we will have new sources,
so we will repeat the above process until all vertices are visited.

1. Initialization
    We will store the graph in Adjacency Lists, which means each parent vertex will have a list containing all of its children.
    We will do this using a HashMap where the key will be the parent vertex number and the value will be a List containing children vertices.
    To find the sources, we will keep a HashMap to count the in-degrees i.e., count of incoming edges of each vertex.
Any vertex with 0 in-degree will be a source.
2. Build the graph and find in-degrees of all vertices
    We will build the graph from the input and populate the in-degrees HashMap.
3. Find all sources
    All vertices with 0 in-degrees will be our sources and we will store them in a Queue.
4. Sort
    For each source, do the following things:
    Add it to the sorted list.
    Get all of its children from the graph.
    Decrement the in-degree of each child by 1.
    If a child?s in-degree becomes 0, add it to the sources Queue.
    Repeat for each source, until the source Queue is empty.

Sequence of problems:
1) Topological Sort - done
2) Tasks Scheduling (similar to https://leetcode.com/problems/course-schedule) - done
3) Tasks Scheduling Order (similar to https://leetcode.com/problems/course-schedule-ii/) - done
4) All Tasks Scheduling Orders (hard) - done
5) Alien Dictionary (hard) - todo
6) Problem Challenge 1: Reconstructing a Sequence (hard) - todo
7) Problem Challenge 2: Minimum Height Trees (hard) - done

8) https://leetcode.com/problems/parallel-courses (hard) - done

Leetcode:
https://leetcode.com/tag/topological-sort/