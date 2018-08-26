/*
Given a collection of integers that might contain duplicates, nums, return all possible subsets (the power set).

Note: The solution set must not contain duplicate subsets.

For example,
If nums = [1,2,2], a solution is:

[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
]
*/
class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
      List<List<Integer>> result = new ArrayList<>();
      Arrays.sort(nums);
      helper(result, new ArrayList<>(), nums,0);
      return result;
    }

    public void helper(List<List<Integer>> result, ArrayList<Integer> tempList, int[] nums, int start){
      result.add(new ArrayList<>(tempList));
      for(int i=start; i< nums.length;i++){
        if(i> start && nums[i]==nums[i-1]) continue;
        tempList.add(nums[i]);
        helper(result, tempList, nums, i+1);
        tempList.remove(tempList.size()-1);
      }
    }
}
