package solving_techniques.p29_Graphs;

import java.util.*;

/**
 * 133. Clone Graph (medium)
 * https://leetcode.com/problems/clone-graph
 *
 * #Company: Amazon Apple Facebook Google LinkedIn Mathworks Microsoft Pocket Gems Splunk Twitter Uber Walmart Labs
 * <p>
 * Given a reference of a node in a connected undirected graph.
 * <p>
 * Return a deep copy (clone) of the graph.
 * <p>
 * Each node in the graph contains a value (int) and a list (List[Node]) of its neighbors.
 * <p>
 * class Node {
 * public int val;
 * public List<Node> neighbors;
 * }
 * <p>
 * <p>
 * Test case format:
 * <p>
 * For simplicity, each node's value is the same as the node's index (1-indexed).
 * For example, the first node with val == 1, the second node with val == 2, and so on.
 * The graph is represented in the test case using an adjacency list.
 * <p>
 * An adjacency list is a collection of unordered lists used to represent a finite graph.
 * Each list describes the set of neighbors of a node in the graph.
 * <p>
 * The given node will always be the first node with val = 1.
 * You must return the copy of the given node as a reference to the cloned graph.
 * <p>
 * Example 1:
 * Input: adjList = [[2,4],[1,3],[2,4],[1,3]]
 * Output: [[2,4],[1,3],[2,4],[1,3]]
 * Explanation: There are 4 nodes in the graph.
 * 1st node (val = 1)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
 * 2nd node (val = 2)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).
 * 3rd node (val = 3)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
 * 4th node (val = 4)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).
 * <p>
 * Example 2:
 * Input: adjList = [[]]
 * Output: [[]]
 * Explanation: Note that the input contains one empty list. The graph consists of only one node with val = 1 and it does not have any neighbors.
 * <p>
 * Example 3:
 * Input: adjList = []
 * Output: []
 * Explanation: This an empty graph, it does not have any nodes.
 * <p>
 * Constraints:
 * The number of nodes in the graph is in the range [0, 100].
 * 1 <= Node.val <= 100
 * Node.val is unique for each node.
 * There are no repeated edges and no self-loops in the graph.
 * The Graph is connected and all nodes can be visited starting from the given node
 */
public class CloneGraph {
    /**
     * KREVSKY SOLUTION:
     * very similar to
     * https://leetcode.com/problems/clone-graph/solutions/3393769/java-simple-dfs-solution-using-hashmap/
     * <p>
     * time to solve ~ 50 mins
     * idea:
     * 1) store oldToNewMap (original node -> cloned node)
     * 2) use DFS traversal and create newNode + fill its neighbours by:
     * a) the result of dfs call, if temp neighbour is not visited
     * b) clone of temp neighbour if it is already visited
     * <p>
     * a lot of attempts:
     * - tried to use BFS, failed
     * - using DFS, forgot !visited validation
     * - incorrectly used newParentNode, but then replaced it with oldToNewMap
     * <p>
     * BEATS = 77%
     */
    public Node cloneGraphDFS(Node node) {
        if (node == null) return null;
        Map<Node, Node> oldToNewMap = new HashMap<>();

        return dfsCopy(node, oldToNewMap);
    }

    private Node dfsCopy(Node oldNode, Map<Node, Node> oldToNewMap) {
        ArrayList<Node> newList = new ArrayList<>();
        Node newNode = new Node(oldNode.val, newList);

        oldToNewMap.put(oldNode, newNode);

        for (Node n : oldNode.neighbors) {
            if (!oldToNewMap.containsKey(n)) {
                newList.add(dfsCopy(n, oldToNewMap));
            } else {
                newList.add(oldToNewMap.get(n));
            }
        }

        return newNode;
    }

    public static void main(String[] args) {
        CloneGraph obj = new CloneGraph();
        obj.testBFSSolution();


    }

    public void testBFSSolution() {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        n1.neighbors = Arrays.asList(n2, n4);
        n2.neighbors = Arrays.asList(n1, n3);
        n3.neighbors = Arrays.asList(n2, n4);
        n4.neighbors = Arrays.asList(n1, n3);

        Node cloned1 = cloneGraphBFS(n1);
        System.out.println("");
    }

    /**
     * SOLUTION #1: BFS:
     * info:
     * https://www.youtube.com/watch?v=vXkT2nYSde0&list=PLUPSMCjQ-7od5IVz8ug6D-apxFLkDTsoy&index=51
     * same idea as KREVSKY SOLUTION, but using BFS
     * 1) clone 'node'
     * 2) put orig 'node' and cloned node to map 'oldToNewMap'
     * 3) create Queue and put orig node to it
     * 4) use BFS:
     *      poll current nod, for its neighbours:
     *          if the neighbour is cloned (=> it exists in the map) - do nothing
     *          else
     *          a) clone it
     *          b) put them to map
     *          c) put cloned neighbour to adjList of cloned current
     *          NOTE: when we poll neighbour from the queue, if append current node to adjList of this neighbour => will get undirected graph!
     *
     * BEATS ~ 93%
     */
    public Node cloneGraphBFS(Node node) {
        if (node == null) return null;

        Map<Node, Node> oldToNewMap = new HashMap<>();
        //create clone + add to map
        Node clonedNode = new Node(node.val);
        oldToNewMap.put(node, clonedNode);

        Queue<Node> q = new LinkedList<>();
        q.add(node);
        while (!q.isEmpty()) {
            Node current = q.poll();

            for (Node neighbour : current.neighbors) {
                if (!oldToNewMap.containsKey(neighbour)) {
                    //create clone + add to map
                    Node clonedNeighbour = new Node(neighbour.val);
                    oldToNewMap.put(neighbour, clonedNeighbour);

                    q.add(neighbour);
                }
                //anyway we put cloned neighbour to adjList of cloned current node
                // (does not matter if cloned neighbour is created now or earlier)!
                oldToNewMap.get(current).neighbors.add(oldToNewMap.get(neighbour));
            }
        }

        return clonedNode;
    }

    class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    /**
     * SOLUTION #2:
     * info:
     * https://leetcode.com/problems/clone-graph/solutions/3393519/using-bfs-in-java/
     *
     * use BFS:
     * store 2 queues: for old and new graphs. add traverse them in parallel
     *
     * time ~ O(n)
     * space ~ O(n)
     */
    public Node cloneGraph2(Node node) {
        if (node == null) return null;

        Deque<Node> bfs1 = new ArrayDeque<>();
        bfs1.offer(node);

        Node res = new Node(node.val);
        Deque<Node> bfs2 = new ArrayDeque<>();
        bfs2.offer(res);

        Node[] added = new Node[101];
        added[node.val] = res;
        while (!bfs1.isEmpty()) {
            Node prev1 = bfs1.remove();
            Node prev2 = bfs2.remove();
            for (Node child : prev1.neighbors) {
                if (added[child.val] == null) {
                    Node copy = new Node(child.val);
                    //NOTE: we do not fill neighbour list for cloned node right here! we will do this once we take it from bfs2 and then it will be called prev2!
                    bfs1.offer(child);
                    bfs2.offer(copy);
                    added[child.val] = copy;
                    prev2.neighbors.add(copy);
                } else {
                    prev2.neighbors.add(added[child.val]);
                }
            }
        }
        return res;
    }
}
