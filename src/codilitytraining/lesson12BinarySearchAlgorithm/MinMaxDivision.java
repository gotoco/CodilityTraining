package codilitytraining.lesson12BinarySearchAlgorithm;

/**
 You are given integers K, M and a non-empty zero-indexed array A consisting of N integers. Every element of the array is not greater than M.
 You should divide this array into K blocks of consecutive elements. The size of the block is any integer between 0 and N. Every element of the array should belong to some block.
 The sum of the block from X to Y equals A[X] + A[X + 1] + ... + A[Y]. The sum of empty block equals 0.
 The large sum is the maximal sum of any block.
 For example, you are given integers K = 3, M = 5 and array A such that:
 A[0] = 2
 A[1] = 1
 A[2] = 5
 A[3] = 1
 A[4] = 2
 A[5] = 2
 A[6] = 2
 The array can be divided, for example, into the following blocks:
 [2, 1, 5, 1, 2, 2, 2], [], [] with a large sum of 15;
 [2], [1, 5, 1, 2], [2, 2] with a large sum of 9;
 [2, 1, 5], [], [1, 2, 2, 2] with a large sum of 8;
 [2, 1], [5, 1], [2, 2, 2] with a large sum of 6.
 The goal is to minimize the large sum. In the above example, 6 is the minimal large sum.
 Write a function:
 class Solution { public int solution(int K, int M, int[] A); }
 that, given integers K, M and a non-empty zero-indexed array A consisting of N integers, returns the minimal large sum.
 For example, given K = 3, M = 5 and array A such that:
 A[0] = 2
 A[1] = 1
 A[2] = 5
 A[3] = 1
 A[4] = 2
 A[5] = 2
 A[6] = 2
 the function should return 6, as explained above. Assume that:
 N and K are integers within the range [1..100,000];
 M is an integer within the range [0..10,000];
 each element of array A is an integer within the range [0..M].
 Complexity:
 expected worst-case time complexity is O(N*log(N+M));
 expected worst-case space complexity is O(1), beyond input storage (not counting the storage required for input arguments).
 Elements of input arrays can be modified.
 */
public class MinMaxDivision {

    public static void main(String [] args) {

        //exampleTest();
        allSameValues();

    }

    public static void exampleTest(){
        MinMaxDivision mm = new MinMaxDivision();
        int [] A = new int [7];
        A[0] = 2;
        A[1] = 1;
        A[2] = 5;
        A[3] = 1;
        A[4] = 2;
        A[5] = 2;
        A[6] = 2;

        System.out.println("Example should return 6 : " + mm.solution(3,5,A));
    }

    public static void allSameValues(){
        MinMaxDivision mm = new MinMaxDivision();
        int [] A = new int [7];
        A[0] = 5;
        A[1] = 5;
        A[2] = 5;
        A[3] = 5;
        A[4] = 5;
        A[5] = 5;
        A[6] = 5;

        System.out.println("allSameValues should return 35 : " + mm.solution(1, 6, A) );
    }

    public static void allSameValues2(){
        MinMaxDivision mm = new MinMaxDivision();
        int [] A = new int [20];
        A[0] = 5;
        A[1] = 5;
        A[2] = 5;
        A[3] = 5;
        A[4] = 5;
        A[5] = 5;
        A[6] = 5;
        A[7] = 5;
        A[8] = 5;
        A[9] = 5;
        A[10] = 5;
        A[11] = 5;
        A[12] = 5;
        A[13] = 5;
        A[14] = 5;
        A[15] = 5;
        A[16] = 5;
        A[17] = 5;
        A[18] = 5;
        A[19] = 5;

        System.out.println("allSameValues2 should return 10 : " + mm.solution(1,100,A) );
    }

    /*Codility checked 100%*/
    public int solution(int K, int M, int[] A) {

        int N = A.length;
        int max = -1;
        int tsum = 0;
        for(int i=0; i<N; i++) max = Math.max(max, A[i]);
        for(int i=0; i<N; i++) tsum += A[i];

        //Now we know that maxSum will be from max to tsum
        //So we will try to find it using binary search
        int begin = max;
        int end = tsum;
        int result = max;
        int mind;
        while(begin<=end)  {
            mind = (begin+end)/2;
            if(alg(K, mind, A)){
                result = mind;
                end = mind - 1;
            } else {
                begin = mind + 1;
            }

        }

       return result;
    }

    //Alg will check if we can divide array to K partition that each of them has sum less or equal to sum
    private boolean alg(int K, int cSum, int [] A){
        int partialSum = 0;
        int cK = 1;
        for(int i=0; i<A.length; i++){
            if(partialSum + A[i] <= cSum){
                partialSum+=A[i];
            } else {
                cK++;
                partialSum = A[i];
            }

            if(cK > K)
                break;
        }

        if(cK <= K)
            return true;

        return false;
    }
}
