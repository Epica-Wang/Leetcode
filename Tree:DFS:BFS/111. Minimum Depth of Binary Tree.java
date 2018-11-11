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
 Given a binary tree, find its minimum depth.
The minimum depth is the number of nodes along the shortest path
from the root node down to the nearest leaf node.
*/

/* Recursive method:
找最小要比找最大复杂，因为递归对于最大来说如果一个节点只有左子树或右子树，它无需额外考虑，
仍可以一时同仁对左右子树调用递归，只不过对null的子树返回的是0，
而在比较取较大者时会自动放弃较小的0值。但对于最小来说，比较的时候是取较小者，
不能对null的子树返回0，否则取较小的时候会取该子树，显然这样不符合要求，只有叶子节点才可以返回0。
*/
class Solution {
    public int minDepth(TreeNode root) {
        if(root==null) return 0;
        //root.left==null
        else if(root.left==null) return minDepth(root.right)+1;
        else if(root.right ==null) return minDepth(root.left)+1;
        else{ // root的左右都不空
            int left = minDepth(root.left)+1;
            int right = minDepth(root.right)+1;
            return left<=right? left:right;
      }
    }
}
/*随手复习写*/
class Solution {
    public int minDepth(TreeNode root) {
      if(root==null) return 0;
      if(root.left!=null && root.right!=null){
        return 1+Math.min(minDepth(root.left),minDepth(root.right));
      }else if(root.left!=null){
        return 1+minDepth(root.left);
      }else if(root.right!=null){
        return 1+minDepth(root.right);
      }else{ //reach a leaf
        return 1;
      }
    }
}

/* Iterative: use two queues. bfs
*/
class Solution {
    public int minDepth(TreeNode root) {
        if(root == null)
            return 0;
        //is an empty tree
        if(root.left == null && root.right == null)
            return 1;
        Queue<TreeNode> nQ = new LinkedList<TreeNode>();  //bfs store node
        Queue<Integer> dQ = new LinkedList<Integer>();  // bfs store node's depth. update with nQ

        nQ.offer(root);
        dQ.offer(1);

        while(!nQ.isEmpty()){
            TreeNode curNode = nQ.poll();
            int curDepth = dQ.poll();  // depth dequeue along with its node

            if(curNode.left == null && curNode.right == null)
                return curDepth;
            if(curNode.left != null){
                nQ.offer(curNode.left);
                dQ.offer(curDepth+1);
            }
            if(curNode.right != null){
                nQ.offer(curNode.right);
                dQ.offer(curDepth+1);
            }
        }
        return -1;
      }
}
