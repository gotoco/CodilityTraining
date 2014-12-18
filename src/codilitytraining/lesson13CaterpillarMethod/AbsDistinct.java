package codilitytraining.lesson13CaterpillarMethod;

import java.util.*;

/**
 A non-empty zero-indexed array A consisting of N numbers is given. The array is sorted in non-decreasing order. The absolute distinct count of this array is the number of distinct absolute values among the elements of the array.
 For example, consider array A such that:
 A[0] = -5
 A[1] = -3
 A[2] = -1
 A[3] =  0
 A[4] =  3
 A[5] =  6
 The absolute distinct count of this array is 5, because there are 5 distinct absolute values among the elements of this array, namely 0, 1, 3, 5 and 6.
 Write a function:
 class Solution { public int solution(int[] A); }
 that, given a non-empty zero-indexed array A consisting of N numbers, returns absolute distinct count of array A.
 For example, given array A such that:
 A[0] = -5
 A[1] = -3
 A[2] = -1
 A[3] =  0
 A[4] =  3
 A[5] =  6
 the function should return 5, as explained above.
 Assume that:
 N is an integer within the range [1..100,000];
 each element of array A is an integer within the range [−2,147,483,648..2,147,483,647];
 array A is sorted in non-decreasing order.
 Complexity:
 expected worst-case time complexity is O(N);
 expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
 */
public class AbsDistinct {

    public static void main(String [] args){
        compare();

        compareProblem2();

        exampleTest();
        compareProblems();
        compareProblem2();
        myTest();
        oneSignsElements();
        oneElement();
        sameElements();
    }

