The Hamming distance between two integers is the number of positions at which the corresponding bits are different.

Given two integers x and y, calculate the Hamming distance.

Note:
0 ≤ x, y < 231.

Example:

Input: x = 1, y = 4

Output: 2

Explanation:
1   (0 0 0 1)
4   (0 1 0 0)
       ↑   ↑

The above arrows point to positions where the corresponding bits are different.

/*
1.首先肯定是XOR 得到代表different bits 的数X
2.如何找出X的binary string里有多少个1 bits？
  -> 每次X & 1再X>>1
*/

class Solution {
    public int hammingDistance(int x, int y) {
      int xor = x ^ y;
      int count =0;
      for(int i=0;i<32;i++){
        count += xor & 1;
        xor = xor >> 1;
      }
      return count;
    }
}
