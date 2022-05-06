package data_structures.chapter4_trees_n_graphs;

public class Node {
    public int value;
    public Node left;
    public Node right;

    public Node(int x) {
        value = x;
    }

    public String toString() {
        return "(" + value + ")";
    }
}
