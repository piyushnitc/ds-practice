package practice.arrays.twopointer.slowfastpointer;

import java.util.Arrays;

/**
 * Given an array X[] of n integers, where some elements are zero and some elements are non-zero.
 * Write a program to move all the zeroes to the end of the array.
 *
 * Input: X[] = [4, 8, 0, 0, 2, 0, 1, 0],
 * Output: X[] = [4, 8, 2, 1, 0, 0, 0, 0]
 *
 * Input: X[] = [1, 2, 3, 4, 0, 0, 0],
 * Output: [1, 2, 3, 4, 0, 0, 0]
 *
 * Input: X[] = [0, 0, 1, 2, 0, 3, 4],
 * Output: [1, 2, 3, 4, 0, 0, 0]
 */
public class MoveZeros {
    public static void main(String args[]) {
        int[] x1 = {4, 8, 0, 0, 2, 0, 1, 0};
        int[] x2 = {1, 2, 3, 4, 0, 0, 0};
        int[] x3 = {0, 0, 1, 2, 0, 3, 4};

        System.out.println(Arrays.toString(bruteForce(x1)));
        System.out.println(Arrays.toString(bruteForce(x2)));
        System.out.println(Arrays.toString(bruteForce(x3)));

        System.out.println(Arrays.toString(bruteForceNoExtraSpace(x1)));
        System.out.println(Arrays.toString(bruteForceNoExtraSpace(x2)));
        System.out.println(Arrays.toString(bruteForceNoExtraSpace(x3)));

        System.out.println(Arrays.toString(twoPointers(x1)));
        System.out.println(Arrays.toString(twoPointers(x2)));
        System.out.println(Arrays.toString(twoPointers(x3)));

    }

    //Obs - scan array from left to right and whenever we encounter zero - we add from the end
    // we take one pointer to add numbers and another pointer to add 0s. we add zeros from the end
    // So we initiate counterEnd = n-1 and counterStart = 0
    // T.C = O(n) S.C = o(n)
    public static int[] bruteForce(int[] x) {
        int n = x.length;
        int counterFromStart = 0;
        int counterFromEnd = n-1;
        int[] output = new int[n];

        for(int i=0; i<n; i++) {
            if(x[i] != 0) {
                output[counterFromStart] = x[i];
                counterFromStart++;
            } else {
                output[counterFromEnd] = 0;
                counterFromEnd--;
            }
        }
        return output;
    }
    //Obs - whenever we get non zero - we push to x[j] and then fill remaining x[j] with 0s
    public static int[] bruteForceNoExtraSpace(int[] x) {
        int n = x.length;
        int j = 0;
        for(int i=0; i<n; i++) {
            if(x[i] !=0) {
                x[j] = x[i];
                j++;
            }
        }
        while(j<n) {
            x[j] = 0;
            j++;
        }
        return x; // Total T.C = O(n) + O(n) = O(n)
    }

    // obs - whenever we get a non-zero - we swap i and j values and increment j.
    // at the end all 0s will be pushed to the end.
    public static int[] twoPointers(int[] x) {
        int n = x.length;
        int j= 0;
        for(int i=0; i<n; i++) {
            if(x[i] !=0) {
                int temp = x[j];
                x[j] = x[i];
                x[i] = temp;
                j++;
            }
        }
        return x;
    }
}
