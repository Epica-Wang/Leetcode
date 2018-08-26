Given an array of strings, group anagrams together.

Example:

Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
Output:
[
  ["ate","eat","tea"],
  ["nat","tan"],
  ["bat"]
]
Note:

All inputs will be in lowercase.
The order of your output does not matter.

/*
比较 Trivial 的问题，没啥特别好说的。。
这题主要的乐趣在于怎么把多个是 anagram 的 string 映射到同一个 key 上。
简单的办法是排序，或者开个 int[] 统计字母数量，
然后生成一个类似于 1a2b4f 这种的 string signature.
*/
public class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> rst = new ArrayList<List<String>>();
        if(strs == null || strs.length == 0) return rst;

        HashMap<String, List<String>> map = new HashMap<String, List<String>>();

        for(String str : strs){
            char[] array = str.toCharArray();
            Arrays.sort(array);
            String sortedStr = new String(array);

            if(!map.containsKey(sortedStr)){
                List<String> list = new ArrayList<String>();
                list.add(str);
                map.put(sortedStr, list);
            } else {
                map.get(sortedStr).add(str);
            }
        }

        for(String str : map.keySet()){
            // OJ wants each list to be sorted
            Collections.sort(map.get(str));
            rst.add(map.get(str));
        }

        return rst;
    }
}
