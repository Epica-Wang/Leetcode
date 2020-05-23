文章：https://zpjiang.me/2017/11/13/top-k-elementes-system-design/

基础做法：
1. HashMap + PriorityQueue
时间复杂度 O(N*log(N)) 空间复杂度 O(N) N为unique元素的个数

2. Bucket Sort
时间复杂度 O(M)  空间复杂度 O(M) M为所有元素的个数

进阶：Top K Frequent Elements in a Data Stream (System Design)
1: Multi HashMap + heap  用Data Sharding， 然后跑基础做法

2. Count-Min Sketch + Heap
Count-Min Sketch 看以下两个video：
https://www.youtube.com/watch?time_continue=81&v=ibxXO-b14j4&feature=emb_logo 基础算法
https://www.youtube.com/watch?v=kx-XDoPjoHw   这个讲的更system design level

3. Lossy Counting Algorithm
