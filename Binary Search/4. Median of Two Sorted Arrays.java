There are two sorted arrays nums1 and nums2 of size m and n respectively.

Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).

Example 1:
nums1 = [1, 3]
nums2 = [2]

The median is 2.0
Example 2:
nums1 = [1, 2]
nums2 = [3, 4]

The median is (2 + 3)/2 = 2.5

/*solution2:
对于中位数问题，首先要做的是明白“找中位数” 等价于 find kth largest element，奇数元素找一遍，偶数元素找两遍。
这个算法的核心思想是，每次可以扔掉 A 或 B 里面，较小的那 k / 2 个数，使得 A 与 B 的剩余搜索范围单调向右，而 k 指数缩小。
复杂度 O(log k)，k = (m + n) / 2

这道题是典型的二分＋DC。
1.判断总元素数量n是奇数还是偶数。
如果是奇数，则结果为第n/2+1个元素，如果是偶数，则结果为（第n／2元素+第n／2+1元素）／2。

2.假设要找第k个元素。
在A和B中分别寻找各自数组的第k/2个元素，比较两个找到的元素的大小，
若A中元素小，则抛弃掉A中的k／2个元素，反之抛弃掉B中的k／2个元素，并继续寻找A和B中的第k（这里k＝k－k／2）个元素。

3.几个边界条件：
1）当其中一个数组元素全部被抛弃时，直接返回另一个数组中的第k个元素。
2）如果k＝＝1，则直接返回两个数组中第一个元素较小的那一个。
3）如果一个数组剩余元素不足k／2个，则抛弃另一个数组的k／2个元素
（肯定不会将要找的第k个元素抛弃掉，因为就算不足的那个数组的元素也一起被抛弃掉，抛掉的元素还是不足k个）。

*/
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
      int lenA = nums1.length;
      int lenB = nums2.length;
      if((lenA+lenB)%2==0){
        return 0.5 * (helper(nums1, 0, nums2, 0,(lenA+lenB)/2) + helper(nums1, 0, nums2, 0,(lenA+lenB)/2+1));
      }else{
        return helper(nums1, 0, nums2, 0,(lenA+lenB)/2+1);
      }
    }

    public double helper(int[] nums1, int startA, int[] nums2, int startB, int k){
      if(startA==nums1.length){
        return nums2[startB+k-1];
      }
      if(startB==nums2.length){
        return nums1[startA+k-1];
      }
      if(k==1){
        return Math.min(nums1[startA], nums2[startB]);
      }
      //k>=2
      int keyA =(startA+k/2-1 >=nums1.length) ? Integer.MAX_VALUE: nums1[startA + k/2-1];
      int keyB = (startB+k/2-1 >= nums2.length)? Integer.MAX_VALUE:nums2[startB + k/2-1];

      if(keyA<keyB){
        return helper(nums1, startA+ k/2, nums2, startB, k- k/2);
      }else{
        return helper(nums1, startA, nums2, startB+k/2, k-k/2);
      }
    }
}






/*solution1:
"丢元素"的思路  binary search
len和为奇 要找的是中间一个  剩下len-1个都丢掉
len和为偶数  要找的是中间两个   剩下len-2个都丢掉
怎么丢？：binary search
如arrayA： 1 2 3 4 5 6. medianA index为2 。3
arrayB：1 3 5 7 9.  medianB index为2. 5
3<5
删掉arrayA里index2之前的（删1，2共两个），删掉arrayB里index2之后的（7，9共两个）
总len为11 一共要删掉10个  现在删了四个
recursion 把arrayA B的start end修改 要删的remaining count修改  传入下一次recursion
*/
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
      int lenA = nums1.length;
      int lenB = nums2.length;
      if((lenA+lenB)%2==0){
        return helper(nums1, 0, lenA-1, nums2, 0,lenB-1, lenA+lenB-2);
      }else{
        return helper(nums1, 0, lenA-1, nums2, 0,lenB-1, lenA+lenB-1);
      }
    }

    public double helper(int[] numsA,int startA,int endA, int[] numsB, int startB, int endB, int remove){
      if(remove==0){
        if(numsA.length>0 && numsB.length>0){
          return (double)(numsA[0]+numsB[0])/2;
        }else if(numsA.length==0){
          return numsB.length>1? (double)(numsB[0]+numsB[1])/2 : (double)numsB[0];
        }else{
          return numsA.length>1? (double)(numsA[0]+numsA[1])/2 : (double)numsA[0];
        }
      }else{
        if(startA>endA)
      }
    }
}
