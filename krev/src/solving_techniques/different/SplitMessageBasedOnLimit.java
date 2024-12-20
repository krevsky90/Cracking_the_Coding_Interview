package solving_techniques.different;

/**
 * 2468. Split Message Based on Limit (hard)
 * https://leetcode.com/problems/split-message-based-on-limit/
 *
 * #Company: 0 - 3 months Uber 4 0 - 6 months Databricks 6 6 months ago Capital One 8 Meta 5 Roblox 4 Amazon 2 TikTok 2 Visa 2 ZipRecruiter 2
 *
 * You are given a string, message, and a positive integer, limit.
 * You must split message into one or more parts based on limit.
 * Each resulting part should have the suffix "<a/b>",
 *      where "b" is to be replaced with the total number of parts
 *      and "a" is to be replaced with the index of the part, starting from 1 and going up to b.
 * Additionally, the length of each resulting part (including its suffix) should be equal to limit,
 *      except for the last part whose length can be at most limit.
 * The resulting parts should be formed such that when their suffixes are removed and they are all concatenated in order,
 *      they should be equal to message. Also, the result should contain as few parts as possible.
 * Return the parts message would be split into as an array of strings.
 * If it is impossible to split message as required, return an empty array.
 *
 * Example 1:
 * Input: message = "this is really a very awesome message", limit = 9
 * Output: ["thi<1/14>","s i<2/14>","s r<3/14>","eal<4/14>","ly <5/14>","a v<6/14>","ery<7/14>"," aw<8/14>","eso<9/14>","me<10/14>"," m<11/14>","es<12/14>","sa<13/14>","ge<14/14>"]
 * Explanation:
 * The first 9 parts take 3 characters each from the beginning of message.
 * The next 5 parts take 2 characters each to finish splitting message.
 * In this example, each part, including the last, has length 9.
 * It can be shown it is not possible to split message into less than 14 parts.
 *
 * Example 2:
 * Input: message = "short message", limit = 15
 * Output: ["short mess<1/2>","age<2/2>"]
 * Explanation:
 * Under the given constraints, the string can be split into two parts:
 * - The first part comprises of the first 10 characters, and has a length 15.
 * - The next part comprises of the last 3 characters, and has a length 8.
 *
 * Constraints:
 * 1 <= message.length <= 10^4
 * message consists only of lowercase English letters and ' '.
 * 1 <= limit <= 10^4
 */
public class SplitMessageBasedOnLimit {
    public static void main(String[] args) {
        SplitMessageBasedOnLimit obj = new SplitMessageBasedOnLimit();

        String message = "this is really a very awesome message";
        int limit = 9;

        String[] res = obj.splitMessage(message, limit);
        System.out.println("");
    }

    /**
     * NOT SOLVED by myself
     * info: https://leetcode.com/problems/split-message-based-on-limit/solutions/4850738/java-binary-search-fails-simple-brute-force-works-like-charm/?envType=company&envId=uber&favoriteSlug=uber-three-months
     * idea:
     * 1) use linear search to find the amount of parts
     * 1.1) use formula amount = 9 * (int) Math.pow(10, k - 1) to get numbers 9, 90, 900 etc
     * 1.2) use separate variable suffixLength since the length of 'parts' var can be reduced
     * 2) build the result based on p.1
     *
     * time to spend ~ 3h+
     * time ~ O(len*log10(len))
     * space ~ O(len) - to store String[]
     *
     * BEATS ~ 61%
     *
     * NOTE: binary search is worse,
     * and it is claimed that it can lead to incorrect result since if X parts is wrong, it does not mean that < X is also wrong
     */
    public String[] splitMessage(String message, int limit) {
        int len = message.length();

        for (int i = 1; i <= len; i++) {
            if (validate(len, limit, i)) {
                return buildResult(message, limit, i);
            }
        }

        return new String[0];
    }

    private String[] buildResult(String message, int limit, int counter) {
        int pos = 0;
        String[] result = new String[counter];
        for (int k = 1; k <= counter; k++) {
            int payload = limit - 3 - getLength(counter) - getLength(k);
            int endIdx = Math.min(message.length(), pos + payload); //do not out of bound
            result[k - 1] = message.substring(pos, endIdx) + "<" + k + "/" + counter + ">";
            pos = pos + payload;
        }

        return result;
    }

    private boolean validate(int len, int limit, int parts) {
        int k = 1;  //length of temp number
        int suffixLength = 3 + getLength(parts) + k;
        while (len > 0 && parts > 0) {
            int payload = limit - suffixLength;
            int amount = 9 * (int) Math.pow(10, k - 1);   //i.e. 9, 90, 900 etc
            //NOTE: amount might be > parts => use Math.min
            amount = Math.min(amount, parts);
            len -= payload * amount;
            parts -= amount;
            k++;
            suffixLength++; // we do not use formula 3 + getLength(parts) + k since parts is changed => its length is changed
        }

        return len <= 0;
    }

    private int getLength(int k) {
        //or Math.log10(k + 1) - to handle case when k = 10 => log10(10) = 1, but we need 2 symbols
        int count = 0;
        while (k > 0) {
            count++;
            k = k / 10;
        }
        return count;
    }
}
