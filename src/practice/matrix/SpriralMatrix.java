package practice.matrix;

/**
 * Given a 2-dimensional m x n matrix, print all the elements in spiral order.
 *
 * 1    2   3   4
 * 5    6   7   8
 * 9    10  11  12
 * 13   14  15  16
 *
 * print as --> 1   2   3   4   8   12  16  15  14  13  9   5   6   7   11  10
 *
 */
public class SpriralMatrix {

    public static void main(String args[]) {

        int[][] x1 = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
        bruteForce(x1, 4,4);
        System.out.println();
        recursion(x1,0,3, 0, 3);
    }

    /**
     * Traverse first row (say rowStart and once finished increment rowStart by 1)
     * traverse last column (say colEnd and once finished decrement colEnd by 1)
     * traverse lastRow (say rowEnd and once finished decrement rowEnd by 1)
     * traverse firstCol (say colStart and once finished increment colStart by 1)
     * do this untill rowStart <= rowEnd && colStart <= colEnd
     */
    public static void bruteForce(int[][] x, int row, int col) {
        int rowStart = 0, rowEnd = row -1;
        int colStart = 0, colEnd = col -1;

        while(rowStart <= rowEnd && colStart <= colEnd) {

            for(int i=colStart; i<=colEnd; i++) {
                System.out.print(" "+x[rowStart][i]+" ");
            }
            rowStart++;

            for(int i=rowStart; i<=rowEnd; i++) {
                System.out.print(" "+x[i][colEnd]+" ");
            }
            colEnd--;

            if(rowStart <= rowEnd) {
                for(int i=colEnd; i>=colStart; i--) {
                    System.out.print(" "+x[rowEnd][i]+" ");
                }
                rowEnd--;
            }

            if(colStart <= colEnd) {
                for(int i= rowEnd; i>=rowStart; i--) {
                    System.out.print(" "+x[i][colStart]+" ");
                }
                colStart++;
            }
        }  // T.C = O(m*n)
    }

    /**
     * Can we solve using recursion.. Think once we have printed the outer boundary of the matrix m*n  -> we need to do
     * the same exact thing for matrix (m-2) * (n-2) recursively
     *
     * what would be the terminating condition
     * 1. when rowStart > rowEnd  or colStart > colEnd
     */
    public static void recursion(int[][] x, int rowStart, int rowEnd, int colStart, int colEnd) {

        if(rowStart > rowEnd || colStart > colEnd) {
            return;
        }
        for(int i=colStart; i<=colEnd; i++){
            System.out.print(" "+x[rowStart][i]+" ");
        }
        rowStart++;

        for(int i=rowStart; i<=rowEnd; i++){
            System.out.print(" "+x[i][colEnd]+" ");
        }
        colEnd --;

        for(int i= colEnd; i>=colStart; i--) {
            System.out.print(" "+x[rowEnd][i]+" ");
        }
        rowEnd --;

        for(int i=rowEnd; i>=rowStart; i--) {
            System.out.print(" "+x[i][colStart]+" ");
        }
        colStart++;

        recursion(x, rowStart, rowEnd, colStart, colEnd);
    }  //T.C = O(m*n)   S.C = depends on the stack trace
    // At every recursion the stack is getting reduced by 2. Hence both m and n are decreasing by 2
    // if m > n ---> depth of recursion = m/2
    // if m < n ---> depth of recursion = n/2
    // Hence, S.C = O(min(m,n))

}
