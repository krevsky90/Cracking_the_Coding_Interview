package data_structures.chapter8_recursion_and_dynamic_programming;

public class Test {
    public static void main(String[] args) {
        int[][] arr = new int[2][5];
        System.out.println(arr.length);
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.println(arr[i][j]);
            }
        }
    }
}
