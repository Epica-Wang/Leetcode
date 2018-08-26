There are N students in a class. Some of them are friends, while some are not. Their friendship is transitive in nature. For example, if A is a direct friend of B, and B is a direct friend of C, then A is an indirect friend of C. And we defined a friend circle is a group of students who are direct or indirect friends.

Given a N*N matrix M representing the friend relationship between students in the class. If M[i][j] = 1, then the ith and jth students are direct friends with each other, otherwise not. And you have to output the total number of friend circles among all the students.

Example 1:
Input:
[[1,1,0],
 [1,1,0],
 [0,0,1]]
Output: 2
Explanation:The 0th and 1st students are direct friends, so they are in a friend circle.
The 2nd student himself is in a friend circle. So return 2.
Example 2:
Input:
[[1,1,0],
 [1,1,1],
 [0,1,1]]
Output: 1
Explanation:The 0th and 1st students are direct friends, the 1st and 2nd students are direct friends,
so the 0th and 2nd students are indirect friends. All of them are in the same friend circle, so return 1.


class Solution {
  class UnionFind{
    private int[] parents;
    private int[] ranks;
    private int count;

    public UnionFind(int n){
      count = n;
      parents = new int[n];
      ranks = new int[n];
      for(int i=0;i<parents.length;i++){
        parents[i] = i;
        ranks[i]=1;
      }
    }

    public int find(int x){
      if(x!=parents[x]){
        parents[x] = find(parents[x]);
      }
      return parents[x];
    }

    public void union(int x,int y){
      int px =  find(x);
      int py = find(y);
      if(px == py){
        //return false;
        return;
      }
      if(ranks[px]>ranks[py]){
        parents[py] = px;
      }else if(ranks[py]>ranks[px]){
        parents[px] = py;
      }else{
        parents[py] = px;
        ranks[px]++;
      }
      count--;
      //return true;
    }

    public int count(){
      return count;
    }
  }
    public int findCircleNum(int[][] M) {
      int n = M.length;
      UnionFind uf = new UnionFind(n);
      for(int i=0;i<n;i++){
        for(int j=i+1;j<n;j++){
          if(M[i][j]==1){
            uf.union(i,j);
          }
        }
      }
      return uf.count();

      //或者再扫一遍 看看find有多少不同的cluster id
      // HashSet<Integer> set = new HashSet<>();
      // for(int i=0;i<M.length;i++){
      //   set.add(find(i));
      // }
      // return set.size();
    }
}
