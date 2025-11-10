package practice.trackUpdateReset;

/**
 * You are given an integer array prices where prices[i] is the price of a given stock on the ith day.
 * On each day, you may decide to buy and/or sell the stock. You can only hold at most one share of the stock at any time.
 * However, you can buy it then immediately sell it on the same day.
 *
 * Find and return the maximum profit you can achieve.
 *
 * Input: prices = [7,1,5,3,6,4]
 * Output: 7
 * Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
 * Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
 * Total profit is 4 + 3 = 7.
 *
 * Input: prices = [1,2,3,4,5]
 * Output: 4
 * Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
 * Total profit is 4.
 *
 * Input: prices = [7,6,4,3,1]
 * Output: 0
 * Explanation: There is no way to make a positive profit, so we never buy the stock to achieve the maximum profit of 0.
 */
public class BuyAndSellStock2 {
    public static void main(String args[]) {
        int[] x1 = {7,1,5,3,6,4};   //7
        int[] x2 = {3,7,5,3,4,6};   //7
        int[] x3 = {1,2,3,4,5};     //4
        int[] x4 = {7,6,4,3,1};     //0
    }

    /**
     * Observations
     * 1. since we can buy and sell on the sam eday --keep buying and seeling as long as the profit is positive.
     * sum all the profits.
     *
     * 2. How to use dynamic programming?
     * 3. How to use DP with Space optimization
     *
     * 4. Recursive solution
     * 5. Recursive with Memoization
     */

    public static int greedyApproach(int[] x) {
        int n = x.length;
        int profit = 0;

        for(int i=1; i<n; i++) {
            if(x[i] - x[i-1] > 0) {
                profit = profit + (x[i] - x[i-1]);
            }
        }
        return profit;
    }

}
