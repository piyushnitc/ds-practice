package practice.arrays.singleloop;

/**
 * Input: X[] = [16, 17, 4, 3, 5, 2]
 * Output: [17, 5, 2]
 *
 * Explanation: Element 2 is the rightmost element, so it is a leader by default.
 * 17 and 5 are also leader elements because they are greater than all the elements on their right side.
 *
 * Input: X[] = [6, 5, 4, 3, 2, 1]
 * Output: [6, 5, 4, 3, 2, 1]
 *
 * Input: X[] = [1, 2, 3, 4, 5, 6]
 * Output: [6]
 */
public class LeadersInArray {

    public static void main(String args[]) {
        int num1[] = {16, 17, 4, 3, 5, 2};
        int num2[] = {6, 5, 4, 3, 2, 1};
        int num3[] = {1, 2, 3, 4, 5, 6};

        bruteForceLeadersInArray(num1);
        bruteForceLeadersInArray(num2);
        bruteForceLeadersInArray(num3);

        leadersInArray(num1);
        leadersInArray(num2);
        leadersInArray(num3);
    }

    /**
     * brute force approach - start from first and compare with every element
     * if condition fails - quit and increment the count
     * T.C = O(n*n)
     * @param nums
     * @return
     */
    public static void bruteForceLeadersInArray(int[] nums) {
        System.out.println();
        for(int i=0; i<nums.length; i++) {
            boolean leader = true;
            int firstElement = nums[i];
            for(int j= i+1; j<nums.length; j++) {
                if(firstElement < nums[j]) {
                    leader = false;
                    break;
                } else {
                    continue;
                }
            }
            if(leader) {
                System.out.print(firstElement+",");
            }
        }
    }

    /**
     * scan from right
     * last one is always a leader
     * track maxFromRight
     * @param nums
     */
    public static void leadersInArray(int[] nums) {
        System.out.println();
        int arrLength = nums.length;
        int maxFromRight = nums[arrLength-1];
        System.out.print(maxFromRight);
        for(int i = arrLength-2; i>=0; i--) {
            if(nums[i] > maxFromRight) {
                System.out.print(","+nums[i]);
                maxFromRight = nums[i];
            }
        }
    }
}
