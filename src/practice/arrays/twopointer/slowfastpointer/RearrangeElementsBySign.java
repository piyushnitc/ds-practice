package practice.arrays.twopointer.slowfastpointer;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * You are given a 0-indexed integer array nums of even length consisting of an equal number of positive
 * and negative integers.
 * You should return the array of nums such that the the array follows the given conditions:
 *
 * Every consecutive pair of integers have opposite signs.
 * For all integers with the same sign, the order in which they were present in nums is preserved.
 * The rearranged array begins with a positive integer.
 *
 * Input: nums = [3,1,-2,-5,2,-4]
 * Output: [3,-2,1,-5,2,-4]

 * The positive integers in nums are [3,1,2]. The negative integers are [-2,-5,-4].
 * The only possible way to rearrange them such that they satisfy all conditions is [3,-2,1,-5,2,-4].
 * Other ways such as [1,-2,2,-5,3,-4], [3,1,2,-2,-5,-4], [-2,3,-5,1,-4,2] are incorrect because
 * they do not satisfy one or more conditions.
 *
 * Input: nums = [-1,1]
 * Output: [1,-1]
 *
 * 1 is the only positive integer and -1 the only negative integer in nums.
 * So nums is rearranged to [1,-1].
 */
public class RearrangeElementsBySign {
    public static void main(String args[]) {
        int[] x1 = {3,1,-2,-5,2,-4};
        int[] x2 = {-1,1};
        int[] x3 = {-1,-2,-3,3,2,1};

        System.out.println(Arrays.toString(usingDynamicList(x1)));
        System.out.println(Arrays.toString(usingDynamicList(x2)));
        System.out.println(Arrays.toString(usingDynamicList(x3)));

        System.out.println(Arrays.toString(oneScan(x1)));
        System.out.println(Arrays.toString(oneScan(x2)));
        System.out.println(Arrays.toString(oneScan(x3)));
    }

    //obs - this can be done using fixed size arrays as well; however t.c will remain the same;
    public static int[] usingDynamicList(int[] x) {
        int n = x.length;
        int[] output = new int[n];
        //S.C = O(n)+O(n)
        LinkedList<Integer> negative = new LinkedList<>();
        LinkedList<Integer> positive = new LinkedList<>();

        //T.C = O(n)
        for(int i=0; i<n; i++) {
            if(x[i] > 0) {
                positive.add(x[i]);
            } else {
                negative.add(x[i]);
            }
        }
        //T.C = O(n)
        for(int i=0, j=0; i<n/2;  i++, j=j+2) {
            output[j] = positive.get(i);
            output[j+1]= negative.get(i);
        }
        return output; // Total T.C = 2O(n) = O(n)    S.C = O(n)  ; Auxiliary S.C = 2O(n)
    }

    // obs - positive nums will occupy 0, 2, 4..(even index) and negative would occupy odd index
    //x1 = {3,1,-2,-5,2,-4};
    //x2 = {-1,-2,-3,1,2,3};
    // if encounter positive send to even position and in case of negative send to odd
    public static int[] oneScan(int[] x) {
        int n = x.length;
        int[] output = new int[n];

        int positiveIndex = 0;
        int negativeIndex = 1;
        for(int i=0; i<n; i++) {
            if(x[i] > 0) {
                output[positiveIndex] = x[i];
                positiveIndex += 2;
            } else {
                output[negativeIndex]= x[i];
                negativeIndex +=2;
            }
        }
        return output; //T.C = O(n) ; S.C = O(n)
    }
}
