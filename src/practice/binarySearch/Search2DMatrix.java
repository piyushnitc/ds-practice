package practice.binarySearch;

/**
 * You are given a row-wise and column-wise sorted 2D matrix and an integer k, write a program to find whether the
 * integer k is present in the matrix or not. The matrix has the following properties:
 *
 * Integers in each row are sorted from left to right.
 * The first integer of each row is greater than the last integer of the previous row.
 * Suppose all matrix values are unique.
 *
 * Input: k = 6
 * mat[][] =
 * [
 *     [1,   2,  6,  7],
 *     [12, 13, 16, 21],
 *     [23, 35, 36, 48]
 * ]
 *
 * Output: true
 * Explanation: Value 6 is present in the matrix.
 *
 *
 * Input: k = 7
 * mat[][] =
 * [
 *     [1,   2,  6],
 *     [12, 13, 16],
 *     [23, 35, 36]
 * ]
 *
 * Output: fasle
 * Explanation: value 7 is not present in the matrix.
 */

public class Search2DMatrix {
    public static void main(String args[]) {
        int[][] x1 = {{1,2,6,7} , {12,13,16,21}, {23,35,36,48}}; // target = 6; o/p = true
        int[][] x2 = {{1,2,6},{12,13,16},{23,35,36}}; // target = 7; o/p = false

        /**
         * observation - linear serach using two nested loops
         */
        System.out.println(linearSearch(x1, 6));
        System.out.println(linearSearch(x2, 7));

        System.out.println(binarySearch(x1, 3, 4, 6));
        System.out.println(binarySearch(x2, 3,3,7));

        System.out.println(binarySearch(x1, 3, 4, 7));
        System.out.println(binarySearch(x1, 3, 4, 13));
        System.out.println(binarySearch(x1, 3, 4, 35));
        System.out.println(binarySearch(x1, 3, 4, 51));

    }

    public static boolean linearSearch(int[][] x, int target) {
        int row = x.length;
        for(int i=0; i<row; i++) {
            for(int j=0; j< x[i].length; j++) {
                if(x[i][j] == target) {
                    return true;
                }
            }
        }
        return false; // T.C = O(n*m)
    }

    /**
     * Think how this can be done using binary search
     * the matrix is sorted row wise.
     *  - find the row of the given matrix (say m * n)
     *  - mid is row = 1
     *  - if x[mid][0] == target --> return true;
     *  - if x[mid][0] > target  --> left = mid-1;
     *  - if x[mid][0] < target
     *          - if x[mid][n] == target --> return true
     *          - if x[mid][n] < target  --> left = mid - 1;
     *          - if x[mid][n] > target  --> search row.
     */

    public static boolean binarySearch(int[][] x, int m, int n, int target) {
        int left = 0, right = m-1;

        while(left <= right) {
            int mid = left + (right-left)/2;

            // middle row 0th element is equal to target. return true
            if(x[mid][0] == target) {
                return true;

                // middle row 0th element is greater than target -- hence look into row from 0 to mid-1
            } else if (x[mid][0] > target) {
                right = mid-1;

                // middle row 0th element is lesser than target. then check the middle row last element
            } else if (x[mid][0] < target) {

                // if middle row last element is equal to target
                if(x[mid][n-1] == target) {
                    return true;

                    // if middle row last element is lesser than target
                } else if(x[mid][n-1] < target) {
                    left = mid+1;

                    // if middle row last element is greater than target
                } else if (x[mid][n-1] > target) {
                    return searchRow(x, mid, n,target);
                }
            }
        }
        return false;
    }

    private static boolean searchRow(int[][] x, int row, int col, int target) {
        int[] rowArray = x[row];
        int left = 0, right = col-1;

        while(left <= right) {
            int mid = left + (right-left)/2;

            if(rowArray[mid] == target) {
                return true;
            } else if(rowArray[mid] > target) {
                right = mid -1;
            } else {
                left = mid +1 ;
            }
        }
        return false;
    }


}
