package practice.binarySearch;

/**
 * You are given an array of integers that is initially increasing and then decreasing, find the maximum
 * value in the array. Hint: keep in mind the corner cases.
 *
 * Input: X[] = [18, 110, 210, 452, 810, 500, 101, 13], Output: 810
 *
 * Input: X[] = [1, 2, 3, 4, 5], Output: 5
 * Explanation: Array is sorted in increasing order. So maximum value in the array = 5
 */
public class MaxInIncreasingDecreasingArray {

    public static void main(String args[]) {
        int[] x1 = {18, 110, 210, 452, 810, 500, 101, 13}; //op = 810
        int[] x2 = {1, 2, 3, 4, 5}; //op = 5

        System.out.println(maxBruteForce(x1));
        System.out.println(maxBruteForce(x2));

        System.out.println(binarySearch(x1));
        System.out.println(binarySearch(x2));

        System.out.println(binarySearchRecursive(x1));
        System.out.println(binarySearchRecursive(x2));
    }

    // obs - scan the array from left to right and see at what point the value at next index is
    // smaller than the previous index.
    // if not --> array is sorted and return the last element
    // if found --> return the value at that index

    public static int maxBruteForce(int[] x) {
        int index = -1;
        for(int i=1; i<x.length; i++) {
            if(x[i] < x[i-1]) {
                index = i-1;
                break;
            }
        }
        if(index == -1) {
            index = x.length-1;
        }
        return x[index];  // Total T.C = O(n)
    }

    /**
     * How can we improve the T.C? How can we apply binary search to find the max element? we need sorted array
     * Obs - the array is sorted in a particular order first increasing and then decreasing
     *
     * if x[mid] > x[mid+1] and x[mid] > x[mid-1] ---> x[mid] is the highest value
     *
     * if x[mid] < x[mid+1] && x[mid] > x[mid-1]  -->  we are on the increasing side of the array. so the max should lie
     * on right sub array
     *
     * if x[mid] > x[mid1+1] & x[mid] < x[mid-1]  --> we are in the decreasing side of the array. so the max should lie
     * on left sub array.
     */

    /**
     * This is an iterative approach of binary search. How can we do the recursive approach
     * @param x
     * @return
     */
    public static int binarySearch(int[] x) {
        int left = 0, right = x.length-1;
        int max = 0;

        while(left <= right) {
            int mid = left + (right - left)/2;

            if(mid == right) {
                max = x[mid];
                break;
            }
            if(x[mid] > x[mid+1] && x[mid] > x[mid-1]) {
                max = x[mid];
                break;
            }

            if(x[mid] < x[mid+1] && x[mid] > x[mid-1]) {
                left = mid+1;
            }

            if(x[mid] > x[mid+1] && x[mid] < x[mid-1]) {
                right = mid-1;
            }
        }
        return max; // Total T.C = O(n)
    }


    //TODO: Please take note of this binary search recursive function.
    public static int binarySearchRecursive(int[] x) {
        int n = x.length;
        int left = 0, right = n-1;

        return recursive(x, left, right);

    }

    private static int recursive(int[] x, int left, int right) {

        if(left == right) {
            return x[left];
        }

        if((right == left+1) && x[right] > x[left]) {
            return x[right];
        }

        if((right == left+1) && x[right] < x[left]) {
            return x[left];
        }

        int mid = left+(right - left)/2;
        if(x[mid] > x[mid+1] && x[mid] > x[mid-1]) {
            return x[mid];
        }

        if(x[mid] < x[mid+1] && x[mid] > x[mid-1]) {
            left = mid+1;
            return recursive(x, left, right);
        }

        if(x[mid] > x[mid+1] && x[mid] < x[mid-1]) {
            right = mid-1;
            return recursive(x, left, right);
        }

        return 0;
    }

}

