package codilitytraining.lesson12BinarySearchAlgorithm;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 You are given two non-empty zero-indexed arrays A and B consisting of N integers. These arrays represent N planks. More precisely, A[K] is the start and B[K] the end of the K−th plank.
 Next, you are given a non-empty zero-indexed array C consisting of M integers. This array represents M nails. More precisely, C[I] is the position where you can hammer in the I−th nail.
 We say that a plank (A[K], B[K]) is nailed if there exists a nail C[I] such that A[K] ≤ C[I] ≤ B[K].
 The goal is to find the minimum number of nails that must be used until all the planks are nailed. In other words, you should find a value J such that all planks will be nailed after using only the first J nails. More precisely, for every plank (A[K], B[K]) such that 0 ≤ K < N, there should exist a nail C[I] such that I < J and A[K] ≤ C[I] ≤ B[K].
 For example, given arrays A, B such that:
 A[0] = 1    B[0] = 4
 A[1] = 4    B[1] = 5
 A[2] = 5    B[2] = 9
 A[3] = 8    B[3] = 10
 four planks are represented: [1, 4], [4, 5], [5, 9] and [8, 10].
 Given array C such that:
 C[0] = 4
 C[1] = 6
 C[2] = 7
 C[3] = 10
 C[4] = 2
 if we use the following nails:
 0, then planks [1, 4] and [4, 5] will both be nailed.
 0, 1, then planks [1, 4], [4, 5] and [5, 9] will be nailed.
 0, 1, 2, then planks [1, 4], [4, 5] and [5, 9] will be nailed.
 0, 1, 2, 3, then all the planks will be nailed.
 Thus, four is the minimum number of nails that, used sequentially, allow all the planks to be nailed.
 Write a function:
 class Solution { public int solution(int[] A, int[] B, int[] C); }
 that, given two non-empty zero-indexed arrays A and B consisting of N integers and a non-empty zero-indexed array C consisting of M integers, returns the minimum number of nails that, used sequentially, allow all the planks to be nailed.
 If it is not possible to nail all the planks, the function should return −1.
 For example, given arrays A, B, C such that:
 A[0] = 1    B[0] = 4
 A[1] = 4    B[1] = 5
 A[2] = 5    B[2] = 9
 A[3] = 8    B[3] = 10

 C[0] = 4
 C[1] = 6
 C[2] = 7
 C[3] = 10
 C[4] = 2
 the function should return 4, as explained above.
 Assume that:
 N and M are integers within the range [1..30,000];
 each element of arrays A, B, C is an integer within the range [1..2*M];
 A[K] ≤ B[K].
 Complexity:
 expected worst-case time complexity is O((N+M)*log(M));
 expected worst-case space complexity is O(M), beyond input storage (not counting the storage required for input arguments).
 Elements of input arrays can be modified.
 */
public class NailingPlanks {

    public static void main(String [] args) {

        compare();

    }

    public static void exampleTest() {

        NailingPlanks np = new NailingPlanks();

        int[] A = new int[4]; int[] B = new int [4]; int[] C = new int[5];

        A[0] = 1;
        A[1] = 4;
        A[2] = 5;
        A[3] = 8;

        B[0] = 4 ;
        B[1] = 5 ;
        B[2] = 9 ;
        B[3] = 10;

        C[0] = 4 ;
        C[1] = 6 ;
        C[2] = 7 ;
        C[3] = 10;
        C[4] = 2 ;

        System.out.println("ExampleTest should return 4: " + np.solution(A, B, C));
    }

    public static void allPlanksArePoints(){
        NailingPlanks np = new NailingPlanks();
        int[] A = new int[4]; int[] B = new int [4]; int[] C = new int[5];

        A[0] = 1;
        A[1] = 2;
        A[2] = 3;
        A[3] = 4;

        B[0] = 1 ;
        B[1] = 2 ;
        B[2] = 3 ;
        B[3] = 4;

        C[0] = 4 ;
        C[1] = 3 ;
        C[2] = 1 ;
        C[3] = 2;
        C[4] = 2 ;

        System.out.println("allPlanksArePoints 4: " + np.solution(A, B, C));
    }

