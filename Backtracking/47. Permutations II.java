/*
Given a collection of numbers that might contain duplicates, return all possible unique permutations.

For example,
[1,1,2] have the following unique permutations:
[
  [1,1,2],
  [1,2,1],
  [2,1,1]
]
*/
class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
      // 记得要sort！！！
      Arrays.sort(nums);
      List<List<Integer>> result = new ArrayList<>();
      boolean[] used = new boolean[nums.length]; // all initialize to false
      helper(result, new ArrayList<>(), nums, used);
      return result;
    }

    public void helper(List<List<Integer>> result, ArrayList<Integer> tempList, int[] nums, boolean[] used){
      if(tempList.size()==nums.length){
        result.add(new ArrayList<>(tempList));
      }else{
        for(int i=0;i<nums.length;i++){
          if(used[i]==true) continue; // visit the element currently in this permutation tempList
          if(i>0&&nums[i]==nums[i-1]&&used[i-1]==false) continue;  // skip the Repeat element in nums
          tempList.add(nums[i]);
          used[i]=true;
          helper(result, tempList, nums, used);
          tempList.remove(tempList.size()-1);
          used[i] = false;
        }
      }
    }
}



class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
      Arrays.sort(nums);
      List<List<Integer>> res = new ArrayList<>();
      if(nums.length<1) return result;
      boolean[] used = new boolean[nums.length];
      helper(nums, used, res, new ArrayList<>());
      return res;
    }

    public void helper(int[] nums, boolean[] used, List<List<Integer>> res, ArrayList<Integer> cur){
      if(cur.size()==nums.length){
        res.add(new ArrayList<>(cur));
        return;
      }

      for(int i=0;i<nums.length;i++){
        if(used[i]) continue;
        cur.add(nums[i]);
        used[i]=true;
        helper(nums, used, res, cur);

        cur.remove(cur.size()-1);
        used[i]= false;
        while(i<nums.length&&nums[i+1]=nums[i]){
          i++;
        }
      }
    }
}
