package codilitytraining.lesson11FibonacciNumbers;

import java.util.ArrayList;
import java.util.List;

/**
 The Fibonacci sequence is defined using the following recursive formula:
    F(0) = 0
    F(1) = 1
    F(M) = F(M - 1) + F(M - 2) if M >= 2

 A small frog wants to get to the other side of a river. The frog is initially located at one bank of the river
 (position −1) and wants to get to the other bank (position N).
 The frog can jump over any distance F(K), where F(K) is the K-th Fibonacci number.
 Luckily, there are many leaves on the river, and the frog can jump between the leaves,
 but only in the direction of the bank at position N.
 The leaves on the river are represented in a zero-indexed array A consisting of N integers.
 Consecutive elements of array A represent consecutive positions from 0 to N − 1 on the river.
 Array A contains only 0s and/or 1s:
 0 represents a position without a leaf;
 1 represents a position containing a leaf.
 The goal is to count the minimum number of jumps in which the frog can get to the other side of the river
 (from position −1 to position N).
 The frog can jump between positions −1 and N (the banks of the river) and every position containing a leaf.
 For example, consider array A such that:

    A[0] = 0
    A[1] = 0
    A[2] = 0
    A[3] = 1
    A[4] = 1
    A[5] = 0
    A[6] = 1
    A[7] = 0
    A[8] = 0
    A[9] = 0
    A[10]= 0

 The frog can make three jumps of length F(5) = 5, F(3) = 2 and F(5) = 5.
 Write a function:

    class Solution { public int solution(int[] A); }

 that, given a zero-indexed array A consisting of N integers,
 returns the minimum number of jumps by which the frog can get to the other side of the river.
 If the frog cannot reach the other side of the river, the function should return −1.
 For example, given:
    A[0] = 0
    A[1] = 0
    A[2] = 0
    A[3] = 1
    A[4] = 1
    A[5] = 0
    A[6] = 1
    A[7] = 0
    A[8] = 0
    A[9] = 0
    A[10] = 0

 the function should return 3, as explained above.
 Assume that:
 N is an integer within the range [0..100,000];
 each element of array A is an integer that can have one of the following values: 0, 1.
 Complexity:
 expected worst-case time complexity is O(N*log(N));
 expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
 Elements of input arrays can be modified.
 */
public class FibFrog {

    public static void main(String [] args) {

        //tests();
        int [] A = new int[11];

        FibFrog ff = new FibFrog();

        A[0] = 1;
        A[1] = 1;
        A[2] = 1;
        A[3] = 1;
        A[4] = 1;
        A[5] = 1;
        A[6] = 1;
        A[7] = 1;
        A[8] = 1;
        A[9] = 1;
        A[10] =1;

        System.out.println("Test should return 3: " + ff.solution(A));

    }

    public static void tests(){
        FibFrog ff = new FibFrog();
        int [] A = new int[11];

        A[0] = 0;
        A[1] = 0;
        A[2] = 0;
        A[3] = 1;
        A[4] = 1;
        A[5] = 0;
        A[6] = 1;
        A[7] = 0;
        A[8] = 0;
        A[9] = 0;
        A[10] =0;

        System.out.println("Example should return 3: " + ff.solution(A));

        A[0] = 0;
        A[1] = 0;
        A[2] = 0;
        A[3] = 0;
        A[4] = 0;
        A[5] = 0;
        A[6] = 0;
        A[7] = 0;
        A[8] = 0;
        A[9] = 0;
        A[10] =0;

        System.out.println("Empty should return -1 : " + ff.solution(A));

        int [] B = {0} ;

        System.out.println("One zero should return 1 : " + ff.solution(B));

        int [] empty = {} ;

        System.out.println("Empty should return 1 : " + ff.solution(empty));


        int [] allOne = {1,1} ;

        System.out.println("allOne should return 1 : " + ff.solution(allOne));
    }

    public int solution(int[] A) {
        int N = A.length;
        if (N <= 2) return 1;

        int i, j, k, result = -1;
        int [] fibo = new int [50];
        int [] cnt = new int[N];

        fibo[1] = 1;

        i = 1;
        while(fibo[i++] <= N) {
            fibo[i] = fibo[i-1] + fibo[i-2];
        }

        for (i = N-1; i >= 0; i--) {
            if (A[i] == 1) {
                k = 2;
                j = i + fibo[k++];
                while(j < N) {
                    if (A[j] == 1 && cnt[j] != 0) {
                        if (cnt[i] == 0) cnt[i] = cnt[j] + 1;
                        else cnt[i] = Math.min(cnt[i], cnt[j] + 1);
                    }
                    j = i + fibo[k++];
                }
                if (j == N) cnt[i] = 1;
            }
        }

        k = 2;
        j = -1 + fibo[k++];
        while (j < N) {
            if (A[j] == 1 && cnt[j] != 0) {
                if (result == -1) result = cnt[j] + 1;
                else result = Math.min(result, cnt[j] + 1);
            }
            j = -1 + fibo[k++];
        }
        if (j == N) result = 1;

        return result > 0 ? result : -1;

    }


    //TODO: Finish rec version of solution
    public int recSolution(int[] A) {

        int N = A.length;

        List<Integer> fibons = new ArrayList<Integer>();
        fibons.add(0,0);
        fibons.add(1,1);

        int current = -1;
        for(int i=2; current<=N+1; i++){
            current = fibons.get(i-1)+fibons.get(i-2);

            if(current<=N+1)
                fibons.add(current);
        }

        int result = jump(A, -1, fibons);


        return result;
    }

    /**
     * This method will check rest of river with fibNumbers
     *
     * @param A river array
     * @param start
     * @return steps to go across river or -1 if it is no possible to cross with this calculation
     */
    private int jump(int [] A, int start, List<Integer> fibNrs)   {
        int result = 0;
        int i;
        for(i=fibNrs.size()-1; i>0; i--){
            int jump = fibNrs.get(i)+start;
            if( (jump<A.length) && A[jump]==1)  {      //jump+1?   +start
                int r = jump(A, jump, fibNrs);

                if(r != -1)
                    result = r+1;
            } else if(jump==A.length){
                return 1;
            }
        }

        if(i == 0 && result == 0){
            return -1;
        }

        return result;
    }



}
