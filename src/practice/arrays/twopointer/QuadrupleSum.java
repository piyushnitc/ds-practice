package practice.arrays.twopointer;

import java.util.HashMap;

/**
 * Given an array nums of n integers, return an array of all the unique quadruplets
 * [nums[a], nums[b], nums[c], nums[d]] such that:
 * 0 <= a, b, c, d < n
 * a, b, c, and d are distinct.
 * nums[a] + nums[b] + nums[c] + nums[d] == target
 *
 * Input: nums = [1,0,-1,0,-2,2], target = 0
 * Output: [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
 *
 * Input: nums = [2,2,2,2,2], target = 8
 * Output: [[2,2,2,2]]
 */
public class QuadrupleSum {
    public static void main(String args[]) {
        int[] x1 = {1,0,-1,0,-2,2};
        int[] x2 = {2,2,2,2,2};

        bruteForce(x1, 0);
        bruteForce(x2, 8);
        System.out.println();
        bruteForceMap(x1,0);
        bruteForceMap(x2,8);
    }

    // Consider all four pairs - O(n^4)
    public static void bruteForce(int[] x, int targetSum) {
        int n = x.length;
        for(int i=0; i<n; i++) {
            for(int j=i+1; j<n; j++) {
                for(int k= j+1; k<n; k++) {
                    for(int l=k+1; l<n; l++) {
                        if(x[i]+x[j]+x[k]+x[l] == targetSum) {
                            System.out.println(x[i]+","+x[j]+","+x[k]+","+x[l]);
                        }
                    }
                }
            }
        }
    }

    // Use a hash map to check if targetSum-x[i]-x[j]-x[k] exists in hashmap
    // if it does exist - return true. if not then insert in map.
    //T.C = O(n^3) and S.C = O(n)
    public static void bruteForceMap(int[] x, int targetSum) {
        int n = x.length;

        for(int i=0; i<n; i++) {
            for(int j=i+1; j<n; j++) {
                HashMap<Integer, Integer> numMap = new HashMap<>();
                for(int k=j+1; k<n; k++) {
                    int remainingSum = targetSum-x[i]-x[j]-x[k];
                    if(numMap.containsKey(remainingSum)) {
                        System.out.println(x[i]+","+x[j]+","+x[k]+","+remainingSum);
                    } else {
                        numMap.put(x[k],k);
                    }
                }
            }
        }
    }

    public static void sortingTwoPointers(int[] x, int targetSum) {

    }

}
