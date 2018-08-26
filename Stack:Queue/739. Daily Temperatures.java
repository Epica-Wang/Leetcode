Given a list of daily temperatures, produce a list that, for each day in the input, tells you how many days you would have to wait until a warmer temperature. If there is no future day for which this is possible, put 0 instead.

For example, given the list temperatures = [73, 74, 75, 71, 69, 72, 76, 73], your output should be [1, 1, 4, 2, 1, 1, 0, 0].

Note: The length of temperatures will be in the range [1, 30000]. Each temperature will be an integer in the range [30, 100].

/* compare with 496 Next Great Element.
对每个element  向右找出要走几步才能碰到第一个比它大的element.
利用stack: stack store elements that we haven't found the first larger element than it.
scan towards right,
if current element > stack.top(): stack keep pop util element is larger than current.同时maintain一个count记录走了几步
if current element <=stack.top()|| stack is empty: current element进栈

考虑：
[73,74,75,71,69,72,76,73]. 当75，71，69在栈中，72来了 69，71 出栈，步数各为1，2。 72入栈，之后76进栈，72出，75出。75出栈时如何得知步数为4？
操作方法：stack里面不存数字本身，而是存在array里的index。数字本身用temperature array获取就行了
*/
class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
      int[] res = new int[temperatures.length];
      Stack<Integer> stack = new Stack<>();
      for(int i=0;i<temperatures.length;i++){
        if(stack.isEmpty() || temperatures[i]<=temperatures[stack.peek()]){
          stack.push(i);
        }else{
          while(!stack.isEmpty() && temperatures[i]>temperatures[stack.peek()]){
            int index = stack.pop();
            res[index] = i-index;
          }
          stack.push(i);
        }
      }
      return res;
    }
}
