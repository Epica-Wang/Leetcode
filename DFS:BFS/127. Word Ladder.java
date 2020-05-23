Given two words (beginWord and endWord), and a dictionarys word list, find the length of shortest transformation sequence from beginWord to endWord, such that:

Only one letter can be changed at a time.
Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
Note:

Return 0 if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.
You may assume no duplicates in the word list.
You may assume beginWord and endWord are non-empty and are not the same.
Example 1:

Input:
beginWord = "hit",
endWord = "cog",
wordList = ["hot","dot","dog","lot","log","cog"]

Output: 5

Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
return its length 5.
Example 2:

Input:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log"]

Output: 0

Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.

/**
花花： https://www.youtube.com/watch?v=vWPCm69MSfs  讲了单向和bidirectional BFS
单向BFS：
1. 从beginword开始，每一位尝试修改成a~z，
    如果新word == endWord 返回此时step
    else 如果新word在wordList里， 加入queue
2. 从queue里pop出word，重复step1：
  即尝试修改每一位 看是否能生成endword， 如果不行看新的word是否在wordList里 加入queue
  把新word当做新的beginWord继续搜索

过程中要注意maintain一个set记录该词是不是已经被生成过了
如：hit -> hot -> dot, dot可以再生成hot但因为hot已经被生成过了 所以不会再重复访问hot
*/

// 进阶写法 思路一样 每生成一个新word就从wordList里把它删掉，因为之后如果再有别的word生成它，路径肯定更长 所以不用考虑了
class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = new HashSet<>(wordList);
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        int level = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int q=0; q < size; q++) {
                char[] cur = queue.poll().toCharArray();
                for (int i=0; i < cur.length; i++) {
                    char tmp = cur[i];
                    for (char chr='a'; chr <= 'z'; chr++) {
                        cur[i] = chr;
                        String dest = new String(cur);
                        if (dict.contains(dest)) {
                            if (dest.equals(endWord)) return level+1;
                            queue.add(dest);
                            dict.remove(dest);
                        }
                    }
                    cur[i] = tmp;
                }
            }
            level++;
        }
        return 0;
    }
}


// 最初写法，时间上有很多可以优化的地方
class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
      if (!wordList.contains(endWord)) return 0;

      //优化时间：把input存成hashset，之后contains操作能快一点，原来超时了
      Set<String> wordListSet = new HashSet<>(wordList);
      Set<String> reached = new HashSet<>();
      Queue<String> q = new LinkedList<>();

      int step = 1;
      reached.add(beginWord);
      q.add(beginWord);

      while (!q.isEmpty()) {
        int size = q.size();
        for (int num = 0; num < size; num++) {
          char[] cur = q.poll().toCharArray();
          for (int i = 0; i < cur.length; i++) {
            char tmp = cur[i];
            for (char ch = 'a'; ch <= 'z'; ch++) {
              cur[i] = ch;
              String transformed = new String(cur);
              if (transformed.equals(endWord)) return step + 1;
              if (wordListSet.contains(transformed) && !reached.contains(transformed)) {
                reached.add(transformed);
                q.add(transformed);
              }
            }
            // reset cur[i]
            cur[i] = tmp;
          }
        }
        step ++;
      }

      return 0;
    }
}
