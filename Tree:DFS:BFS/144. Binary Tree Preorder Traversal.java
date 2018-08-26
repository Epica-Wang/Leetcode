Given a binary tree, return the preorder traversal of its nodes values.

Example:

Input: [1,null,2,3]
   1
    \
     2
    /
   3

Output: [1,2,3]
Follow up: Recursive solution is trivial, could you do it iteratively?

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
/*iterative  用stack  注意先右后左*/
class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
      List<Integer> res = new LinkedList<>();
      if(root==null) return res;
      Stack<TreeNode> stack = new Stack<>();
      stack.push(root);
      while(!stack.isEmpty()){
        TreeNode cur = stack.pop();
        res.add(cur.val);
        if(cur.right!=null) stack.push(cur.right);
        if(cur.left!=null) stack.push(cur.left);
      }
      return res;
    }
}

/*recursive*/
class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
      List<Integer> res = new LinkedList<>();
      helper(root, res);
      return res;
    }
    public void helper(TreeNode root, List<Integer> res){
      if(root==null) return;
      res.add(root.val);
      helper(root.left,res);
      helper(root.right,res);
    }
}
