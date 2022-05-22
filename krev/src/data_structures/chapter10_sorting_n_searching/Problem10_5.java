package data_structures.chapter10_sorting_n_searching;

/**
 * p.162
 * 10.5 Sparse Search:
 * Given a sorted array of strings that is interspersed with empty strings,
 * write a method to find the location of a given string.
 * EXAMPLE
 * Input: ball, {"at", "", "", "", "ball", "", "", "car", "", "", "dad", "", ""}
 * Output: 4
 * Hints: #256
 * <p>
 * ASSUMPTION/VALIDATION:
 */
public class Problem10_5 {
    public static void main(String[] args) {
        String s = "ball";
        String[] arr = new String[]{"at", "", "", "", "ball", "", "", "car", "", "", "dad", "", ""};
//        String[] arr = new String[]{"at", "", "", "", "ball", "", "", "", "", "", "", "", ""};
        int result = search(arr, s);
        System.out.println(result);
    }

    public static int search(String[] arr, String s) {
        if (arr == null || s == null || "".equals(s)) {
            return -1;
        }
        return searchKREV(arr, s, 0, arr.length);
    }

    /**
     * The idea is the same as for original solution:
     * if arr[mid] is empty -> find the nearest element that is not empty, and consider its index as mid
     */
    public static int searchKREV(String[] arr, String s, int left, int right) {
        if (left > right) {
            return -1;
        }

        int mid = (left + right) / 2;
        if ("".equals(arr[mid])) {
            //move (for example) to the end direction until we find non-empty element
            int i = mid + 1;
            while (i < right && "".equals(arr[i])) {
                i++;
            }
            if (i == right) {
                mid = mid - 1;
            } else {
                mid = i;
            }
        }

        if (s.compareTo(arr[mid]) > 0) {
            return searchKREV(arr, s, mid + 1, right);
        } else if (s.compareTo(arr[mid]) == 0) {
            return mid;
        } else {
            return searchKREV(arr, s, left, mid - 1);
        }
    }

    /**
     * ORIGINAL SOLUTION
     */
    public static int searchORIG(String[] strings, String str, int first, int last) {
        if (first > last) return -1;
        /* Move mid to the middle */
        int mid = (last + first) / 2;
        /* If mid is empty, find closest non-empty string. */
        if (strings[mid].isEmpty()) {
            int left = mid - 1;
            int right = mid + 1;
            while (true) {
                if (left < first && right > last) {
                    return -1;
                } else if (right <= last && !strings[right].isEmpty()) {
                    mid = right;
                    break;
                } else if (left >= first && !strings[left].isEmpty()) {
                    mid = left;
                    break;
                }
                right++;
                left--;
            }
        }
        /* Check for string, and recurse if necessary */
        if (str.equals(strings[mid])) { // Found it!
            return mid;
        } else if (strings[mid].compareTo(str) < 0) { // Search right
            return searchORIG(strings, str, mid + 1, last);
        } else { // Search left
            return searchORIG(strings, str, first, mid - 1);
        }
    }
}