    public static void twoProblematicCases(){
        NailingPlanks np = new NailingPlanks();
        int[] A = {5, 6, 6, 1, 6};
        int[] B = {13, 12, 13, 4, 16};
        int[] C = {4, 5, 9, 10, 5, 10, 8, 4, 8};


        System.out.println("first problematic should return 3: " + np.solution(A, B, C));

        int [] Aa = {7,3,2,6};
        int [] Bb = {15,6,6,12};
        int [] Cc = {10,5,5,5,4};

        System.out.println("second problematic should return 2: " + np.solution(Aa, Bb, Cc));
    }


    public int solution(int[] A, int[] B, int[] C) {
        int N = A.length;

        int [] Cp = new int [C.length];

        List<Plank> planks = new ArrayList<Plank>();

        for(int j=0; j<N; j++){
            Plank p = new Plank();
            p.start = A[j];
            p.end   = B[j];

            planks.add(p);
        }

        Collections.sort(planks);

        for(int i=0; i<C.length; i++)Cp[i]=C[i];

        for(int i=0; i<C.length; i++){
            Integer r;
            do{
                r = searchPlank(planks, C[i]);
                if(r!=null){
                    planks.remove(r.intValue());
                    Cp[i] = -1;
                }
            } while (r != null);

        }

        if(!planks.isEmpty()) return -1;

        int result = 0;
        for(int i=Cp.length-1; i>=0; i--) if(Cp[i]==-1) {result = i+1 ; break;}

        return result;
    }

    public Integer searchPlank(List<Plank>planks, int c){
        Integer result = null;
        int begin = 0;
        int end = planks.size()-1;
        int mind;
        while(begin<=end)  {
            mind = (begin+end)/2;
            if(planks.get(mind).contain(c)) {
                result = mind;
            }
            if(new Plank(c,c).compareTo(planks.get(mind)) <= 0){

                end = mind - 1;
            } else {

                begin = mind + 1;
            }
        }
        return result;
    }

    public int bruteForceSolution(int[] A, int[] B, int[] C) {
        int N = A.length;

        int [] Cp = new int [C.length];
        int [] Aa = new int [A.length];
        int [] Bb = new int [B.length];

        for(int i=0; i<C.length; i++)Cp[i]=C[i];
        for(int i=0; i<N; i++)Aa[i]=A[i];
        for(int i=0; i<N; i++)Bb[i]=B[i];


        for(int i=0; i<C.length; i++){
            for(int j=0; j<N; j++)    {
                if(Aa[j] == -1)
                    continue;

                if( Aa[j]<=C[i] && C[i]<=Bb[j]){
                    Aa[j] = -1; Bb[j] = -1; Cp[i] = -1;
                }
            }
        }
        for(int i=0; i<N; i++)  if(Aa[i]!=-1) return -1;


        int result = 0;
        for(int i=Cp.length-1; i>=0; i--) if(Cp[i]==-1) {result = i+1 ; break;};

        return result;

    }


    public static void compare(){
        NailingPlanks np = new NailingPlanks();
        int size = Integer.MAX_VALUE;
        Random random = new Random() ;
        for(int i=0 ; i<size; i++){

            int randomNumber = random.nextInt(8);
            int randomNumber2 = randomNumber + random.nextInt(8);

            int[] A = new int[randomNumber]; int[] B = new int [randomNumber]; int[] C = new int[randomNumber2];
            int[] Aa = new int[randomNumber]; int[] Bb = new int [randomNumber];

            for(int j=0; j<A.length; j++){
                A[j] = random.nextInt(10)+1;
                Aa[j] = A[j];
                B[j] = A[j] + random.nextInt(10)+1;
                Bb[j] = B[j];
            }

            for(int j=0; j<C.length; j++){
                C[j] = random.nextInt(10)+1;

            }

            int sol1 =  np.bruteForceSolution(A, B, C) ;
            int sol2 =  np.solution(A, B, C) ;

            if(sol1 != sol2){
                System.out.println(" i: " + i);
                System.out.println("sol1 =  " + sol1);
                System.out.println("sol2 =  " + sol2);

                System.out.println("  ");
            }

        }

    }

}

class Plank implements Comparable<Plank>{

    int start;
    int end;

    @Override
    public int compareTo(Plank o) {
        Plank comparable = (Plank)o;

        if(this.start==comparable.start && this.end==comparable.end)
            return 0;
        if(this.start != comparable.start)
            return this.start - comparable.start;
        else
            return  this.end - comparable.end;
    }

    public Plank(){

    }

    public Plank(int start, int end){
        this.start = start;
        this.end = end;
    }

    public boolean contain(int c){
        if(this.start<=c && this.end>=c) {
            return true;
        }
        return false;
    }
}

