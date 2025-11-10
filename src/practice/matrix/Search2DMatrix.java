package practice.matrix;

/**
 * Write an efficient algorithm that searches for a value target in an m x n integer matrix. This matrix has the
 * following properties:
 *
 * Integers in each row are sorted in ascending from left to right.
 * Integers in each column are sorted in ascending from top to bottom.
 *
 * 1    4   7   11  15
 * 2    5   8   12  19
 * 3    6   9   16  22
 * 10   13  14  17  24
 * 18   21  23  26  30
 *
 */
public class Search2DMatrix {
    public static void main(String args[]) {
        int[][] x1 = {{1,4,7,11,15},{2,5,8,12,19},{3,6,9,16,22},{10,13,14,17,24},{18,21,23,26,30}};
        // target = 5 Output: true
        // target = 20 Output : false;
    }

    /**
     * Two approach
     * 1. Linear scan T.C = O(n*n)
     * 2. Binary Search T.C = O(nlogn) --> since rows and column are sorted --> take the mid
     * and reduce or increase mid accordingly.
     */
}
