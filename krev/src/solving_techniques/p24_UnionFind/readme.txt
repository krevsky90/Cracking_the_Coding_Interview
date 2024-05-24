https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/65424a80702ba25d62fbba71


Union Find, also known as Disjoint Set Union (DSU),
is a data structure that keeps track of a partition of a set into disjoint subsets (meaning no set overlaps with another).
It provides two primary operations:
- find, which determines which subset a particular element is in,
- and union, which merges two subsets into a single subset.

NOTE: condition:
    Union-Find cycle detection is only applicable for Graphs that are UNdirected!

This pattern is particularly useful for problems where we need to find whether 2 elements belong to the same group
or need to solve connectivity-related problems in a graph or tree.

Theory:
/**
 * info:
 * https://www.geeksforgeeks.org/introduction-to-disjoint-set-data-structure-or-union-find-algorithm
 *
 * For each set/group we can select representative element of this group. This can be each element of the group,
 * but usually we select element with the biggest index.
 *
 * Problem #1: to define if x and y belongs to the same group
 * Solution: we can use the fact that all elements of the same group have the SAME representative
 *
 * Problem #2: to join(union) the groups
 * Solution: we can join their representative elements
 *
 * Data Structures:
 * parent[] array
 * its size = amount of given elements
 * parent[i] contains the representative element for i-th element of given array.
 *
 * Initial condition:
 * before applying relations, each element is separate group/set and is representative element of this group
 *
 */

 Examples and main method's implementations:
 see
 src/solving_techniques/p24_UnionFind/UnionFind.java
 and optimized
 src/solving_techniques/p24_UnionFind/UnionFindByRank.java
 src/solving_techniques/p24_UnionFind/UnionFindBySize.java

Sequence of problems:
1) Redundant Connection (medium) - done
2) Number of Provinces (medium) - done
3) Is Graph Bipartite? (medium) - done
4) Path With Minimum Effort (medium) - done
5) https://leetcode.com/problems/find-the-safest-path-in-a-grid (medium) - todo