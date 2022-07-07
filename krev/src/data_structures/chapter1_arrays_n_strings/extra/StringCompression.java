package data_structures.chapter1_arrays_n_strings.extra;

/**
 * https://www.youtube.com/watch?v=XMKMgzU1uiw&list=PLNmW52ef0uwsjnM06LweaYEZr-wjPKBnj&index=8
 * Byte by Byte: Interview Question: String Compression
 * <p>
 * Given a string, write a function to compress it by shortening every sequence
 * of the same character to that character followed by the number of repetitions.
 * If the compressed string is longer than the original,
 * you should return the original string
 *
 * Examples:
 * compress("a") = a
 * compress("aaa") = a3
 * compress("aaabbb") = a3b3
 * compress("aaabccc") = a3b1c3
 *
 *
 */
public class StringCompression {
    public static void main(String[] args) {
        System.out.println(compress("ab"));
        System.out.println(compress("aaabbb"));
        System.out.println(compress("aaabccc"));
    }
    /**
     * KREVSKY SOLUTION
     */
    public static String compressKrev(String s) {
        if (s == null || s.isEmpty()) return s;
        StringBuilder sb = new StringBuilder();
        char prev = 0;
        int count = 0;
        for (char c : s.toCharArray()) {
            if (c == prev) {
                count++;
            } else {
                if (prev != 0) sb.append(prev).append(count);
                count = 1;
                prev = c;
            }
        }
        sb.append(prev).append(count);
        return sb.length() > s.length() ? s : sb.toString();
    }

    /**
     * ORIGINAL SOLUTION
     */
    public static String compress(String s) {
        String out = "";
        int count = 1;
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == s.charAt(i+1)) {
                count++;
            } else {
                //ATTENTION!
                //if we write 'out += s.charAt(i) + count, then we will get mistake,
                //because s.charAt(i) converts to int number =>
                // we just add int number (s.charAt(i) + count) to string
                // So we need to write out = out + ... to notify compilation,
                // that '+' is concatenation of the strings rather than sum of int numbers
                out = out + s.charAt(i) + count;
                count = 1;
            }
        }
        out = out + s.charAt(s.length() - 1) + count;
        return out.length() > s.length() ? s : out;
    }
}
