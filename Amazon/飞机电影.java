飞机上放电影，给一个int[] dur代表所有电影的时间长度, 和一个int 飞行时间长度，
要选取2部电影，保证2部电影加起来的时间小于等于飞行时长减去30分钟。
然后选取2部电影，使得总长度最长，如果有多组总长度一样长的，选取包含单独最长的电影的组合。
我直接O(n^2)扫一遍all pass了。

//类似two sum close
public static void findOptimalWeights(double capacity, double[] weights, int numOfContainers){
        double min = 0.0;
        int minPos = 0;
        int maxPos = weights.length - 1;
        int i =0 , j =weights.length-1;

        Arrays.sort(weights);

        while(i < j){
            double sum = weights[i] + weights[j];

            if(sum > min && sum <= capacity){
                min = sum;
                minPos = i;
                maxPos = j;
            }

            if(sum > capacity){
                j--;
            }else {
                i++;
            }
        }
    }
