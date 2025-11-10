package practice.binarySearch;

import java.util.Arrays;

/**
 * Given an array of integers sorted in ascending order, write a program to find the first and last
 * position of a given target value.
 *
 * If the target is not found in the array, return [-1, -1].
 * We must design an algorithm with O(log n) time complexity.
 *
 * Input: A[] = [-1, 1, 2, 2, 2, 5, 6, 12, 12], target = 2
 * Output: First occurrence = 2, Last occurrence = 4.
 *
 * Input: A[] = [21, 32, 51, 70, 71], target = 70
 * Output: First occurrence = 3, Last occurrence = 3.
 */
public class ElementPositionInArray {

    /**
     * Obs - we can do a linear scan from right to left. first occurrence of the element
     * save the position and keep on searching for last occurrence by increasing the occurrence index
     *
     * 2. use two pointers one from start (for first occurrence) and second pointer from end
     * (for last occurrence)
     *
     * 3. binary search ?? how can we search for an element using binary search where duplicates elements
     * are allowed -- Think??
     */

    public static void main(String args[]) {
        int[] x1 = {-1, 1, 2, 2, 2, 5, 6, 12, 12}; // target = 2    o/p = [2,4]
        int[] x2 = {21, 32, 51, 70, 71}; // target = 70             o/p = [3,3]
        int[] x3 = {21, 32, 51, 70, 71}; // target = 50             o/p = [-1,-1]

        int mid = 9/2;
        System.out.println(mid);
        System.out.println(Arrays.toString(linearSearch(x1, 2)));
        System.out.println(Arrays.toString(linearSearch(x2, 70)));
        System.out.println(Arrays.toString(linearSearch(x3, 50)));

        System.out.println(Arrays.toString(twoPointer(x1, 2)));
        System.out.println(Arrays.toString(twoPointer(x2, 70)));
        System.out.println(Arrays.toString(twoPointer(x3, 50)));

        System.out.println(Arrays.toString(binarySearch(x1, 2)));
        System.out.println(Arrays.toString(binarySearch(x2, 70)));
        System.out.println(Arrays.toString(binarySearch(x3, 50)));
    }

    public static int[] linearSearch(int[] x, int target) {
        int firstPosition = -1;
        int lastPosition = -1;

        for(int i=0; i<x.length; i++) {
            if(x[i] == target && firstPosition == -1) {
                firstPosition = i;
                continue;
            }

            if(x[i] == target) {
                lastPosition = i;
            }
        }

        if(firstPosition != -1 && lastPosition == -1) {
            lastPosition = firstPosition;
        }

        int[] position = new int[2];
        position[0] = firstPosition;
        position[1] = lastPosition;

        return position;
    }

    public static int[] twoPointer(int[] x, int target) {
        int firstPosition = -1;
        int lastPosition = -1;

        for(int i=0; i<x.length; i++) {
            if(x[i] == target && firstPosition == -1) {
                firstPosition = i;
            }
            if(x[x.length-1-i] == target && lastPosition == -1) {
                lastPosition = x.length-1-i;
            }
        }
        if(firstPosition != -1 && lastPosition == -1) {
            lastPosition = firstPosition;
        }
        int[] position = new int[2];
        position[0] = firstPosition;
        position[1] = lastPosition;

        return position;
    }

    /**
     * T.C for both the approaches were O(n). Think how binary search can be applied here
     * The array is sorted and contains duplicates. So we implement binary search to find the first occurrence of the target
     * again we implement binary search to find the last occurrence of the target.
     *
     * first occurrence of the target
     * low = 0, high = n-1, mid = low+(high-low)/2
     * if x[mid] == target && x[mid-1] < target -----> this would be the first occurrence
     * if mid == 0 and x[mid] == target ----> this would be the first occurrence
     *
     * if x[mid] < target ----> we search for the first occurrence in the right part
     * if x[mid] > target ----> we search for the first occurrence in the left part
     *
     */

    public static int[] binarySearch(int[] x, int target) {
        int[] output = new int[2];
        output[0] = firstOccurrence(x, target);
        output[1] = lastOccurrence(x, target);

        return output;
    }


    private static int firstOccurrence(int[] x, int target) {
        int low =0, high = x.length-1;

        while(low <= high) {

            int mid = low + (high-low)/2;
            if((mid == 0 || x[mid-1] < target) && x[mid] == target) {
                return mid;
            } else if (x[mid] < target) {
                low = mid +1;
            } else {
                high = mid -1;
            }
        }
        return -1;
    }

    private static int lastOccurrence(int[] x, int target) {
        int low =0, high = x.length-1;

        while(low <= high) {

            int mid = low + (high-low)/2;
            if((mid == x.length-1 || x[mid+1] > target) && x[mid] == target) {
                return mid;
            } else if (x[mid] > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

}


