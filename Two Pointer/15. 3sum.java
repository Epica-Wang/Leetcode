Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.

Note:

The solution set must not contain duplicate triplets.

Example:

Given array nums = [-1, 0, 1, 2, -1, -4],

A solution set is:
[
  [-1, 0, 1],
  [-1, -1, 2]
]

/* 3pointers
这题唯一的问题就是去重，三个指针都要注意一次迭代，同样只加一次，要靠 while 推一下。
*/
public class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
         List<List<Integer>> list = new  ArrayList<List<Integer>>();
         if(nums == null || nums.length == 0) return list;
         Arrays.sort(nums);

         for(int i = 0; i < nums.length - 2; i++){
             int left = i + 1;
             int right = nums.length - 1;
             while(left < right){
                 int sum = nums[i] + nums[left] + nums[right];
                 if(sum == 0){
                     list.add(Arrays.asList(nums[i], nums[left ++], nums[right --]));
                    //注意这儿left已经++ right--
                     while(left < right && nums[left] == nums[left - 1]) left ++;
                     while(left < right && nums[right] == nums[right + 1]) right --;
                 } else if(sum < 0){
                     left ++;
                 } else{
                     right --;
                 }
             }

             while(i < nums.length - 2 && nums[i] == nums[i + 1]) i++;
         }

         return list;
    }
}
/* Solution just use two pointers without using map or set
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        if(nums.length<3)
          return null;
        Arrays.sort(nums);
        List<List<Integer>> answerList = new LinkedList<List<Integer>>();

        for(int i=0;i<=nums.length-3;i++){
          //
          if(i>=1 && (nums[i-1]==nums[i]))){
            continue;
          }
          int twoSumTarget = nums[i];
          int head = i+1;
          int tail = nums.length-1;
          while(head<tail){
            if(nums[head]+nums[tail]>-1*twoSumTarget){
              tail--;
            }else if(nums[head]+nums[tail]<-1*twoSumTarget){
              head++;
            }else{ //nums[head]+nums[tail]==-1*twoSumTarget
              answerList.add(new LinkedList<Integer>(Arrays.asList(nums[i],nums[head],nums[tail])));
              head++;
              tail--;
              // we need to check if nums[head] and nums[tail] have repeated elements since we should not contain duplicate triplets.
              // consider twoSumTarget=-3, we have remaining array 1 1 1 2 2 2
              // last while call: 1(head) 2(tail) -> 2(head) 1(tail) terminate while
              while(head<tail&&(nums[head-1]==nums[head]||nums[tail+1]==nums[tail])){
                if(nums[head-1]==nums[head]) head++;
                if(nums[tail+1]==nums[tail]) tail--;
              }
            }
          }
        }

        return answerList;
    }
}
*/

/* Solution 2 */
/* HashSet<List<Integer>>   两个list比较  是比较内容  list override了equal*/
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        if(nums.length<3)
          return new LinkedList<List<Integer>>();
        Arrays.sort(nums);
        List<List<Integer>> answerList = new LinkedList<List<Integer>>();
        HashSet<List<Integer>> hsPair = new HashSet<>();
        for(int i=0;i<=nums.length-3;i++){
          // if(i>=1 && (nums[i-1]==nums[i]))){
          //   continue;
          // }
          int twoSumTarget = nums[i];
          int head = i+1;
          int tail = nums.length-1;
          while(head<tail){
            if(nums[head]+nums[tail]>-1*twoSumTarget){
              tail--;
            }else if(nums[head]+nums[tail]<-1*twoSumTarget){
              head++;
            }else{ //nums[head]+nums[tail]==-1*twoSumTarget
              List<Integer> temp = new LinkedList<Integer>(Arrays.asList(nums[i],nums[head],nums[tail]));
              if(hsPair.add(temp)){
                //hsPair.add(temp);
                answerList.add(new LinkedList<Integer>(Arrays.asList(nums[i],nums[head],nums[tail])));
              }
              head++;
              tail--;
              // // we need to check if nums[head] and nums[tail] have repeated elements since we should not contain duplicate triplets.
              // // consider twoSumTarget=-3, we have remaining array 1 1 1 2 2 2
              // // last while call: 1(head) 2(tail) -> 2(head) 1(tail) terminate while
              // while(head<tail&&(nums[head-1]==nums[head]||nums[tail+1]==nums[tail])){
              //   if(nums[head-1]==nums[head]) head++;
              //   if(nums[tail+1]==nums[tail]) tail--;
              // }
            }
          }
        }

        return answerList;
    }
}

/* 用int[]做key不行   array是pass by reference  不是比较内容
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        if(nums.length<3)
          return new LinkedList<List<Integer>>();
        Arrays.sort(nums);
        List<List<Integer>> answerList = new LinkedList<List<Integer>>();
        HashMap<int[],Integer> hsPair = new HashMap<>();
        for(int i=0;i<=nums.length-3;i++){
          // if(i>=1 && (nums[i-1]==nums[i]))){
          //   continue;
          // }
          int twoSumTarget = nums[i];
          int head = i+1;
          int tail = nums.length-1;
          while(head<tail){
            if(nums[head]+nums[tail]>-1*twoSumTarget){
              tail--;
            }else if(nums[head]+nums[tail]<-1*twoSumTarget){
              head++;
            }else{ //nums[head]+nums[tail]==-1*twoSumTarget
              if(!hsPair.containsKey(new int[]{nums[i],nums[head],nums[tail]})){
                hsPair.put(new int[]{nums[i],nums[head],nums[tail]},1);
                answerList.add(new LinkedList<Integer>(Arrays.asList(nums[i],nums[head],nums[tail])));
              }
              head++;
              tail--;
              // // we need to check if nums[head] and nums[tail] have repeated elements since we should not contain duplicate triplets.
              // // consider twoSumTarget=-3, we have remaining array 1 1 1 2 2 2
              // // last while call: 1(head) 2(tail) -> 2(head) 1(tail) terminate while
              // while(head<tail&&(nums[head-1]==nums[head]||nums[tail+1]==nums[tail])){
              //   if(nums[head-1]==nums[head]) head++;
              //   if(nums[tail+1]==nums[tail]) tail--;
              // }
            }
          }
        }

        return answerList;
    }
}*/
