package practice.arrays.prefixarray;

/**
 * Given an integer array nums, return the number of subarrays filled with 0.
 * A subarray is a contiguous non-empty sequence of elements within an array.
 *
 * Input: nums = [1,3,0,0,2,0,0,4]
 * Output: 6
 * There are 4 occurrences of [0] as a subarray.
 * There are 2 occurrences of [0,0] as a subarray.
 * There is no occurrence of a subarray with a size more than 2 filled with 0. Therefore, we return 6.
 *
 * Input: nums = [0,0,0,2,0,0]
 * Output: 9
 * Explanation:
 * There are 5 occurrences of [0] as a subarray.
 * There are 3 occurrences of [0,0] as a subarray.
 * There is 1 occurrence of [0,0,0] as a subarray.
 * There is no occurrence of a subarray with a size more than 3 filled with 0. Therefore, we return 9.
 *
 * Input: nums = [2,10,2019]
 * Output: 0
 * Explanation: There is no subarray filled with 0. Therefore, we return 0
 */
public class ZeroFilledSubArray {

    public static void main(String args[]) {
        int[] x1 = {1,3,0,0,2,0,0,4};
        int[] x2 = {0,0,0,2,0,0};
        int[] x3 = {2,10,2019};

        System.out.println(bruteForce(x1));
        System.out.println(bruteForce(x2));
        System.out.println(bruteForce(x3));
    }

    //Observations - count the number of 0s in a pair of 2 or 3 or x
    // scan from left to right - find the total number of 0s. also track max continuous zero number.(j)
    // if total < length of array
        // count the number of 0s with continuous length = 1 to j
    public static int bruteForce(int[] x) {
        int numSubArray = 0;
        int ans = 0;
        for(int i=0; i<x.length; i++) {
            if(x[i] ==0) {
                numSubArray++;
            } else {
                numSubArray = 0;
            }
            ans += numSubArray;
        }
        return ans;
    }
}
