Inorder Traversal:
/* iterative method
1) Create an empty stack S.
2) Initialize current node as root
3) Push the current node to S and set current = current->left until current is NULL
4) If current is NULL and stack is not empty then
     a) Pop the top item from stack.
     b) Print the popped item, set current = popped_item->right
     c) Go to step 3.
5) If current is NULL and stack is empty then we are done.
*/
class inorder{
  public void inorderIterative() {
		if (root == null) {
			return;
		}
		//keep the nodes in the path that are waiting to be visited
		Stack<Node> stack = new Stack<Node>();
		Node node = root;

		//first node to be visited will be the left one
		while (node != null) {
			stack.push(node);
			node = node.left;
		}

		// traverse the tree
		while (stack.size() > 0) {

			// visit the top node
			node = stack.pop();
			System.out.print(node.data + " ");
			if (node.right != null) {
				node = node.right;
				// the next node to be visited is the leftmost
				while (node != null) {
					stack.push(node);
					node = node.left;
				}
			}
		}
	}
}
  // Recursive Solution
	public void inOrderRecursive(TreeNode root) {
		if(root !=  null) {
			inOrder(root.left);
			//Visit the node by Printing the node data
			System.out.printf("%d ",root.data);
			inOrder(root.right);
		}
	}
}
////////////////////////////////////////////
class preorder{
  public void preOrderIterative() {
    Stack<TreeNode> nodes = new Stack<>();
    nodes.push(root);
    while (!nodes.isEmpty()) {
      TreeNode current = nodes.pop();
      System.out.printf("%s ", current.data);
      if (current.right != null) {  // right child push to stack first
        nodes.push(current.right);
      } if (current.left != null) {
        nodes.push(current.left);
      }
    }
  }
  public void preorder(TreeNode root) {
    if(root !=  null) {
   //Visit the node by Printing the node data
      System.out.printf("%d ",root.data);
      preorder(root.left);
      preorder(root.right);
    }
  }
}
////////////////////////////////////////////
class postorder{
  public List<Integer> postorderTraversal(TreeNode root) {
    List<Integer> res = new ArrayList<Integer>();
    if(root==null) {
        return res;
    }
    Stack<TreeNode> stack = new Stack<TreeNode>();
    stack.push(root);
    while(!stack.isEmpty()) {
        TreeNode temp = stack.peek();
        if(temp.left==null && temp.right==null) {
            TreeNode pop = stack.pop();
            res.add(pop.val);
        }
        else {  // remember to first push right child
            if(temp.right!=null) {
                stack.push(temp.right);
                temp.right = null;
            }
            if(temp.left!=null) {
                stack.push(temp.left);
                temp.left = null;
            }
        }
    }
    return res;
  }
  // Recursive Solution
	public void postOrder(TreeNode root) {
		if(root !=  null) {
			postOrder(root.left);
			postOrder(root.right);
			//Visit the node by Printing the node data
			System.out.printf("%d ",root.data);
		}
	}
}
