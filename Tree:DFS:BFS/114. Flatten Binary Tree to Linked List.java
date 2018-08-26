Given a binary tree, flatten it to a linked list in-place.

For example, given the following tree:

    1
   / \
  2   5
 / \   \
3   4   6
The flattened tree should look like:

1
 \
  2
   \
    3
     \
      4
       \
        5
         \
          6

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
iterative solution:
对root，寻找root左子树中能沿着右边走的最长的节点node. 把root的右子树连在这个node上（即左子树里最右的节点上）
让node.right = root.right。
再把root的左子树连到root的右边。root.right = root.left，root.left = null;
root = root.right  继续上述步骤。直到reach 最后一个点
*/
class Solution {
    public void flatten(TreeNode root) {
      TreeNode cur = root;
      while(cur!=null){
        TreeNode left = cur.left;
        if(left==null){
          cur = cur.right;
        }else{
          TreeNode prev = left;
          while(prev.right!=null){
            prev = prev.right;
          }
          //prev is the rightmost in left subtree of current
          prev.right = cur.right;
          cur.right = cur.left;
          cur.left = null;
          cur = cur.right;
        }
      }
    }
}
 /*recursive solution:
很符合递归思想的写法，美中不足是每次都要把左子树遍历一遍好去找尾端节点，如果整个树左子树体积非常大而右子树很小的话，时间复杂度会很高。最差的情况是，整个树是一个只有左边的链表，时间复杂度可以达到 O(n^2)，而且用递归还要花费栈空间。、
 */
class Solution {
    public void flatten(TreeNode root) {
      if(root==null) return;
      flatten(root.left);
      flatten(root.right);

      /*找到root.left subtree里最右端的点node.进行修改指针的操作
      将root原本的right变成node的right
      root的left变成root right
      最后将root left指向null
      */
      /*
     注意当root本身就是叶子结点时 没有left sub里最右端一说 不用修改指针了
     直接return
      */
      TreeNode node = root.left;
      if(node==null){
        return;
      }else{
        while(node.right!=null){
          node = node.right;
        }
        node.right = root.right;
        root.right = root.left;
        root.left = null;
      }
    }
}
