package practice.binarySearch;

import java.util.Arrays;

/**
 * There are two sorted arrays A[] and B[] of size n each. Write a program to find the median of the larger sorted array
 * (array of size 2n) obtained after merging both sorted arrays.
 *
 * When n is odd, the median is the middle element.
 * When n is even, the median is the average of the two middle elements.
 * After merging both sorted arrays, the size of the larger sorted array will be 2n, an even value.
 * For the convenience, let's assume that n is odd.
 *
 * Input: A[] = [1, 3, 6], B[] = [2, 8, 12], Output: 4.5.
 * Explanation: After merging the sorted arrays, we obtain the larger sorted array [1, 2, 3, 6, 8, 12].
 * The total number of elements is 6, so the median is the average of the two middle elements, 3 and 6.
 *
 * Input: A[] = [1, 3, 4, 6, 9], B[] = [2, 5, 7, 8, 10], Output: 5.5.
 * Explanation: After merging the sorted arrays, we obtain a larger sorted array of size 10:
 * [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]. The median is the average of the two middle elements, 5 and 6.
 */
public class MedianOfTwoSortedArray {
    public static void main(String args[]) {
        int[] x1 = {1, 3, 6}, y1 = {2, 8, 12} ;             // o/p = 4.5
        int[] x2 = {1, 3, 4, 6, 9}, y2 ={2, 5, 7, 8, 10};  // o/p = 5.5
        int[] x3 = {1,2,3}, y3= {4,5,6};

        System.out.println(mergeAndLinearSearch(x1, y1));
        System.out.println(mergeAndLinearSearch(x2, y2));
        System.out.println(mergeAndLinearSearch(x3, y3));

        System.out.println(linearSearchWithoutMerging(x1, y1));
        System.out.println(linearSearchWithoutMerging(x2, y2));
        System.out.println(linearSearchWithoutMerging(x3, y3));


        /**
         * Observation
         * 1. Merge two arrays using extra space and create the final array. then do the linear search to find the values
         * at those index and return the value
         *
         * 2.
         */
    }

    public static double mergeAndLinearSearch(int[] x, int[] y) {
        int n = x.length; // assuming both arrays are of same size
        int[] z = new int[2*n]; // Space Complexity = O(n)
        int i=0, j=0, k=0;

        // one array is exhausted, and we do not know which one.
        while(i <n && j<n) {        // T.C = O(n)
            if(x[i] < y[j]) {
                z[k] = x[i];
                i++;
                k++;
            } else {
                if(x[i] > y[j]) {
                  z[k] = y[j];
                  k++;
                  j++;
                }
            }
        }

        // so we need to add remaining elements(either from x or from y to the final array)
        while (i < n) {
            z[k] = x[i];
            i++;
            k++;
        }

        while(j <n) {
            z[k] = y[j];
            j++;
            k++;
        }

        System.out.println(Arrays.toString(z));
        double median = (double) (z[n] + z[n-1])/2;
        return median;
    }

    /**
     * Obs - m elements in one array and n elements in another array
     * [1,2,3] and [4,5,6,7] --- > [1,2,3,4,5,6,7] --> if the elements are odd --> then median is (m+n)/2 th element
     * [1,3,6] and [2,8,12] -----> [1,2,3,4,5,6] ---> if the elements are even ---> then median is (m+n)/2 and {(m+n)/2-1} th element
     *
     * keep the count of elements merged so far. so when we reach the count half of the total --> print the median
     */
    public static double linearSearchWithoutMerging(int[] x, int[] y) {
        int m = x.length;
        int n = y.length;
        int mid1 = 0, mid2 =0;
        int i = 0, j =0;

        for(int count=0; count <= (m+n)/2; count++) {
            mid2 = mid1;
            if(i !=m && j !=n) {
                if(x[i] > y[j]) {
                    mid1 = y[j++];
                } else {
                    mid1 = x[i++];
                }
            } else if(i < m) {
                mid1 = x[i++];
            } else {
                mid1 = y[j++];
            }
        }

        if((m+n)%2 == 1) {
            return (double) mid1;
        } else {
            return (double) (mid1+mid2)/2.0;
        }
    }

}
