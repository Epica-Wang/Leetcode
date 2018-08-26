Given n non-negative integers a1, a2, ..., an,
where each represents a point at coordinate (i, ai).
n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0).
Find two lines, which together with x-axis forms a container,
such that the container contains the most water.

Note: You may not slant the container and n is at least 2.

attention! test case:[1,1]
class Solution {
    public int maxArea(int[] height) {
        int head = 0;
        int tail = height.length-1;
        int maxArea = (tail-head)*Math.min(height[head],height[tail]);
        while(head<tail){
          if(height[head]<height[tail]){  //move head towards right
            // towards right find first height that large than height[head]
            int index = head;
            while(index<=tail){
              if(height[head]>=height[index])
                index++;
              else{
                head = index;
                break;
              }
            }
          }else if(height[head]>height[tail]){  // move tail towards left
            // towards left find first height that large than height[tail]
            int index = tail;
            while(index>=head){
              if(height[tail]>=height[index])
                index--;
              else{
                tail = index;
                break;
              }
            }
          }else{
            /*height[head]==height[tail]   update in two directions
              之前把=的情况放在上面两个if其中之一，然后test case是[1,1]的时候 time limit error
              因为height[head] = height[tail] 且仅有两个元素   head和tail永远不更新
            */
            head++;
            tail--;
          }
          int tempArea = (tail-head)*Math.min(height[head],height[tail]);
          maxArea = Math.max(maxArea,tempArea);
        }
        return maxArea;
    }
}
