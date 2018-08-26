/*
https://articles.leetcode.com/convert-binary-search-tree-bst-to/
In-place修改
node的left，right指针就是天然的prev，next指针
要注意的点就在于什么时候能修改left和right指针：要保证改完之后不会re-access其left节点或right节点了
当我们做in-order traversal的时候：
  1.访问到了某node时，可以修改node的left指针，指向prev node。因为node.left肯定在node前被访问过了，我们不会需要node.left了 所以可以修改
  2.与此同时可以修改prev的right指针，指向node。因为在prev被访问到的时候，如果prev.right！=null的话，prev.right也已经入栈了。我们也不会需要prev.right了

*/

/*iterative: stack解法 时间O(N) 空间O（logN）
pop的时候更新prev node和cur node的指针
额外用于遍历 LinkedList 还需要存下 head ; 在 prev 为 null 的时候 root 就代表着最左面的节点，设一下就好，之后就不用管了。
*/
public TreeNode convert(TreeNode root){
  if(root==null) return null;

  TreeNode prev = null;
  TreeNode head = null;

  Stack<TreeNode> stack = new Stack<>();
  TreeNode cur = root;
  while(cur!=null){
    stack.push(cur);
    cur = cur.left;
  }

  while(!stack.isEmpty()){
    TreeNode node = stack.pop();

    if(prev ==null){ //第一个pop的node  head指向此
      head = node;
    }else{
      prev.right = node;
      node.left = prev;
    }
    prev = node;
    cur = node.right;
    while(cur!=null){
      stack.push(cur);
      cur = cur.left;
    }
  }
  return head;
}

/*
recursive 解法：时间复杂度O(N)
While doing inorder traversal,
keep track of the previously visited node in a variable say prev.
For every visited node, make it next of prev and previous of this node as prev.
*/
private static class TreeNode{
  int val;
  TreeNode left, right;
  public TreeNode(int val){
    this.val = val;
  }
}

static TreeNode prev;
static TreeNode head;

public static void convert(TreeNode root){
  if(root==null) return;

  convert(root.left);

  if(prev == null){
    head = root;
  }else{
    root.left = prev;
    prev.right = root;
  }

  prev = root;
  convert(root.right);
}
