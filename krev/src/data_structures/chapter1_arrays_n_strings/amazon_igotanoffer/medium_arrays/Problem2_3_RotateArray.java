package data_structures.chapter1_arrays_n_strings.amazon_igotanoffer.medium_arrays;

public class Problem2_3_RotateArray {


    /**
     * KREVSKY SOLUTION
     * idea - simple filling of new array and copying to the initial array
     * time complexity ~ O(n)
     * space complexity ~ O(n)
     * time to solve ~ 10-15 mins
     */
    public void rotateKrev1(int[] nums, int k) {
        int len = nums.length;
        int kk = k % len;

        //nums: 1,2,3,4,5,6,7
        //len = 7
        //k = 2
        //i = 6: nums[6 - 2] = nums[4] = 5
        //res: 6,7,1,2,3,4,5

        int[] res = new int[len];
        for (int i = 0; i < kk; i++) {
            res[i] = nums[len - kk + i];
        }

        for (int i = kk; i < len; i++) {
            res[i] = nums[i - kk];
        }

        System.arraycopy(res, 0, nums, 0, len);
    }

    /**
     * KREVSKY SOLUTION
     * time complexity ~ O((n-k)*k)
     * space complexity ~ O(1)
     * time to solve ~ 30 mins
     */
    public void rotateKrev2(int[] nums, int k) {
        int len = nums.length;
        int kk = k % len;

        if (kk == 0) return;

        //nums: 1,2,3,4,5,6,7
        //len = 7
        //kk = 3
        //temp = 7
        //save the last element (it will be shifted in future)
        int temp = nums[len - 1];
        //move len - kk - 1-th element to the end
        nums[len - 1] = nums[len - kk - 1];
        //1 2 3 4 | 5 6 7
        for (int i = len - kk - 1; i >= 0; i--) {
            //change to 1 2 3 4 5 6 4
            nums[i + kk] = nums[i];

            //move kk-1 elements (5 and 6) to the left in one position
            //i.e change to 1 2 3 5 6 6 4
            //i = 3
            //j = 4
            //i + 1 + kk-1 = i + kk = 3 + 3 = 6
            for (int j = i + 1; j < i + 1 + kk - 1; j++) {
                nums[j - 1] = nums[j];
            }
        }

        //i = 2
        //i + 1 + kk-1 = i + kk = 2 + 3 = 5
        //j = 3
        //1 2 5 6 6 3 4
        //..
        //5 6 6 1 2 3 4

        //change to 5 6 7 1 2 3 4
        nums[kk - 1] = temp;
    }

    /**
     * BEST SOLUTION
     * https://leetcode.com/problems/rotate-array/solutions/3125740/easy-solution-with-explanation-without-using-extra-space/
     * idea - reverse whole array and then reverse two parts (divided by k amount) again
     * time complexity ~ O(n)
     * space complexity ~ O(1)
     */
    public void rotate(int[] nums, int k) {
        //k = 3
        k %= nums.length;
        //1 2 3 4 5 6 7
        rotate(nums, 0, nums.length - 1);
        //7 6 5 4 3 2 1
        rotate(nums, 0, k - 1);
        //5 6 7 4 3 2 1
        rotate(nums, k, nums.length - 1);
        //5 6 7 1 2 3 4
    }

    private void rotate(int[] nums, int low, int high) {
        while (low < high) {
            int temp = nums[low];
            nums[low] = nums[high];
            nums[high] = temp;
            low++;
            high--;
        }
    }
}
