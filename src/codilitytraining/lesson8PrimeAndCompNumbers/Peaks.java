package codilitytraining.lesson8PrimeAndCompNumbers;

/**
 A non-empty zero-indexed array A consisting of N integers is given.
 A peak is an array element which is larger than its neighbors. More precisely, it is an index P such that 0 < P < N − 1,  A[P − 1] < A[P] and A[P] > A[P + 1].
 For example, the following array A:
 A[0] = 1
 A[1] = 2
 A[2] = 3
 A[3] = 4
 A[4] = 3
 A[5] = 4
 A[6] = 1
 A[7] = 2
 A[8] = 3
 A[9] = 4
 A[10] = 6
 A[11] = 2
 has exactly three peaks: 3, 5, 10.
 We want to divide this array into blocks containing the same number of elements. More precisely, we want to choose a number K that will yield the following blocks:
 A[0], A[1], ..., A[K − 1],
 A[K], A[K + 1], ..., A[2K − 1],
 ...
 A[N − K], A[N − K + 1], ..., A[N − 1].
 What's more, every block should contain at least one peak. Notice that extreme elements of the blocks (for example A[K − 1] or A[K]) can also be peaks, but only if they have both neighbors (including one in an adjacent blocks).
 The goal is to find the maximum number of blocks into which the array A can be divided.
 Array A can be divided into blocks as follows:
 one block (1, 2, 3, 4, 3, 4, 1, 2, 3, 4, 6, 2). This block contains three peaks.
 two blocks (1, 2, 3, 4, 3, 4) and (1, 2, 3, 4, 6, 2). Every block has a peak.
 three blocks (1, 2, 3, 4), (3, 4, 1, 2), (3, 4, 6, 2). Every block has a peak. Notice in particular that the first block (1, 2, 3, 4) has a peak at A[3], because A[2] < A[3] > A[4], even though A[4] is in the adjacent block.
 However, array A cannot be divided into four blocks, (1, 2, 3), (4, 3, 4), (1, 2, 3) and (4, 6, 2), because the (1, 2, 3) blocks do not contain a peak. Notice in particular that the (4, 3, 4) block contains two peaks: A[3] and A[5].
 The maximum number of blocks that array A can be divided into is three.
 Write a function:
 class Solution { public int solution(int[] A); }
 that, given a non-empty zero-indexed array A consisting of N integers, returns the maximum number of blocks into which A can be divided.
 If A cannot be divided into some number of blocks, the function should return 0.
 For example, given:
 A[0] = 1
 A[1] = 2
 A[2] = 3
 A[3] = 4
 A[4] = 3
 A[5] = 4
 A[6] = 1
 A[7] = 2
 A[8] = 3
 A[9] = 4
 A[10] = 6
 A[11] = 2
 the function should return 3, as explained above.
 Assume that:
 N is an integer within the range [1..100,000];
 each element of array A is an integer within the range [0..1,000,000,000].
 Complexity:
 expected worst-case time complexity is O(N*log(log(N)));
 expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
 Elements of input arrays can be modified.
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Peaks {

    public static void main (String [] args) {

        Peaks p = new Peaks();

//        int [] firstTestCase = {1, 2, 3, 4, 3, 4, 1, 2, 3, 4, 6, 2};
//
//        System.out.println("First test case should return 3 : " + p.solution(firstTestCase));
//
//        int [] secondEmptyCase = {1, 1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
//
//        System.out.println("Second test case should return 0 : " + p.solution(secondEmptyCase));
//
//        int [] smallCase = {1,2,3};
//
//        System.out.println("Small case should return 0 : " + p.solution(smallCase));
//
//        int [] normalCase = {1,2,3, 1,2,3, 1,2,3};
//
//        System.out.println("normalCase should return 1 : " + p.solution(normalCase));
//
//        int [] nastyCase = {1,2,3, 1,2,3, 1,2,3, 1};
//
//        System.out.println("nastyCase should return 2 : " + p.solution(nastyCase));
//
//
        int [] nastyCase2 = {67,79,1,44,93,77,7,18,68,71,75,13,73,75,35,  };

        for(int j=0; j<nastyCase2.length; j++){
            System.out.print(nastyCase2[j]);
            System.out.print(",");
        }
        System.out.println("");

        //System.out.println("nastyCase2 should return 4 : " + p.solution(nastyCase2));
        System.out.println("nastyCase2 should return 4 : " + p.brutForceSolution(nastyCase2));


        int size = 10000 ;//Integer.MAX_VALUE;

        for(int i=0 ; i<size; i++){

            Random random = new Random() ;
            int randomNumber = random.nextInt(150);

            int sizeArray = randomNumber;
            int [] array = new int[sizeArray];

            for(int j=0; j<array.length; j++){
                array[j] = random.nextInt(100) ;
            }

            int sol1 =  p.solution(array) ;
            int sol2 = p.brutForceSolution(array) ;

            if(sol1 != sol2){
                System.out.println("  ");
                System.out.println("sol1 =  " + sol1);
                System.out.println("sol2 =  " + sol2);
                for(int k=0;k<sizeArray;k++){
                    System.out.print(array[k]);
                    System.out.print(",");
                }
                System.out.println("  ");
            }

        }

    }

       /*Codility 100%*/
    public int solution(int[] A) {

        int N = A.length;
        int [] peaksNumbers = new int[N];

        if(N<3)
            return 0;

        for(int i=1; i<(N-1); i++){

            if (A[i - 1] < A[i] && A[i] > A[i + 1]){
                peaksNumbers[i] = peaksNumbers[i-1]+1  ;
            } else {
                peaksNumbers[i] = peaksNumbers[i-1]  ;
            }
        }
        peaksNumbers[N-1] =  peaksNumbers[N-2];

        System.out.println("");
            for(int j=0; j<peaksNumbers.length; j++){
                System.out.print(peaksNumbers[j]);
                System.out.print(",");
            }

        List<Integer> divisors = new ArrayList<Integer>();

        for(int i=1; i<N+1; i++){
            if(N % i == 0){
                divisors.add(i);
            }
        }

        for(Integer d : divisors){
            boolean success = false;
            int last = 0;

            for(int i=d-1; i<N; i+=d) {
                  if(peaksNumbers[i] > last){
                      last = peaksNumbers[i];
                      success = true;
                  } else {
                      success = false;
                      break;
                  }
            }
            if(success)
                return N/d;
        }
        return 0;
    }

    /* This is funny but this also get on Codility 100%*/
    public int brutForceSolution(int [] A){
        int result = 0;
        int N = A.length;

        if(N<3)
            return 0;

        List<Integer> smallDivN = new ArrayList<Integer>();
        List<Integer> bigDivN = new ArrayList<Integer>();

        int d = 1;
        //First find whole divinders of N and put them in divN table
        while (N/d > d) {  // use '/' to avoid overflow
            if (N % d == 0){
                smallDivN.add(d);
                bigDivN.add(N/d);
            }
            d++;
        }
        if (N % d == 0 && N / d == d)
            bigDivN.add(d);

        Collections.reverse(smallDivN) ;
        bigDivN.addAll(smallDivN);

        //for each divider starting from big check if is possible to div array

        for(Integer div : bigDivN){
            int ileCzesci = div;
            int elems = N/div;
            boolean nextDiv=false;
            for(int s=0; s<N; s+=elems){

                for(int k=s; k<s+elems; k++){

                    if(k+1 < N && k-1>=0)
                        if(A[k] > A[k-1] && A[k] > A[k+1]){
                            break;
                        }

                    if(k+1 == s+elems){
                        nextDiv = true;
                        break;
                    }
                }
                if(nextDiv){
                    break;
                }

            }
            if(!nextDiv){
                result = div;
                break;
            }
        }

        return result;
    }
}


