/* Given a collection of distinct numbers, return all possible permutations.
Example:

Input: [1,2,3]
Output:
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]
无duplicate
*/
class Solution {
    public List<List<Integer>> permute(int[] nums) {
      List<List<Integer>> result = new ArrayList<>();
      helper(result, new ArrayList<>(), nums);
      return result;
    }

    public void helper(List<List<Integer>> result, ArrayList<Integer> tempList, int[] nums){
      if(tempList.size()==nums.length){
        result.add(new ArrayList<>(tempList));
      }else{
        for(int i=0;i<nums.length;i++){
          if(tempList.contains(nums[i])) continue;
          tempList.add(nums[i]);
          helper(result, tempList, nums);
          tempList.remove(tempList.size()-1);
        }
      }
    }
}



class Solution {
    public List<List<Integer>> permute(int[] nums) {
      List<List<Integer>> res = new ArrayList<>();
      if(nums.length<1) return res;
      helper(nums, res, new ArrayList<>());
      return res;
    }

    public void helper(int[] nums, List<List<Integer>> res, ArrayList<Integer> cur){
      if(cur.size()==nums.length){
        res.add(new ArrayList<>(cur));
        return;
      }

      for(int i=0;i<nums.length;i++){
        if(cur.contains(nums[i])){
          continue;
        }
        cur.add(nums[i]);
        helper(nums, res, cur);
        cur.remove(cur.size()-1);
      }
    }
}
