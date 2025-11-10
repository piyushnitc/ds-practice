package practice.arrays.singleloop;

/**
 * The array X is a mountain array if and only if n >= 3 and there exists some i (0 < i < n -1) such that:
 * X[0] < X[1] <...< X[i-1] < X[i] and X[i] > X[i+1] > ...> X[n-1].
 * In other words, the array is a valid mountain when it is first strictly increasing and then
 * strictly decreasing.
 *
 * Input: X[] = [5, 2, 1, 4], Output: false
 * Input: X[] = [5, 8, 8], Output: false
 * Input: X[] = [1, 2, 6, 5, 3], Output: true
 */
public class CheckArrayValidMountain {

    public static void main(String args[]) {
        int[] x1 = {5, 2, 1, 4};
        int[] x2 = {5, 8, 8};
        int[] x3 = {1, 2, 6, 5, 3};
        int[] x4 = {1,20,10,30,5};

        System.out.println(isValidMountain(x1));
        System.out.println(isValidMountain(x2));
        System.out.println(isValidMountain(x3));
        System.out.println(isValidMountain(x4));


    }

    /**
     * strictly find increasing and decreasing order
     * @param x
     * @return
     */
    public static boolean isValidMountain(int[] x) {
        boolean increasing;
        boolean decreasing = false;
        boolean valid = false;

        if (x[1] > x[0]) {
            increasing = true;
        } else {
            return false;
        }

        for (int i = 2; i < x.length-1; i++) {
            if (x[i] < x[i + 1]) {
                increasing = true;
            } else if (x[i] > x[i + 1]) {
                decreasing = true;
            } else {
                continue;
            }
        }
        if (increasing && decreasing) {
            valid = true;
        }
        return valid;
    }
}
