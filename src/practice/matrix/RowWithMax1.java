package practice.matrix;

/**
 * Given a 2D boolean array of size m x n, where each row is sorted. Write program to find the row with the maximum
 * number of 1s.
 *
 * Input: X[][]
 * 0 1 1 1
 * 1 1 1 1 <- row with max number of 1's
 * 0 0 1 1
 * 0 0 0 0
 *
 * Output: 1 (0-based indexing)
 *
 * Input: X[][]
 * 0 0 1 1
 * 0 0 0 1
 * 0 1 1 1 <- row with max number of 1's
 * 0 0 0 1
 *
 * Output: 2
 */
public class RowWithMax1 {
    public static void main(String args[]) {
        int[][] x1 = {{0,0,1,1},{0,0,0,1},{0,1,1,1},{0,0,0,1}};
        int[][] x2 = {{0,1,1,1},{0,0,1,1},{1,1,1,1},{0,0,0,0}};

        System.out.println(bruteForceLinearSearch(x1));
        System.out.println(bruteForceLinearSearch(x2));

        System.out.println(binarySearchRow(x1,4,4));
        System.out.println(binarySearchRow(x2, 4,4));

        System.out.println(nestedLoop(x1,4, 4));
        System.out.println(nestedLoop(x2,4, 4));
    }

    /**
     * brute force approach using two nested loops
     */
    public static int bruteForceLinearSearch(int[][] x) {
        int rowSize = x.length;
        int maxOneCount = 0;

        for(int i=0; i<rowSize; i++) {  // T.C = O(m)
            int colSize = x[i].length;
            int oneCount =0;
            for(int j=0; j<colSize; j++) { // T.C = O(n)
                if(x[i][j] ==1) {
                    oneCount++;
                }
            }
            if(oneCount > maxOneCount) {
                maxOneCount = oneCount;
            }
            if(maxOneCount == colSize) {
                return oneCount;
            }
        }
        return maxOneCount; // Total T.C = O(m*n)
    }

    /**
     * How to use binary search--?
     * every row is sorted - so there is a possibility to apply binary search. find the first index of 1 - and after that
     * all the numbers would be 1
     * so we apply binary search at each row.
     * so the time complexity would be O(logm) - m = number of rows
     * {0,0,1,1,1} left =0, right = 4 mid =2
     * x[mid] == 1 --> right = 2-1 = 1, left = 0
     * int[][] x1 = {{0,0,1,1},{0,0,0,1},{0,1,1,1},{0,0,0,1}};
     */

    public static int binarySearchRow(int[][] x, int row, int column) {
        int left =0, right = row-1;
        int maxCount = 0;
        // for every row - apply binary search. T.C for loop = O(m)
        for(int i=0; i<row; i++) {
            int firstIndex = firstOccurrence(x[i], column); // T.C = O(logn)
            int onesCount = (column) - firstIndex;
            if(onesCount > maxCount) {
                maxCount = onesCount;
            }
        }
        return maxCount; // Total T.C = O(mlogn)
    }


    /**
     * Find the first occurrence of one using binary search. This will return the index.
     */
    //T.C = o(logn)
    private static int firstOccurrence(int[] x, int n) {
        int left =0, right = n-1;
        while(left <= right) {
            int mid = left + (right-left)/2;

            if(x[mid] == 1) {
                right = mid-1;
            } else {
                left = mid+1;
            }
        }
        return left;
    }

    /**
     * Can we do any better ?  the row is sorted in the matrix. so if we start from right most corner of the first row
     * if rightmost = 1 --> keep moving left till you find 0. store this one count
     * then move down and see where this index returns 1. - if you find one move left till you find 0
     * once you find zero - move downwards
     * {0,1,1,1},
     * {0,0,1,1},
     * {1,1,1,1},
     * {0,0,0,0}
     */

    public static int nestedLoop(int[][] x, int row, int column) {
        int m = row, n = column;
        int currCol = n;
        for(int i=0; i<m; i++) {
            while ( currCol > 0 && x[i][currCol-1] ==1) {
                currCol--;
            }
            if(currCol ==0) {
                return column;
            }
        }
        return column -currCol;
    }
}
