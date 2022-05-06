package data_structures.chapter5_bit_manipulation;

/**
 * p.128
 * 5.2 Binary to String:
 * Given a real number between 0 and 1 (e.g., 0.72) that is passed in as a double,
 * print the binary representation.
 * If the number cannot be represented accurately in binary with at most 32 characters,
 * print "ERROR:'
 * Hints: #143, #167, #173, #269, #297
 * <p>
 * ASSUMPTION/VALIDATION:
 * 1) 0 < num < 1
 */
public class Problem5_2 {
    /**
     * ликбез по представлению десятичной дроби в бинарном виде
     * https://server.179.ru/tasks/cpp/total/131.html
     */
    public static void main(String[] args) {
        System.out.println(Integer.toBinaryString(1875));
        double n = 0.1875;  // = 3/16
        String result = binaryRepresentationKrevsky(n);
        System.out.println(result); // = 0.0011
    }

    /**
     * ORIGINAL SOLUTION:
     * To print the decimal part, we can multiply by 2 and check if 2n is greater than or equal to 1.
     * This is essentially "shifting" the fractional (дробной) sum.
     * If r >= 1, then we know that num had a 1 right after the decimal point.
     * By doing this continuously, we can check every digit.
     */
    public static String binaryRepresentation(double num) {
        if (num <= 0 || num >= 1) {
            return "ERROR";
        }
        StringBuilder sb = new StringBuilder(".");
        while (num > 0) {
            if (sb.length() >= 32) {
                return "ERROR";
            }

            double r = 2 * num;
            if (r > 1) {
                sb.append(1);
                num = r - 1;
            } else {
                sb.append(0);
                num = r;
            }
        }
        return sb.toString();
    }

    /**
     * по сути та же идея, что и оригинальная
     */
    public static String binaryRepresentationKrevsky(double n) {
        if (n <= 0 || n >= 1) {
            return "ERROR";
        }
        StringBuilder sb = new StringBuilder(".");
        int i = 1;
        while (i < 32) {
            double d = Math.pow(2, -i);
            if (n >= d) {
                n -= d;
                sb.append(1);
                if (n == 0) {
                    return sb.toString();
                }
            } else {
                sb.append(0);
            }
            i++;
        }

        return "ERROR";
    }
}
