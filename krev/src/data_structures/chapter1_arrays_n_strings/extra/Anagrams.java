package data_structures.chapter1_arrays_n_strings.extra;

/**
 * https://www.youtube.com/watch?v=6W_Fve7qIe4&list=PLNmW52ef0uwsjnM06LweaYEZr-wjPKBnj&index=7
 * Byte by Byte: Interview Question: Anagrams
 * <p>
 * Given two strings, write a function to determine whether they are anagrams
 *
 */
public class Anagrams {
    /**
     * KREVSKY SOLUTION
     */
    public static boolean isAnagramKrev(String s1, String s2) {
        if (s1 == null || s2 == null) return false;
        if (s1.length() != s2.length()) return false;
        s1 = s1.toLowerCase();
        s2 = s2.toLowerCase();

        int[] a1 = getArr(s1);
        int[] a2 = getArr(s2);
        //there will not be any problems since each arr has length = 256
        for(int i = 0; i < a1.length; i++) {
            if (a1[i] != a2[i]) return false;
        }

        return true;
    }

    private static int[] getArr(String s) {
        int[] res = new int[256];
        for (int i = 0; i < s.length(); i++) {
            res[s.charAt(i)]++; //THAT'S THE IDEA! since each char is number (i.e. byte) from 0 to 255
        }
        return res;
    }

    /**
     * ORIGINAL SOLUTION
     */
    public static boolean isAnagram(String s1, String s2) {
        if (s1 == null || s2 == null) return false;
        if (s1.length() != s2.length()) return false;
        s1 = s1.toLowerCase();
        s2 = s2.toLowerCase();

        int[] arr = new int[256];

        for (char c : s1.toCharArray()) {
            arr[c]++;
        }

        for (char c : s2.toCharArray()) {
            arr[c]--;   // decrement!
        }

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 0) return false;
        }

        return true;
    }

}
