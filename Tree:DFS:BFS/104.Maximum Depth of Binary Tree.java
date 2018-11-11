/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public int maxDepth(TreeNode root) {
        if(root == null) return 0;
        return Math.max(maxDepth(root.left),maxDepth(root.right))+1;
    }
}

/* same idea with iterative of 111.min depth of binary tree
iterative method: dfs, using two stacks, one for nodes, another for their depth
everytime pop update curDepth & maxDepth
*/

class Solution {
    public int maxDepth(TreeNode root) {
        Stack<TreeNode> nodeStack = new Stack<>();
        Stack<Integer> depthStack = new Stack<>();
        int curDepth = 0;
        int maxDepth = 0;
        while (root != null || !stack.isEmpty()) {
          while (root != null) {
            curDepth +=1;
            nodeStack.push(root);
            depthStack.push(curDepth);
            root = root.left;
          }

          root = nodeStack.pop();
          curDepth = depthStack.pop();
          maxDepth = Math.max(maxDepth, curDepth);
          root = root.right;
        }
        return maxDepth;
    }
}

/* iterative method: bfs + two queues
*/

class Solution {
  public int maxDepth(TreeNode root) {
    Queue<TreeNode> nodeQ = new LinkedList<>();
    Queue<Integer> depthQ = new LinkedList<>();
    if (root == null) return 0;
    int maxDepth = 0;
    nodeQ.add(root);
    depthQ.add(1);

    while (!nodeQ.isEmpty()) {
      root = nodeQ.poll();
      int curDepth = depthQ.poll();
      maxDepth = Math.max(curDepth, maxDepth);
      if (root.left != null) {
        nodeQ.add(root.left);
        depthQ.add(curDepth + 1);
      }
      if (root.right != null) {
        nodeQ.add(root.right);
        depthQ.add(curDepth + 1);
      }
    }

    return maxDepth;
  }
}
