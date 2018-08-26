public class Solution {
    public int findCircleNum(int[][] M) {
        int[] visited = new int[M.length];
        int count = 0;
        for (int i = 0; i < M.length; i++) {
            if (visited[i] == 0) {
                dfs(M, visited, i);
                count++;
            }
        }
        return count;
    }

    public void dfs(int[][] M, int[] visited, int i) {
        for (int j = 0; j < M.length; j++) {
            if (M[i][j] == 1 && visited[j] == 0) {
                visited[j] = 1;
                dfs(M, visited, j);
            }
        }
    }
}
//07.12.2018 dfs版本重写练习
class Solution {
    public int findCircleNum(int[][] M) {
        boolean[] visited = new boolean[M.length];
        int row = M.length;
        if(row==0) return 0;
        int count = 0;
        for(int i=0;i<row;i++){
            if(visited[i]==false){
                count++;
                dfs(M,visited,i);
            }
        }
        return count;
    }
    //dfs : dfs visit person i
    // boolean[] 防止重复visit
    public void dfs(int[][] M, boolean[] visited, int i){
        if(visited[i]) return;
        visited[i]=true;
        for(int j=0;j<M.length;j++){
            if(M[i][j]==1){
                dfs(M, visited,j);
            }
        }
    }
}
/*错误做法：直接用num of islands. 没法检测出indirect friend  只能判断direct friends
考虑例子：
  1 0 1 0
  0 1 1 1
  1 1 1 0
  0 1 0 1
其实四个人属于一个friend group   但用num of island count是3
*/
/*
class Solution {
    public int findCircleNum(int[][] M) {
        int n = M.length;
		int count = 0;
	    for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
                if(M[i][j]==1){
                    count++;
                    dfs(i,j,M);
                }
            }
        }//2-for
        return count;
    }

	public void dfs(int i, int j, int[][] M){
        if(i<0||j<0||i>=M.length||j>=M.length||M[i][j]==0){
            return;
        }
        M[i][j]=0;
        dfs(i-1,j,M);
        dfs(i+1,j,M);
        dfs(i,j+1,M);
        dfs(i,j-1,M);
    }
}
*/
