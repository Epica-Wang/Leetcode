// https://www.geeksforgeeks.org/shortest-distance-between-two-nodes-in-bst/
We start from root and for every node, we do following.

1. If both keys are greater than current node, we move to right child of current node.
2. If both keys are smaller than current node, we move to left child of current node.
3. If one keys is smaller and other key is greater, current node is
Lowest Common Ancestor (LCA) of two nodes.
We find distances of current node from two keys and return sum of the distances.


// This function returns distance of x from
// root. This function assumes that x exists
// in BST and BST is not NULL.
int distanceFromRoot(struct Node* root, int x)
{
    if (root->key == x)
        return 0;
    else if (root->key > x)
        return 1 + distanceFromRoot(root->left, x);
    return 1 + distanceFromRoot(root->right, x);
}

// Returns minimum distance beween a and b.
// This function assumes that a and b exist
// in BST.
int distanceBetween2(struct Node* root, int a, int b)
{
    if (!root)
        return 0;

    // Both keys lie in left
    if (root->key > a && root->key > b)
        return distanceBetween2(root->left, a, b);

    // Both keys lie in right
    if (root->key < a && root->key < b) // same path
        return distanceBetween2(root->right, a, b);

    // Lie in opposite directions (Root is
    // LCA of two nodes)
    if (root->key >= a && root->key <= b)
        return distanceFromRoot(root, a) +
               distanceFromRoot(root, b);
}

// This function make sure that a is smaller
// than b before making a call to findDistWrapper()
int findDistWrapper(Node *root, int a, int b)
{
   if (a > b)
     swap(a, b);
   return distanceBetween2(root, a, b);
} 
