Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

Example:

Input: n = 4, k = 2
Output:
[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]
class Solution {
    public List<List<Integer>> combine(int n, int k) {
      List<List<Integer>> res = new ArrayList<>();
      helper(1,n,k,res,new ArrayList<>());
      return res;
    }

    public void helper(int start, int n, int remain, List<List<Integer>> res, ArrayList<Integer> cur){
      if(remain==0){
        res.add(new ArrayList<>(cur));
        return;
      }
      if(start>n){
        return;
      }
      for(int i=start;i<=n;i++){
        cur.add(i);
        helper(i+1, n, k-1, res,cur);
        cur.remove(cur.size()-1);
      }
    }
}
