On a 2D plane, we place stones at some integer coordinate points.  Each coordinate point may have at most one stone.

Now, a move consists of removing a stone that shares a column or row with another stone on the grid.

What is the largest possible number of moves we can make?

Example 1:
Input: stones = [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
Output: 5

Example 2:
Input: stones = [[0,0],[0,2],[1,1],[2,0],[2,2]]
Output: 3

Example 3:
Input: stones = [[0,0]]
Output: 0

Note:
1 <= stones.length <= 1000
0 <= stones[i][j] < 10000

class Solution {
    // key is stone[0] + " " + stone[1]
    private HashMap<String, String> parent = new HashMap<>();
    private int count;
    public int removeStones(int[][] stones) {
      count = stones.length;
      for (int[] stone: stones) {
        parent.put(stone[0] + " " + stone[1], stone[0] + " " + stone[1]);
      }
      for (int[] stone : stones) {
        for (int[] stone2 : stones) {
          if (stone[0] == stone2[0] || stone[1] == stone2[1]) {
            union(stone[0] + " " + stone[1], stone2[1] + " " + stone2[1]);
          }
        }
      }
      return count;

    }

    public void union(String s1, String s2) {
      String p1 = find(s1);
      String p2 = find(s2);
      if (!p1.equals(p2)) {
        parent.put(p2, p1);
        count--;
      }
    }

    public String find(String s) {
      String p = parent.get(s);
      while (!s.equals(p)) {
        parent.put(s, find(p));
      }
      return parent.get(s);
    }
}
