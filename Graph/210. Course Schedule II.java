class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
      ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
      // initialize
      for(int i=0;i<numCourses;i++){
        adj.add(new ArrayList<Integer>());
      }
      // add edge
      for(int i=0;i<prerequisites.length;i++){
        int[] edge = prerequisites[i];
        int u =  edge[1];
        int v = edge[0];
        adj.get(u).add(v);
      }

      // construct indegree array
      int[] indegree = new int[numCourses];
      for(int i=0;i<numCourses;i++){
        ArrayList<Integer> temp = adj.get(i);
        for(int v: temp){
          indegree[v]++;
        }
      }

      // construct zeroIndegree queue
      Queue<Integer> zeroIndegree = new LinkedList<>();
      for(int i=0; i<indegree.length;i++){
        if(indegree[i]==0){
          zeroIndegree.add(i);
        }
      }

      // print topological order
      ArrayList<Integer> topoOrder = new ArrayList<>();
      while(!zeroIndegree.isEmpty()){
        int u = zeroIndegree.poll();
        for(int v: adj.get(u)){
          if(--indegree[v]==0){
            zeroIndegree.add(v);
          }
        }
        topoOrder.add(u);
      }

      if(topoOrder.size()!=numCourses) return new int[]{};
      int[] ans = new int[topoOrder.size()];
      for(int i=0;i<topoOrder.size();i++){
          ans[i] = topoOrder.get(i);
      }
      return ans;
    }
}
