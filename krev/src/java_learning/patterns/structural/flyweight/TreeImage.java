package java_learning.patterns.structural.flyweight;

import java.util.Objects;

/**
 * NOTE: since the diversity of such objects is low, class should be immutable!
 */
public class TreeImage {
    private final int color;
    //suppose it is not string, but real image that takes ~ MBs
    private final String image;

    public TreeImage(int color, String image) {
        this.color = color;
        this.image = image;
    }

    public int getColor() {
        return color;
    }

    public String getImage() {
        return image;
    }

    /**
     * not necessary, but this class can contain the behavior of Tree object
     */
    public void draw(int x, int y) {
        System.out.println("Tree: {" + x + ", " + y + ": color = " + color + ", image = " + image);
    }
}
