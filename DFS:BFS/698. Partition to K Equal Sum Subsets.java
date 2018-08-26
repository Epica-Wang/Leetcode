Given an array of integers nums and a positive integer k, find whether it's possible to divide this array into k non-empty subsets whose sums are all equal.

Example 1:
Input: nums = [4, 3, 2, 3, 5, 2, 1], k = 4
Output: True
Explanation: It's possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3) with equal sums.
Note:

1 <= k <= len(nums) <= 16.
0 < nums[i] < 10000.
/*
Assume sum is the sum of nums[] .
The dfs process is to find a subset of nums[] which sum equals to sum/k.
We use an array visited[] to record which element in nums[] is used.
Each time when we get a cur_sum = sum/k, we will start from position 0 in nums[] to
look up the elements that are not used yet and find another cur_sum = sum/k.
*/
/*
正确dfs：helper method应该return boolean 而不是void。因为需要根据dfs搜索后的情况返回来修改当前元素的使用情况
而不像number of island类型的题，dfs 返回值是void，因为不需要根据dfs搜索后的情况返回修改当前元素的使用情况
当前current subset sum合法之后要return helper（）是不是true才行啊
即改正下面错误的写法：某元素可以放入某subset 当且仅当 该subset的sum合法了，且剩下的元素可以组成k-1个subset。

写前要明确该dfs recursively解决的是什么subproblem。自然就能明确function的返回值是什么了
*/
class Solution {
    public boolean canPartitionKSubsets(int[] nums, int k) {
      boolean[] visited = new boolean[nums.length];
      int sum=0;

      for(int i=0;i<nums.length;i++){
        sum+=nums[i];
      }
      if(sum%k!=0) return false;
      return dfs(nums,visited,0,0,k,sum/k);
    }
    public boolean dfs(int[] nums, boolean[] visited, int curSum,int start_index,int k, int target){
      if(k==0) return true;
      if(curSum==target) return dfs(nums, visited, 0, 0, k-1, target);
      for(int i=start_index;i<nums.length;i++){
        if(visited[i]==false && curSum+nums[i]<=target){
          visited[i]=true;
          if(dfs(nums, visited, curSum+nums[i], i+1, k, target)){
            //下一个dfs直接从i+1开始找起，因为在current dfs里 i已经是第一个满足没有被visit的,且加上nums[i]之后<target的了
            //i之前的要么visited=true，可以直接丢掉；要么visited=false 但curSum+nums[j]>target。下一个dfs curSum已经多加了个nums[i] 更大了，所以这种也可以丢掉
            return true;
          }else{
            visited[i]= false;
          }
        }
      }
      return false;
    }
    /* for 里从0开始找超时了。。。 得加个start index
    public boolean dfs(int[] nums, boolean[] visited, int curSum, int k, int target){
      if(k==0) return true;
      if(curSum==target) return dfs(nums, visited, 0, k-1, target);
      for(int i=0;i<nums.length;i++){
        if(visited[i]==false && curSum+nums[i]<=target){
          visited[i]= true;
          if(dfs(nums,visited,curSum+nums[i],k,target)){
            return true;
          }else{
            visited[i]=false;
          }
        }
      }
      return false;
    }
    */
}

/*
一种错误dfs解法：元素按顺序放入subset。
可能某元素放入某subset之后，该subset的sum合法了，就不再考虑这个元素放入其他subset的可能性。
错误点：有可能该元素放入某subset后，即使该subset的sum合法，但剩下的元素没法组成其他valid subset了。
test case:[4,3,2,3,5,2,1], k=4的时候是对的。应该分成(4,1), (5), (2,3), (2,3)
但换一下input顺序：[1,2,2,3,3,4,5] k=4就不对。
按照本解法分组找到的第一个合法subset(1,2,2), 但这样后面剩(3,3,4,5)不可能组成其他subset了
*/
class Solution {
    private int count;
    private int curSum=0;
    public boolean canPartitionKSubsets(int[] nums, int k) {
      count = k;
      boolean[] visited = new boolean[nums.length];
      int sum=0;

      for(int i=0;i<nums.length;i++){
        sum+=nums[i];
      }
      if(sum%k!=0) return false;
      //Arrays.sort(nums);
      dfs(sum/k,nums, visited);
      return count==0;
    }

    public void dfs(int target,int[]nums, boolean[] visited){
      if(curSum==target){
        count -=1;
        System.out.format("count is %d %n",count);
        curSum =0;
        return;
      }
      int remainCount = count;
      for(int i=0;i<nums.length;i++){
        if(i==nums.length-1 && visited[i]==true) return;
        if(visited[i]==false && curSum+nums[i]>target){
          return;
        }
        if(visited[i]==false&& (curSum+nums[i]<=target)){
          visited[i]=true;
          curSum+=nums[i];
          System.out.format("try use %d,curSum is %d, count is %d, %n",nums[i],curSum,count);
          dfs(target,nums,visited);
          if(remainCount==count){
            visited[i]=false;
            curSum-=nums[i];
            System.out.format("CANNOT use %d,curSum is %d, count is %d, %n",nums[i],curSum,count);
          }
        }
      }
    }
}
