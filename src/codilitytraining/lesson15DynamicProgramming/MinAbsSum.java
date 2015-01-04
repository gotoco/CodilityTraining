package codilitytraining.lesson15DynamicProgramming;

import java.util.Arrays;
import java.util.Random;

/**
 For a given array A of N integers and a sequence S of N integers from the set {−1, 1}, we define val(A, S) as follows:
 val(A, S) = |sum{ A[i]*S[i] for i = 0..N−1 }|
 (Assume that the sum of zero elements equals zero.)
 For a given array A, we are looking for such a sequence S that minimizes val(A,S).
 Write a function:
 class Solution { public int solution(int[] A); }
 that, given an array A of N integers, computes the minimum value of val(A,S) from all possible values of val(A,S) for all possible sequences S of N integers from the set {−1, 1}.
 For example, given array:
 A[0] =  1
 A[1] =  5
 A[2] =  2
 A[3] = -2
 your function should return 0, since for S = [−1, 1, −1, 1], val(A, S) = 0, which is the minimum possible value.
 Assume that:
 N is an integer within the range [0..20,000];
 each element of array A is an integer within the range [−100..100].
 Complexity:
 expected worst-case time complexity is O(N*max(abs(A))2);
 expected worst-case space complexity is O(N+sum(abs(A))), beyond input storage (not counting the storage required for input arguments).
 Elements of input arrays can be modified.
 */
public class MinAbsSum {

    static MinAbsSum that = new MinAbsSum();

    public static void main(String [] args){
       // emptyAndSingleElement();
        example();
    }

    public static void example(){

        int [] A = new int [4];

        A[0] =  1;
        A[1] =  5;
        A[2] =  2;
        A[3] = -2;

        System.out.println(that.solution(A));
    }

    public static void emptyAndSingleElement(){

        int [] empty = {};

        System.out.println(that.solution(empty));

        int [] single = {1};

        System.out.println(that.solution(single));
    }

    public int solution(int[] A){
        return optimizedSolution(A);
    }

    /*Codility 100%*/
    public int optimizedSolution(int [] A){
        int N = A.length;
        int M=0; int S = 0;
        for(int i=0; i<N; i++){
            A[i] = Math.abs(A[i]);
            M = Math.max(M, A[i]);
            S+=A[i];
        }
        int[] count = new int[M+1];
        for(int i=0;i<N; i++){
            count[A[i]] += 1;
        }
        int[] dp = new int [S+1];
        for(int i=1;i<S+1; i++){
            dp[i]= -1;
        }
        dp[0] = 0;
        for(int i=1; i<M+1; i++){
            if(count[i] > 0){
                for(int j=0; j<S; j++){
                    if(dp[j] >= 0){
                        dp[j] = count[i];
                    } else if(j>=i && dp[j-i]>0){
                        dp[j] = dp[j-i]-1;
                    }
                }
            }
        }
        int result = S;
        for(int i=0; i<S/2+1; i++){
            if(dp[i] >= 0)
                result = Math.min(result, S-2*i);
        }
        return result;
    }

    /* Codility 70%*/
    public int slowSolution(int[] A){
        int N = A.length;
        int M = 0; int sum=0;
        if(N<1) return 0;

        for(int i=0; i<N; i++){
            A[i] = Math.abs(A[i]);
            M = Math.max(A[i], M);
            sum += A[i];
        }
        int [] dp = new int[sum];
        dp[0] = 1;
        for(int j=0; j<N; j++){
            for(int i=sum-1; i>-1; i--){
                if(dp[i] == 1 && (i+A[j] <sum))
                    dp[i+A[j]] = 1;
            }
        }
        int result = sum;
        for(int i=0; i<sum/2+1; i++)
            if(dp[i] == 1)
                result = Math.min(result, sum-2*i );

        return result;
    }


    /* Brute force solution Codility 40%*/
    public int bruteForceSolution(int[] A){
        int N = A.length;
        if(N<=0) return 0;

        int [] S = new int[N];
        int total = (int)Math.pow(2, N);
        int minSum = Integer.MAX_VALUE;
        //Generate each possible subset S for A and calculate it sum
        //As a subset use binary representation of i
        //0 will be -1 and 1 is 1
        for(int i=0; i<total; i++){
            String representation = Integer.toString(i,2);

            for(int j=0; j<representation.length(); j++){
                S[N-1-j] = Integer.parseInt(""+representation.charAt(j));
            }
            int tmpSum=0;
            for(int j=0; j<N; j++){

                if(S[j] == 0){
                  tmpSum -= A[j];
                }
                else {
                  tmpSum += A[j];
                }
            }
            tmpSum = Math.abs(tmpSum);
            minSum = Math.min(minSum, tmpSum);
        }
        return minSum;
    }

    public static void compare(){

        int size = 10 ;//Integer.MAX_VALUE;

        for(int i=0 ; i<size; i++){

            Random random = new Random() ;
            int randomNumber = random.nextInt(15);

            int sizeArray = randomNumber;
            int [] array = new int[sizeArray];

            for(int j=0; j<array.length; j++){
                array[j] = random.nextInt(100) - 50;
            }

            int sol1 = that.bruteForceSolution(array) ;
            int sol2 = that.solution(array) ;

            if(sol1 != sol2){
                System.out.println("  ");
                System.out.println("sol1 =  " + sol1);
                System.out.println("sol2 =  " + sol2);
                for(int k=0;k<sizeArray;k++){
                    System.out.print(array[k]);
                    System.out.print(",");
                }
                System.out.println("  ");
            }

        }
    }

}
