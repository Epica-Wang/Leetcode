/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
/* my solution 2: upgrade solution1. use peek() instead of poll(), so that can get
node without remove it. So no need to use newLevelNode arraylist, also save time for
traversing newLevelNode */
class Solution{
  public List<List<Integer>> levelOrder(TreeNode root) {
    List<List<Integer>> allLevels = new ArrayList<List<Integer>>();
    Queue<TreeNode> queue = new LinkedList<>();
    if (root == null)
      return allLevels;
    queue.add(root);

    while(!queue.isEmpty()){
      int levelNum = queue.size();
      ArrayList<Integer> newLevelVal = new ArrayList<Integer>();
      for(int i=0;i<levelNum;i++){
        if(queue.peek().left!=null)
          queue.add(queue.peek().left);
        if(queue.peek().right!=null)
          queue.add(queue.peek().right);
        // Mission of this node complete:1. provide val 2. provide info of its children
        //remove this node from queue.
        newLevelVal.add(queue.poll().val);
      }
      // this level is finished.
      allLevels.add(newLevelVal);
    }
    return allLevels;
  }
}

/*recursive解法：

*/
class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        if(root==null) return res;
        preOrder(res, root, 0);
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

/* my 1st solu. queue is used to store nodes in current level. When travese queue,
deque node(i.e.poll()), store node in newLevelNode(temporary use),put each nodes.val in newLevelVal.
Last step traverse newLevelNode, enqueue left&right child of each node.
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> allLevels = new ArrayList<List<Integer>>();
        Queue<TreeNode> queue = new LinkedList<>();
        if (root == null)
          return allLevels;
        queue.add(root);

        while(!queue.isEmpty()){
          ArrayList<TreeNode> newLevelNode = new ArrayList<TreeNode>();
          ArrayList<Integer> newLevelVal = new ArrayList<Integer>();
          while(!queue.isEmpty()){
            TreeNode node = queue.poll();
            newLevelNode.add(node);
            newLevelVal.add(node.val);
          }
          allLevels.add(newLevelVal);
          for(TreeNode node:newLevelNode){
            if(node.left!=null)
              queue.add(node.left);
            if(node.right!=null)
              queue.add(node.right);
          }
        }
        return allLevels;
    }
}
*/
