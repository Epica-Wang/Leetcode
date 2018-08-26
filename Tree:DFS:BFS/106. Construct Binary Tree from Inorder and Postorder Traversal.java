/*
对比105 给了preorder
对于postorder 最后一位是root。以postorder里的node倒序为root建左右子树
通过inorder定位root ->定位root的左子树 右子树
在postorder里确定左子树 右子树的root位置  递归
*/
class Solution {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
      return helper(inorder,0,inorder.length-1, postorder,postorder.length-1);
    }
    public TreeNode helper(int[] inorder, int inStart, int inEnd, int[] postorder, int postIndex){
      if(postIndex<0 || inStart>inEnd) return null;

      int rootVal = postorder[postIndex];
      int pos = inStart;
      for(int i=inStart;i<=inEnd;i++){
        if(rootVal==inorder[i]){
          pos = i;
          break;
        }
      }
      TreeNode root = new TreeNode(rootVal);
      root.right = helper(inorder, pos+1, inEnd, postorder, postIndex-1);
      int rightSubTreeLength = inEnd-(pos+1)+1;
      root.left = helper(inorder, inStart, pos-1, postorder, postIndex-1-rightSubTreeLength);
      return root;
    }
}
