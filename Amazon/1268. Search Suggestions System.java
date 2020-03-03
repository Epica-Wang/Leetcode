/*

We first need to sort the products array, then construct the trie tree to store the products string array. 
In this case, each node of the Trie tree will also store 
at most 3 search suggestion results besides regular node value (char) and childern.

When constrcuting the Trie tree for any product string, we iterate through each char, if it's a new char, we construct a new node,
and store current whole product string in the suggestion result list of this node if the suggestion list has length <=3. 
(Since we already sort the product array and construct the Trie according to the sorted product order, we ensure that the first
3 product added to the suggestion list of the node in Trie are the first 3 lexicographically products should be returned)

After the Trie tree get constructed, given any search word, we just iterate through each char to see if there is a node representing 
that char in the Trie tree, if it appears, append the 3 product suggestion of that node to the result list. If there is no node
in the Trie representing the char, which means no products match current input search word, we should append an empty list to the result.
*/

class Solution {
    class Node {
        char val;
        Node[] children;
        List<String> suggestion;
        public Node(char val) {
            this.val = val;
            this.children = new Node[26];
            this.suggestion = new ArrayList<>();
        }
    }
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        Arrays.sort(products); 
        Node root = new Node(' ');
        for (String product : products) {
            add(root, product);
        }
        List<List<String>> ans = new ArrayList<>();
        for (char c : searchWord.toCharArray()) { 
            if (root != null) {
                root = root.children[c - 'a'];
            }
            ans.add(root == null ? new ArrayList<>() : root.suggestion);
        }
        return ans;
    }
    
    public void add(Node root, String product) {
        Node node = root;
        for (char c : product.toCharArray()) { 
            if (node.children[c - 'a'] == null) {
                node.children[c - 'a'] = new Node(c);
            }
            node = node.children[c - 'a'];
            if (node.suggestion.size() < 3) {
                node.suggestion.add(product);
            }
        }
    }
}
