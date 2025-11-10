package practice.arrays.singleloop;

/**
 * There is a biker going on a road trip. The road trip consists of n + 1 points at different altitudes.
 * The biker starts his trip on point 0 with altitude equal 0.
 *
 * You are given an integer array gain of length n where gain[i] is the net gain in altitude between points
 * i and i + 1 for all (0 <= i < n). Return the highest altitude of a point.
 *
 * Input: gain = [-5,1,5,0,-7]
 * Output: 1
 * Explanation: The altitudes are [0,-5,-4,1,1,-6]. The highest is 1.
 *
 * Input: gain = [-4,-3,-2,-1,4,3,2]
 * Output: 0
 * Explanation: The altitudes are [0,-4,-7,-9,-10,-6,-3,-1]. The highest is 0.
 */
public class HighestAltitude {

    /**
     * Observation - track highest and scan the loop
     * @param args
     */
    public static void main(String args[]) {
        int[] gain1 = {-5,1,5,0,-7};
        int[] gain2 = {-4,-3,-2,-1,4,3,2};

        System.out.println(highestAlt(gain1));
        System.out.println(highestAlt(gain2));
    }

    public static int highestAlt(int[] x) {
        int highest = Integer.MIN_VALUE;
        int totalGain = 0;
        for(int i=0; i<x.length; i++) {
            totalGain = totalGain+x[i];
            if(totalGain > highest) {
                highest = totalGain;
            }
        }
        if(highest < 0) {
            highest = 0;
        }
        return highest;
    }
}
