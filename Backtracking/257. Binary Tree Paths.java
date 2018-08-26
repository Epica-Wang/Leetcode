/*Given a binary tree, return all root-to-leaf paths.

For example, given the following binary tree:

   1
 /   \
2     3
 \
  5
All root-to-leaf paths are:

["1->2->5", "1->3"] */

//Solution 1: BFS - Queue
public class Solution {
  public List<String> binaryTreePaths(TreeNode root) {
      List<String> list=new ArrayList<String>();
      Queue<TreeNode> qNode=new LinkedList<TreeNode>();
      Queue<String> qStr=new LinkedList<String>();

      if (root==null) return list;
      qNode.add(root);
      qStr.add("");
      while(!qNode.isEmpty()) {
          TreeNode curNode=qNode.remove();
          String curStr=qStr.remove();

          if (curNode.left==null && curNode.right==null) list.add(curStr+curNode.val);
          if (curNode.left!=null) {
              qNode.add(curNode.left);
              qStr.add(curStr+curNode.val+"->");
          }
          if (curNode.right!=null) {
              qNode.add(curNode.right);
              qStr.add(curStr+curNode.val+"->");
          }
      }
      return list;
}

//Solution2: DFS - Stack
public class Solution {
  public List<String> binaryTreePaths(TreeNode root) {
      List<String> list=new ArrayList<String>();
      Stack<TreeNode> sNode=new Stack<TreeNode>();
      Stack<String> sStr=new Stack<String>();

      if(root==null) return list;
      sNode.push(root);
      sStr.push("");
      while(!sNode.isEmpty()) {
          TreeNode curNode=sNode.pop();
          String curStr=sStr.pop();

          if(curNode.left==null && curNode.right==null) list.add(curStr+curNode.val);
          if(curNode.left!=null) {
              sNode.push(curNode.left);
              sStr.push(curStr+curNode.val+"->");
          }
          if(curNode.right!=null) {
              sNode.push(curNode.right);
              sStr.push(curStr+curNode.val+"->");
          }
      }
      return list;
}

/* Solution 3: use StringBuilder*/
public class Solution {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> rst = new ArrayList<String>();
        if(root == null) return rst;
        StringBuilder sb = new StringBuilder();  //use 1 StringBuilder for all recursion
        helper(rst, sb, root);
        return rst;
    }

    public void helper(List<String> rst, StringBuilder sb, TreeNode root){
        if(root == null) return;
        //first get path length before this current node
        int tmp = sb.length();
        if(root.left == null && root.right == null){ // leaf node
            sb.append(root.val);
            rst.add(sb.toString());
            sb.delete(tmp , sb.length());
            return;
        }
        // current node has child(s)
        sb.append(root.val + "->");
        helper(rst, sb, root.left);
        helper(rst, sb, root.right);
        // we have done for this current node, should delete this node and all its following childrens from sb
        sb.delete(tmp , sb.length());
        return;
    }
}

/*Solution 4: string manipulation. string creates a copy in every recursion! costly
public List<String> binaryTreePaths(TreeNode root) {
    List<String> answer = new ArrayList<String>();
    if (root != null) searchBT(root, "", answer);
    return answer;
}
private void searchBT(TreeNode root, String path, List<String> answer) {
    if (root.left == null && root.right == null) answer.add(path + root.val);
    if (root.left != null) searchBT(root.left, path + root.val + "->", answer);
    if (root.right != null) searchBT(root.right, path + root.val + "->", answer);
}
*/

/* backtracking练习*/
class Solution {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new LinkedList<>();
        if(root==null) return res;
        helper(root, res, new StringBuilder());
        return res;
    }

    public void helper(TreeNode root, List<String> res, StringBuilder cur){
      int temp = cur.length();
      //需要存一下刚传进来时cur的length，因为后面append的val可能是负数 负号也算一个char的长度
      //所以后面delete的时候无法确定删多少个
      if(root.left==null && root.right==null){
        cur.append(root.val);
        res.add(cur.toString());
        cur.delete(temp,cur.length());
        return;
      }
      cur.append(root.val);
      cur.append("->");
      if(root.left!=null){
        helper(root.left,res,cur);
      }
      if(root.right!=null){
        helper(root.right,res,cur);
      }

      cur.delete(temp,cur.length());
    }
}
