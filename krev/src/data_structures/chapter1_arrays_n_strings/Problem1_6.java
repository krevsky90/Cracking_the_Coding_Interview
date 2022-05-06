package data_structures.chapter1_arrays_n_strings;

/**
 * p.103
 *
 * 1.6. String Compression: Implement a method to perform basic string compression using the counts
 * of repeated characters. For example, the string aabcccccaaa would become a2blc5a3. If the
 * "compressed" string would not become smaller than the original string, your method should return
 * the original string. You can assume the string has only uppercase and lowercase letters (a - z).
 *
 * Hints: #92, #110
 *
 * NOTE: эту же задачка давал Макс в Яндексе в 2020м
 */
public class Problem1_6 {
    public static void main(String[] args) {
        String s = "aabcccccaaa";
//        String s = "abc";
        System.out.println(compression(s));
    }

    //KREVSKY = SOLUTION p.214
    public static String compression(String s) {
        StringBuilder sb = new StringBuilder(s.length());   //NOTE: initialization helps to avoid several extensions! - не догадался!
        int count = 1;
        char currentChar = s.charAt(0);
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == currentChar) {
                count++;
            } else {
                sb.append(currentChar).append(count);
                currentChar = s.charAt(i);
                count = 1;
            }
        }

        //DO NOT FORGET!!!
        sb.append(currentChar).append(count);

        return sb.length() >= s.length() ? s : sb.toString();
    }
}
