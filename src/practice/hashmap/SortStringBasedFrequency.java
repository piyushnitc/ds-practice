package practice.hashmap;

/**
 * Given a string S, write a program to sort string in decreasing order based on the frequency of characters.
 *
 * The frequency of a character is the number of times it appears in the string.
 * In the output, characters with higher frequency come first.
 * If two characters have the same frequency, whichever occurs earliest in S, must come first. In other words,
 * the sorting must be stable.
 * Assume string consists of uppercase and lowercase English letters. We need to consider the uppercase and lowercase
 * of the same character as a different one.*
 *
 * Input: S = "tree", Output: "eetr"
 * Explanation: 'e' appears twice while 'r' and 't' both appear once. So 'e' must appear before both 'r' and 't'.
 *
 * Input: S = "ccaaaAA", Output: "aaaccAA"
 * Explanation: 'a' appears three times while 'c' and 'A' appear twice. So 'a' must appear before both 'c' and 'A'.
 * Similarly, the frequency of 'c' and 'A' are the same. To maintain the stable order 'c' must appear before 'A'.
 */
public class SortStringBasedFrequency {
    public static void main(String args[]) {
        String s1 = "tree";
        String s2 = "ccaaaAA";
    }

    /**
     * Observation
     *
     * 1. Brute force
     *      --  way to complex. no need to do this
     * 2. using binary search
     *      --
     * 3. hash table
     */
}
