Equations are given in the format A / B = k, where A and B are variables represented as strings, and k is a real number (floating point number). Given some queries, return the answers. If the answer does not exist, return -1.0.

Example:
Given a / b = 2.0, b / c = 3.0.
queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? .
return [6.0, 0.5, -1.0, 1.0, -1.0 ].

The input is: vector<pair<string, string>> equations, vector<double>& values, vector<pair<string, string>> queries , where equations.size() == values.size(), and the values are positive. This represents the equations. Return vector<double>.

According to the example above:

equations = [ ["a", "b"], ["b", "c"] ],
values = [2.0, 3.0],
queries = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ].
The input is always valid. You may assume that evaluating the queries will result in no division by zero and there is no contradiction.

/*
Solution 1: Graph + DFS
HashMap<String, HashMap<String, Double>> store equations. e.g.: (a -> (b, 2.0), (f, 3.0))
dfs search

time complexity: O(# of equations + # queries * # of equations). 每个query都要从头dfs一遍map
space complexity: O(# of equations)
*/
class Solution {
    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
      HashMap<String, HashMap<String, Double>> equationsMap = constructEquations (equations, values);
      double[] res = new double[queries.length];
      for (int i = 0; i < queries.length; i++) {
        Double value = dfs(equationsMap, queries[i][0], queries[i][1], new HashSet<String>());
        if (value != null) {
          res[i] = value;
        } else {
          res[i] = -1.0;
        }
      }
      return res;
    }

    public HashMap<String, HashMap<String, Double>> constructEquations (String[][] equations, double[] values) {
      HashMap<String, HashMap<String, Double>> equationsMap = new HashMap<String, HashMap<String, Double>>();
      for (int i = 0; i< equations.length; i++) {
        String num = equations[i][0];
        String denom = equations[i][1];
        double value = values[i];

        HashMap<String, Double> temp1 = equationsMap.getOrDefault(num, new HashMap<String, Double>());
        HashMap<String, Double> temp2 = equationsMap.getOrDefault(denom, new HashMap<String, Double>());
        temp1.put(denom, value);
        //temp1.put(num, 1.0);
        temp2.put(num, 1/value);
        //temp2.put(denom, 1.0);
        equationsMap.put(num, temp1);
        equationsMap.put(denom, temp2);

        // equationsMap.put(num, equationsMap.getOrDefault(num, new HashMap<String, Double>()).put(denom + "", value ));
        // equationsMap.put(denom, equationsMap.getOrDefault(denom, new HashMap<String, Double>()).put(num + "", 1 / value ));
        /*
        上面两行长串看似fancy，但其实报错。。。原因在于map.put(key, value)其实是有返回值的， 返回value。
        所以后半部分
        equationsMap.getOrDefault(num, new HashMap<String, Double>()).put(denom + "", value ) 返回的是value
        然后就变成equationsMap.put(num, value（是String Type）)  然而本来想put的是HashMap<String, Double> type！
        */
      }
      return equationsMap;
    }

    // return acc product from a path: num to denom. if no path, return null
    // 在这整个过程中，终点denom是一直不变的，但起点num一直在变
    public Double dfs (HashMap<String, HashMap<String, Double>> equationsMap, String num, String denom, HashSet<String> set) {
      if (!equationsMap.containsKey(num) || !equationsMap.containsKey(denom)) {
        return null;
      }

      if (num.equals(denom)) return 1.0;
      if (set.contains(num)) {
        return null;
      }

      set.add(num);
      HashMap<String, Double> denomsValues = equationsMap.get(num);
      for (Map.Entry<String, Double> entry: denomsValues.entrySet()) {
        Double res = dfs(equationsMap, entry.getKey(), denom, set);
        if (res != null) {
          return entry.getValue() * res;
        }
      }
      set.remove(num);
      return null;
    }
}
