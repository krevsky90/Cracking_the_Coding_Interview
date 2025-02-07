package solving_techniques.different;

/**
 * 468. Validate IP Address (medium)
 * https://leetcode.com/problems/validate-ip-address
 * <p>
 * #Company (7.02.2025): 0 - 6 months Meta 4 Microsoft 2 Amazon 2 Turing 2 6 months ago TikTok 6 Apple 4 Oracle 3 ServiceNow 3 Sprinklr 3 Cisco 2 X 2 Deutsche Bank 2
 * <p>
 * Given a string queryIP, return "IPv4" if IP is a valid IPv4 address, "IPv6" if IP is a valid IPv6 address or "Neither"
 * if IP is not a correct IP of any type.
 * <p>
 * A valid IPv4 address is an IP in the form "x1.x2.x3.x4" where 0 <= xi <= 255 and xi cannot contain leading zeros.
 * For example, "192.168.1.1" and "192.168.1.0" are valid IPv4 addresses
 * while "192.168.01.1", "192.168.1.00", and "192.168@1.1" are invalid IPv4 addresses.
 * <p>
 * A valid IPv6 address is an IP in the form "x1:x2:x3:x4:x5:x6:x7:x8" where:
 * <p>
 * 1 <= xi.length <= 4
 * xi is a hexadecimal string which may contain digits, lowercase English letter ('a' to 'f')
 * and upper-case English letters ('A' to 'F').
 * Leading zeros are allowed in xi.
 * For example, "2001:0db8:85a3:0000:0000:8a2e:0370:7334" and "2001:db8:85a3:0:0:8A2E:0370:7334" are valid IPv6 addresses,
 * while "2001:0db8:85a3::8A2E:037j:7334" and "02001:0db8:85a3:0000:0000:8a2e:0370:7334" are invalid IPv6 addresses.
 * <p>
 * Example 1:
 * Input: queryIP = "172.16.254.1"
 * Output: "IPv4"
 * Explanation: This is a valid IPv4 address, return "IPv4".
 * <p>
 * Example 2:
 * Input: queryIP = "2001:0db8:85a3:0:0:8A2E:0370:7334"
 * Output: "IPv6"
 * Explanation: This is a valid IPv6 address, return "IPv6".
 * <p>
 * Example 3:
 * Input: queryIP = "256.256.256.256"
 * Output: "Neither"
 * Explanation: This is neither a IPv4 address nor a IPv6 address.
 * <p>
 * Constraints:
 * queryIP consists only of English letters, digits and the characters '.' and ':'.
 */
public class ValidateIPAddress {
    /**
     * KREVSKY SOLUTION:
     * time to solve ~ 15 mins
     * time ~ O(n)
     * space ~ O(n)
     * <p>
     * 3 attempts:
     * - used split method incorrectly (without limit = -1)
     * - for IPv4 did not check if 1 <= length <= 3
     * <p>
     * BEATS ~ 94%
     */
    public String validIPAddress(String queryIP) {
        if (validateIPv4(queryIP)) {
            return "IPv4";
        } else if (validateIPv6(queryIP)) {
            return "IPv6";
        } else {
            return "Neither";
        }
    }

    private boolean validateIPv4(String ip) {
        String[] parts = ip.split("\\.", -1);
        for (String p : parts) {
            if (!(1 <= p.length() && p.length() <= 3)) return false;
            if (p.charAt(0) == '0' && p.length() > 1) return false;
            for (char c : p.toCharArray()) {
                if (!Character.isDigit(c)) return false;
            }
            int num = Integer.valueOf(p);
            if (num > 255) return false;
        }

        return parts.length == 4;
    }

    private boolean validateIPv6(String ip) {
        String[] parts = ip.split("\\:", -1);
        for (String p : parts) {
            if (!(1 <= p.length() && p.length() <= 4)) return false;
            for (char c : p.toCharArray()) {
                if (!('0' <= c && c <= '9' || 'a' <= c && c <= 'f' || 'A' <= c && c <= 'F')) return false;
            }
        }

        return parts.length == 8;
    }

}
