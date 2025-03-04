package java_learning.patterns.structural.flyweight;

import java.util.HashMap;
import java.util.Map;

public class TreeImageHolder {
    private static Map<String, TreeImage> imageTrees = new HashMap<>();

    public static TreeImage getTreeImage(int color, String image) {
        String uniqueKey = image + "_" + color;
        TreeImage result = imageTrees.get(uniqueKey);
        if (result == null) {
            result = new TreeImage(color, image);
            imageTrees.put(uniqueKey, result);
        }

        return result;
    }

    public static void printAllTreeImages() {
        System.out.println(imageTrees.keySet());
    }
}
