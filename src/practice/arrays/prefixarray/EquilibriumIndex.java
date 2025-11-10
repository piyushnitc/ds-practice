package practice.arrays.prefixarray;

import java.util.ArrayList;
import java.util.List;

/**
 * The equilibrium index of an array is an index such that the sum of elements at lower indexes is
 * equal to the sum of elements at higher indexes.
 *
 * Note - there can be multiple equilibrium index
 *
 * For i = 0, we assume that the sum of elements at lower indexes is equal to 0.
 * For i = n - 1, we assume that the sum of elements at higher indexes is equal to 0.
 *
 * Input: A[] = [-7, 1, 5, 2, -4, 3, 0], Output: 3
 * Explanation: 3 is an equilibrium index, i.e., A[0] + A[1] + A[2] = A[4] + A[5] + A[6] = -1.
 *
 * Input: A[] = [0, 1, 3, -2, -1], Output: 1
 * Explanation: 1 is an equilibrium index, i.e., A[0] = A[2] + A[3] + A[4] = 0.
 *
 * Input: A[] = [1, 2, -2, -1], Output: -1
 * Explanation: There is no such equilibrium index in the array.
 */
public class EquilibriumIndex {

    public static void main(String args[]) {
        int[] x1 = {-7, 1, 5, 2, -4, 3, 0};
        int[] x2 = {0, 1, 3, -2, -1};
        int[] x3 = {1, 2, -2, -1};

//        Arrays.stream(bruteForceApproach3(x1)).forEach(e-> System.out.print(e+","));
//        System.out.println();
//        Arrays.stream(bruteForceApproach3(x2)).forEach(e-> System.out.print(e+","));
//        System.out.println();
//        Arrays.stream(bruteForceApproach3(x3)).forEach(e-> System.out.print(e+","));

        System.out.println(prefixArray(x1));
        System.out.println(prefixArray(x2));
        System.out.println(prefixArray(x3));


    }

    // brute force - > consider every element and find the sum to left and right
    // T.C = O(n*n)
    public static int bruteForceEquilibriumIndex(int[] x) {

        int equilbriumIndex = -1;
        for(int i=0; i<x.length; i++) {
            int leftSum = 0;
            int rightSum = 0;
            if(i == 0) {
                leftSum = 0;
            }
            if(i == x.length-1) {
                rightSum = 0;
            }
            for(int j=i+1; j<x.length; j++) {
                rightSum = rightSum + x[j];
            }
            for(int k=0; k<i; k++) {
                leftSum = leftSum+x[k];
            }
            if(leftSum == rightSum) {
                equilbriumIndex = i;
                break;
            }
        }
        return equilbriumIndex;
    }

    // get the total sum of the array
    public static int bruteForceApproach2(int[] x) {
        int totalSum = 0;
        int equIndex = -1;
        int leftSum = 0;

        // t.c = o(n) - get the total totalSum of the array
        for(int i=0; i<x.length; i++) {
            totalSum = totalSum+x[i];
        }
        // T.C = o(n) - scan the entire array
        for(int i=0; i< x.length; i++) {
            int rightSum = totalSum - leftSum - x[i];

            if(leftSum == rightSum) {
                equIndex = i;
                break;
            }
            leftSum = leftSum + x[i];
        }
        return equIndex;
        // Total T.C = 0(n)+ O(n) = 2*O(n) = O(n)
        // total comparison = 3* n
    }

    // get the total sum of the array
    // get multiple equilibrium points
    public static int[] bruteForceApproach3(int[] x) {
        int totalSum = 0;
        int leftSum = 0;
        int equIndex = 0;
        List<Integer> equiIndexList = new ArrayList<>();
        // t.c = o(n) - get the total totalSum of the array
        for(int i=0; i<x.length; i++) {
            totalSum = totalSum+x[i];
        }
        // T.C = o(n) - scan the entire array
        for(int i=0; i< x.length; i++) {
            equIndex = -1;
            int rightSum = totalSum - leftSum - x[i];

            if(leftSum == rightSum) {
                equIndex = i;
                equiIndexList.add(i);
            }
            leftSum = leftSum + x[i];
        }
        if(equiIndexList.isEmpty()) {
            equiIndexList.add(-1);
        }
        return equiIndexList.stream().mapToInt(Integer::intValue).toArray();
        // Total T.C = 0(n)+ O(n) = 2*O(n) = O(n)
        // total comparison = 3* n
    }

    // define prefixSum as sum of all the elements to left including the element
    // leftSum = prefixSum[i-1]
    // rightSum = totalSum - prefixSum[i]-x[i]
    public static int prefixArray(int[] x) {
        int prefixSum[] = new int[x.length];
        prefixSum[0] = x[0];

        for(int i=1; i<x.length; i++) {
            prefixSum[i] = prefixSum[i-1]+x[i];
        }
        int totalSum = prefixSum[x.length-1];
        int leftSum = 0;
        int rightSum = 0;

        for(int i=0; i<x.length; i++) {
           if(i==0) {
               leftSum = 0;
               rightSum = totalSum - leftSum-x[i];
           } else {
               leftSum = prefixSum[i-1];
               rightSum = totalSum - prefixSum[i-1]-x[i];
           }

           if(leftSum == rightSum) {
               return i;
           }
        }
        return -1;
    }
}
