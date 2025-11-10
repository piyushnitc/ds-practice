package practice.arrays.prefixarray;

/**
 * Given an integer array nums, handle multiple queries of the following type:
 * Calculate the sum of the elements of nums between indices left and right inclusive where left <= right.
 *
 * ["NumArray", "sumRange", "sumRange", "sumRange"]
 * [[[-2, 0, 3, -5, 2, -1]], [0, 2], [2, 5], [0, 5]]
 * Output
 * [null, 1, -1, -3]
 */
public class RangeSumQuery {
    public int[] prefixSum;

    public RangeSumQuery(int[] nums) {
        int[] prefixSum = getPrefixSum(nums);
        this.prefixSum = prefixSum;
    }

    private int[] getPrefixSum(int[] nums) {
        int[] prefixSum = new int[nums.length];
        prefixSum[0] = nums[0];
        for(int i=1; i<nums.length; i++) {
            prefixSum[i] = prefixSum[i-1]+nums[i];
        }
        return prefixSum;
    }

    public int sumRange(int i, int j) {
        if(i==0) {
            return prefixSum[j];
        }

        return prefixSum[j] - prefixSum[i - 1];

    }

    public static void main(String args[]) {
        int[] nums = {-2, 0, 3, -5, 2, -1};
        System.out.println(sumRangeBruteForce(nums,0,2));
        System.out.println(sumRangeBruteForce(nums,2,5));
        System.out.println(sumRangeBruteForce(nums,0,5));

        RangeSumQuery rs = new RangeSumQuery(nums);
        System.out.println(rs.sumRange(0,2));
        System.out.println(rs.sumRange(2,5));
        System.out.println(rs.sumRange(0,5));

    }

    //T.C = O(n)
    public static int sumRangeBruteForce(int[] nums, int i, int j) {
        int sum = 0;
        for(int k=i; k<=j; k++) {
            sum = sum+nums[k];
        }
        return sum;
    }
}
