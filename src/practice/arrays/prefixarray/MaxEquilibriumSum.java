package practice.arrays.prefixarray;

/**
 * Given an array arr[]. Find the maximum value of prefix sum which is also suffix
 * sum for index i in arr[].
 *
 * Input : arr[] = {-1, 2, 3, 0, 3, 2, -1}
 * Output : 4
 * Explanation : Prefix sum of arr[0..3] = Suffix sum of arr[3..6]
 *
 * Input : arr[] = {-2, 5, 3, 1, 2, 6, -4, 2}
 * Output : 7
 * Explanation : Prefix sum of arr[0..3] = Suffix sum of arr[3..7]
 */
public class MaxEquilibriumSum {

    public static void main(String[] args) {
        int[] x1 = {-1, 2, 3, 0, 3, 2, -1};
        int[] x2 = {-2, 5, 3, 1, 2, 6, -4, 2};

        //Observation 1 - at every index calculate left sum (including i) and right sum (including i) and compare
        //Observation 2 - at every index populate the prefix and suffix sum and compare the two arrays
        //

    }



}
