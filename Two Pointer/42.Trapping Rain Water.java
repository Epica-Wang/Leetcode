

/*The idea is very simple.
Begin scan from beginning and end of array.
Compare value of left and right pointer, hold the greater one
and move the other to inner array.
Compute passed area when pointer gets inner.*/

public class Solution {
    public int trap(int[] height) {
        if(height == null || height.length <= 2) return 0;
        int trapped = 0;
        int i = 0;
        int j = height.length - 1;

        int leftMax = height[i];
        int rightMax = height[j];

        while(i < j){
            if(leftMax < rightMax){
                if(i + 1 < height.length && height[i + 1] < leftMax){
                    trapped += leftMax - height[i + 1];
                }
                i++;
                leftMax = Math.max(leftMax, height[i]);
            } else {
                if(j - 1 >= 0 && height[j - 1] < rightMax){
                    trapped += rightMax - height[j - 1];
                }
                j--;
                rightMax = Math.max(rightMax, height[j]);
            }
        }

        return trapped;
    }
}
/* 写法2
public int trap(int[] height) {
	int secHight = 0;
	int left = 0;
	int right = height.length - 1;
	int area = 0;
	while (left < right) {
		if (height[left] < height[right]) {
			secHight = Math.max(height[left], secHight);
			area += secHight - height[left];
			left++;
		} else {
			secHight = Math.max(height[right], secHight);
			area += secHight - height[right];
			right--;
		}
	}
	return area;
}
*/

//07.31.2018 重写练习
class Solution {
    public int trap(int[] height) {

    }
}
