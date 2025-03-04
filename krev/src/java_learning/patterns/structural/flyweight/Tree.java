package java_learning.patterns.structural.flyweight;

public class Tree {
    private int x;
    private int y;
    private TreeImage image;

    public Tree(int x, int y, int color, String image) {
        this.x = x;
        this.y = y;
        this.image = TreeImageHolder.getTreeImage(color, image);
    }

    public void draw() {
        image.draw(x, y);
    }
}
