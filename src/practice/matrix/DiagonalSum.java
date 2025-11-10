package practice.matrix;

/**
 * Given a square matrix mat, return the sum of the matrix diagonals.
 * Only include the sum of all the elements on the primary diagonal and all the elements on the secondary diagonal that
 * are not part of the primary diagonal.
 *
 * 1    2   3
 * 4    5   6
 * 7    8   9
 *
 * Diagonal sum = 1+5+9+3+7 = 25
 * Index = { (0,0),(0,2), (2,0), (2,2), (1,1) }
 *
 * 1    1   1   1
 * 1    1   1   1
 * 1    1   1   1
 * 1    1   1   1
 *
 * Diagnoal Sum = 1+1+1+1+1+1+1+1
 * index - > {(0,0), (0,3),(3,0), (3,3) }-- all corner elements
 * diagonal central index = {(1,1), (1,2), (2,1), (2,2) }
 */
public class DiagonalSum {
    public static void main(String args[]) {
        int[][] x1 = {{1,2,3},{4,5,6},{7,8,9}};
        int[][] x2 = {{1,1,1,1}, {1,1,1,1}, {1,1,1,1},{1,1,1,1}};
    }

    /**
     * We can see that elements along the primary diagonals have the same row and column number. So, all elements of
     * the form mat[i][i] with i ranging from i = 0 to i = n - 1, where n is the number of rows (or columns) in mat,
     * form the primary diagonal.
     *
     * Let's form the secondary diagonal starting with the last row and first column, i.e., mat[n - 1][0]. mat[n - 2][1]
     * is the next element over the secondary diagonal, one row up and one column ahead. The following element,
     * mat[n - 3][2], is again one row up and one column ahead of the previous element. The final element is mat[0][n - 1].
     * We can notice that the sum of the row and column numbers is constant (n - 1) because the column increases by one
     * but the row decreases by one. As a result, all elements of the form mat[n - 1 - i][i] with i ranging
     * from i = 0 to i = n - 1 constitute the secondary diagonal.
     *
     * When we compare a square matrix with an odd number of rows to a square matrix with an even number of rows, we
     * notice that there is a common element mat[n / 2][n / 2] at the intersection of the primary and secondary
     * diagonals in the case of the matrix with odd rows:
     */

    public static int bruteForce(int[][] x) {
        int n = x.length;
        int ans = 0;

        for(int i=0; i<n; i++) {
            //primary diagonal sum
            ans += x[i][i];

            // secondary diagonal sum
            ans += x[n-1-i][i];
        }

        // if n is odd - the middle element is added twice.
        if(n %2 == 0) {
            ans = ans - x[n/2][n/2];
        }

        return ans;
    }
}
