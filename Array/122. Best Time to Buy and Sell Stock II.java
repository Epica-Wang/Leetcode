Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times).

Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).

Example 1:

Input: [7,1,5,3,6,4]
Output: 7
Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
             Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
Example 2:

Input: [1,2,3,4,5]
Output: 4
Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
             Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are
             engaging multiple transactions at the same time. You must sell before buying again.
Example 3:

Input: [7,6,4,3,1]
Output: 0
Explanation: In this case, no transaction is done, i.e. max profit = 0.

// Solution 1: Peak Valley https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/solution/
class Solution {
    public int maxProfit(int[] prices) {
        if (prices.length == 0) return 0;
        int i = 0;
        int valley = prices[0];
        int peak = prices[0];
        int maxprofit = 0;
        while (i < prices.length - 1) {
          // 先找谷valley 再找峰peak确保了peak一定出现在valley后面
            while (i < prices.length - 1 && prices[i] >= prices[i + 1])
                i++;
            valley = prices[i];
            while (i < prices.length - 1 && prices[i] <= prices[i + 1])
                i++;
            peak = prices[i];
            maxprofit += peak - valley;
        }
        return maxprofit;
    }
}

// Solution 2: Simple one pass. solution 1变形
// 画出折线图来看，最终赚钱的一定是上升的段
public class Solution {
    public int maxProfit(int[] prices) {
        int maxProfit = 0;
        if(prices.length<=1) return 0;
        for(int i=0; i<prices.length-1;i++){
            if(prices[i+1]>prices[i]){
                maxProfit+= prices[i+1]-prices[i];
            }
        }
        return maxProfit;
    }
}
