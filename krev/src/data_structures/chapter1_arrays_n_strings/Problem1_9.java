package data_structures.chapter1_arrays_n_strings;

/**
 * p.103
 *
 * 1.9. String Rotation: Assume you have a method isSubstring which checks if one word is a substring
 * of another. Given two strings, s1 and s2, write code to check if s2 is a rotation of s1 using only one
 * call to isSubstring (e.g., "waterbottle" is a rotation of "erbottlewat").
 * Hints: #34, #88, #105
 *
 * ASSUMPTION:
 * 1) s1 is not empty
 * 2) s1.length = s2.length
 *
 */
public class Problem1_9 {
    public static void main(String[] args) {
        String s1 = "waterbottle";
        String s2 = "erbottlewat";
        System.out.println(isRotation(s1, s2));
    }

    /**
     * SOLUTION:
     * we need to check if there's a way to split s1 into x and y such that xy = s1 and yx = s2. Regardless of
     * where the division between x and y is, we can see that yx will always bea substring of xyxy. That is, s2 will
     * always be a substring of s1s1.
     *
     */
    public static boolean isRotation(String s1, String s2) {
        if (s1 == null || s2 == null || s1.isEmpty() || s2.length() != s1.length()) {
            return false;
        }

        String s1s1 = s1+s1;
        return s1s1.contains(s2);
    }
}
