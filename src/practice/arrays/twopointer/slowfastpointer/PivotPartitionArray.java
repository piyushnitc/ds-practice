package practice.arrays.twopointer.slowfastpointer;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * You are given a 0-indexed integer array nums and an integer pivot. Rearrange nums such that the
 * following conditions are satisfied:
 * Every element less than pivot appears before every element greater than pivot.
 * Every element equal to pivot appears in between the elements less than and greater than pivot.
 * The relative order of the elements less than pivot and the elements greater than pivot is maintained.
 *
 * Input: nums = [9,12,5,10,14,3,10], pivot = 10
 * Output: [9,5,3,10,10,12,14]
 *
 * The elements 9, 5, and 3 are less than the pivot so they are on the left side of the array.
 * The elements 12 and 14 are greater than the pivot so they are on the right side of the array.
 * The relative ordering of the elements less than and greater than pivot is also maintained.
 * [9, 5, 3] and [12, 14] are the respective orderings.
 *
 * Input: nums = [-3,4,3,2], pivot = 2
 * Output: [-3,2,4,3]
 *
 * The element -3 is less than the pivot so it is on the left side of the array.
 * The elements 4 and 3 are greater than the pivot so they are on the right side of the array.
 * The relative ordering of the elements less than and greater than pivot is also maintained.
 * [-3] and [4, 3] are the respective orderings.
 */
public class PivotPartitionArray {
    public static void main(String args[]) {
        int[] x1 = {9,12,5,10,14,3,10}; //pivot = 10
        int[] x2 = {-3,4,3,2}; //pivot = 2

        System.out.println(Arrays.toString(bruteForce(x1,10)));
        System.out.println(Arrays.toString(bruteForce(x2,2)));

        System.out.println(Arrays.toString(dynamicListPivot(x1,10)));
        System.out.println(Arrays.toString(dynamicListPivot(x2,2)));

        System.out.println(Arrays.toString(twoPointers(x1,10)));
        System.out.println(Arrays.toString(twoPointers(x2,2)));

        System.out.println(Arrays.toString(twpPointersApproach2(x1,10)));
        System.out.println(Arrays.toString(twpPointersApproach2(x2,2)));
    }

    //obs - count number less than pivot and greater than pivot
    // calculate duplicate pivots as well
    // lessThanPivot = 3
    // greaterThanPivot = 2
    // duplicatePivot = 2
    // less than pivot index would start from 0 to lessThanPivot-1 [0,2]
    // greater than pivot index would start from (lessThanPivot+duplicatePivot) to n-1 [5,6]
    // duplicate pivot index would start from lessThanPivot to lessThanPivot+duplicatePivot-1 [3,4]
    // Please note that this approach does two passes.
    public static int[] bruteForce(int[] x, int pivot) {
        int n = x.length;
        int greaterThanPivot = 0;
        int lessThanPivot = 0;
        int[] output = new int[n];

        //T.C = O(n)
        for(int i=0; i<n; i++) {
            if(x[i] > pivot) {
                greaterThanPivot++;
            } else if(x[i] < pivot) {
                lessThanPivot++;
            }
        }
        int numberOfPivots = n - greaterThanPivot - lessThanPivot;

        int lessThanPivotIndex = 0;
        int pivotIndex = lessThanPivot;
        int greaterThanPivotIndex = lessThanPivot+numberOfPivots;

        //T.C = O(n)
        for(int i=0; i<n; i++) {
            if(x[i] < pivot) {
                output[lessThanPivotIndex] = x[i];
                lessThanPivotIndex++;
            } else if(x[i] > pivot) {
                output[greaterThanPivotIndex] = x[i];
                greaterThanPivotIndex++;
            } else if(x[i] == pivot) {
                output[pivotIndex] = x[i];
                pivotIndex++;
            }
        }
        return output; // Total T.C = 2O(n) ; S.C = O(n)
        // Is there a better approach think?
    }

    // obs - use dynamic list and add elements less than pivot to less list
    // greater than pivot to greater and equal to equal list.
    public static int[] dynamicListPivot(int[] x, int pivot) {
        int n = x.length;
        int[] output = new int[n];

        // using dynamic list
        LinkedList<Integer> less = new LinkedList<>();
        LinkedList<Integer> equal = new LinkedList<>();
        LinkedList<Integer> greater = new LinkedList<>();

        for(int i=0; i<n; i++) {
            if(x[i] < pivot) {
                less.add(x[i]);
            } else if(x[i] == pivot) {
                equal.add(x[i]);
            } else {
                greater.add(x[i]);
            }
        }
        less.addAll(equal);
        less.addAll(greater);

        int i=0;
        for(int nums: less) {
            output[i++] = nums;
        }
        return output;
    }

    // obs - order of elements need to be maintained.
    // so we scan from left to right and right to left using two pointers
    // left = 0 right = n-1
    // if left element is < pivot --> it goes to the left side and increment left index
    // if right element is > pivot --> it goes to the right side and decrement right index
    // if left is > pivot --> skip  ----> let this be taken care by right pointer.
    // if right is < pivot --> skip  ----> let this be taken care by left pointer.
    public static int[] twoPointers(int[] x, int pivot) {
        int n = x.length;
        int[] output = new int[n];
        int leftIndex = 0;
        int rightIndex = n-1;
        int i = 0;
        int j = n-1;

        while(leftIndex < n && rightIndex > 0) {
            if(x[leftIndex] < pivot) {
                output[i] = x[leftIndex];
                i++;
            }
            if(x[rightIndex] > pivot) {
                output[j] = x[rightIndex];
                j--;
            }
            leftIndex++;
            rightIndex--;
        }

        // once the elements are at proper position - we fill the remaining spots with pivot elements.
        while (i <= j) {
            output[i] = pivot;
            i++;
        }

        return output;
    }

    // this is a modified version of the above approach where we use for loop with two variables
    public static int[] twpPointersApproach2(int[] x, int pivot) {
        int n = x.length;
        int[] output = new int[n];

        int lessIndex = 0;
        int greaterIndex = n-1;

        for(int i=0, j=n-1; i<n; i++, j--) {
            if(x[i] < pivot) {
                output[lessIndex] = x[i];
                lessIndex++;
            }
            if(x[j] > pivot) {
                output[greaterIndex] = x[i];
                greaterIndex--;
            }
        }
        while (lessIndex <=greaterIndex) {
            output[lessIndex] = pivot;
        }
        return output;
    }

}
