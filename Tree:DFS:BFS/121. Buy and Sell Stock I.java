// only need to update min.
/* current min 后面比它大的elements都不会影响在current min
买入的事实，只会造成maxProfit的改变。 直到遇见下一个比current min
小的value， 另它为currentMin 考虑在它之后的maxProfit*/
class Solution{
  public int maxProfit(int[] prices){
    if(prices.length<=1) return 0;
    int maxProfit = 0;
    int curMin = prices[0]; // assume buy on first
    for(int i=0;i<prices.length;i++){
      if(curMin>prices[i]){
        curMin = prices[i];
      }
      if(maxProfit<prices[i]-curMin){
        maxProfit = prices[i]-curMin;
      }
    }
    return maxProfit;
  }
}

//简洁写法：
class Solution{
  public int maxProfit(int[] prices){
    if(prices.length<=1) return 0;
    int maxProfit = 0;
    int curMin = prices[0]; // assume buy on first
    for(int i=0;i<prices.length;i++){
      curMin = Math.min(curMin,prices[i]);
      maxProfit = Math.max(maxProfit,prices[i]-curMin);
    }
    return maxProfit;
  }
}
