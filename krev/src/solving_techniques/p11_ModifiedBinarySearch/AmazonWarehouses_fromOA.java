package solving_techniques.p11_ModifiedBinarySearch;

/**
 * https://leetcode.com/discuss/interview-question/5158290/Amazon-OA
 * problem #2:
 * <p>
 * Given n centers, each has location as number on number line (-10^9, 10^9)
 * We can create warehouse. The place is suitable if the total distance between all centers and this warehouse is <= d/2
 * <p>
 * Return amount of suitable places for warehouses
 */
public class AmazonWarehouses_fromOA {
    /**
     * idea:
     * 1) use binary search to find minimum suitable value (i.e. place) on the number line
     *  NOTE: if dist > d then we need to understand what part should be checked (left or right).
     *        to determine this, we calculate the dist for mid+1 and compare it with dist for mid
     * 2) use binary search to find maximum suitable value (i.e. place) on the number line
     * 3) return max - min + 1;
     *
     * time to implement ~ 10 mins
     *
     * time ~ O(n*logD), where n = center.length, D - max distance in the number line
     * space ~ O(1)
     *
     * 1 attempt
     */
    public int suitableLocation(int[] center, int d) {
        //find min
        int low = -1000000000;
        int high = 1000000000;

        int boundL = Integer.MIN_VALUE;
        while (low <= high) {
            int mid = low + (high - low)/2;

            int dist = calcDist(center, mid);
            int dist1 = calcDist(center, mid + 1);

            if (dist <= d) {
                boundL = mid;
                high = mid - 1;
            } else if (dist < dist1) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        if (boundL == Integer.MIN_VALUE) return 0;  //there is no suitable place!

        //find max
        low = boundL;
        high = 1000000000;
        int boundR = Integer.MAX_VALUE;
        while (low <= high) {
            int mid = low + (high - low)/2;

            int dist = calcDist(center, mid);
            int dist1 = calcDist(center, mid + 1);

            if (dist <= d) {
                boundR = mid;
                low = mid + 1;
            } else if (dist < dist1) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return boundR - boundL + 1;
    }


    private int calcDist(int[] center, int x) {
        int sum = 0;
        for (int y : center) {
            sum += 2 * Math.abs(y - x);
        }
        return sum;
    }
}
