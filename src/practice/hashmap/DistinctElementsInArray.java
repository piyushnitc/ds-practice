package practice.hashmap;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Given an array, check whether all elements in an array are distinct or not.
 *
 * Input : 1, 3, 2, 4
 * Output : Yes
 *
 * Input : "Geeks", "for", "Geeks"
 * Output : No
 *
 * Input : "All", "Not", "Equal"
 * Output : Yes
 */
public class DistinctElementsInArray {
    public static void main(String args[]) {
        String[] x1 = {"1","2","3","4"};
        String[] x2 = {"Geeks", "for", "Geeks"};
        String[] x3 = {"All", "Not", "Equal"};

        System.out.println(hashMapApproach(x1));
        System.out.println(hashMapApproach(x2));
        System.out.println(hashMapApproach(x3));
    }


    /**
     * Observation -
     *
     * 1. Brute Force.. Check each element against every other element
     * 2. Use Hash Table.. if size of array == size of hash ---> distinct elements
     */

    public static boolean hashMapApproach(String[] x) {
        Set<String> stringSet = new HashSet<>(Arrays.asList(x));
        if(x.length == stringSet.size()) {
            return  true;
        } else {
            return false;
        }
    }
}
