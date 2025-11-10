package practice.binarySearch;

/**
 * There is an integer array nums sorted in ascending order (with distinct values).
 * Prior to being passed to your function, nums is possibly rotated at an unknown pivot index k (1 <= k < nums.length)
 * such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed).
 * For example, [0,1,2,4,5,6,7] might be rotated at pivot index 3 and become [4,5,6,7,0,1,2].
 *
 * Given the array nums after the possible rotation and an integer target, return the index of target if it is in nums,
 * or -1 if it is not in nums.
 *
 * You must write an algorithm with O(log n) runtime complexity.

 * Input: nums = [4,5,6,7,0,1,2], target = 0
 * Output: 4

 * Input: nums = [4,5,6,7,0,1,2], target = 3
 * Output: -1

 * Input: nums = [1], target = 0
 * Output: -1
 */

public class SearchRotatedArray {
    /**
     * Obs - can be done in linear scan
     */
    public static void main(String args[]) {
        int[] x1 = {4,5,6,7,0,1,2}; // target = 0 o/p = 4
        int[] x2 = {4,5,6,7,0,1,2}; // target = 3 o/p = -1
        int[] x3 = {1}; // target 0 o/p = -1

        System.out.println(binarySearch(x1, 0));
        System.out.println(binarySearch(x2, 3));
        System.out.println(binarySearch(x3, 0));

        System.out.println(pivotAndBinarySearch(x1, 0));
        System.out.println(pivotAndBinarySearch(x2, 3));
        System.out.println(pivotAndBinarySearch(x3, 0));

    }

    /**
     * Think how can we apply binary search on this.
     * low = 0 , high = 7-1 = 6 mid = 3
     *
     * eg :- {0,1,2,4,5,6,7} can be rotated on any index
     * {0,1,2,4,5,6,7}
     * {1,2,4,5,6,7,0}
     * {2,4,5,6,7,0,1}
     * {4,5,6,7,0,1,2}
     * {5,6,7,0,1,2,4}
     * {6,7,0,1,2,4,5}
     * {7,0,1,2,4,5,6}
     *
     * if we divide the array on mid --> there will always be an increasing subarray. now if target falls between this
     * increasing subarray --> we just need to do binary search for this array  else --> we need to look into the other
     * subarray
     *
     * if x[mid] == target --> return mid
     *
     * if left subarray is sorted i.e x[mid] >= x[low]
     *      if target < x[mid] && target >= x[low]  --- > high = mid-1
     *      else ---> low = mid +1;
     *
     * else
     *      if target > x[mid] && target <= x[high] --> low = mid +1
     *      else ---> high = mid - 1;
     */

    public static int binarySearch(int[] x, int target) {
        int n = x.length;
        int left =0, right = n-1;

        while(left <= right) {
            int mid = left + (right-left)/2;

            if(x[mid] == target) {
                return mid;
            }

            // Case 2 - when left sub-array is sorted
            if (x[mid] >= x[left]) {
                if (x[left] <= target && target < x[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                // Case 3 - when right sub-array is sorted
                if(target > x[mid] && target <= x[right]) {
                    left = mid+1;
                } else {
                    right = mid -1;
                }
            }
        }
        return -1;
    }

    /**
     * Second approach --> find the pivot index (index of the smallest number) and then search on both left and right
     * subarray based on this pivot index
     *
     * How to find the smallest number using binary search in the following
     * {0,1,2,4,5,6,7}
     * {1,2,4,5,6,7,0}
     * {2,4,5,6,7,0,1}
     * {4,5,6,7,0,1,2}
     * {5,6,7,0,1,2,4}
     * {6,7,0,1,2,4,5}
     * {7,0,1,2,4,5,6}
     *
     * array is sorted and pivoted. if the mid element is greater than rightmost element --> then the pivot or the smallest
     * number lies in the right subarray else in the left subarray.
     *
     * once we find the pivot index check where the target can be by comparing with pivot
     */

    public static int pivotAndBinarySearch(int[] x, int target) {
        int n = x.length;
        int left = 0, right = n-1;

        // find the pivot index (pivot = smallest value)
        while(left <= right) {
            int mid = left + (right - left)/2;

            if(x[mid] > x[n-1]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        // look in the left sub-array and if found return
        int result = binarySearchUsingPivot(x, 0, left-1, target);
        if(result != -1) {
            return result;
        } else {
           // look into the right sub-array
            return binarySearchUsingPivot(x, left, n-1, target);
        }
    }

    private static int binarySearchUsingPivot(int[] x, int left, int right, int target) {

        while(left <= right) {
            int mid = left + (right -left)/2;

            if(x[mid] == target) {
                return mid;
            }
            if(x[mid] > target) {
                right = mid-1;
            } else {
                left = mid+1;
            }
        }
        return -1;
    }
}
