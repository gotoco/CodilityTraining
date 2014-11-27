package codilitytraining.lesson9SieveOfEratosthenes;

/**
 You are given a non-empty zero-indexed array A consisting of N integers.
 For each number A[i] such that 0 ≤ i < N, we want to count the number of elements of the array that are not the divisors of A[i]. We say that these elements are non-divisors.
 For example, consider integer N = 5 and array A such that:
 A[0] = 3
 A[1] = 1
 A[2] = 2
 A[3] = 3
 A[4] = 6
 For the following elements:
 A[0] = 3, the non-divisors are: 2, 6,
 A[1] = 1, the non-divisors are: 3, 2, 3, 6,
 A[2] = 2, the non-divisors are: 3, 3, 6,
 A[3] = 3, the non-divisors are: 2, 6,
 A[6] = 6, there aren't any non-divisors.
 Write a function:
 class Solution { public int[] solution(int[] A); }
 that, given a non-empty zero-indexed array A consisting of N integers, returns a sequence of integers representing the amount of non-divisors.
 The sequence should be returned as:
 a structure Results (in C), or
 a vector of integers (in C++), or
 a record Results (in Pascal), or
 an array of integers (in any other programming language).
 For example, given:
 A[0] = 3
 A[1] = 1
 A[2] = 2
 A[3] = 3
 A[4] = 6
 the function should return [2, 4, 3, 2, 0], as explained above.
 Assume that:
 N is an integer within the range [1..50,000];
 each element of array A is an integer within the range [1..2 * N].
 Complexity:
 expected worst-case time complexity is O(N*log(N));
 expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
 Elements of input arrays can be modified.
 */
import java.util.Arrays;
import java.util.Random;

public class CountNonDivisible {

    /*Simple test to find ege cases for smart algorithm*/
    public static void deepCompare(CountNonDivisible d) {

        int size = Integer.MAX_VALUE;

        for(int i=0 ; i<size; i++){

            Random random = new Random() ;
            int randomNumber = random.nextInt(100);

            int sizeArray = randomNumber;
            int [] array1 = new int[sizeArray];
            int [] array2 = new int[sizeArray];

            for(int j=0; j<array1.length; j++){
                array1[j] = random.nextInt(50)+1;
                array2[j] = array1[j]+random.nextInt(50);
            }

            int [] sol1 =  d.solution(array1) ;
            int [] sol2 =  d.brutForceSolution(array1) ;

            boolean areEqual = true;

            for(int k=0; k<array1.length; k++){

                if(sol1[k] != sol2[k]){
                    areEqual = false;
                }

            }

            if(!areEqual){
                for(int k=0; k<array1.length; k++){
                    System.out.print(array1[k]+",");
                }
                System.out.println("\n Result should be: ");

                for(int k=0; k<array1.length; k++){
                    System.out.print(sol2[k]+",");
                }

                System.out.println("\n But is... ");

                for(int k=0; k<array1.length; k++){
                    System.out.print(sol1[k]+",");
                }

                System.out.println(" ...");
            }

        }

    }

    public static void main(String [] args){

        CountNonDivisible s = new CountNonDivisible();

//        int [] testCase1 = {3, 1, 2, 3, 6};
//        int [] result1   = {2, 4, 3, 2, 0};
//
//        int [] solution1 = s.solution(testCase1);
//        for(int i=0; i<testCase1.length; i++){
//            System.out.println("result1[i] :" + result1[i] + " solution1[i] :" + solution1[i] +" res ="+ (result1[i] == solution1[i]));
//        }
//
//        int [] testCase2 = {5, 5, 5, 5, 5, 5};
//        int [] result2   = {0, 0, 0, 0, 0, 0};
//
//        int [] solution2 = s.solution(testCase2);
//        for(int i=0; i<testCase2.length; i++){
//            System.out.println("result2[i] :" + result2[i] + " solution2[i] :" + solution2[i] +" res ="+ (result2[i] == solution2[i]));
//        }
//
//       int [] testCase3 = {1, 5};
//       int [] result3   = {1, 0};
//
//       int [] solution3 = s.solution(testCase3);
//       for(int i=0; i<testCase3.length; i++){
//           System.out.println("result3[i] :" + result3[i] + " solution3[i] :" + solution3[i] +" res ="+ (result3[i] == solution3[i]));
//       }

//         int [] testCase4 = {32,23,36,3,40,6,31,49};
//         int [] result4   = {7,7,5,7,7,6,7,7};
//
//         int [] solution4 = s.solution(testCase4);
//         for(int i=0; i<testCase4.length; i++){
//             System.out.println("result4[i] :" + result4[i] + " solution4[i] :" + solution4[i] +" res ="+ (result4[i] == solution4[i]));
//         }

        deepCompare(s);

    }

    public int[] solution(int[] A) {
        return smarterSolution(A);
    }

    /*Codility 100% */
    public int[] smarterSolution(int[]A){
        int N = A.length;

        //find max in A!
        int maxInA = 0;

        for(int i=0; i<N; i++){
            maxInA = Math.max(A[i], maxInA);
        }

        int nN = maxInA+1;

        //Hold occurance of number in A array
        int [] numberCnt = new int [nN];
        int [] divinders = new int [N]; //That table hold counter of number i divindors
        int numberOfNumbers = 0;

        //Step first count each numbers in the A: O(N) operation
        for(int i=0; i<N; i++){
            numberCnt[A[i]]++;
        }

        for(int i=1; i<nN; i++){
            numberOfNumbers += numberCnt[i];
        }

        int [] result = new int[N];

        for(int i=0; i<N; i++){

            int cur = A[i];

            if(cur == 1){
                divinders[i] = numberCnt[1];
                result[i] = numberOfNumbers - divinders[i];
                continue;
            }

            for(int j=1; j*j<=cur; j++){
                if(cur%j == 0){
                    divinders[i] += numberCnt[j];
                    if(j != A[i]/j)
                    divinders[i] += numberCnt[A[i]/j];
                }
            }
            result[i] = numberOfNumbers - divinders[i];
        }

        return result;
    }

    public int[] brutForceSolution(int[] A) {
        int N = A.length;

        //find max in A!
        int maxInA = 0;

        for(int i=0; i<N; i++){
            maxInA = Math.max(A[i], maxInA);
        }

        int nN = maxInA+1;

        //Hold occurance of number in A array
        int [] numberCnt = new int [nN];

        //Step first count each numbers in the A: O(N) operation
        for(int i=0; i<N; i++){
            numberCnt[A[i]]++;
        }

        int [] result = new int[N];

        //Main loop: N time executed
            // 1. for each member of numberCnt find it factors by while(i*i<N) loop O(log(N)
            // 2. check iho many times this factor occur in numberCnt
        for(int i=0; i<N; i++) {

            int cur = A[i];
            for(int j=1; j <nN; j++){
                if(cur%j != 0){
                    result[i] += numberCnt[j];
                }
            }
        }

        return result;
    }

}
