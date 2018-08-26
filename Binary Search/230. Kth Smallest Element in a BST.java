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
 this solution is from lc discussion.
 The best performance is we just have to count the nodes for once (i.e. kth is root),which is O(n);
 the worse/average case when we need count nodes for each subtree traversal,
 binary search is always log(n), and number of traversed subtrees could be n,
 then as total is O(nlog(n)).
 */
class Solution{
  public int kthSmallest(TreeNode root, int k) {
        int count = countNodes(root.left);
        // count is used to store # of nodes in left subtree of current root. i.e # of nodes smaller than current root.

        if (k <= count) {
            return kthSmallest(root.left, k);
        } else if (k > count + 1) {
            return kthSmallest(root.right, k-1-count); // 1 is counted as current node
        }

        // k = count +1 . then the current root is the kth smallest.
        return root.val;
    }

    public int countNodes(TreeNode n) {
        if (n == null) return 0;
        return 1 + countNodes(n.left) + countNodes(n.right);
    }
}


/* my second solution, similar to first solution, but only store 1 number.
time: theta(n)
class Solution {
  static int count = 0;
  static int number = 0;
  public int kthSmallest(TreeNode root, int k){
    inorderTreeWalk(root, k);
    return number;
  }
  private void inorderTreeWalk(TreeNode root, int k){
    if(root!=null){
      inorderTreeWalk(root.left,k);
      if(count<k)
        count +=1;
      else
        number = root.val;
      inorderTreeWalk(root.right,k);
    }
  }
}
  */

  /*
   //My first Solution: DFS traverse binary tree in order, store all elements in arraylist, at last return kthSmallest. Time: theta(n)
class Solution{
    public int kthSmallest(TreeNode root, int k) {
      ArrayList<Integer> sortedNodes = new ArrayList<Integer>();
      inorderTreeWalk(root,sortedNodes);
      return sortedNodes.get(k-1);
    }
    public void inorderTreeWalk(TreeNode root,ArrayList sortedNodes){
      if(root!=null){
        inorderTreeWalk(root.left,sortedNodes);
        sortedNodes.add(root);
        inorderTreeWalk(root.right,sortedNodes);
      }
  }

*/
