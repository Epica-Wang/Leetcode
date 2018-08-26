import java.util.*;
/*
topological sort: determine if there is a cycle.
numCourses: number of vertices
each prerequisite: a directed edge
ArrayList<ArrayList<Integer>> adj: adj[i]: directed edge(i,j1),(i,j2).... adj[i] can be none: a sole vertex
Array indegree: indegree[v] = an int.
Queue zeroIndegree:
*/
class CourseSchedule{
    public static boolean canFinish(int numCourses, int[][] prerequisites) {
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

      if(topoOrder.size()!=numCourses) return false;
      return true;
    }

    public static void main(String[] args){
      int numCourses = 3;
      int[][] prerequisites = new int[][]{{1,0}};
      boolean result = canFinish(numCourses, prerequisites);
      System.out.println(result);
    }

}
