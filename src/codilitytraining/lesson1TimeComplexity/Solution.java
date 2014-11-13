package codilitytraining.lesson1TimeComplexity;

/**
 * Author: Mazeryt Freager
 * Contact: skirki@o2.pl
 * Date: 4/13/14
 * Time: 11:46 AM
 */
public class Solution {

    public static void main(String [] args)     {

        int[] test1 = {3,1,2,4,3};
        int[] test2 = {2000, 2000};

        Solution test = new Solution();

        System.out.println("RESULT for test1 data : " + test.solution(test1));
        System.out.println("RESULT for test2 data : " + test.solution(test2));

    }

    public int solution(int[] A) {
        int [] diff;
        int sum1;
        int sum2=0;
        int ans, localMin;
        diff = new int[A.length-1];

        //AT P=1 sum1=A[0]
        sum1=A[0];

        for (int i =1;i<A.length;i++){
            sum2 += A[i];
        }

        ans = Math.abs(sum1- sum2);

        for (int p= 1;p<A.length;p++){
            localMin= Math.abs(sum1- sum2);

            if( localMin < ans ){
                ans = localMin;
            }
            //advance the sum1, sum2
            sum1+= A[p];
            sum2-= A[p];
            diff[p-1]=localMin;
        }

        return (getMinVal(diff));
    }

    public int getMinVal(int[] arr){
        int minValue = arr[0];
        for(int i=1;i<arr.length;i++){
            if(arr[i] < minValue){
                minValue = arr[i];
            }
        }
        return minValue;
    }
}
