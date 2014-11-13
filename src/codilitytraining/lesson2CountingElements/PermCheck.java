package codilitytraining.lesson2CountingElements;

import java.util.Arrays;

/**
 A non-empty zero-indexed array A consisting of N integers is given.
 A permutation is a sequence containing each element from 1 to N once, and only once.
 For example, array A such that:
 A[0] = 4
 A[1] = 1
 A[2] = 3
 A[3] = 2
 is a permutation, but array A such that:
 A[0] = 4
 A[1] = 1
 A[2] = 3
 is not a permutation.
 The goal is to check whether array A is a permutation.
 Write a function:
 class Solution { public int solution(int[] A); }
 that, given a zero-indexed array A, returns 1 if array A is a permutation and 0 if it is not.
 For example, given array A such that:
 A[0] = 4
 A[1] = 1
 A[2] = 3
 A[3] = 2
 the function should return 1.
 Given array A such that:
 A[0] = 4
 A[1] = 1
 A[2] = 3
 the function should return 0.
 Assume that:
 N is an integer within the range [1..100,000];
 each element of array A is an integer within the range [1..1,000,000,000].
 Complexity:
 expected worst-case time complexity is O(N);
 expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
 Elements of input arrays can be modified.
 */
public class PermCheck {

    public static void main(String [] args)     {
        PermCheck test = new PermCheck();

        int [] testCase1 = {4,1,3,2} ;
        int [] testCase2 = {4,1,3};
        int [] testCase3 = {3};
        int [] testCase4 = {1};
        int [] testCase5 = {1, 2, 2, 4, 4};
        int [] testCase6 = {1, 2, 2, 5};

        System.out.println("Result for testCase1 : " + test.solution(testCase1));
        System.out.println("Result for testCase2 : " + test.solution(testCase2));
        System.out.println("Result for testCase3 : " + test.solution(testCase3));
        System.out.println("Result for testCase4 : " + test.solution(testCase4));
        System.out.println("Result for testCase5 : " + test.solution(testCase5));
        System.out.println("Result for testCase6 : " + test.solution(testCase6));
    }


    public int solution(int[] A) {
        int result = 0;
        boolean duplicates[] = new boolean[A.length] ;

        Arrays.fill(duplicates, false);

        //We will sum elements in array, and indexes, if this will be a permutation
        //sums should be equals and they wouldn't have duplicates
        int expectedSum = 0;
        int elementsSum = 0;

        for(int i=0; i<A.length; i++){
            if(A[i] > A.length || duplicates[A[i]-1]){
                result = 0;
                return result;
            }

            duplicates[A[i]-1] = true;

            elementsSum += A[i];
            expectedSum += i+1;

        }

        if(elementsSum == expectedSum){
            result = 1;
        }

        return result;
    }

}
