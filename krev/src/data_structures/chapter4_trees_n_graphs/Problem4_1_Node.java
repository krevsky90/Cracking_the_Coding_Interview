package data_structures.chapter4_trees_n_graphs;

import java.util.ArrayList;
import java.util.List;

public class Problem4_1_Node {
    public int value;
    public boolean marked = false;
    public List<Problem4_1_Node> children = new ArrayList<>();
    //for original solution
    public Problem4_1_States state;

    public Problem4_1_Node(int v) {
        value = v;
    }

}
