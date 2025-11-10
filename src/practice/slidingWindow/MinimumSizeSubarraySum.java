package practice.slidingWindow;

import java.util.Arrays;

/**
 * Given an array of positive integers nums and a positive integer target, return the minimal length of a subarray
 * whose sum is greater than or equal to target. If there is no such subarray, return 0 instead.
 *
 * Input: target = 7, nums = [2,3,1,2,4,3]
 * Output: 2
 * Explanation: The subarray [4,3] has the minimal length under the problem constraint.
 * *
 * Input: target = 4, nums = [1,4,4]
 * Output: 1
 *
 * Input: target = 11, nums = [1,1,1,1,1,1,1,1]
 * Output: 0
 */
public class MinimumSizeSubarraySum {
    public static void main(String args[]) {
        int[] x1 = {2,3,1,6,4,2}; int target1 = 7;          //2
        int[] x2 = {1,4,4}; int target2 = 4;                //1
        int[] x3 = {1,1,1,1,1,1,1,1}; int target3 = 11;     //0

        System.out.println(slidingWindow(x1, 7));
        System.out.println(slidingWindow(x2, 4));
        System.out.println(slidingWindow(x3, 11));

        System.out.println(sortingApproach(x1, 7));
        System.out.println(sortingApproach(x2, 4));
        System.out.println(sortingApproach(x3, 11));
    }

    public static int slidingWindow(int[] x, int target) {
        int n = x.length;
        int sum = 0;
        int minLength = n + 1;

        // j is the end pointer
        for (int j = 0, i = 0; j < n; j++) {
            sum = sum + x[j];

            while (i < n && sum >= target) {
                minLength = Math.min(minLength, j - i + 1);
                sum = sum - x[i];
                i++;
            }
        }
        return minLength == n+1 ? 0 : minLength;
    }

    public static int sortingApproach(int[] x, int target) {
        int sum = 0, n = x.length;
        Arrays.sort(x); // T.C = O(nlogn)

        //2, 3, 1 7, 4, 2 ---> 1, 2, 2, 3, 4, 6
        for(int i=n-1; i>=0; i--) {
            sum = sum+ x[i];
            if(sum >= target) {
                return n-i;
            }
        }
        return 0;
    }
}
