package codilitytraining.lesson3PrefixSums;

/**
 A non-empty zero-indexed string S is given. String S consists of N characters from the set
 of upper-case English letters A, C, G, T.
 This string actually represents a DNA sequence, and the upper-case letters represent single nucleotides.
 You are also given non-empty zero-indexed arrays P and Q consisting of M integers.
 These arrays represent queries about minimal nucleotides. We represent the letters of string S
 as integers 1, 2, 3, 4 in arrays P and Q, where A = 1, C = 2, G = 3, T = 4, and we assume that A < C < G < T.
 Query K requires you to find the minimal nucleotide from the range (P[K], Q[K]), 0 ≤ P[i] ≤ Q[i] < N.
 For example, consider string S = GACACCATA and arrays P, Q such that:
 P[0] = 0    Q[0] = 8
 P[1] = 0    Q[1] = 2
 P[2] = 4    Q[2] = 5
 P[3] = 7    Q[3] = 7
 The minimal nucleotides from these ranges are as follows:
 (0, 8) is A identified by 1,
 (0, 2) is A identified by 1,
 (4, 5) is C identified by 2,
 (7, 7) is T identified by 4.
 Write a function:
 class Solution { public int[] solution(String S, int[] P, int[] Q); }
 that, given a non-empty zero-indexed string S consisting of N characters and two non-empty zero-indexed
 arrays P and Q consisting of M integers, returns an array consisting of M characters specifying the
 consecutive answers to all queries.
 The sequence should be returned as:
 a Results structure (in C), or
 a vector of integers (in C++), or
 a Results record (in Pascal), or
 an array of integers (in any other programming language).
 For example, given the string S = GACACCATA and arrays P, Q such that:
 P[0] = 0    Q[0] = 8
 P[1] = 0    Q[1] = 2
 P[2] = 4    Q[2] = 5
 P[3] = 7    Q[3] = 7
 the function should return the values [1, 1, 2, 4], as explained above.
 Assume that:
 N is an integer within the range [1..100,000];
 M is an integer within the range [1..50,000];
 each element of array P, Q is an integer within the range [0..N − 1];
 P[i] ≤ Q[i];
 string S consists only of upper-case English letters A, C, G, T.
 Complexity:
 expected worst-case time complexity is O(N+M);
 expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
 Elements of input arrays can be modified.
 */

/* Codility 100%*/
public class GenomicRangeQuery1 {

    public static void main(String[] args){

        GenomicRangeQuery1 test = new GenomicRangeQuery1();
        //Test case 1:
        int [] p = {0,0,4,7};
        int [] q = {8,2,5,7};
        String s = "GACACCATA";

        int [] result = test.solution(s,p,q);

        for(Integer element : result){
            System.out.print(element + ",");
        }
        System.out.println("");

        //Test case 2:
        int [] p2 = {0};
        int [] q2 = {0};
        String s2 = "A";

        int [] result2 = test.solution(s2,p2,q2);

        for(Integer element : result2){
            System.out.print(element + ",");
        }
        System.out.println("");

        //Test case 1:
        int [] p3 = {0, 0, 2};
        int [] q3 = {0, 1, 3};
        String s3 = "ACAC";

        int [] result3 = test.solution(s3,p3,q3);

        for(Integer element : result3){
            System.out.print(element + ",");
        }
        System.out.println("");
    }

    public int[] solution(String S, int[] P, int[] Q) {
        int N = S.length();
        int M = P.length;
        int [][] genomicOccurance = new int[N][4] ;

        //At first step calculate occurrence of nucleons
        for(int i=0; i<N; i++){

            char nucleon = S.charAt(i);

            switch(nucleon){
                case 'A' :  genomicOccurance[i][0]++; break;
                case 'C' :  genomicOccurance[i][1]++; break;
                case 'G' :  genomicOccurance[i][2]++; break;
                case 'T' :  genomicOccurance[i][3]++; break;
            }
        }

        //At second step count nucleons due whole gen
        for(int i=1; i<N; i++){
            genomicOccurance[i][0] += genomicOccurance[i-1][0];
            genomicOccurance[i][1] += genomicOccurance[i-1][1];
            genomicOccurance[i][2] += genomicOccurance[i-1][2];
            genomicOccurance[i][3] += genomicOccurance[i-1][3];
        }

        //At least iterate P and Q if occurrence at start P[i] != occurrence at end Q[i]
        // that means we encounter this nucleon at this range!
        for(int i=0; i<M; i++){
            int startRange = P[i];
            int endRange = Q[i];
            int result = 0;

            if(startRange == 0) {
                if(genomicOccurance[endRange][0] != 0) result = 1;
                else if(genomicOccurance[endRange][1] != 0) result = 2;
                else if(genomicOccurance[endRange][2] != 0) result = 3;
                else if(genomicOccurance[endRange][3] != 0) result = 4;
            }
            else if(P[i] == Q[i] ) {
                if(genomicOccurance[startRange][0] != genomicOccurance[startRange-1][0]) result = 1;
                else if(genomicOccurance[startRange][1] != genomicOccurance[startRange-1][1]) result = 2;
                else if(genomicOccurance[startRange][2] != genomicOccurance[startRange-1][2]) result = 3;
                else if(genomicOccurance[startRange][3] != genomicOccurance[startRange-1][3]) result = 4;
            }
            else if(genomicOccurance[endRange][0] - genomicOccurance[startRange-1][0] != 0) result = 1;
            else if(genomicOccurance[endRange][1] - genomicOccurance[startRange-1][1] != 0) result = 2;
            else if(genomicOccurance[endRange][2] - genomicOccurance[startRange-1][2] != 0) result = 3;
            else if(genomicOccurance[endRange][3] - genomicOccurance[startRange-1][3] != 0) result = 4;

            //Because we still don't need P[i] jet lets use it to handle a result!
            P[i] = result;
        }

        return P;
    }


}

