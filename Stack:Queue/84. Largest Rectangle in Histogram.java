Given n non-negative integers representing the histograms bar height where the width of each bar is 1, find the area of largest rectangle in the histogram.

//图见leetcode https://leetcode.com/problems/largest-rectangle-in-histogram/description/
Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].

The largest rectangle is shown in the shaded area, which has area = 10 unit.

Example:

Input: [2,1,5,6,2,3]
Output: 10

/*
想法：对每个bar，找到以这个bar为最大高度的矩形的面积。即向左，向右找到第一个小于bar的高度
（right-1-（left+1）+1)*barHeight 就是以这个bar为最大高度的矩形的面积。 过程中maintain一个maxArea的变量
如何高效的完成”向左，向右找到第一个大于bar的高度“这个job？

*/
