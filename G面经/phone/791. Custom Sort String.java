S and T are strings composed of lowercase letters. In S, no letter occurs more than once.

S was sorted in some custom order previously. We want to permute the characters of T so that they match the order that S was sorted. More specifically, if x occurs before y in S, then x should occur before y in the returned string.

Return any permutation of T (as a string) that satisfies this property.

Example :
Input:
S = "cba"
T = "abcd"
Output: "cbad"
Explanation:
"a", "b", "c" appear in S, so the order of "a", "b", "c" should be "c", "b", and "a".
Since "d" does not appear in S, it can be at any position in T. "dcba", "cdba", "cbda" are also valid outputs.


Note:

S has length at most 26, and no character is repeated in S.
T has length at most 200.
S and T consist of lowercase letters only.

/*
S中没有repeated，但T里可能有
T里有S中没有的char 顺序怎样都不重要  不用care
*/

/*
map扫一遍T，记录每个char的个数
再扫一遍S， 按S中char的顺序将map中的char append到result里，同时修改char的count--
最后扫一遍map，将在T而不在S中的char添加到结果中
*/

/* 写法2：
因为S最多26个， 可以用array来存下标0~25  不用map

*/
class Solution {
  public String customSortString(String S, String T) {
    int[] count = new int[26];
    for (char c: T.toCharArray()) {
      count[c - 'a'] ++;
    }

    StringBuilder sb = new StringBuilder();
    for (char c : S.toCharArray()) {
      while (count[c - 'a'] > 0) {
        sb.append(c);
        count[c - 'a']--;
      }
    }

    // append chars in T not in S
    for (char c = 'a'; c <= 'z'; c++) {
      while (count[c - 'a'] > 0) {
        sb.append(c);
        count[c - 'a']--;
      }
    }
    return sb.toString();
  }
}
