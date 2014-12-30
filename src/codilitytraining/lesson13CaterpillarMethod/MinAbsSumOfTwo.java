package codilitytraining.lesson13CaterpillarMethod;

import java.util.Arrays;
import java.util.Random;

/**
 Let A be a non-empty zero-indexed array consisting of N integers.
 The abs sum of two for a pair of indices (P, Q) is the absolute value |A[P] + A[Q]|, for 0 ≤ P ≤ Q < N.
 For example, the following array A:
 A[0] =  1
 A[1] =  4
 A[2] = -3
 has pairs of indices (0, 0), (0, 1), (0, 2), (1, 1), (1, 2), (2, 2).
 The abs sum of two for the pair (0, 0) is A[0] + A[0] = |1 + 1| = 2.
 The abs sum of two for the pair (0, 1) is A[0] + A[1] = |1 + 4| = 5.
 The abs sum of two for the pair (0, 2) is A[0] + A[2] = |1 + (−3)| = 2.
 The abs sum of two for the pair (1, 1) is A[1] + A[1] = |4 + 4| = 8.
 The abs sum of two for the pair (1, 2) is A[1] + A[2] = |4 + (−3)| = 1.
 The abs sum of two for the pair (2, 2) is A[2] + A[2] = |(−3) + (−3)| = 6.
 Write a function:
 class Solution { public int solution(int[] A); }
 that, given a non-empty zero-indexed array A consisting of N integers, returns the minimal abs sum of two for any pair of indices in this array.
 For example, given the following array A:
 A[0] =  1
 A[1] =  4
 A[2] = -3
 the function should return 1, as explained above.
 Given array A:
 A[0] = -8
 A[1] =  4
 A[2] =  5
 A[3] =-10
 A[4] =  3
 the function should return |(−8) + 5| = 3.
 Assume that:
 N is an integer within the range [1..100,000];
 each element of array A is an integer within the range [−1,000,000,000..1,000,000,000].
 Complexity:
 expected worst-case time complexity is O(N*log(N));
 expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
 Elements of input arrays can be modified.
 */
public class MinAbsSumOfTwo {

    static MinAbsSumOfTwo that = new MinAbsSumOfTwo();

    public static void main(String [] args){

        compareCase2();
        compareCase1();

        exampleTest1();
        exampleTest2();
        compare();

    }

    public static void compareCase1(){

        int [] A = {-9,-3,2,5,};
        final int result = that.bruteForceSolution(A);

        if(that.solution(A) != 1)
            throw new RuntimeException("Wrong answer for example test expected:"+result+" got:" + that.solution(A));

        StackTraceElement[] stack = Thread.currentThread().getStackTrace();
        StackTraceElement e = stack[Thread.currentThread().getStackTrace().length-2];
        System.out.println(e.getMethodName() + " run successfully");
    }

    public static void compareCase2(){

        int [] A = {10,8,-3,2,};
        final int result = that.bruteForceSolution(A);

        if(that.solution(A) != 1)
            throw new RuntimeException("Wrong answer for example test expected:"+result+" got:" + that.solution(A));

        StackTraceElement[] stack = Thread.currentThread().getStackTrace();
        StackTraceElement e = stack[Thread.currentThread().getStackTrace().length-2];
        System.out.println(e.getMethodName() + " run successfully");
    }

    public static void exampleTest1() throws RuntimeException{
        final int result = 1;
        int [] A = new int[3];

        A[0] =  1;
        A[1] =  4;
        A[2] = -3;


        if(that.solution(A) != 1)
            throw new RuntimeException("Wrong answer for example test expected:"+result+" got:" + that.solution(A));

        StackTraceElement[] stack = Thread.currentThread().getStackTrace();
        StackTraceElement e = stack[Thread.currentThread().getStackTrace().length-2];
        System.out.println(e.getMethodName() + " run successfully");
    }

    public static void exampleTest2() throws RuntimeException{
        final int result = 3;
        int [] A = new int[5];

        A[0] = -8;
        A[1] =  4;
        A[2] =  5;
        A[3] = -10;
        A[4] =  3;


        if(that.solution(A) != result)
            throw new RuntimeException("Wrong answer for example test expected:"+result+" got:" + that.solution(A));

        StackTraceElement[] stack = Thread.currentThread().getStackTrace();
        StackTraceElement e = stack[Thread.currentThread().getStackTrace().length-2];
        System.out.println(e.getMethodName() + " run successfully");
    }

    public int solution(int[] A) {

        return caterpilarSolution(A);
    }

    /* Codility 100%*/
    public int smartSolution(int A[]){

        int N = A.length;

        int [] aClone = new int[N];
        int minAbs = Integer.MAX_VALUE;

        for(int i=0; i<N; i++) aClone[i] = A[i];

        Arrays.sort(aClone);

        for(int i=0; i<N; i++){

            int index = Arrays.binarySearch(aClone, -A[i]);

            if(index < 0){
                index = -(index+1);

                int current = Math.abs(A[i] + A[i]);

                if(index < N)
                    current = Math.min(current, Math.abs(A[i] + aClone[index]) );
                else
                    index = N-1;

                if(index-1 >= 0)
                    current = Math.min(current, Math.abs(A[i] + aClone[index-1])) ;

                if(index+1 < N && index-1 >= 0) {
                    current = Math.min(current, Math.abs(A[i] + aClone[index + 1]));
                }else{
                    current = Math.min(current, Math.abs(A[i] + aClone[N-1]));
                }
                minAbs = Math.min(minAbs, current);
            } else {
                return 0;
            }
        }

        return minAbs;
    }

    /* Codility 100% */
    public int caterpilarSolution(int [] A){
        int N = A.length;
        if(N == 0)return -1;
        if(N<2) return Math.abs(Math.abs(2*A[0]));

        int head, tail, min, tmp;
        Arrays.sort(A);

        if(A[0] >= 0) return Math.abs(2*A[0]);
        if(A[N-1] <= 0) return Math.abs(2*A[N-1]);

        head = 0;
        tail = N - 1;
        min = Math.abs(A[head] + A[tail]);
        while(head <= tail){
            tmp = A[head] + A[tail];
            min = Math.min(min, Math.abs(tmp));
            if(tmp == 0) return 0;
            else if (tmp > 0) tail--;
            else head++;
        }

        return  min;
    }

    public int bruteForceSolution(int [] A) {

        int N = A.length;
        if(N == 0)return -1;
        int minAbs = Integer.MAX_VALUE;

        for(int i=0; i<N; i++) {
            for(int j=i; j<N; j++){
                minAbs = Math.min(Math.abs(A[i]+A[j]), minAbs);
            }
        }

        return minAbs;
    }


    public static void compare(){

        int size = 100000000;
        Random random = new Random();
        int lastProcent = 0;
        System.out.println(lastProcent+"%");
        for (int i = 0; i < size; i++) {

            int randomNumber = random.nextInt(15);

            int[] A = new int[randomNumber];

            for (int j = 0; j < A.length; j++) {
                A[j] = random.nextInt(30) - 15;
            }

            int sol1 = that.bruteForceSolution(A);
            int sol2 = that.solution(A);

            int cP = (int)(((long)i*100)/size);

            if(cP > lastProcent){
                lastProcent = cP;
                System.out.println(cP+"%");
            }

            if (sol1 != sol2) {
                System.out.println(" i: " + i);
                System.out.println("sol1 =  " + sol1);
                System.out.println("sol2 =  " + sol2);

                for(int j=0 ;j<A.length; j++) System.out.print(A[j]+",");

                System.out.println();
            }


        }

    }

}
