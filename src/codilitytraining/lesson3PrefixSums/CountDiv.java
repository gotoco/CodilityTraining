package codilitytraining.lesson3PrefixSums;

/**
 Write a function:
 int solution(int A, int B, int K);
 that, given three integers A, B and K, returns the number of integers within the range [A..B] that are divisible by K, i.e.:
 { i : A ≤ i ≤ B, i mod K = 0 }
 For example, for A = 6, B = 11 and K = 2, your function should return 3, because there are three numbers divisible by 2 within the range [6..11], namely 6, 8 and 10.
 Assume that:
 A and B are integers within the range [0..2,000,000,000];
 K is an integer within the range [1..2,000,000,000];
 A ≤ B.
 Complexity:
 expected worst-case time complexity is O(1);
 expected worst-case space complexity is O(1).
 */

//TODO : currently 65% get 100%
public class CountDiv {

    public static void main(String[] args){

        CountDiv test = new CountDiv();

        System.out.println("First test case A = 6, B = 11, K = 2 : 3:" + test.solution(6,11,2));

        System.out.println("Second test case A = 0, B = MAX_INT, K = 2 : :" + test.solution(0,2000000000,1));

        System.out.println("3 test case A = 10, B = 10, K = 5 : 0 :" + test.solution(10,10,5));
        System.out.println("4 test case A = 10, B = 10, K = 7 : 0 :" + test.solution(10,10,7));
        System.out.println("5 test case A = 10, B = 10, K = 20 : 0 :" + test.solution(10,10,20));

    }


    public int solution(int A, int B, int K) {
        int a = (B)/K ;
        int b = (A-1)/K;



        return a-b;
    }
}
