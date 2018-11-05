/*
Given a set of distinct integers, nums, return all possible subsets (the power set).

Note: The solution set must not contain duplicate subsets.

For example,
If nums = [1,2,3], a solution is:

[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]
*/
/*
                                    []
                                /   |    \
                              [1]  [2]   [3]
                              /     |
                            [1 2]  [2 3]
                            /
                          [1 2 3]
 */
class Solution {
    public List<List<Integer>> subsets(int[] nums){
      List<List<Integer>> result = new ArrayList<>();
      helper(result, new ArrayList<>(), nums, 0);
      return result;
    }
    // when index=0, finish all sets begin with nums[0]
    //and then backtracking remove all unitl nums[0].
    // then to find all sets begin with with nums[1]
    public void helper(List<List<Integer>> result, List<Integer> tempList, int[] nums, int index){
      if(start==nums.length) return;

      result.add(new ArrayList<Integer>(tempList));  // add first then go to for

      for(int i = index;i<nums.length;i++){
        tempList.add(nums[i]);
        helper(result, tempList, nums,i+1);
        tempList.remove(tempList.size()-1);
      }
    }
}


class Solution {
    public List<List<Integer>> subsets(int[] nums) {
      List<List<Integer>> res = new ArrayList<>();
      if(nums.length<1) return res;
      res.add(new ArrayList<>());
      helper(0, nums, res, new ArrayList<>());
      return res;
    }

    public void helper(int start, int[] nums, List<List<Integer>> res, ArrayList<Integer> cur){
      if(start==nums.length) return;
      for(int i=start;i<nums.length;i++){
        cur.add(nums[i]);
        res.add(new ArrayList<>(cur));
        System.out.format("start is %d, cur.size is %d, last is %d %n",i,cur.size(),cur.get(cur.size()-1));
        for(Integer ele:cur){
            System.out.println(ele);
        }
        helper(i+1, nums, res, cur);
        cur.remove(cur.size()-1);
      }
    }
}
