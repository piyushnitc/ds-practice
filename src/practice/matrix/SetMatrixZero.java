package practice.matrix;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an m x n integer matrix, if an element is 0, set its entire row and column to 0's.
 * You must do it in place.
 *
 * 1    1   1           1   0   1
 * 1    0   1   --->    0   0   0
 * 1    1   1           1   0   1
 *
 * 1    2   0   3
 * 2    3   4   5
 * 0    1   2   3
 *
 * 0    1   2   0           0   0   0   0
 * 3    4   5   2   --->    0   4   5   0
 * 1    3   1   5           0   3   1   0
 */
public class SetMatrixZero {
    public static void main(String args[]) {
        int[][] x1 = {{1,1,1},{1,0,1},{1,1,1}};
        int[][] x2 = {{0,1,2,0},{3,4,5,2},{1,3,1,5}};
        //bruteForce(x1, 3,3);
        System.out.println();
        //bruteForce(x2, 3,4);

        betterApproach(x2);

    }

    /**
     * brute force --> scan matrix and find 0's position lets say first position = (1,1)
     * for row = 1 --> set all 0
     * for col = 1 --> set all 0
     */

    public static void bruteForce(int[][] x, int row, int col) {
        int m = row, n = col;
        List<MatrixIndex> matrixIndexList = new ArrayList<MatrixIndex>();

        //T.C = O(m*n)
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                if(x[i][j] ==0) {
                    // store i and j value;
                    MatrixIndex index = new MatrixIndex(i, j);  // Space Complexity is O(m+n)
                    matrixIndexList.add(index);
                }
            }
        }

        for(int i=0; i<matrixIndexList.size(); i++) {
            setZero(x, matrixIndexList.get(i).getRow(), matrixIndexList.get(i).getCol(), m, n);
        }
        printMatrix(x);

    }

    private static void setZero(int[][] x, int rowIndex, int colIndex, int row, int col) {
        for(int i=0; i< col; i++) {
            x[rowIndex][i] = 0;
        }

        for(int i=0; i<row; i++) {
            x[i][colIndex] = 0;
        }
    }

    private static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int value : row) {
                System.out.printf("%5d", value);  // width of 5 for alignment
            }
            System.out.println();
        }
    }

    /**
     * Can you think of a better idea??
     * T.C = O(m*n) --> because we need top find the index where it is zero. Now can we do in place change?
     *
     */

    public static void betterApproach(int[][] x) {
        int m = x.length, n = x[0].length;
        int c0 = 1;

        printMatrix(x);

        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {

                if(x[i][j] ==0) {
                    x[i][0] = 0;

                    if(j ==0){
                        c0=0;
                    } else {
                        x[0][j] = 0;
                    }
                }
            }
         }
        System.out.println();
        printMatrix(x);

        for(int i=1; i<m; i++) {
            for (int j = 1; j < n; j++) {
                if (x[i][0] == 0 || x[0][j] == 0) {
                    x[i][j] = 0;
                }
            }
        }

        System.out.println();
        printMatrix(x);
        }

    }

class MatrixIndex {
    int row;
    int col;

    public MatrixIndex(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
