package data_structures.chapter4_trees_n_graphs.extra;

public class RandomBinaryTreeTest {
    public static void main(String[] args) {
        RandomBinaryTree tree = new RandomBinaryTree();
        tree.add(4);
        tree.add(2);
        tree.add(1);
        tree.add(3);
        tree.add(6);
        tree.add(5);
        tree.add(7);

        System.out.println(tree.getRandomNode());
    }
}
