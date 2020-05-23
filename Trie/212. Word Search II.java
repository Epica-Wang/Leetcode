Given a 2D board and a list of words from the dictionary, find all words in the board.

Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.

Example:

Input:
words = ["oath","pea","eat","rain"] and board =
[
  ['o','a','a','n'],
  ['e','t','a','e'],
  ['i','h','k','r'],
  ['i','f','l','v']
]

Output: ["eat","oath"]
Note:
You may assume that all inputs are consist of lowercase letters a-z.

/*
如果用79 word search的做法: 对每个word，试图在matrix里找 if it exists.
而用dfs + Trie:
  对所有words建立一个trie tree
  用matrix的char构建可能的string，查找这个string是否在trie中
  有点反过来的意思
*/

/*
注意坑爹test cases：
Input:
[["a","a"]]
["a"]
Output:
["a","a"]
Expected:
["a"]
-------------
Input:
[["a","a"]]
["a", "a"]
Expected:
["a"]      找到重复的也只返回一个  result最后用hashset存一下
*/
import java.util.*;

public class Solution {
  public class Trie {
    public class TrieNode {
      private char val;
      private TrieNode[] children;
      private boolean isWord =  false;
      public TrieNode(char val) {
        this.val = val;
        children = new TrieNode[26];
      }
    }
    private TrieNode root;
    public Trie() {
      this.root = new TrieNode(' ');
    }
    public void insert(String word) {
      char[] charArr = word.toCharArray();
      TrieNode cur = root;
      for (char c: charArr) {
      	if (cur.children[c - 'a'] == null) {
        	cur.children[c - 'a'] = new TrieNode(c);
        }
        cur = cur.children[c - 'a'];
      }
      cur.isWord = true;
    }

    public boolean search(String word){
      char[] charArr = word.toCharArray();
      TrieNode cur = root;
      for (char c: charArr) {
        if (cur.children[c - 'a'] == null){
          return false;
        } else {
          cur = cur.children[c - 'a'];
        }
      }
      return cur.isWord;
    }
  }

  public List<String> findWords(char[][] board, String[] words) {
    Trie root = new Trie();
    for (String word: words) {
      root.insert(word);
    }
    List<String> res = new LinkedList<>();
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[0].length; j++) {
        dfs(board, i, j, "", new boolean[board.length][board[0].length], root, res);
      }
    }
    return res;
  }

  public void dfs(char[][] board, int i, int j, String str, boolean[][] visited, Trie root, List<String> res) {
    if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || visited[i][j]) return;

    str += board[i][j];
    if (root.search(str)) {
      res.add(str);
    }
    visited[i][j] = true;
    dfs(board, i-1, j, str, visited, root, res);
    dfs(board, i+1, j, str, visited, root, res);
    dfs(board, i, j-1, str, visited, root, res);
    dfs(board, i, j+1, str, visited, root, res);
    visited[i][j] = false;
  }

  // public static void main(String[] args) {
  // 	Solution so = new Solution();
  // 	String[] words = new String[]{"oath", "eat"};
  // 	char[][] board = new char[][]{{'o','a','a','n'},{'e','t','a','e'},{'i','h','k','r'},{'i','f','l','v'}};
  // 	List<String> res = so.findWords(board, words);
  // 	System.out.println(res);
  // }
}
