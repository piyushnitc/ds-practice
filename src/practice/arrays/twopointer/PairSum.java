package practice.arrays.twopointer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of n integers and a value targetSum, write a program to check whether there
 * is a pair of elements in the array whose sum is equal to targetSum.
 * If yes, return true; otherwise, return false.
 *
 * Assume all elements are distinct.
 * Values in the array can be both negative and positive.
 *
 * Input: X[] = [-5, 1, -40, 20, 6, 8, 7], targetSum = 15, Output: true
 * Explanation: (7, 8) or (-5, 20) are the pairs with the sum of 15.
 *
 * Input: X[] = [-5, 4, -2, 16, 8, 9], targetSum = 15, Output: false
 * Explanation: There is no pair of elements whose sum is equal to 15.
 */
public class PairSum {
    public static void main(String args[]) {
        int[] x1 = {-5, 1, -40, 20, 6, 8, 7};
        int[] x2 = {-5, 4, -2, 16, 8, 9};

        System.out.println(bruteForce(x1,15));
        System.out.println(bruteForce(x2, 15));

        System.out.println(sortingAndBinarySearch(x1,15));
        System.out.println(sortingAndBinarySearch(x2, 15));

        System.out.println(sortingAndTwoPointers(x1,15));
        System.out.println(sortingAndTwoPointers(x2, 15));

        System.out.println(hashTable(x1,15));
        System.out.println(hashTable(x2, 15));

    }

    // Obs - compare each and every pair to check if the sum = target sum
    // T.C = O(n) * O(n) = O(n^2)
    public static boolean bruteForce(int[] x, int targetSum) {
        for(int i=0; i<x.length; i++) {
            for(int j=i; j<x.length; j++) {
                if(x[i]+x[j] == targetSum) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean sortingAndBinarySearch(int[] x, int targetSum) {
        Arrays.sort(x); // T.C = O(nlogn)

        // T.C = O(n) * O(logn) = O(nlogn)
        for(int i=0; i<x.length; i++) {
            int left=i, right=x.length-1;
            while(left <= right) {
                int mid = left + (right-left)/2;
                if(x[mid] < targetSum -x[i]){
                    left = mid+1;
                } else if(x[mid] > targetSum -x[i]){
                    right = right-1;
                } else {
                    return true;
                }
            }
        }
        return false; // Total T.C = O(nlogn) + O(nlogn) = O(nlogn)
    }

    public static boolean sortingAndTwoPointers(int[] x, int targetSum) {
        Arrays.sort(x); // T.C = O(nlogn)
        int n = x.length;
        int rightIndex = n-1;
        int leftIndex = 0;

        while(leftIndex <=rightIndex) { // T.C = O(n)
            if(x[leftIndex] + x[rightIndex] < targetSum) {
                leftIndex++;
            } else if(x[leftIndex] + x[rightIndex] > targetSum) {
                rightIndex --;
            } else {
                return true;
            }
        }
        return false; // Total T.C = O(n)+O(nlogn)
    }

    //better approach. less T.C but increased Space complexity = o(n)
    public static boolean hashTable(int[] x, int targetSum) {
        Map<Integer,Integer> numMap = new HashMap<>(); // Space Complexity = O(n)
        //T.C = O(n)
        for(int i=0; i<x.length; i++) {
            if(numMap.containsKey(targetSum-x[i])) {
                return true;
            } else {
                numMap.put(x[i], i);
            }
        }
        return false;
    }
}
