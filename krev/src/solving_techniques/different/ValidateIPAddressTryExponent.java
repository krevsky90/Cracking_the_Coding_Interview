package solving_techniques.different;

/**
 * https://www.tryexponent.com/practice/prepare/validate-ip-address
 * Validate an IP address (IPv4).
 * An address is valid if and only if it is in the form "X.X.X.X",
 * where each X is a number from 0 to 255.
 * <p>
 * For example, "12.34.5.6", "0.23.25.0", and "255.255.255.255" are valid IP addresses,
 * while "12.34.56.oops", "1.2.3.4.5", and "123.235.153.425" are invalid IP addresses.
 * <p>
 * Examples:
 * <p>
 * ip = '192.168.0.1'
 * output: true
 * <p>
 * ip = '0.0.0.0'
 * output: true
 * <p>
 * ip = '123.24.59.99'
 * output: true
 * <p>
 * ip = '192.168.123.456'
 * output: false
 */
public class ValidateIPAddressTryExponent {
    /**
     * KREVSKY SOLUTION
     * <p>
     * time to solve ~ 30 mins
     * <p>
     * many attempts
     */
    static boolean validateIP(String ip) {
        // your code goes here
        if (ip == null) return false;

        char[] arr = ip.toCharArray();
        String num = "";
        int dotCounter = 0;
        for (int i = 0; i < arr.length + 1; i++) {
            if (i < arr.length && Character.isDigit(arr[i])) {
                num += arr[i];
            } else if (i == arr.length || arr[i] == '.') {
                if (num.isEmpty()) return false; //..0.0 or 0.0.0.
                if (num.charAt(0) == '0' && num.length() > 1) return false; //00.0.0.0
                int numValue = Integer.valueOf(num);
                if (!(0 <= numValue && numValue <= 255)) return false;

                if (i == arr.length) {
                    return dotCounter == 3;
                } else {
                    dotCounter++;
                    if (dotCounter > 3) return false;

                    num = "";
                }
            } else if (i < arr.length) {
                return false;
            }
        }

        return true;
    }

    /**
     * SOLUTION #2:
     * use split method
     */
    static boolean validateIP2(String ip) {
        String[] arr = ip.split("\\.", -1);
        for (String x : arr) {
            if (!(1 <= x.length() && x.length() <= 3)) return false;
            if (x.charAt(0) == '0' && x.length() > 1) return false;
            for (char ch : x.toCharArray()) {
                if (!Character.isDigit(ch)) return false;
            }
            int n = Integer.valueOf(x);
            if (n < 0 || n > 255) return false;
        }

        return arr.length == 4;
    }



    public static void main(String[] args) {
        // debug your code below
        String ip = "192.168.0.1";
//        boolean result = validateIP(ip);
//        System.out.println("Is IP valid? " + result);

        System.out.println(validateIP2(".2..3 "));

//        "..1.1.1.1...".split("\\.", -1) => ["", "", "1", "1", "1", "", "", ""]
//        "..1.1.1.1...".split("\\.") => ["", "", "1", "1", "1", "1"]
//        "...1.1.1.1...".split("\\.+") => ["", "1", "1", "1", "1"]

    }
}
