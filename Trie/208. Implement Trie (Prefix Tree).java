Implement a trie with insert, search, and startsWith methods.

Example:

Trie trie = new Trie();

trie.insert("apple");
trie.search("apple");   // returns true
trie.search("app");     // returns false
trie.startsWith("app"); // returns true
trie.insert("app");
trie.search("app");     // returns true
Note:

You may assume that all inputs are consist of lowercase letters a-z.
All inputs are guaranteed to be non-empty strings.
/*
只有lowercase letter 的版本。 每个node的children用 size=26的array存就行
如果不仅限于lowercase letter，每个node的children用hashmap存:
class TrieNode{
  public E val;
  public boolean isWord;
  public HashMap<E, TrieNode> childrenMap;

  public TrieNode(E val){
    this.val = val;
    childrenMap = new HashMap<>();
  }
}
*/

class Trie {
  class TrieNode{
      public char val;
      public boolean isWord;
      public TrieNode[] children;

      public TrieNode(char c){
        this.val = c;
        children = new TrieNode[26];
      }
    }

    private TrieNode root;

    public Trie() {
      root = new TrieNode(' ');
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
      char[] charArray = word.toCharArray();
      TrieNode start = root;
      for(int i=0;i<charArray.length;i++){
        char c = charArray[i];
        if(start.children[c-'a']==null){
          start.children[c-'a']= new TrieNode(c);
        }
        start = start.children[c-'a'];
      }
      start.isWord = true;  //不要忘了哦！！
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
      char[] charArray = word.toCharArray();
      TrieNode start = root;
      for(int i=0;i<charArray.length;i++){
        char c = charArray[i];
        if(start.children[c-'a']==null){
          return false;
        }else{
          start = start.children[c-'a'];
        }
      }
      return start.isWord;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
      char[] charArray = prefix.toCharArray();
      TrieNode start = root;
      for(int i=0;i<charArray.length;i++){
        char c = charArray[i];
        if(start.children[c-'a']==null){
          return false;
        }else{
          start = start.children[c-'a'];
        }
      }
      return true;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
