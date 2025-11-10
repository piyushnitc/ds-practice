package practice.stack;

import java.util.Stack;

/**
 * Given an encoded string, return its decoded string.
 * The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated
 * exactly k times. Note that k is guaranteed to be a positive integer.
 *
 * You may assume that the input string is always valid; there are no extra white spaces, square brackets are well-formed,
 * etc. Furthermore, you may assume that the original data does not contain any digits and that digits are only for
 * those repeat numbers, k. For example, there will not be input like 3a or 2[4].
 *
 * The test cases are generated so that the length of the output will never exceed 105.
 *
 * Input: s = "3[a]2[bc]"
 * Output: "aaabcbc"
 * Example 2:
 *
 * Input: s = "3[a2[c]]"
 * Output: "accaccacc"
 * Example 3:
 *
 * Input: s = "2[abc]3[cd]ef"
 * Output: "abcabccdcdcdef"
 */
public class DecodeString {
    public static void main(String args[]) {
        String s1 = "3[a]2[bc]";            // "aaabcbc"
        String s2 = "3[a2[c]]";             // "accaccacc"
        String s3 = "2[abc]3[cd]ef";        // "abcabccdcdcdef"

        /**
         * Observation -
         * 1.need the starting number and string inside the valid quotes
         * scan the strings and put all the characters in character stack
         * put all the integers in integer stack
         * whenever closing bracket is encountered -- pop the integer and pop the characters till the opening bracket is encountered
         * with integer and characters build a string and push this to stack again.
         * repeat the above and once the processing is finished -- pop out all the characters from stack and build result
         * string.. finally reverse the string and return as output.
         */

        System.out.println(usingStack(s1));
        System.out.println(usingStack2(s1));
        System.out.println(withoutStack(s1));

        System.out.println(usingStack(s2));
        System.out.println(usingStack2(s2));
        System.out.println(withoutStack(s2));

        System.out.println(usingStack(s3));
        System.out.println(usingStack2(s3));
        System.out.println(withoutStack(s3));
    }

    public static String usingStack(String str) {
        int n = str.length();
        String temp = "";
        Stack<Character> charStack = new Stack<>();
        Stack<Integer> intStack = new Stack<>();
        StringBuilder result = new StringBuilder();


        for(int i=0; i<n; i++) {
            // if number - push to integer stack
            int count = 0;
            if(Character.isDigit(str.charAt(i))) {
                intStack.push(Character.getNumericValue(str.charAt(i)));
            } else if(str.charAt(i) == ']') {
               temp = "";
                // get the count from int stack
                count = intStack.pop();

                // pop all the characters till open bracket is encountered
                while(charStack.peek() != '[') {
                    temp = charStack.pop()+temp;
                }

                charStack.pop(); // remove [

                // get the repeated string with number
                StringBuilder repeated = new StringBuilder();
                for(int j=0; j<count; j++) {
                    repeated.append(temp);
                }

                // put the repeated characters to stack
                for(int k=0; k<repeated.length(); k++) {
                    charStack.push(repeated.charAt(k));
                }
            } else {
                charStack.push(str.charAt(i));
            }
        }

        // now pop all the elements to result string
        while(!charStack.isEmpty()) {
            result.append(charStack.pop());
        }
        return result.reverse().toString();
        // T.C = O(n)  Space Complexity = 3* O(n) = O(n)
    }

    /**
     * Obs 2-
     * can we improve the space complexity-- by removing integer stack? Think?
     * lets store all the characters in character stack only.
     */

    //String s1 = "3[a2[c]]";
    public static String usingStack2(String str) {
        int n = str.length();
        Stack<Character> charStack = new Stack<>();
        StringBuilder result = new StringBuilder();
        String temp = "";

        for(int i=0; i<n; i++) {
            if(str.charAt(i) == ']') {
                temp = "";
                while(charStack.peek() != '[') {
                    temp = charStack.pop()+temp;
                }
                charStack.pop(); // remove [
                int count = Character.getNumericValue(charStack.pop()); // after removing opening [ this must be integer

                // Build the repeated string
                StringBuilder repeated = new StringBuilder();
                for(int j=0; j<count; j++) {
                    repeated.append(temp);
                }

                // push this repeated string to stack
                for(int k=0; k<repeated.length(); k++) {
                    charStack.push(repeated.charAt(k));
                }
            } else {
                charStack.push(str.charAt(i));
            }
        }

        // now pop all the characters from char stack
        while(!charStack.isEmpty()) {
            result.append(charStack.pop());
        }
        return result.reverse().toString();
    }

    /**
     * Can we do without stack?? Think?
     * Scan the array and keep store the elements in a string till you encounter closing bracket.
     * once [ is encountered - temp string = 3[a2[c -- remove opening [ and expand 2c
     * temp = 3[acc
     * scan further encountered ] --> temp = 3[acc -- remove opening [ and expand acc three times.
     */

    public static String withoutStack(String str) {
        int n = str.length();
        StringBuilder result = new StringBuilder();

        for(int i=0; i<n; i++) {
            if(str.charAt(i) != ']') {
                result.append(str.charAt(i));
            } else {
                // we encountered closing string
                StringBuilder temp = new StringBuilder();
                while(result.length() >0 && result.charAt(result.length()-1) != '[') {
                   temp.append(result.charAt(result.length()-1));
                   result.deleteCharAt(result.length()-1); // after appending delete the char from result
                }

                //reverse temp
                temp.reverse();

                //remove the closing string [
                result.deleteCharAt(result.length()-1);

                // get the number now
                int count = Character.getNumericValue(result.charAt(result.length()-1));

                // remove the number
                result.deleteCharAt(result.length()-1);

                //build the repeated string
                for(int k=0; k<count; k++) {
                    result.append(temp);
                }
            }
        }
        return result.toString();
    }
}
