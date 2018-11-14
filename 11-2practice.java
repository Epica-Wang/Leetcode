binary tree in-order traversal
 /**
  * Definition for a binary tree node.
  * public class TreeNode {
  *     int val;
  *     TreeNode left;
  *     TreeNode right;
  *     TreeNode(int x) { val = x; }
  * }
  */
/*
step:
1. pop root
2. while (root.left != null)
  push root.left,
  root = root.left
3. now pop root.
  if root.right != null
  push root.right
  root = root.right
  go back to step 2
*/
 class Solution {
     public List<Integer> inorderTraversal(TreeNode root) {
       Stack<TreeNode> stack = new Stack<>();
       List<Integer> res = new LinkedList<>();
       if (root == null) return res;
       stack.push(root);
       while(!stack.isEmpty()){
         TreeNode cur = stack.pop();
         res.add(cur.val);
         while(cur.left != null) {
           stack.push(cur.left);
           cur = cur.left;
         }
         // now cur.left == null
         cur = stack.pop();
         res.add(cur.val);
         if(cur.right !=null){
           stack.push(cur.right);
           cur = cur.right;
           while(cur.left != null) {
             stack.push(cur.left);
             cur = cur.left;
           }
         }
       }
       return res;
     }
 }
