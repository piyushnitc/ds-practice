package practice.matrix;

/**
 * Given an n x n square matrix, write a program to rotate the matrix by 90 degrees in the anti-clockwise direction.
 * The goal is to perform the matrix rotation in place, meaning we need to rotate the matrix without using extra space
 * eg:-
 *  1   2   3              3    6   9
 *  4   5   6              2    5   8
 *  7   8   9              1    4   7
 */
public class RotateMatrix {
    public static void main(String args[]) {
        int[][] x1 = {{1,2,3},{4,5,6},{7,8,9}};
        int[][] x2 = {{1,2,3,4}, {5,6,7,8}, {9,10,11,12}};

        //printMatrix(bruteForce(x1,3,3));
        //printMatrix(bruteForce(x2, 3,4));

        printMatrix(transposeMatrix(x1, 3,3));
        System.out.println();
        printMatrix(transposeMatrix(x2, 3,4));
        System.out.println();
        printMatrix(transposeInPlace(x1,3,3));
        System.out.println();
        printMatrix(transposeInPlace(x2,3,4));
    }

    /**
     * observation - rotating matrix by anticlockwise by 90 --> last column --> first row
     * (last column - 1) = first row + 1
     * (last column - 2) = first row + 2
     *
     * However, this would require extra space to create a new matrix.
     *
     * Approach 2 ->  take a transpose of the matrix and then reverse the row first row with last row
     *  1   2   3                           1    4   7                          3   6   9
     *  4   5   6  --> transpose            2    5   8   ---> reverse --->      2   5   8
     *  7   8   9                           3    6   9                          1   4   7
     *
     *  1   2   3   4                       1   5   9                           4   8   12
     *  5   6   7   8     --->transpose     2   6   10  ----> reverse --->      3   7   11
     *  9   10  11  12                      3   7   11                          2   6   10
     *                                      4   8   12                          1   5   9
     */

    public static int[][] bruteForce(int[][] x, int row, int col) {
        // idea is last column = first row
        int[][] output = new int[col][row];
        int k =0;

        for(int i=col-1; i>=0; i--) {  // T.C = O(m)
            for (int j=0; j<row; j++){ // T.C = O(n)
                output[k][j] = x[j][i];
            }
            k++;
        }
        return output;  // T.C = O(m*n)
    }

    public static int[][] transposeAndReverse(int[][] x, int row, int col) {
        int[][] transpose = transposeMatrix(x, row,col);
        return reverse(x, row, col);
    }

    /**
     * This transpose method does not happen in place. it uses extra space
     */
    private static int[][] transposeMatrix(int[][] x, int row, int col) {
        int[][] transpose = new int[col][row];

        int k = 0;
        for(int i=0; i<row; i++) {
            for(int j=0; j<col; j++) {
                transpose[j][k] = x[i][j];
            }
            k++;
        }
        return transpose;
    }

    /**
     *  1   2   3                                    1    4   7
     *  4   5   6           --> transpose            2    5   8
     *  7   8   9                                    3    6   9
     *
     *  The transpose of a matrix is obtained by flipping over the elements over its diagonal
     *  i.e - the row index of an element becomes the column index and vice versa
     *  This will only work for a square matrix.
     */
    private static int[][] transposeInPlace(int[][] x, int row, int col) {
        for(int i=0; i<row; i++) {
            for(int j=i; j<col; j++) {
                int temp = x[i][j];
                x[i][j] = x[j][i];
                x[j][i] = temp;
            }
        }
        return x;
    }

    private static int[][] reverse(int[][] x, int row, int col) { return null;}

    private static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int value : row) {
                System.out.printf("%5d", value);  // width of 5 for alignment
            }
            System.out.println();
        }
    }

}
