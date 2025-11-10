package practice.stack;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * Given a string s, remove duplicate letters so that every letter appears once and only once. You must make sure your
 * result is the smallest in lexicographical order among all possible results.
 *
 * Input: s = "bcabc"
 * Output: "abc"
 *
 * Input: s = "cbacdcbc"
 * Output: "acdb"
 */
public class RemoveDuplicates {
    public static void main(String args[]) {
        String s1 = "bcabc";        //abc
        String s2 = "cbacdcbc";     //acdb

        System.out.println(nestedApproach(s1));
        System.out.println(nestedApproach(s2));

        System.out.println(usingHashMap(s1));
        System.out.println(usingHashMap(s2));

        System.out.println(usingStack(s1));
        System.out.println(usingStack(s2));
    }

    /**
     * Observation
     * 1. if lexicographical order is not required here are the below approaches
     *      --> nested loops t.C = O(n^2)
     *      --> using hash map  T.C = O(n) S.C = O(n)
     *      --> using frequency array
     */

    public static String nestedApproach(String str) {
        StringBuilder result = new StringBuilder();
        for(int i=0; i<str.length(); i++) {     // T.C = O(n)
            char c = str.charAt(i);
            if(result.indexOf(String.valueOf(c)) == -1) {       // T.C = O(n) -- as it will loop every element
                result.append(c);
            }
        }
        return result.toString();
    }


    public static String usingHashMap(String str) {
        Set<Character> charSet = new HashSet<>();
        StringBuilder result = new StringBuilder();

        for(char c: str.toCharArray()) {
            if(!charSet.contains(c)) {
                result.append(c);
                charSet.add(c);
            }
        }
        return result.toString();
    }

    /**
     * If lexicographical order is required - we need to maintain order (i.e we need to put elements at the right position
     * this can be done using stack
     */

    // "cbacdcbc";
    public static String usingStack(String str) {
        Stack<Character> stack = new Stack<>();
        boolean[] inStack = new boolean[26]; // considering all small alphabets
        int[] count = new int[26];

        for(char c: str.toCharArray()) {
            count[c -'a']++;
        }

        for(char c: str.toCharArray()) {
            // decrease the count for the first count.
            count[c -'a']--;

            // if the char is in stack - do not add. continue to next character
            if(inStack[c-'a']) {
                continue;
            } else {
                // add char to stack. but we must check what's at the top of stack and compare with c
                // if peek > c -- we pop out only if we know that we will encounter this character again later on
                // or else do not pop out
                while(!stack.isEmpty() && stack.peek() > c) {
                    if(count[stack.peek()-'a'] == 0) {
                        break;
                    }
                    inStack[stack.pop()-'a'] = false;
                }
                stack.push(c);
                inStack[c-'a'] = true;
            }
        }

        StringBuilder result = new StringBuilder();
        while(!stack.isEmpty()) {
            result.append(stack.pop());
        }

        return result.reverse().toString();
    }
}
