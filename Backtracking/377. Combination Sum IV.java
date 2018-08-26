Given an integer array with all positive numbers and no duplicates, find the number of possible combinations that add up to a positive integer target.

Example:

nums = [1, 2, 3]
target = 4

The possible combination ways are:
(1, 1, 1, 1)
(1, 1, 2)
(1, 2, 1)
(1, 3)
(2, 1, 1)
(2, 2)
(3, 1)

Note that different sequences are counted as different combinations.

Therefore the output is 7.
Follow up:
What if negative numbers are allowed in the given array?
How does it change the problem?
What limitation we need to add to the question to allow negative numbers?

Credits:
Special thanks to @pbrother for adding this problem and creating all test cases.


class Solution {
    public int combinationSum4(int[] nums, int target) {
      List<List<Integer>> res = new ArrayList<>();
      if(nums.length<1) return 0;
      helper(nums, target,res, new ArrayList<>());
      return res.size();
    }

    public void helper(int[] nums, int target, List<List<Integer>> res, ArrayList<Integer> cur){
      if(target==0){
        res.add(new ArrayList<>(cur));
        return;
      }
      if(target<0){
        return;
      }

      for(int i=0;i<nums.length;i++){
        cur.add(nums[i]);
        helper(nums, target-nums[i],res,cur);
        cur.remove(cur.size()-1);
      }
    }
}
