package practice.arrays.twopointer.slowfastpointer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Given an array that includes positive and negative numbers, write a program to find the smallest
 * missing positive integer.
 *
 * Input: X[] = [2, -9, 5, 11, 1, -10, 7], Output: 3
 * Explanation: Consecutive positive integers 1 and 2 are present in the array. So the first missing
 * positive is 3.
 *
 * Input: X[] = [2, 3, -2, 1], Output: 4
 * Explanation: Consecutive positive integers 1, 2, and 3 are present in the array. So the first missing
 * positive is 4.
 *
 * Input: X[] = [4, 6, 5, 3, 8], Output: 1
 * Explanation: All values are positive, but the first positive integer 1 is missing in the input.
 * So the output is 1.
 */

public class FirstMissingPositive {
    public static void main(String args[]) {
        int[] x1 = {2, -9, 5, 11, 1, -10, 7};   //op = 3
        int[] x2 = {2, 3, -2, 1};               //op = 4
        int[] x3 = {4, 6, 5, 3, 8};             //op = 1
        int[] x4 = {-1,-2,-3,-4,1};             //op = 2
        int[] x5 = {-1,-2,-3,-4,0};             //op = 1

//        System.out.println(sortingApproach(x1));
//        System.out.println(sortingApproach(x2));
//        System.out.println(sortingApproach(x3));
//        System.out.println(sortingApproach(x4));
//        System.out.println(sortingApproach(x5));
//
//        System.out.println(bruteForce(x1));
//        System.out.println(bruteForce(x2));
//        System.out.println(bruteForce(x3));
//        System.out.println(bruteForce(x4));
//        System.out.println(bruteForce(x5));
//
//        System.out.println(sortingAndSingleScan(x1));
//        System.out.println(sortingAndSingleScan(x2));
//        System.out.println(sortingAndSingleScan(x3));
//        System.out.println(sortingAndSingleScan(x4));
//        System.out.println(sortingAndSingleScan(x5));

        System.out.println(usinghashTable(x1));
        System.out.println(usinghashTable(x2));
        System.out.println(usinghashTable(x3));
        System.out.println(usinghashTable(x4));
        System.out.println(usinghashTable(x5));
    }

    // obs - sort the array in ascending order and find the min positive number in the array and also the index
    // if min is greater than 1 --> this means missing positive = 1
    // if min is equal to 1 -> then we need to loop and find which number is missing.
    // if minMissing is 0 -> this means that continuous numbers are present. Hence, we take the last element and add 1 to it
    public static int sortingApproach(int[] x) {
        int missingPositive = 0;
        int n = x.length;
        Arrays.sort(x); // T.C = O(nlogn)

        int subArrayStartIndex= 0;
        int subArrayEndIndex = n-2;
        int minPositive = Integer.MAX_VALUE;

        //T.C = O(n)
        for(int i=0; i<n; i++) {
            if(x[i]> 0) {
                subArrayStartIndex = Math.min(subArrayStartIndex, i);
                minPositive = Math.min(minPositive, x[i]);
            }
        }

        // now minPositive can be 1 or greater than 1
        if(minPositive > 1) {
            return 1;
        } else {
            //T.C = O(n)
            for(int j=subArrayStartIndex; j<subArrayEndIndex; j++) {
                if(x[j] > 0 && x[j]+1 != x[j+1]) {
                    missingPositive = x[j] + 1;
                    break;
                }
            }
            if(missingPositive == 0) {
                return x[n-1]+1;
            }
        }
        return missingPositive; // Total T.C = o(nlogn)+ O(n) + O(n) = 2O(n)+O(nlogn) = O(n)+O(nlogn)
    }

    //obs - in an array of size n -> the consecutive positive numbers would be from [1 ... n+1]
    // if an array of size n is created with numbers(positive only, negative only, positive and negative numbers)
    // certainly one of the numbers would be missing in the new array
    public static int bruteForce(int[] x) {
        int n = x.length;
        int missingPositiveNumber = 0;

        //T.C = O(n)
        for(int i=1; i<n+2; i++) {
            boolean missingNumber = true;
            //T.C = O(n)
            for(int j=0; j<n; j++) {
                if(x[j] == i) {
                    missingNumber = false;
                    break;
                }
            }
            if(missingNumber) {
                missingPositiveNumber = i;
                break;
            }
        }
        return missingPositiveNumber;  // T.C = O(n^2)
    }

    //Obs - sort the array. after sorting we need to find the first positive integer
    // this first positive could be 1 or greater than 1
    // if it is greater than 1 -> return 1
    // if it is equal to 1 --> then check subsequent numbers if they are in series. if anything missing return
    // if the series is complete --> then return last element + 1;
    public static int sortingAndSingleScan(int[] x) {
        int n = x.length;
        int missingPositive = 1;
        Arrays.sort(x); // T.C = O(nlogn)
        int i = 0;

        while(i<n && x[i] < 1 ) {
            i = i+1; // this index tells where the positive number starts
        }
        // good logic.
        for (int j = i; j < n; j = j + 1)
        {
            if (missingPositive == x[j])
                missingPositive = missingPositive + 1;
            else if (x[j] > missingPositive)
                return missingPositive;
        }
        return missingPositive; // Total T.C = O(nlogn) + O(n)
    }

    //Obs - put all positive numbers in hash table
    // check if numbers from 1 to n+1 are there in this hash table. return if the number is missing
    public static int usinghashTable(int[] x) {
        int missingPositive = 1;
        int n = x.length;
        Map<Integer, Integer> numMap = new HashMap<>();

        //T.C = O(n) ; S.C = O(n)
        for(int i=0; i<n; i++) {
            if(x[i]> 0) {
                numMap.putIfAbsent(x[i], i);
            }
        }

        //T.C = O(n)
        for(int j=1; j<n+2; j++) {
            if(!numMap.containsKey(j)) {
                missingPositive = j;
                break;
            }
        }
        return missingPositive; // Total T.C = 2O(n) ; S.C = O(n)
    }

    public static void inPlaceHashing(int[] x) {
        //TODO:
    }
}
