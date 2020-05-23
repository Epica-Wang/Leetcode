/*
Given a collection of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.

Each number in C may only be used once in the combination.

Note:
All numbers (including target) will be positive integers.
The solution set must not contain duplicate combinations.
For example, given candidate set [10, 1, 2, 7, 6, 1, 5] and target 8,
A solution set is:
[
  [1, 7],
  [1, 2, 5],
  [2, 6],
  [1, 1, 6]
]
全positive，有duplicates，每个元素只能用一次
*/

class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
      Arrays.sort(candidates);
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
        if(i>start&&candidates[i]==candidates[i-1]){
          continue;
        }
        cur.add(candidates[i]);
        helper(i+1, target-candidates[i], candidates, res, cur);
        cur.remove(cur.size()-1);
      }
    }
}





class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
      Arrays.sort(candidates);
      List<List<Integer>> result = new ArrayList<>();
      //boolean[] used = new boolean[candidates.length];

      helper(result, new ArrayList<>(), candidates, target, 0);
      return result;
    }

    public void helper(List<List<Integer>> result, ArrayList<Integer> tempList, int[] candidates, int target, int start){
      if(target==0 && tempList.size()!=0){
        result.add(new ArrayList<>(tempList));
      }else if(target<0){
        return;
      }else{
        for(int i=start; i<candidates.length;i++){
          if(i>start && candidates[i]==candidates[i-1]) continue;
          tempList.add(candidates[i]);
          helper(result, tempList, candidates, target-candidates[i], i+1);
          tempList.remove(tempList.size()-1);
        }
      }
    }
}
