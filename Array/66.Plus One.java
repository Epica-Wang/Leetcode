class Solution{
	public int[] plusOne(int[] digits){
		int length = digits.length;
    int tail=length-1;
    //从尾往前
		for(tail=length-1;tail>=0;tail--){
			if(digits[tail]==9){
        digits[tail]=0;
			}else{
        /*else!=9: 包含了两种情况
        如果当前的tail是length-1那位，且！=9  那直接+1 然后break
        如果当前tail不是length-1位，说明是之前都是9  然后进位进到current tail这位了
        然而tail位！=9， 直接+1 break：）
        因为如果tail还是9的话 在if里面设成0然后就continue了
        */
				digits[tail]+=1;
        break;
      }//else
    }//for
    //不用补1的情况：
    //tail>0 或者 原本就只有1位 加完也是1位  如8+1=9
    if(tail>=0 && digits[0]!=0) return digits;  //也可直接写成if(digits[0]!=0) return digits;
    //需要补1喽
    int[] newDigits = new int[length+1];
    newDigits[0]=1;
    for(int i=0;i<length;i++){
        newDigits[i+1]=digits[i];
    }
    return newDigits;
    }
}

// 03/12/2020 练习
class Solution{
	public int[] plusOne(int[] digits){
		int length = digits.length;
        int tail=length-1;
		for(tail=length-1;tail>=0;tail--){
			if(digits[tail]!=9){
        digits[tail]+=1;
        break;
			}else{
				digits[tail]=0;
            }//else
        }//for

        if (digits[0] != 0) return digits;
        int[] newDigits = new int[length+1];
        newDigits[0]=1;
        for(int i=0;i<length;i++){
            newDigits[i+1]=digits[i];
        }
        return newDigits;
    }

}
