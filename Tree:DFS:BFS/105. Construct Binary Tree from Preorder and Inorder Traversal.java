Given preorder and inorder traversal of a tree, construct the binary tree.

Note:
You may assume that duplicates do not exist in the tree.

For example, given

preorder = [3,9,20,15,7]
inorder = [9,3,15,20,7]
Return the following binary tree:

    3
   / \
  9  20
    /  \
   15   7
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
        [1]
      /     \
    [5]      [4]
    /   \       \
  [2]   [3]      [6]
                  /
                [7]
In-order: 【(2,5,3) 1 (4,7,6)】
Pre-order: 【1 (5,2,3) (4,6,7)】
Post-order: 【(2,3,5) (7,6,4) 1】
In-order 和 pre-order 单独都只能提供一部分树的信息，只依靠一个无法建立出完全一样的树，因为有歧义。
  1.In-order : 对于指定位置 index 的 root
  对于每个 tree / subtree, array 结构
  【左子树】【root】【右子树】
  2.Pre-order:
  对于每个 tree / subtree, array 结构
  【root】【左子树】【右子树】
  3.Post-order:
  对于每个 tree / subtree, array 结构
  【左子树】【右子树】【root】
递归建树的过程是
建当前 root;
建 左/右 子树；
建 右/左 子树；
因此根据 inorder 和 preorder 的性质，我们用 preorder 的顺序决定“先建哪个为 root”，用 inorder 的相对位置决定 “左右子树是谁”。
因此这个问题是关于 preorder 的遍历，对于每个元素要在 inorder 中寻找相对位置。
对于任意指定子树，在 inorder / preorder / postorder 中都是长度一样的连续 subarray，只是位置不同。
*/

/*
具体步骤:按顺序为preorder中的node建左子树和右子树
1. 当前为preorder中的某root建树
2. 在In中找到该root的位置i。In中【start，i-1】为root的左子树的inorder，【i+1,end】为root的右子树的inorder
3. 在pre中找到root左右子树的preorder：pre中从该node位置+1开始，往后连续数【i-1-start+1】这么多是左子树的长度，之后的是右子树preorder
4. 前三步已分别找到了root的左右子树的inorder和preorder  递归传递inorder preorder 建左右子树
*/
 class Solution {
     public TreeNode buildTree(int[] preorder, int[] inorder) {
       return helper(preorder,0,inorder,0,inorder.length-1);
     }

     public TreeNode helper(int[] preorder, int preIndex, int[] inorder, int inStart, int inEnd){
       if(preIndex>=preorder.length || inStart>inEnd){
         return null;
       }
       int rootVal = preorder[preIndex];
       TreeNode root = new TreeNode(rootVal);
       int pos = inStart;
       //找该root在inorder中的位置
       for(int i=inStart;i<=inEnd;i++){
         if(inorder[i]==rootVal){
           pos = i;
           break;
         }
       }
       int leftSubTreeLength = pos-inStart;
       root.left = helper(preorer, preIndex+1, inorder, inStart, pos-1);
       //注意root.right的preIndex位置哟
       root.right = helper(preorder,preIndex+1+leftSubTreeLength, inorder, pos+1, inEnd);
       return root;
     }
 }
