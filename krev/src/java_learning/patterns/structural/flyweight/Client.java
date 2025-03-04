package java_learning.patterns.structural.flyweight;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Client {
    public static void main(String[] args) {
        int[] colors = new int[]{100,200,300};
        String[] images = new String[]{"Baobab", "Dub", "Klen"};
        Random r = new Random();
        int amount = 100;
        List<Tree> forest = new ArrayList<>(amount);
        for (int i = 0; i < amount; i++) {
            int rand = r.nextInt(3);
            forest.add(new Tree(i, i, colors[rand], images[rand]));
        }

        for (Tree t : forest) {
            t.draw();
        }

        //to ensure that amount of different TreeImages is not 100, but <= 3
        TreeImageHolder.printAllTreeImages();
    }
}
