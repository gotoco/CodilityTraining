package codilitytraining.lesson3PrefixSums;

/**
 A non-empty zero-indexed array A consisting of N integers is given. A pair of integers (P, Q), such that 0 ≤ P < Q < N,
 is called a slice of array A (notice that the slice contains at least two elements). The average of a slice (P, Q)
 is the sum of A[P] + A[P + 1] + ... + A[Q] divided by the length of the slice.
 To be precise, the average equals (A[P] + A[P + 1] + ... + A[Q]) / (Q − P + 1).
 For example, array A such that:
 A[0] = 4
 A[1] = 2
 A[2] = 2
 A[3] = 5
 A[4] = 1
 A[5] = 5
 A[6] = 8
 contains the following example slices:
 slice (1, 2), whose average is (2 + 2) / 2 = 2;
 slice (3, 4), whose average is (5 + 1) / 2 = 3;
 slice (1, 4), whose average is (2 + 2 + 5 + 1) / 4 = 2.5.
 The goal is to find the starting position of a slice whose average is minimal.
 Write a function:
 class Solution { public int solution(int[] A); }
 that, given a non-empty zero-indexed array A consisting of N integers,
 returns the starting position of the slice with the minimal average.
 If there is more than one slice with a minimal average, you should return the smallest starting position of such a slice.
 For example, given array A such that:
 A[0] = 4
 A[1] = 2
 A[2] = 2
 A[3] = 5
 A[4] = 1
 A[5] = 5
 A[6] = 8
 the function should return 1, as explained above.
 Assume that:
 N is an integer within the range [2..100,000];
 each element of array A is an integer within the range [−10,000..10,000].
 Complexity:
 expected worst-case time complexity is O(N);
 expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
 Elements of input arrays can be modified.
 */
public class MinAvgTwoSlice {

    public static void main(String[] args){

        MinAvgTwoSlice test = new MinAvgTwoSlice();

        int [] testCase = {4,2,2,5,1,5,8};

        System.out.println("Result : 1 : " + test.solution(testCase));

        int [] testCase2 = {4,2,2,5,1,-5,8};

        System.out.println("Result2 : 4 : " + test.solution(testCase2));

        int [] testCase3 = {1, 2, 4, 2, 0, 0, 2, 5, 1, -5, -2, -3, -4, 0, 8};

        System.out.println("Result3 : 9 : " + test.solution(testCase3));

        int [] testCase4 = {95, 1, 101, 1, 92, 53, 51, 55};

        System.out.println("Result4 : 1 : " + test.solution(testCase4));

        int [] testCase5 = {-1, 1, -1, 1, 1, 0, 1, -12};

        System.out.println("Result5 : 5 : " + test.solution(testCase5));

        int [] testCase6 = {-1, 1, -1, 1, -1, 1, -1, -1, 1, -1, 2};

        System.out.println("Result6 : 6 : " + test.solution(testCase6));
    }

    /* Codility : 100% */
    public int solution(int[] A) {
        //returns the starting position of the slice with the minimal average
        int result = 0;
        int[] sum= new int[A.length];

        int tmpSum = 0;
        for(int i=0; i< A.length; i++)  {
            tmpSum += A[i];
            sum[i] = tmpSum;
        }

        //For sum[0] and sum[1] make calculation without loop because ArrayIndexOutOfBoundsException
        tmpSum = sum[1];
        double minAverage = tmpSum/2.0;

        if(A.length ==2) return 0;

        if( A[2] < minAverage){
            tmpSum = sum[2];
            minAverage = (double)(tmpSum)/(3);
        }

        for(int i=1; i+1<sum.length; i++){
            int tmp = i;

            //calculate current average
            tmpSum = sum[i+1]-sum[tmp-1];
            double currentAverage = (double)(tmpSum)/(i-tmp+2);

            if(i+2<sum.length && A[i+2] < currentAverage){
                tmpSum = sum[i+2]-sum[tmp-1];
                currentAverage = (double)(tmpSum)/(i-tmp+3);
            }

            if(currentAverage<minAverage){
                minAverage=currentAverage;
                result = tmp;
            }
        }

        return result;
    }

}
