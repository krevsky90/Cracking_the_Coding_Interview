package solving_techniques.p11_ModifiedBinarySearch.binarySearch;

public class TestBinarySearch {
    public static void main(String[] args) {
        TestBinarySearch obj = new TestBinarySearch();
        int[] arr = {1,2,2,4,5,6,6,6,9};
        System.out.println(obj.findLeftMostElementThatEqualsOrGreater(arr, 2)); //ER = 1
        System.out.println(obj.findLeftMostElementThatEqualsOrGreater(arr, 5)); //ER = 4
        System.out.println(obj.findLeftMostElementThatEqualsOrGreater(arr, 3)); //ER = 3, since it will be between 2 and 4
        System.out.println("---------");
        System.out.println(obj.findLeftMostElementThatGreater(arr, 2)); //ER = 3, since 4 > 2
        System.out.println(obj.findLeftMostElementThatGreater(arr, 5)); //ER = 5
        System.out.println(obj.findLeftMostElementThatGreater(arr, -3)); //ER = 0, i.e. insert before existing 0-th element
        System.out.println(obj.findLeftMostElementThatGreater(arr, 100)); //ER = 9 = arr.length, i.e. insert in the end of array
        System.out.println("---------");
        System.out.println(obj.findRightMostElementThatEqualsOrLower(arr, 2)); //ER = 2, the second '2'
        System.out.println(obj.findRightMostElementThatEqualsOrLower(arr, 5)); //ER = 4, i.e. element = 5
        System.out.println(obj.findRightMostElementThatEqualsOrLower(arr, 100)); //ER = 8, i.e. element = 9
        System.out.println(obj.findRightMostElementThatEqualsOrLower(arr, -1)); //ER = -1, since does not exist
        System.out.println("---------");
        System.out.println(obj.findRightMostElementThatLower(arr, 2)); //ER = 0, i.e. element = 1
        System.out.println(obj.findRightMostElementThatLower(arr, 5)); //ER = 3, i.e. element = 4
        System.out.println(obj.findRightMostElementThatLower(arr, 100)); //ER = 8, i.e. element = 9
        System.out.println(obj.findRightMostElementThatLower(arr, -1)); //ER = -1, since does not exist
    }

    public int findLeftMostElementThatEqualsOrGreater(int[] arr, int target) {
        int left = -1;
        int right = arr.length;

        while (right - left > 1) {
            int mid = left + (right - left)/2;
            if (arr[mid] >= target) {
                right = mid;
            } else {
                left = mid;
            }
        }

        return right;
    }

    public int findLeftMostElementThatGreater(int[] arr, int target) {
        int left = -1;
        int right = arr.length;

        while (right - left > 1) {
            //NOTE: just replace >= with >
            int mid = left + (right - left)/2;
            if (arr[mid] > target) {
                right = mid;
            } else {
                left = mid;
            }
        }

        return right;
    }

    public int findRightMostElementThatEqualsOrLower(int[] arr, int target) {
        int left = -1;
        int right = arr.length;

        while (right - left > 1) {
            int mid = left + (right - left)/2;
            if (arr[mid] <= target) {
                left = mid;
            } else {
                right = mid;
            }
        }

        return left;
    }

    public int findRightMostElementThatLower(int[] arr, int target) {
        int left = -1;
        int right = arr.length;

        while (right - left > 1) {
            int mid = left + (right - left)/2;
            //NOTE: just replace <= with <
            if (arr[mid] < target) {
                left = mid;
            } else {
                right = mid;
            }
        }

        return left;
    }

}
