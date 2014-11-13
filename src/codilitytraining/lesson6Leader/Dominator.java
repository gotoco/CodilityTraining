package codilitytraining.lesson6Leader;

/**
 A zero-indexed array A consisting of N integers is given. The dominator of array A is the value that occurs
 in more than half of the elements of A.
 For example, consider array A such that

 A[0] = 3    A[1] = 4    A[2] =  3
 A[3] = 2    A[4] = 3    A[5] = -1
 A[6] = 3    A[7] = 3

 The dominator of A is 3 because it occurs in 5 out of 8 elements of A (namely in those with indices 0, 2, 4, 6 and 7)
 and 5 is more than a half of 8.
 Write a function

 class Solution { public int solution(int[] A); }

 that, given a zero-indexed array A consisting of N integers, returns index of any element of array A
 in which the dominator of A occurs. The function should return −1 if array A does not have a dominator.

 Assume that:
 N is an integer within the range [0..100,000];
 each element of array A is an integer within the range [−2,147,483,648..2,147,483,647].
 For example, given array A such that
 A[0] = 3    A[1] = 4    A[2] =  3
 A[3] = 2    A[4] = 3    A[5] = -1
 A[6] = 3    A[7] = 3
 the function may return 0, 2, 4, 6 or 7, as explained above.
 Complexity:
 expected worst-case time complexity is O(N);
 expected worst-case space complexity is O(1), beyond input storage (not counting the storage required for input arguments).
 Elements of input arrays can be modified.
 */
public class Dominator {

    /*Codility 100%*/
    public static void main(String[] args){

        Dominator d = new Dominator();
        int [] testCase1 = {3, 4, 3, 2, 3, -1, 3, 3};

        System.out.println("### First test case should return 0, 2, 4, 6 or 7 : " + d.solution(testCase1));

        int [] empty = {};
        System.out.println("### Empty test should return -1 : " + d.solution(empty));

        int [] almost_one = {Integer.MAX_VALUE}   ;
        System.out.println("### Almost One test should return 0 : " + d.solution(almost_one));
    }

    public int solution(int[] A){

        if(A.length == 0){
            return -1;
        } else if(A.length == 1){
            return 0;
        }

        int size = 0;
        int n = A.length;
        int value = -1;

        for(int i=0; i<n; i++){
            if(size == 0){
                size++;
                value = A[i];
            } else {
                if(value != A[i]){
                    size--;
                }else {
                    size++;
                }
            }
        }

        int candidate = -1;
        int index = -1;
        if(size>0) {
          candidate = value;
        }
        int count = 0;
        for(int i=0; i<n; i++){
            if(A[i] == candidate){
                count++;
                index = i;
            }
        }

        if(count > n/2)
           return index;

        return -1;
    }

}
