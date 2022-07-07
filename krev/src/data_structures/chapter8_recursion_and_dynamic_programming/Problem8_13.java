package data_structures.chapter8_recursion_and_dynamic_programming;

import java.util.*;

/**
 * p.148
 * 8.13 Stack of Boxes:
 * You have a stack of n boxes, with widths w_i heights h_i and depths d_i.
 * The boxes cannot be rotated and can only be stacked on top of one another if each box in the stack is strictly
 * larger than the box above it in width, height, and depth.
 * Implement a method to compute the height of the tallest possible stack.
 * The height of a stack is the sum of the heights of each box.
 * Hints: #155, #194, #214, #260, #322, #368, #378
 * ASSUMPTION/VALIDATION:
 */
public class Problem8_13 {
    public static void main(String[] args) {
        Box b1 = new Box(1, 1, 1);
        Box b2 = new Box(2, 2, 2);
        Box b3 = new Box(3, 3, 2);

        ArrayList<Box> list = new ArrayList<>();
        list.addAll(Arrays.asList(b1, b2, b3));

        int maxH = getHeight(list, null);
        System.out.println(maxH);
    }

    /**
     * KREVSKY SOLUTION
     * O(n^n)
     * проблема: я НЕ СМОГ придумать, как удалять из листа(очереди) те значения, к-ые уже юзаются, а потом их восстанавливать
     * НЕ УВЕРЕН, что решение абсолютно правильное, но на простм примере работает корректно
     */

    public static int getHeight(ArrayList<Box> list, Box prev) {
        if (list == null || list.isEmpty()) return 0;

        Iterator<Box> it = list.iterator();
        int maxH = 0;
        while (it.hasNext()) {  //O(n^2)
            Box b = it.next();
            int h = 0;
            if (prev == null || (!b.isUsed && b.smaller(prev))) {
                h = b.height;
                b.isUsed = true;
                h += getHeight(list, b);
                b.isUsed = false;
            }
            maxH = Math.max(maxH, h);
        }

        return maxH;
    }

    /**
     * ORIGINAL SOLUTION #1
     * idea: it is logical to SORT the boxes ny any of dimension to avoid a range of useless comparisons in future
     * ALSO this sorting helps to avoid the problem with removing and restoring of used box as in KREVSKY solution!
     */
    public static int createStack(ArrayList<Box> boxes) {
        Collections.sort(boxes, (x, y) -> y.height - x.height);
        int maxH = 0;
        for (int i = 0; i < boxes.size(); i++) {
            int height = createStack(boxes, i);
            maxH = Math.max(maxH, height);
        }
        return maxH;
    }

    protected static int createStack(ArrayList<Box> boxes, int bottomIndex) {
        Box bottom = boxes.get(bottomIndex);
        int maxH = 0;
        for (int i = bottomIndex + 1; i < boxes.size(); i++) {
            if (boxes.get(i).smaller(bottom)) {
                int height = createStack(boxes, bottomIndex + 1);
                maxH = Math.max(maxH, height);
            }
        }
        maxH += bottom.height;
        return maxH;
    }

    /**
     * ORIGINAL SOLUTION #1 memorization
     * stack[i] represents the tallest stack with box i at the bottom.
     */
    public static int createStackMemo(ArrayList<Box> boxes) {
        Collections.sort(boxes, (x, y) -> y.height - x.height);

        int[] stack = new int[boxes.size()];
        int maxH = 0;
        for (int i = 0; i < boxes.size(); i++) {
            int height = createStackMemo(boxes, i, stack);
            maxH = Math.max(maxH, height);
        }
        return maxH;
    }

    protected static int createStackMemo(ArrayList<Box> boxes, int bottomIndex, int[] stack) {
        //I'm not sure do we really need validation bottomIndex < boxes.size()
        if (bottomIndex < boxes.size() && stack[bottomIndex] > 0) {
            return stack[bottomIndex];
        }
        Box bottom = boxes.get(bottomIndex);
        int maxH = 0;
        for (int i = bottomIndex + 1; i < boxes.size(); i++) {
            if (boxes.get(i).smaller(bottom)) {
                int height = createStack(boxes, bottomIndex + 1);
                maxH = Math.max(maxH, height);
            }
        }
        maxH += bottom.height;
        stack[bottomIndex] = maxH;
        return maxH;
    }

    private static class Box {
        int width;
        int height;
        int depth;
        boolean isUsed;

        public Box(int w, int h, int d) {
            width = w;
            height = h;
            depth = d;
        }

        public boolean smaller(Box b) {
            return b.width > width && b.height > height && b.depth > depth;
        }
    }
}
