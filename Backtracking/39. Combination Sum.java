/*
Given a set of candidate numbers (C) (without duplicates) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.

The same repeated number may be chosen from C unlimited number of times.

Note:
All numbers (including target) will be positive integers.
The solution set must not contain duplicate combinations.
For example, given candidate set [2, 3, 6, 7] and target 7,
A solution set is:
[
  [7],
  [2, 2, 3]
]
*/

class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
      List<List<Integer>> res = new ArrayList<>();
      if(candidates.length<1) return res;
      helper(0, target, candidates, res, new ArrayList<>());
      return res;
    }

    public void helper(int start, int target, int[] candidates, List<List<Integer>> res, ArrayList<Integer> cur){
      if(target==0){
        res.add(new ArrayList<>(cur));
        return;
      }
      if(target<0){
        return;
      }

      for(int i=start;i<candidates.length;i++){
        cur.add(candidates[i]);
        helper(i, target-candidates[i], candidates, res, cur);
        cur.remove(cur.size()-1);
      }
    }
}



/////////写法2

class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
      Arrays.sort(candidates);
      List<List<Integer>> result = new ArrayList<>();
      helper(result, new ArrayList<>(), candidates, target, candidates[0]);
      return result;
    }

    public void helper(List<List<Integer>> result, ArrayList<Integer> tempList, int[] candidates, int target){
      if(tempList!=null && target==0){   // need to check if tempList is not null. because may have initial target =0
        result.add(new ArrayList<>(tempList));
      }
      for(int i=0;i<candidates.length;i++){
        if(target<0) break;  //减枝 target已小于0就不继续尝试后面的数了
        if(tempList.size()>0 && (candidates[i]<tempList.get(tempList.size()-1)))
          continue;
        // 能写成candidates[i]<tempList.get(tempList.size()-1) 基于已经sort了array
        //这步写tempList!=null会报错？ tempList!=null和tempList.size()=0 区别？
        tempList.add(candidates[i]);
        helper(result, tempList, candidates, target-candidates[i]);
        tempList.remove(tempList.size()-1);
      }//for
    }
}
