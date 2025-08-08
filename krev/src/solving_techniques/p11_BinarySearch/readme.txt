Mavrin 1-20 mins from https://vk.com/video-186224312_456239025

ideas:
1) initiate left and right by indexes that are out of bounds
2) while condition r - l > 1. => each iteration left < mid < right. => we won't get infinite loop!
3) identify what part will be 'bad' and 'good'. based on that write if-condition. If it is true, mid element belongs to 'good' part.
4) don't need to think if we need to write +1 or -1 for left and right. just set = mid
5) return pointer (left or right) that is changed when if-condition (from p.3) is true

Assume, we want to find the most left element that >= x
NOTE: it also works in case when there are several elements = x

Example of algorithm:
    int left = -1;
    int right = arr.length;

    while (right - left > 1) {
        int mid = left + (right - left)/2;
        if (arr[mid] >= x) { //conditionToFindGoodElements
            right = mid;
        } else {
            left = mid;
        }
    }

    return right;

Cases:
1.1) find the most left element >= target:
    code:
        if (arr[mid] >= target) {
            right = mid;
        } else {
            left = mid;
        }
    return: right

1.2) find the most left element > target:
    code:
        //NOTE: just replace >= with >
        if (arr[mid] > target) {
            right = mid;
        } else {
            left = mid;
        }
    return: right

2.1) find the most right element <= target:
    code:
        if (arr[mid] <= target) {
            left = mid;
        } else {
            right = mid;
        }
    return: left

2.2) find the most right element < target:
    code:
        //NOTE: just replace <= with <
        if (arr[mid] < target) {
            left = mid;
        } else {
            right = mid;
        }
    return: left

see these cases here src/solving_techniques/p11_ModifiedBinarySearch/binarySearch/TestBinarySearch.java

ATTENTION!!!
NOTE: be careful in case if we need to return element, but not index!
    see https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
    here we need to check if left and right is not out of bound

3) naive and understandable implementation:
3.1) find the left most element = target:
    private int findLeft(int[] arr, int c) {
            int low = 0;
            int high = arr.length - 1;
            int res = -1;
            while (low <= high) {
                int mid = low + (high - low) / 2;
                if (arr[mid] < c) {
                    low = mid + 1;
                } else if (arr[mid] > c) {
                    high = mid - 1;
                } else {
                    res = mid;
                    high = mid - 1;
                }
            }

            return res;
        }
3.2) find the right most element = target:
    private int findRight(int[] arr, int c) {
        int low = 0;
        int high = arr.length - 1;
        int res = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] < c) {
                low = mid + 1;
            } else if (arr[mid] > c) {
                high = mid - 1;
            } else {
                res = mid;
                low = mid + 1;
            }
        }

        return res;
    }

