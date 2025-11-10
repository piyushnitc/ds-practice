package practice.arrays.singleloop;

import java.util.Arrays;

/**
 * Given an unsorted array of n integers, write a program to sort the array into a wave array.
 * An array A[n] is sorted in wave arrangement if
 *  A[0] >= A[1] <= A[2] >= A[3] <= A[4] >= ….
 * Note: There can be multiple possible answers, but we need to return any one of the possible waveforms
 *
 * Input: A[] = [1, 2, 3, 4]
 * Output: A[] = [2, 1, 4, 3] or [4, 1, 3, 2] or any other wave form like structure.
 *
 * Input: A[] = [20, 10, 8, 6, 4, 2]
 * Output: A[] = [20, 8, 10, 4, 6, 2] or [10, 8, 20, 2, 6, 4] or any other wave form like structure.
 */
public class SortArrayInWaveform {
    public static void main(String args[]) {
        int[] x1 = {1, 2, 3, 4};
        int[] x2 = {20, 10, 8, 6, 4, 2};
        int[] x3 = {20, 10, 8, 6, 4,};
        sortArray(x1);
        System.out.println();
        sortArray(x2);
        System.out.println();
        sortArray(x3);
    }

    // approach 1- sort the array and fill the elements from first and last OR swap the adjacent element
    public static void sortArray(int[] x) {
        Arrays.sort(x); // T.C = o(nlogn)

        for(int i=0; i<x.length-1; i+=2) {
            int temp = x[i];
            x[i] = x[i+1];
            x[i+1] = temp;
        }
        Arrays.stream(x).forEach(e-> System.out.print(e+","));

        //T.C = O(nlogn)+ O(n)
    }

    public static void sortArrayWithoutSorting(int[] x) {

        for(int i=0; i<x.length-1; i+=2) {
            int temp = x[i];
            x[i] = x[i+1];
            x[i+1] = temp;
        }
        Arrays.stream(x).forEach(e-> System.out.print(e+","));
    }
}