    public static void compare() {
        AbsDistinct ad = new AbsDistinct();

        int size = 100000000;
        Random random = new Random();
        int lastProcent = 0;
        System.out.println(lastProcent+"%");
        for (int i = 0; i < size; i++) {

            int randomNumber = random.nextInt(8);

            int[] A1 = new int[randomNumber];
            int[] A2 = new int[randomNumber];
            int[] A = new int[randomNumber];

            for (int j = 0; j < A.length; j++) {
                A[j] = random.nextInt(15) - 8;
            }

            Arrays.sort(A);

            for (int j = 0; j < A.length; j++) {
                A1[j] = A[j];
                A2[j] = A[j];
            }

            int sol1 = ad.bruteForceSolution(A1);
            int sol2 = ad.solution(A2);


            int cP = (i*100)/size;

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

    public static void compareProblems(){
        AbsDistinct ad = new AbsDistinct();
        int [] A ={-7,-2,-1,3}   ;

        System.out.println("compareProblems should return 4 : " + ad.solution(A));
    }

    public static void compareProblem2(){
        AbsDistinct ad = new AbsDistinct();
        int [] A = {-7, -6, -6, -2, 1, 4, 6,};

        System.out.println("CompareProblem2 should return 5 : " + ad.solution(A));

        int [] B = {-3,-2,0,3,4,5,6,};
        int [] B1 = {-3,-2,0,3,4,5,6,};

        System.out.println("CompareProblem2 should return " + ad.bruteForceSolution(B1) + " - "+ ad.solution(B));

        int [] C = {-2,-2,-1,0,2,4,6,};
        int [] C1 = {-2,-2,-1,0,2,4,6,};

        System.out.println("CompareProblem2 should return " + ad.bruteForceSolution(C1) + " - "+ ad.solution(C));

        int [] D = {-8,-6,-6,-1,0,3,6};
        int [] D1 = {-8,-6,-6,-1,0,3,6};

        System.out.println("CompareProblem2 should return " + ad.bruteForceSolution(D1) + " - "+ ad.solution(D));
    }

    public static void exampleTest(){
        AbsDistinct ad = new AbsDistinct();
        int [] A = new int[6];

        A[0] = -5;
        A[1] = -3;
        A[2] = -1;
        A[3] =  0;
        A[4] =  3;
        A[5] =  6;

        System.out.println("Example Test case should return 5 : " + ad.solution(A));

    }

    public static void myTest(){
        AbsDistinct ad = new AbsDistinct();
        int [] A = new int[15];

        A[0]  = -8;
        A[1]  = -5;
        A[2]  = -3;
        A[3]  = -3;
        A[4]  = -3;
        A[5]  = -3;
        A[6]  =  0;
        A[7]  =  3;
        A[8]  =  3;
        A[9]  =  3;
        A[10] =  3;
        A[11] =  6;
        A[12] =  8;
        A[13] =  8;
        A[14] =  9;

        System.out.println("Example Test case should return  6 : " + ad.solution(A));

    }

    public static void oneSignsElements(){
        AbsDistinct ad = new AbsDistinct();
        int [] A = new int[2];

        A[0]  = -8;
        A[1]  = -6;

        System.out.println("Example Test case should return 2 : " + ad.solution(A));
    }

    public static void oneElement(){
        AbsDistinct ad = new AbsDistinct();
        int [] A = new int[1];

        A[0]  = -8;

        System.out.println("Example Test case should return 1 : " + ad.solution(A));
    }

    public static void sameElements(){
        AbsDistinct ad = new AbsDistinct();
        int [] A = new int[6];

        A[0]  = -8;
        A[1]  = -8;
        A[2]  = -8;
        A[3]  = -8;
        A[4]  = 5;
        A[5]  = 5;

        System.out.println("sameElements1 case should return 2 : " + ad.solution(A));


        A[0]  = -8;
        A[1]  = -8;
        A[2]  = -8;
        A[3]  = -8;
        A[4]  = -5;
        A[5]  = -5;

        System.out.println("sameElements2 case should return 2 : " + ad.solution(A));

        A[0]  = 8;
        A[1]  = 8;
        A[2]  = 8;
        A[3]  = 8;
        A[4]  = 5;
        A[5]  = 5;

        System.out.println("sameElements case should return 2 : " + ad.solution(A));

    }

    public int solution(int[] A) {

        return shorterSolution(A);
    }

    /**
     * This task is possible to solve with O(N) Time complexity and O(1) space!
     * Without java.util etc.
     * but algorithm is not easy
     */
    public int difficultSolution(int[] A) {
        int N = A.length;
        if(N == 0) return 0;

        //Find zero point (or negation point)
        int zero = -1;
        for(int i=1; i<N; i++){if(A[i-1]<0 && A[i]>=0) zero=i;}
        int solution = 1;
        if(zero==-1) {
            zero=0;
        }
        int front = zero; int end=zero;
        //Starting from zero:
        int lastFront = Math.abs(A[zero]);
        int lastEnd = Math.abs(A[zero]);

        while(front>=0 || end<N){

            int frontEl;
            if(front>=0)
                frontEl = Math.abs(A[front]);
            else
                frontEl = Math.abs(A[0]);

            int endEl;
            if(end<N)
                endEl = Math.abs(A[end]);
            else
                endEl = Math.abs(A[N-1]);

            if(frontEl>endEl){
                while(frontEl>=endEl && end<N){
                    end++;
                    if(end<N)
                        endEl = Math.abs(A[end]);
                    else
                        endEl = Math.abs(A[N-1]);

                    if(endEl != lastEnd && endEl!=lastFront){
                        solution++;
                        lastEnd=endEl;
                    }
                }
            }

            if(frontEl<endEl){
                while(frontEl<=endEl && front>=0){
                    front--;
                    if(front>=0)
                        frontEl = Math.abs(A[front]);
                    else
                        frontEl = Math.abs(A[0]);

                    if(frontEl != lastFront && frontEl!=lastEnd){
                        solution++;
                        lastFront=frontEl;
                    }
                }
            }

            if(frontEl==endEl){
                if(frontEl==lastFront && front>0){
                    front--;
                    frontEl = Math.abs(A[front]);
                    if(frontEl!=lastFront && frontEl!=lastEnd){
                        solution++;
                        lastFront = frontEl;
                    }
                }
                else if(endEl==lastEnd && end<N-1){
                    end++;
                    endEl = Math.abs(A[end]);
                    if(endEl!=lastFront && endEl!=lastEnd){
                        solution++;
                        lastEnd=endEl;
                    }
                }
            }

            if(end>=N-1){
                while(front>=0){
                    frontEl = Math.abs(A[front]);
                    if(frontEl!=lastFront && frontEl!=lastEnd) {
                        solution++;
                        lastFront = frontEl;
                    }
                    front--;
                }
            }

            if(front<=0) {
                while(end<=N-1){
                    endEl = Math.abs(A[end]);
                    if(endEl!=lastFront && endEl!=lastEnd) {
                        solution++;
                        lastEnd = endEl;
                    }
                    end++;
                }
            }

        }

        return solution;
    }

    /**
     * Using Java.util is pretty cool
     */
    public int shorterSolution(int [] A){
        int N = A.length;

        Set<Integer> set= new HashSet<Integer>();

        for(int i=0; i<N; i++){
            set.add(Math.abs(A[i]));
        }

        return set.size();
    }

    public int refactoredSolution(int [] A){
        int newLength = 0;

        for(int i=1;i<A.length;i++) {
            if(A[i]!=A[newLength]) {
                A[++newLength] = A[i];
            }
        }

        int ans = ++newLength;
        int left = 0, right = 0;
        while (left < newLength && A[left] < 0) left++;
        right = left;
        left--;
        while (left >= 0 && right < newLength) {
            if (A[left] + A[right] == 0) {
                ans--;
                left--;
                right++;
            } else if (A[left] + A[right] > 0) {
                left--;
            } else right++;
        }
        return ans;
    }

    public int bruteForceSolution(int[] A){
        int N = A.length;

        if(N == 0) return 0;

        int c = Math.abs(A[0]);
        int solution = 0;

        for(int i=0; i<N; i++){
            if(Math.abs(A[i]) == c && i!=0) continue;

            for(int j=i+1; j<N; j++){
                if(Math.abs(A[j])==Math.abs(A[i]) )
                    A[j]=c;
            }

            A[i]=c;
            solution++;
        }
        return solution;
    }

    public int memGreedSolution(int[] A) {
        int N = A.length;
        int front = 0;
        int end = N-1;
        int solution = 0;
        while(front <= end){

            int firstEl = Math.abs(A[front]);
            int lastEl  = Math.abs(A[end]);

            if( firstEl > lastEl){
                solution++;
                front++;
            }   else if (firstEl < lastEl){
                solution++;
                end--;
            } else {
                solution++;
                front++;
                end--;
            }
        }
        return solution;
    }
}
