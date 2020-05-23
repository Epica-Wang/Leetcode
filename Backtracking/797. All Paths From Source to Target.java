//更好的写法
class Solution {
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();

        path.add(0);
        dfsSearch(graph, 0, res, path);

        return res;
    }

    private void dfsSearch(int[][] graph, int node, List<List<Integer>> res, List<Integer> path) {
        if (node == graph.length - 1) {
            res.add(new ArrayList<Integer>(path));
            return;
        }

        for (int nextNode : graph[node]) {
            path.add(nextNode);
            dfsSearch(graph, nextNode, res, path);
            path.remove(path.size() - 1);
        }
    }
}

//带memorization的写法 此时backtracking的function要return 该层的结果
class Solution {
public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
    Map<Integer, List<List<Integer>>> map = new HashMap<>();

    return dfsSearch(graph, 0, map);
}

private List<List<Integer>> dfsSearch(int[][] graph, int node, Map<Integer, List<List<Integer>>> map) {
    if (map.containsKey(node)) {
        return map.get(node);
    }

    List<List<Integer>> res = new ArrayList<>();
    if (node == graph.length - 1) {
        List<Integer> path = new LinkedList<>();
        path.add(node);
        res.add(path);
    } else {
        for (int nextNode : graph[node]) {
            List<List<Integer>> subPaths = dfsSearch(graph, nextNode, map);
            for (List<Integer> path : subPaths) {
                LinkedList<Integer> newPath = new LinkedList<>(path);
                newPath.addFirst(node);
                res.add(newPath);
            }
        }
    }

    map.put(node, res);
    return res;
}
}

// 初始写法
class Solution {
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        int destination = graph.length - 1;
        List<List<Integer>> res = new LinkedList<>();

        addPath(graph, 0, new LinkedList<Integer>(), res, destination);
        return res;
    }

    private void addPath(int[][] graph, int index, List<Integer> temp, List<List<Integer>> res, int destination) {
        if (index == destination) {
            temp.add(index);
            res.add(new LinkedList<>(temp));
            temp.remove(temp.size() - 1);
            return;
        }

        temp.add(index);
        System.out.format("add %d%n", index);
        int[] outNodes = graph[index];
        for (int outNode: outNodes) {
            addPath(graph, outNode, temp, res, destination);
        }
        System.out.format("remove %d%n", temp.size() - 1);
        temp.remove(temp.size() - 1);
    }
}
