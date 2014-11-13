package codilitytraining.lesson4Sorting;
/**
 A non-empty zero-indexed array A consisting of N integers is given. The product of triplet (P, Q, R) equates to A[P] * A[Q] * A[R] (0 ≤ P < Q < R < N).
 For example, array A such that:
 A[0] = -3
 A[1] = 1
 A[2] = 2
 A[3] = -2
 A[4] = 5
 A[5] = 6
 contains the following example triplets:
 (0, 1, 2), product is −3 * 1 * 2 = −6
 (1, 2, 4), product is 1 * 2 * 5 = 10
 (2, 4, 5), product is 2 * 5 * 6 = 60
 Your goal is to find the maximal product of any triplet.
 Write a function:
 class Solution { public int solution(int[] A); }
 that, given a non-empty zero-indexed array A, returns the value of the maximal product of any triplet.
 For example, given array A such that:
 A[0] = -3
 A[1] = 1
 A[2] = 2
 A[3] = -2
 A[4] = 5
 A[5] = 6
 the function should return 60, as the product of triplet (2, 4, 5) is maximal.
 Assume that:
 N is an integer within the range [3..100,000];
 each element of array A is an integer within the range [−1,000..1,000].
 Complexity:
 expected worst-case time complexity is O(N*log(N));
 expected worst-case space complexity is O(1), beyond input storage (not counting the storage required for input arguments).
 */
import java.util.Arrays;


public class MaxProductOfThree {

    public static void main(String[] args){

        MaxProductOfThree test = new MaxProductOfThree();

        int[] testCase1 = {-3, 1, 2, -2, 5, 6};
        System.out.println("First test case should return : 60 : " + test.solutionSort(testCase1));

        int[] tc2 = {-3, 25, 31, -97, -25, 6};
        System.out.println("2 test case should return : 75175 : " + test.solutionSort(tc2));


        int[] tc3 = {-3, 2, -1, 5};
        System.out.println("2 test case should return : 15 : " + test.solutionSort(tc3));
        //2000 * (-10..10) + [-1000, 500, -1]
        int[] tc4 = {-10,10,10,10,-10,10,-10, -1000, 500, -1};
        System.out.println("2 test case should return : 5000000 : " + test.solutionSort(tc4));

        //-1000, -999, ... 1000
        int[] tc5 = {-1000, -999, - 998, 0, 998, 999, 1000};
        System.out.println("2 test case should return : 5000000 : " + test.solutionSort(tc5));
    }

    public int solution(int[] A) {

        if(A.length == 3){
            return A[1]*A[0]*A[2];
        }

        //Array of max 3 positive numbers
        int[] mT = {0, 0, 0};
        //Array of max 3 negative numbers
        int[] nmT = {0, 0, 0};

        for(int i=0; i<A.length; i++){

            int v = A[i];

            for(int j = 2; j>=0; j--){

                if(v >= 0){

                    if(v> mT[j]) {int tmp=mT[j]; mT[j] = v; v = tmp;  }
                } else {
                    if(v< nmT[j]) {int tmp=nmT[j]; nmT[j] = v; v = tmp;  }
                }

            }

        }

        if(nmT[1]*nmT[2] > mT[1]*mT[2] || (mT[0] == 0)) {
            return mT[2]*nmT[1]*nmT[2];
        }

        return mT[0]*mT[1]*mT[2];
    }

    /*Codility 100%*/
    public int solutionSort(int[] A) {
        int N = A.length;
        Arrays.sort(A);
        int right = A[N - 1] * A[N - 2] * A[N - 3];
        int left = A[N - 1] * A[0] * A[1];

        return left > right ? left : right;
    }

}
