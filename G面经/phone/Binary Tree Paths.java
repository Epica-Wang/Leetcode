/*
return a list of all paths of binary tree
*/

/*
backtracking:dfs
*/
class Solution {
  public List<List<Integer>> binaryTreePaths (TreeNode root) {
    List<List<Integer>> res = new ArrayList<>();
    helper(root, res, new ArrayList<Integer>());
    return res;
  }

  public void helper(TreeNode root, List<List<Integer>> res, List<Integer> temp) {
    if (root == null) return;
    if (root.left == null && root.right == null) {
      temp.add(root.val);
      res.add(new ArrayList<>(temp));
      temp.remove(temp.size()-1);
      return;
    }
    temp.add(root.val);
    helper(root.left, res, temp);
    helper(root.right, res, temp);
    temp.remove(temp.size()-1);
  }
}

/*
recursive
*/
class Solution {
  public List<List<Integer>> binaryTreePaths (TreeNode root) {
  // return all path under this root
    if (root == null) return null;
    if (root.left == null && root.right == null) {
      List<Integer> middle = new ArrayList<Integer>();
      middle.add(root.val);
      List<List<Integer>> res = new ArrayList<List<Integer>>();
      res.add(middle);
      return res;
    }
    List<List<Integer>> resLeft = binaryTreePaths(root.left);
    List<List<Integer>> resRight = binaryTreePaths(root.right);
    List<List<Integer>> res = new ArrayList<>();
    if (resLeft != null) {
      for (List<Integer> left: resLeft) {
        left.add(0, root.val);
        res.add(left);
      }
    }
    if (resRight != null) {
      for (List<Integer> right: resRight) {
        right.add(0, root.val);
        res.add(right);
      }
    }
    return res;
  }
}

/*
find path with longest path
*/
class Soluiton {
  public List<Integer> binaryTreePaths (TreeNode root) {
    if (root == null) return null;
    List<Integer> resLeft = binaryTreePaths(root.left);
    List<Integer> resRight = binaryTreePaths(root.right);
    List<Integer> res = new ArrayList<>();
    if (resLeft == null && resRight == null) {
      res.add(root.val);
      return res;
    }else if (resLeft == null) {
      resRight.add(0, root.val);
      return resRight;
    }else if (resRight == null) {
      resLeft.add(0, root.val);
      return resLeft;
    }else {
      res = resLeft.size() >= resRight.size()? resLeft : resRight;
      res.add(0, root.val);
      return res;
    }
  }
}
