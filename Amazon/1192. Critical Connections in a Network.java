/*
https://www.geeksforgeeks.org/articulation-points-or-cut-vertices-in-a-graph/
A O(V+E) algorithm to find all Articulation Points (APs)
The idea is to use DFS (Depth First Search). In DFS, we follow vertices in tree form called DFS tree. In DFS tree, a vertex u is parent of another vertex v, if v is discovered by u (obviously v is an adjacent of u in graph). In DFS tree, a vertex u is articulation point if one of the following two conditions is true.
1) u is root of DFS tree and it has at least two children.
2) u is not root of DFS tree and it has a child v such that no vertex in subtree rooted with v has a back edge to one of the ancestors (in DFS tree) of u.

We do DFS traversal of given graph with additional code to find out Articulation Points (APs). In DFS traversal, we maintain a parent[] array where parent[u] stores parent of vertex u. Among the above mentioned two cases, the first case is simple to detect. For every vertex, count children. If currently visited vertex u is root (parent[u] is NIL) and has more than two children, print it.
How to handle second case? The second case is trickier. We maintain an array disc[] to store discovery time of vertices. For every node u, we need to find out the earliest visited vertex (the vertex with minimum discovery time) that can be reached from subtree rooted with u. So we maintain an additional array low[] which is defined as follows.

low[u] = min(disc[u], disc[w]) 
where w is an ancestor of u and there is a back edge from 
some descendant of u to w.
*/

class Solution {
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        Map<Integer, List<Integer>> graph = new HashMap();
        for(List<Integer> edge : connections)
        {
            graph.putIfAbsent(edge.get(0), new ArrayList());
            graph.putIfAbsent(edge.get(1), new ArrayList());
            graph.get(edge.get(0)).add(edge.get(1));
            graph.get(edge.get(1)).add(edge.get(0));
        }
        List<List<Integer>> criticalConnections = new LinkedList();
        int[] steps = new int[n];
        Arrays.fill(steps, -1);
        criticalConnections_(graph, 0, -1, steps, criticalConnections, 0);
        return criticalConnections;
    }
    
    private int criticalConnections_(Map<Integer, List<Integer>> graph, int currentNode, int parent, int[] steps, List<List<Integer>> criticalConnections, int step) {
        steps[currentNode] = step;
        List<Integer> children = graph.get(currentNode);
        int minSteps = step;
        for(Integer child : children)
        {
            if(child == parent) continue;
            else if(steps[child] == -1) minSteps = Math.min(minSteps, criticalConnections_(graph, child, currentNode, steps, criticalConnections, step + 1));
            else if(steps[child] != -1) minSteps = Math.min(minSteps, steps[child]);
        }
        if(minSteps == step && currentNode != 0)
        {
            List<Integer> criticalConnection = new ArrayList();
            criticalConnection.add(currentNode);
            criticalConnection.add(parent);
            criticalConnections.add(criticalConnection);
        }
        return minSteps;
    }
}
