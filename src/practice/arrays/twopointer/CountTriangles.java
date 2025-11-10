package practice.arrays.twopointer;

import java.util.Arrays;

/**
 * Write a program to count the number of possible triangles that can be formed with three elements from a
 * given unsorted array of positive integers. The three elements, representing the lengths of the sides
 * of a triangle, must satisfy the triangle inequality: the sum of any two sides must be greater than
 * the third side. In other words, x + y > z, y + z > x, and z + x > y must all be true for the
 * triplet (x, y, z) to form a valid triangle.
 *
 * Input: X[]= [4, 6, 3, 7], Output = 3
 * Explanation: There are three triangles possible: {3, 4, 6}, {4, 6, 7} and {3, 6, 7}.
 *
 * Input: X[] = [10, 21, 22, 100, 101, 200, 300], Output = 6
 * Explanation: There can be 6 possible triangles: {10, 21, 22}, {21, 100, 101}, {22, 100, 101},
 * {10, 100, 101}, {100, 101, 200} and {101, 200, 300}.
 */
public class CountTriangles {

    public static void main(String args[]) {
        int[] x1 = {4, 6, 3, 7}; //op = 3
        int[] x2 = {10, 21, 22, 100, 101, 200, 300}; //op=6
        int[] x3 = {11,4,9,6,15,18};

        System.out.println(bruteForce(x1));
        System.out.println(bruteForce(x2));

        System.out.println(sortingBinarySearch(x1));
        System.out.println(sortingBinarySearch(x2));

        System.out.println(sortingNestedLoops(x1));
        System.out.println(sortingNestedLoops(x2));

        System.out.println(sortingTwoPointers(x1));
        System.out.println(sortingTwoPointers(x2));
        System.out.println(sortingTwoPointers(x3));

    }

    // obs - consider all the triplets of the array
    //T.C = o(n^3)
    public static int bruteForce(int[] x) {
        int n = x.length;
        int validTriangles = 0;
        for(int i=0; i<n; i++) {
            for(int j= i+1; j<n; j++) {
                for(int k= j+1; k<n; k++) {
                    validTriangles = validTriangles+isValidTriangle(x[i], x[j], x[k]);
                }
            }
        }
        return validTriangles;
    }

    private static int isValidTriangle(int x1, int x2, int x3) {
        if(x1+x2 > x3 && x1+x3 > x2 && x2+x3 > x1) {
            return 1;
        } else {
            return 0;
        }
    }

    // Obs - sort the array. {1,3,4,6,7}
    // we need to do binary search on (4,6,7) to find element which is less than 1+3
    public static int sortingBinarySearch(int[] x) {
        int n = x.length;
        int validTriangles = 0;
        Arrays.sort(x); //T.C = O(nlogn)

        //T.C = O(n^2)
        for(int i=0; i<n; i++) {
            for(int j=i+1; j<n;  j++) {
                // do binary search here where x[i]+x[j] > x[k] where k belongs to j+1 to n
                int left = j+1;
                int right = n-1;
                while(left <= right) { // T.C = O(logn)
                    int mid = left + (right - left) / 2;
                    if(x[i]+x[j] > x[mid]) {
                        left++;
                        validTriangles++;
                    } else {
                        right --;
                    }
                }
            }
        }
        return validTriangles; // Total T.C = O(nlogn) + O(n^2logn)
    }

    // choose the first side of the triangle from i=0 to i=n-3
    // choose the second side of the triangle from j = i+1 to j = n-2
    // for third side - inside the second loop - run a while to find the third. remember we are starting k from i+2
    // but we need to make sure that its always greater than j by 1
    public static int sortingNestedLoops(int[] x) {
        int n = x.length;
        int validTriangles = 0;
        Arrays.sort(x); // T.C = O(nlogn)

        //T.C = O(n^2)
        for(int i=0; i<n-2; i++) {
            int k = i+2;
            for(int j=i+1; j<n-1; j++) {
                k = j+1;
                while(k<n && x[i]+x[j] > x[k]) {
                    k++;
                    validTriangles++;
                }
            }
        }
        return validTriangles; // Total T.C = O(nlogn)+O(n^2)
    }

    // obs - scan from right to left. consider the right element to be third side of the triangle
    // we need to find two more sides of the triangle from left = 0 to right = n-2 using two pointers
    // where x[left]+x[right] > x[third]
    public static int sortingTwoPointers(int[] x) {
        int n = x.length;
        int validTriangles = 0;
        Arrays.sort(x); // T.C = o(nlogn)

        //T.C = O(n)
        for(int i=n-1; i>=0; i--) {
            int left = 0;
            int right = i-1;
            while(right > left) {               // T.C = O(n)
                if(x[right] + x[left] > x[i]) {
                    validTriangles++;
                    System.out.println(x[right] + "," + x[left] + "," + x[i]);
                }
                left++;
            }
        }
        return validTriangles; // Total T.C = O(nlogn) + O(n) * O(n) = O(nlogn) + O(n^2)
    }
}
