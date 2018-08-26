/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

/* Solution 1: iteration
basic idea is to traverse bst in order. Use stack to implement.
every time pop up a node, compare its value with previous one
*/
class Solution {
    public boolean isValidBST(TreeNode root) {
      if(root==null) return true;

      Stack<TreeNode> stack = new Stack<>();
      TreeNode pre = null;
      while(root!=null || !stack.isEmpty()){
        while(root!=null){
          stack.push(root);
          root = root.left;
        }

        TreeNode cur = stack.pop();
        if(pre!=null && pre.val>=cur.val) return false;
        pre = cur;
        root = cur.right;
    }
    return true;
  }
}

/* Solution 2: Basically what I am doing is recursively iterating over the tree while defining interval <minVal, maxVal> for each node which value must fit in.*/

public class Solution {
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean isValidBST(TreeNode root, long minVal, long maxVal) {
        if (root == null) return true;
        if (root.val >= maxVal || root.val <= minVal) return false;
        return isValidBST(root.left, minVal, root.val) && isValidBST(root.right, root.val, maxVal);
    }
}
/*recursive 练习*/
class Solution {
    public boolean isValidBST(TreeNode root) {
      long max = Long.MAX_VALUE; //改成long 防止node val=Integer.max or IntegerMin
      long min = Long.MIN_VALUE;
      return helper(root,min,max);
    }

    public boolean helper(TreeNode root, long min, long max){
      if(root==null) return true;
      if(root.val<max && root.val>min){
        long left_max = root.val;
        long left_min = min;
        long right_max = max;
        long right_min = root.val;
        return helper(root.left, left_min,left_max) && helper(root.right, right_min,right_max);
      }else{
        return false;
      }
    }
}



/*inoder traversal*/
class Solution {
    public boolean isValidBST(TreeNode root) {

    }
}



/* Wrong solution, max and min range is not correct
class Solution {
    public boolean isValidBST(TreeNode root) {
      if(root==null) return true;
      if((root.left!=null && root.val<=root.left.val)||(root.right!=null && root.val>=root.right.val))
        return false;
      return isValidBSTHelperLeft(root.left,root.val)&&isValidBSTHelperRight(root.right,root.val);
    }

    public boolean isValidBSTHelperLeft(TreeNode root, int max){
      if(root==null) return true;
      if((root.left!=null && root.val<=root.left.val)||(root.right!=null && root.val>=root.right.val))
        return false;
      if((root.left!=null&&root.left.val>=max)||(root.right!=null&&root.right.val>=max))
        return false;
      return isValidBSTHelperLeft(root.left,root.val)&&isValidBSTHelperRight(root.right,root.val);
    }

    public boolean isValidBSTHelperRight(TreeNode root, int min){
      if(root==null) return true;
      if((root.left!=null && root.val<=root.left.val)||(root.right!=null && root.val>=root.right.val))
        return false;
      if((root.left!=null&&root.left.val<=min)||(root.right!=null&&root.right.val<=min))
        return false;
      return isValidBSTHelperLeft(root.left,root.val)&&isValidBSTHelperRight(root.right,root.val);
    }
}
*/
