Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.

A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.



Example:

Input: "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
Note:

Although the above answer is in lexicographical order, your answer could be in any order you want.

/*backtracking的写法：用map存数字和字母间对应关系
然后递归求digits上每一位数字对应字母的所有组合。
*/
class Solution {
    public List<String> letterCombinations(String digits) {
      List<String> res = new LinkedList<>();
      if(digits.length()==0 || digits==null){
        return res;
      }
      HashMap<Character,char[]> map = new HashMap<>();
      map.put('0', new char[] {});
      map.put('1', new char[] {});
      //注意直接把0 1 设成空
      map.put('2', new char[] { 'a', 'b', 'c' });
      map.put('3', new char[] { 'd', 'e', 'f' });
      map.put('4', new char[] { 'g', 'h', 'i' });
      map.put('5', new char[] { 'j', 'k', 'l' });
      map.put('6', new char[] { 'm', 'n', 'o' });
      map.put('7', new char[] { 'p', 'q', 'r', 's' });
      map.put('8', new char[] { 't', 'u', 'v'});
      map.put('9', new char[] { 'w', 'x', 'y', 'z' });

      helper(res, new StringBuilder(),0, digits, map);
      return res;
    }
    public void helper(List<String> res, StringBuilder cur,int index, String digits, HashMap<Character,char[]> map){
      if(index == digits.length()){
        res.add(cur.toString());
        return;
      }
      char[] charValue = map.get(digits.charAt(index));
      for(char c:charValue){
        cur.append(c);
        helper(res,cur,index+1,digits,map);
        cur.deleteCharAt(cur.length()-1);
      }
    }
}

/*一个BFS靠queue的写法：
以 queue 首长度 == i 来判断层数，反复做 join. 另外维护一个 String[] 用作字典查询。
*/
public List<String> letterCombinations(String digits) {
        LinkedList<String> queue = new LinkedList<String>();
        if(digits == null || digits.length() == 0) return queue;

        queue.add("");
        String[] letters = new String[]{"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

        for(int i = 0; i < digits.length(); i++){
            int cur = digits.charAt(i) - '0';
            while(queue.peek().length() == i){
                String str = queue.remove();
                char[] candidates = letters[cur].toCharArray();
                for(char chr : candidates){
                    queue.add(str + chr);
                }
            }
        }

        return queue;
}
