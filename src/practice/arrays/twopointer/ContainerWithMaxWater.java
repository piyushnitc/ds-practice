package practice.arrays.twopointer;

/**
 * Given an array of n non-negative integers height [n], where each value represents a point at coordinate
 * (i, height[i]). Now n vertical lines are drawn such that the two endpoints of line i are at (i, 0)
 * and (i, height[i]). Here each pair of a line with the x-axis forms a container.
 *
 * We need to maximize the area formed between the vertical lines using the shorter line as height and the distance
 * between the lines as the width i.e Area = max [(j - i) * min (height[i], height[j])].
 *
 * Input: height[] = [1, 5, 6, 3, 4, 2], Output: 12
 * Explanation: The area between lines 5 and 4 will be maximum. 5 and 4 are distance 3 apart, so the
 * size of the base = 3. Height of container = min(5, 4) = 4, So total area = 3 * 4 = 12
 */
public class ContainerWithMaxWater {

    public static void main(String args[]) {
        int[] h1 = {1, 5, 6, 3, 4, 2};
        System.out.println(bruteForce(h1));
        System.out.println(twoPointer(h1));

    }

    // Obs - calculate area for every pair and track the max
    //T.C = O(n) *O(n) = O(n^2)
    public static int bruteForce(int[] x) {
        int maxArea = 0;
        int n = x.length;
        for(int i=0; i<n; i++) {
            for(int j=i+1; j<n; j++) {
                maxArea = Math.max(Math.min(x[i], x[j]) * (j-1), maxArea);
            }
        }
        return maxArea;
    }

    //Obs - {1, 5, 6, 3, 4, 2} - if x[i] <x[j] -> this means this is the max area for x[i]
    // if x[i] > x[j] -> then we need to keep comparing the area till we find x[i] < x[j]
    // what happens if there are duplicates??
    public static int twoPointer(int[] x) {
        int maxArea = 0, left =0, right = x.length-1;

        while(left < right) {
            if(x[left] < x[right]) {
                maxArea = Math.max(maxArea, x[left]* (right-left));
                left++;
            } else {
                maxArea = Math.max(maxArea, x[right]* (right-left));
                right --;
            }
        }
        return maxArea;
    }
}
