package practice.trackUpdateReset;

/**
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.
 * You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future
 * to sell that stock.
 *
 * Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.
 *
 * Input: prices = [7,1,5,3,6,4]
 * Output: 5
 * Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
 * Note that buying on day 2 and selling on day 1 is not allowed because you must buy before you sell.
 *
 * Input: prices = [7,6,4,3,1]
 * Output: 0
 * Explanation: In this case, no transactions are done and the max profit = 0.
 */
public class BuyAndSellStock {
    public static void main(String args[]) {
        int[] x1 = {7,1,5,3,6,4};   // 5 (1, 6)
        int[] x2 = {7,6,4,3,1};     // 0
        System.out.println("Brute Force: " + maxProfitBruteForce(x1)); // Expected: 5
        System.out.println("Brute Force: " + maxProfitBruteForce(x2)); // Expected: 0
        System.out.println("Two Pointer: " + maxProfitTwoPointer(x1)); // Expected: 5
        System.out.println("Two Pointer: " + maxProfitTwoPointer(x2)); // Expected: 0

        System.out.println(bruteForce(x1));
        System.out.println(bruteForce(x2));

        System.out.println(twoPointerApproach(x1));
        System.out.println(twoPointerApproach(x2));
    }

    // Brute force approach: O(n^2)
    public static int maxProfitBruteForce(int[] prices) {
        int maxProfit = 0;
        int n = prices.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                int profit = prices[j] - prices[i];
                if (profit > maxProfit) {
                    maxProfit = profit;
                }
            }
        }
        return maxProfit;
    }

    /**
     * Two pointer (greedy/one-pass) approach: O(n)
     * Track the minimum price so far and compute the max profit at each step.
     */
    public static int maxProfitTwoPointer(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
        for (int price : prices) {
            if (price < minPrice) {
                minPrice = price;
            } else if (price - minPrice > maxProfit) {
                maxProfit = price - minPrice;
            }
        }
        return maxProfit;
    }

    /**
     * 1. brute force approach --> compare every pair -- O(n*n)
     *
     * 2. observation - max(A[j] - A[i]) where j > i
     * two pointer approach. track max diff.. This is a greedy approach and can be done in one pass
     *
     * 3. Dynamic programming -- not required for this problem
     */


    public static int bruteForce(int[] x) {
        int n = x.length;
        int maxProfit = 0;
        for(int i=0; i<n; i++) {        // T.C = O(n)
            for(int j=i; j<n; j++) {    // T.C = O(n)
                int profit = x[j] - x[i];
                if(profit > maxProfit) {
                    maxProfit = profit;
                }
            }
        }
        return maxProfit;   // T.C = O(n*n)
    }

    //int[] x2 = {7,6,4,3,1};
    public static int twoPointerApproach(int[] x) {
        int n = x.length;
        int maxProfit = 0;
        int i= n-1, j = 0;

        while(i >=0 && j <n) {
            int profit = x[i] - x[j];
            if(profit > 0) {
                maxProfit = Math.max(maxProfit, profit);
                i--;
            } else {
                j++;
            }
        }
        return maxProfit;
    }
}
