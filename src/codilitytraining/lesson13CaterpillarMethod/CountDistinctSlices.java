package codilitytraining.lesson13CaterpillarMethod;

import java.util.HashSet;
import java.util.Set;

/**
 An integer M and a non-empty zero-indexed array A consisting of N non-negative integers are given. All integers in array A are less than or equal to M.
 A pair of integers (P, Q), such that 0 ≤ P ≤ Q < N, is called a slice of array A. The slice consists of the elements A[P], A[P + 1], ..., A[Q]. A distinct slice is a slice consisting of only unique numbers. That is, no individual number occurs more than once in the slice.
 For example, consider integer M = 6 and array A such that:
 A[0] = 3
 A[1] = 4
 A[2] = 5
 A[3] = 5
 A[4] = 2
 There are exactly nine distinct slices: (0, 0), (0, 1), (0, 2), (1, 1), (1, 2), (2, 2), (3, 3), (3, 4) and (4, 4).
 The goal is to calculate the number of distinct slices.
 Write a function:
 class Solution { public int solution(int M, int[] A); }
 that, given an integer M and a non-empty zero-indexed array A consisting of N integers, returns the number of distinct slices.
 If the number of distinct slices is greater than 1,000,000,000, the function should return 1,000,000,000.
 For example, given integer M = 6 and array A such that:
 A[0] = 3
 A[1] = 4
 A[2] = 5
 A[3] = 5
 A[4] = 2
 the function should return 9, as explained above.
 Assume that:
 N is an integer within the range [1..100,000];
 M is an integer within the range [0..100,000];
 each element of array A is an integer within the range [0..M].
 Complexity:
 expected worst-case time complexity is O(N);
 expected worst-case space complexity is O(M), beyond input storage (not counting the storage required for input arguments).
 Elements of input arrays can be modified.
 */
public class CountDistinctSlices {

    public static void main(String [] args){

        mirrorTest();

//        exampleTest();
//        myTest();
//        secondTest();
//        mirrorTest();
    }

    public static void exampleTest(){
        CountDistinctSlices cds = new CountDistinctSlices();

        int A [] = new int [5];

        A[0] = 3;
        A[1] = 4;
        A[2] = 5;
        A[3] = 5;
        A[4] = 2;

        System.out.println("Example test should return 9 : " +cds.solution(5, A));
    }

    public static void myTest(){
        CountDistinctSlices cds = new CountDistinctSlices();

        int A [] = new int [7];

        A[0] = 3;
        A[1] = 4;
        A[2] = 5;
        A[3] = 5;
        A[4] = 5;
        A[5] = 5;
        A[6] = 3;

        System.out.println("Example test should return 11 : " +cds.solution(5, A));

    }

    public static void secondTest(){
        CountDistinctSlices cds = new CountDistinctSlices();

        int A [] = new int [5];

        A[0] = 3;
        A[1] = 4;
        A[2] = 5;
        A[3] = 3;
        A[4] = 4;

        System.out.println("Example test should return 12 : " +cds.solution(5, A));

    }

    public static void mirrorTest(){
        CountDistinctSlices cds = new CountDistinctSlices();
        int B [] = new int [6];

        B[0] = 3;
        B[1] = 4;
        B[2] = 5;
        B[3] = 6;
        B[4] = 4;
        B[5] = 3;

        System.out.println("Example test should return 17 : " +cds.solution(6, B));
    }

    /*Codility 100% */
    public int solution(int M, int[] A) {

        int N = A.length;
        Set<Integer> set = new HashSet<Integer>();
        int result = 0;
        int front=0, end=0;
        int cnt=0;
        for(end=0; end<N; end++) {

            while(front <= end && set.contains(A[end])) {
                set.remove(A[front]);
                front++;
                cnt--;
            }
            cnt++;
            set.add(A[end]);
            result+=cnt;

            if(result >1000000000 ) return 1000000000;
        }

        return result;
    }
}
