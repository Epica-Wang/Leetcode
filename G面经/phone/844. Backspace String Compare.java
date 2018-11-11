Given two strings S and T, return if they are equal when both are typed into empty text editors. # means a backspace character.

Example 1:

Input: S = "ab#c", T = "ad#c"
Output: true
Explanation: Both S and T become "ac".
Example 2:

Input: S = "ab##", T = "c#d#"
Output: true
Explanation: Both S and T become "".
Example 3:

Input: S = "a##c", T = "#a#c"
Output: true
Explanation: Both S and T become "c".
Example 4:

Input: S = "a#c", T = "b"
Output: false
Explanation: S becomes "c" while T becomes "b".
Note:

1 <= S.length <= 200
1 <= T.length <= 200
S and T only contain lowercase letters and '#' characters.

Follow up:

Can you solve it in O(N) time and O(1) space?

/*
O(N) time and O(1) space:
O(N) time have to scan strings in one(or several) pass without go back
but "#" affects previous char
so naturally thinking that start scan from right to left

S = a b # # c
T = a d # c

use two pointer ps and pt, start from the end:
while ps, pt >=0
1. if S.charAt(ps) == T.charAt(pt) and they are not "#"
  then we know this currentIndex matches. ps--, pt--

2. if S.charAt(ps) != T.charAt(pt) and they are not "#"
  return false

3. if S.charAt(ps) or T.charAt(pt) is "#" or both are "#",
 then we maintain a count of "#", move ps(pt) towards left until find a char that should not be deleted.
 then compare char
 a tricky test case: S:ab##   T: c#d#
 
*/

/*
第二种错误写法。。。。
没过：
"bxj##tw"
"bxo#j##tw"
应该return true
*/
class Solution {
  public boolean backspaceCompare(String S, String T) {
    int sLen = S.length();
    int tLen = T.length();

    int ps = sLen - 1;
    int pt = tLen - 1;

    while (ps >=0 || pt >=0) {
      // deal with ps & pt seperatly
      while (ps >=0) {
        if (S.charAt(ps) != '#') break;

        int sCount = 0;
        while (S.charAt(ps) == '#') {
          sCount ++;
          ps --;
        }
        ps = ps - sCount; // move ps to ps-sCount, if new char at ps is still '#', will go inside while again
        /*
        又错了。。。
        没过
        "bxj##tw"
        "bxo#j##tw"
        不应该把ps 直接挪到ps - sCount,因为这中间可能还有#  会被miss掉
        正确思路是一个位置一个位置移动，中间碰到# count ++， 移动到直到count为0了为止
        */
      }

      while (pt >=0) {
        if (T.charAt(pt) != '#') break;
        int tCount = 0;
        while (T.charAt(pt) == '#') {
          tCount ++;
          pt --;
        }
        pt = pt - tCount;
      }
      // after two while
      // ps and pt are at the char they should compare
      if (ps >=0 && pt >=0 && S.charAt(ps) != T.charAt(pt)) {
        return false;
      }
      if (ps < 0 && pt >= 0) {
        return false;
      }else if (pt < 0 && ps >= 0)
        return false;
      // otherwise if one p <0, another one >0, will go inside large while again trying to delete
    }
    return true;
  }
}
/*
一开始的错误写法....见下面注释
*/
class WrongSolution {
    public boolean backspaceCompare(String S, String T) {
      if (S.length() == 0 || T.length() == 0) return true;
      int sLen = S.length();
      int tLen = T.length();

      int ps = sLen - 1;
      int pt = tLen - 1;
      while (ps >=0 && pt >=0) {
        if (S.charAt(ps) == T.charAt(pt) && S.charAt(ps) != '#') {
          ps --;
          pt --;
        }else if (S.charAt(ps) != T.charAt(pt) && S.charAt(ps)!= '#' && T.charAt(pt) != '#') {
          return false;
        }else {
          // at least one of ps or pt is #
          int sCount = 0; // record num of #
          int tCount = 0;
          while (ps >= 0 && S.charAt(ps) == '#' ) {
            sCount ++;
            ps --;
          }
          while (pt >= 0 && T.charAt(pt) == '#') {
            tCount ++;
            pt --;
          }
          // move ps and pt to char not deleted
          ps = ps - sCount;
          pt = pt - tCount;

          // its possible that now ps and pt already < 0
          if (ps < 0 && pt <0) {
            return true;
          } else if (ps < 0 || pt < 0) {
            return false;
          } //注意！这边有错误
          /*
          这么写只考虑了#连续的情况，会miss掉另一种情况：当一个pointer < 0另一个>0， 但>0的那个pointer还可以继续删
          S = ab## T = c#d#
          当ps删完了走到头的时候，pt还剩下c#，但pt应该继续删 最后删完还是能return true的
          */
          // otherwise continue to compare
        }
      }
      /* 错误的一种解决办法是跳出while之后，肯定至少有一个pointer小于0了
      如果 ps pt都<0 还是可以return true的
      如果只有一个pointer<0  对该string能删继续删，看最后pointer也能move到头
      但这样要对S 和 T 写两遍这样的code  too clumsy
      */
      // if return after while, ps <0 pt <0
      return true;
    }
}
