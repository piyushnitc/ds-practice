package practice.arrays.twopointer.slowfastpointer;

import java.util.Arrays;

/**
 * Given an array X[] consisting of 0s, 1s, and 2s, write a program to sort the array of 0’s, 1’s, and 2’s
 * in ascending order.
 *
 * Input: X[] = [0, 2, 1, 0, 1, 2, 1, 0], Output: X[] = [0, 0, 0, 1, 1, 1, 2, 2]
 * Input: X[]= [0, 1, 1, 0, 1, 2, 1, 2, 0, 0], Output: X[] = [0, 0, 0, 0, 1, 1, 1, 1, 2, 2]
 * Input: X[] = [2, 1, 0], Output: X[] = [0, 1, 2]
 *
 */
public class SortTriplets {
    public static void main(String args[]) {
        int[] x1 = {0, 2, 1, 0, 1, 2, 1, 0};
        int[] x2 = {0, 1, 1, 0, 1, 2, 1, 2, 0, 0};
        int[] x3 = {2, 1, 0};

        System.out.println(Arrays.toString(bruteForce(x1)));
        System.out.println(Arrays.toString(bruteForce(x2)));
        System.out.println(Arrays.toString(bruteForce(x3)));

        System.out.println(Arrays.toString(doubleSorting(x1)));
        System.out.println(Arrays.toString(doubleSorting(x2)));
        System.out.println(Arrays.toString(doubleSorting(x3)));

        System.out.println(Arrays.toString(threeWayPartionning(x1)));
        System.out.println(Arrays.toString(threeWayPartionning(x2)));
        System.out.println(Arrays.toString(threeWayPartionning(x3)));
    }

    // count 0s, 1s and 2s
    // fill the array with 0s, 1s and 2s
    // this is an example of double traversal
    public static int[] bruteForce(int[] x) {
        int n = x.length;
        int zerosCount=0, onesCount=0;

        for(int i=0; i<n; i++) {
            if(x[i] == 0) {
                zerosCount++;
            } else if(x[i] == 1) {
                onesCount++;
            }
        }

        for(int i=0; i<n; i++) {
            if(zerosCount > 0) {
                x[i] = 0;
                zerosCount--;
            } else if(zerosCount <=0 && onesCount >0) {
                x[i] = 1;
                onesCount--;
            } else {
                x[i] = 2;
            }
        }
        return x; // Total T.C = O(n)+O(n) = O(n)
    }

    // Obs - in 0s and 1s we wer swapping 1s with 0s
    // here we need to swap 0s, 1s and 2s so that 0s in the beginning, 2s at the end and everything else in between
    // first push 0s in the beginning and 1, 2 will be unsorted
    // in second scan - swap 1s and 2s
    public static int[] doubleSorting(int[] x) {
        int n = x.length;
        int j = 0;
        // we pushed 0s in the beginning.
        for (int i = 0; i < n; i++) {
            if (x[i] == 0) {
                swap(x, i, j);
                j++;
            }
        }

        // we will sort 1s and 2s here now
        // array will look like [0, 0, 0, 0, 1, 2, 1, 2, 1, 1]. so we need to consider the sub-array [1,2,1,2,1,1]
        // the starting index of this sub-array = j
        int k = j;
        for(int i=k; i<n; i++) {
            if(x[i] != 2) {
                swap(x,  i, k);
                k++;
            }
        }
        return x; // Total T.C = O(n) + O(n) = 2O(n) = O(n)
    }

    // Obs - low = 0, mid =0 and high = n-1;
    // all 0s will go to low
    // all 2s will go to high
    // all 1s will remain in the middle
    public static int[] threeWayPartionning(int[] x) {
        int n = x.length;
        int low=0, mid=0, high = n-1;

        while(mid <= high) {
            if(x[mid] == 0) {
                swap(x, mid, low);
                low++;
                mid++;
            } else if(x[mid] == 1) {
                mid++;
            } else if(x[mid] == 2) {
                swap(x, mid, high);
                high--;
            }
        }
        return x;
    }

    private static void swap(int[] x, int a, int b) {
        int temp = x[a];
        x[a] = x[b];
        x[b] = temp;
    }
}
