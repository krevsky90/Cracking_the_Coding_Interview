package data_structures.chapter8_recursion_and_dynamic_programming.Recursion_in_Programming_Full_Course;

public class SolutionsForStrings {
    public static void main(String[] args) {
        String input = "kayak";
        System.out.println(isPalindrome(input));
        System.out.println(findBinary(4, ""));
    }


    public static boolean isPalindrome(String input) {
        if (input == null) return false;
        if (input.length() <= 1) return true;

        return input.charAt(0) == input.charAt(input.length() - 1) && isPalindrome(input.substring(1, input.length() - 1));
    }

    public static String findBinary(int decimal, String result) {
        //base-case
        if (decimal == 0) return result;

        result = decimal % 2 + result;
        return findBinary(decimal / 2, result);
    }
}
