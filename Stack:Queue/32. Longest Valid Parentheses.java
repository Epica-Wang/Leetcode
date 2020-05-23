Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.

Example 1:

Input: "(()"
Output: 2
Explanation: The longest valid parentheses substring is "()"
Example 2:

Input: ")()())"
Output: 4
Explanation: The longest valid parentheses substring is "()()"

解释的太好了！！！
https://www.youtube.com/watch?v=M1Vw5Tk1rw4

/** 正确思路 灵感来源于上述video
还是要用stack，但需要maintain一个leftMost
  0 1 2 3 4 5 6 7 8 9 10
  ( ( ) ( ) ) ( ) ) ( )
  Index     leftMost    Stack   pop   curLength
               -1        Empty              0
(    0         -1        0                  0
(    1         -1        0 1                0
)    2         -1        0       1          2 - 0 (Index - stack.peek)
(    3         -1        0 3                2
)    4         -1        0       3          4 - 0
)    5         -1        empty   0          5 - (-1) (index - leftMost)
(    6         -1        6                  6
)    7         -1        empty   6          7 - (-1)
)    8         8         Empty   没得pop
(    9         8         9
)    10        8         empty   9          10 - 8 = 2 (index - leftMost)

leftMost代表这一组valid parentheses起始在哪个index，初始化为-1
iterate through string：
if "(" -> 把index加入stack
if ")":
   1. 若stack不为空：先pop最上index出来 代表了跟这个")"match的左括号
        1.1 若pop之后stack不为空
          curLength = index - stack.peek()
          即stack目前最顶端peek的"("是最近一个没有被match到的左括号，已经valid的长度就是现在的index - stack.peek()
        1.2 若pop之后stack为空
          curLength = index - leftMost
          即所有的“(”都被match上了，那长度就是现在的index - 上一组valid parentheses结束的位置
   2. 若stack已经为空：
      意味着这个")"没得match，当前维持的valid parentheses到这儿断掉了
      就将这个")" 的index设为leftMost  起始新的valid parenthese
*/
class Solution {
    public int longestValidParentheses(String s) {
      if (s == null || s.length() == 0) return 0;

      Stack<Integer> stack = new Stack<>();
      int maxLength = 0;
      int curLength = 0;
      int leftMost = -1;

      for (int index = 0; index < s.length(); index++) {
        if (s.charAt(index) == '(') {
          stack.push(index);
          continue;
        }
        if (s.charAt(index) == ')') { // ')'
          if (!stack.isEmpty()) {
            stack.pop();
            if (!stack.isEmpty()) {
              curLength = index - stack.peek();
            } else {
              curLength = index - leftMost;
            }
            maxLength = Math.max(maxLength, curLength);
          } else { //stack.isEmpty
            leftMost = index;
          }
        }
      }

      return maxLength;
    }
}


/** initial thought  大错特错。
此test case不过：
Input:
"()(()"
Output:
4
Expected:
2
// 以下是错误的第一次写法  当时以为自己可对了
int curLength
int maxLength
Iterate through String
maintain a stack: when meet "(", push in "(",
if meet ")":
  -> if there is "(" remaining in stack, pop "(" out, curLength+=2
  -> if there is no "(" remaining in stack, means invalid:
     -> maxLength = max(curLength, maxLength)
     -> reset stack to be empty!
     -> reset curLength to be 0;

错误的关键在于stack存的是"("本身而不是其index，无法detect中间是不是有多余的(
"()(()"例子中会把前一对()和最后一对()看成是连续的，错误的output 4
*/
class Solution {
    public int longestValidParentheses(String s) {
        char[] charArr = s.toCharArray();
        int curLength = 0;
        int maxLength = 0;
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < charArr.length; i++) {
          if (charArr[i] == '(') {
            stack.push('(');
          }
          if (charArr[i] == ')') {
            if (!stack.isEmpty()) {
              stack.pop();
              curLength += 2;
              maxLength = Math.max(curLength, maxLength);
            } else { //stack is empty
              maxLength = Math.max(curLength, maxLength);
              stack = new Stack<Character>();
              curLength = 0;
            }
          }
        }
        return maxLength;
    }
}
