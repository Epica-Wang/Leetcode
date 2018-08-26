Given a binary tree, return the bottom-up level order traversal of its nodes values.
(ie, from left to right, level by level from leaf to root).

For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its bottom-up level order traversal as:
[
  [15,7],
  [9,20],
  [3]
]
//相比102 level order, 此题reverse一下就行

/*
Recursive解法：BFS。 相当于pre-order traverse. 需要记着每个node的level
*/

class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        if(root==null) return res;
        preOrder(res, root, 0);
        Collections.reverse(res);
        return res;
    }

    public void preOrder(List<List<Integer>> res, TreeNode root, int level){
      if(root==null) return;
      if(level>res.size()-1){  //注意这儿index是size-1
        res.add(new LinkedList<>());
      }

      res.get(level).add(root.val);

      preOrder(res,root.left,level+1);
      preOrder(res, root.right, level+1);
    }
}
