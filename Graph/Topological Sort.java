/*  https://www.geeksforgeeks.org/topological-sorting-indegree-based-solution/
*/
Topological Sorting for a graph is not possible if the graph is not a DAG.
A DAG G has at least one vertex with in-degree 0 and one vertex with out-degree 0.
Algorithm:
Steps involved in finding the topological ordering of a DAG:

Step-1: Compute in-degree (number of incoming edges) for each of the vertex present in the DAG and initialize the count of visited nodes as 0.

Step-2: Pick all the vertices with in-degree as 0 and add them into a queue (Enqueue operation)

Step-3: Remove a vertex from the queue (Dequeue operation) and then.

Increment count of visited nodes by 1.
Decrease in-degree by 1 for all its neighboring nodes.
If in-degree of a neighboring nodes is reduced to zero, then add it to the queue.
Step 5: Repeat Step 3 until the queue is empty.

Step 5: If count of visited nodes is not equal to the number of nodes in the graph then the topological sort is not possible for the given graph.
//////////////////////////////////////////////////////////////////////////////

class Graph{
  int V; // number of vertices
  // An array of List which contains references to the Adjacency List
  // of each vertex
  ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();;
  public Graph(int V){
    this.V = V;
    for(int i=0; i<V;i++){
      adj.add(new ArrayList<Integer>());
    }
  }
  public void addEdge(int u, int v){
    adj.get(u).add(v);
  }
  // print topo sort of complete graph
  public void topologicalSort(){
    // An array to store indegrees of all vertices.
    // Initialize all indegre to 0.
    int indegree[] = new int[V];

    // Traverse Adjacency List to fill indegrees of
    // vertices. This takes O(V+E) times.
    for(int i=0;i<V;i++){
      ArrayList<Integer> temp = adj[i];
      for(int node:temp){
        indegree[node]++;
      }
    }

    // Maintain a queue to store vertex with indegree 0
    Queue<Integer> zeroIndegree = new LinkedList<Integer>();
    for(int i=0;i<V;i++){
      if(indegree[i]==0){
        zeroIndegree.add(i);
      }
    }

    // Initialize count of visited vertices
    int count = 0;
    // store result
    ArrayList<Integer> resultOrder = new ArrayList<Integer>();
    while(!zeroIndegree.isEmpty()){
      int u = q.poll();
      resultOrder.add(u);

      // Iterate through all u's neighbor, decrease their indegree by 1
      for(int node:adj.get(u)){
        if(--indegree[node]==0){  // first decrease then compare to0
          zeroIndegree.add(node);
        }
      }

      count++; // finish visit u
    }

    if(count!=V){
      System.out.println("There exists a cycle in this graph. No Topological sort");
      return ;
    }

    for(int i: resultOrder){
      System.out.print(i+" ");
    }
  }
}
