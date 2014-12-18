package codilitytraining.lesson13CaterpillarMethod;

/**
 * Author: Mazeryt Freager
 * Contact: skirki@o2.pl
 * Date: 14.12.14
 * Time: 15:31
 */
public class Test {

    public static void main(String [] args){

        int [] A = {6, 2, 7, 4, 1, 3, 6};

        System.out.println("is sum 12?" + caterpillarExample(A, 12));
    }


    public static boolean caterpillarExample(int [] A, int s){

        int N = A.length;
        int front = 0, total = 0;

        for(int i=0; i<N; i++){
            while (front < N && total+A[front] <=s) {
                total += A[front];
                front += 1;
            }
            if(total == s)
                return true;

            total -= A[i];
        }
        return false;
    }
}
