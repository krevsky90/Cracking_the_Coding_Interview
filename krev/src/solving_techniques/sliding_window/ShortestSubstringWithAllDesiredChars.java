package solving_techniques.sliding_window;

import java.util.HashMap;
import java.util.Map;

/**
 * https://youtu.be/jM2dhDPYMQM?t=1342
 *
 * HARD question in case if strings may contain repeating characters - use Map!
 * for this case info https://www.youtube.com/watch?v=eS6PZLjoaq8
 */
public class ShortestSubstringWithAllDesiredChars {
    public static void main(String[] args) {
        String s = "fa4chba4c";
        String desiredChars = "abc";
        String result = findShortestSubstringWithAllDesiredCharsUnique(s, desiredChars);
        System.out.println(result);
    }

    public static String findShortestSubstringWithAllDesiredCharsUnique(String s, String chars) {
        int start = 0;
        int minLength = Integer.MAX_VALUE;


        char[] arrS = s.toCharArray();
        char[] arrChars = chars.toCharArray();

        int startStr = 0;
        int endStr = 0;

        //fill map and sum of its values
        int sumMapValues = arrChars.length;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < arrChars.length; i++) {
            int cur = map.getOrDefault(arrChars[i], 0);
            map.put(arrChars[i], cur + 1);
        }

        for (int end = 0; end < arrS.length; end++) {
            char tempC = arrS[end];
            if (map.containsKey(tempC) && map.get(tempC) > 0) {
                map.put(tempC, map.get(tempC) - 1);
                sumMapValues--;
            }

            //check if all values of the map are empty
            while (sumMapValues == 0) {
                minLength = Math.min(minLength, end - start + 1);
                startStr = start;
                endStr = end;

                //start shrinking the sliding window
                char currentStartChar = arrS[start];
                if (map.containsKey(currentStartChar)) {
                    map.put(currentStartChar, map.getOrDefault(currentStartChar, 0) + 1);
                    sumMapValues++;
                }
                start++;
            }
        }

        return s.substring(startStr, endStr + 1);
    }

}
