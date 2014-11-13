package codilitytraining.lesson6Leader;

/**
 A non-empty zero-indexed array A consisting of N integers is given.
 The leader of this array is the value that occurs in more than half of the elements of A.
 An equi leader is an index S such that 0 ≤ S < N − 1 and two sequences A[0], A[1], ..., A[S] and A[S + 1], A[S + 2], ..., A[N − 1] have leaders of the same value.
 For example, given array A such that:
 A[0] = 4
 A[1] = 3
 A[2] = 4
 A[3] = 4
 A[4] = 4
 A[5] = 2
 we can find two equi leaders:
 0, because sequences: (4) and (3, 4, 4, 4, 2) have the same leader, whose value is 4.
 2, because sequences: (4, 3, 4) and (4, 4, 2) have the same leader, whose value is 4.
 The goal is to count the number of equi leaders. Write a function:
 class Solution { public int solution(int[] A); }
 that, given a non-empty zero-indexed array A consisting of N integers, returns the number of equi leaders.
 For example, given:
 A[0] = 4
 A[1] = 3
 A[2] = 4
 A[3] = 4
 A[4] = 4
 A[5] = 2
 the function should return 2, as explained above.
 Assume that:
 N is an integer within the range [1..100,000];
 each element of array A is an integer within the range [−1,000,000,000..1,000,000,000].
 Complexity:
 expected worst-case time complexity is O(N);
 expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).

 */
public class EquiLeader {

    public static void main(String[] args){

        EquiLeader solution = new EquiLeader();

        int [] testCase1 = {4, 3, 4, 4, 4, 2};
        System.out.println("First test case should return 2 : " + solution.solution(testCase1));

    }

    /* Codility 100%*/
    public int solution(int[] A) {
        int [] findResult = findLeaderIndex(A);
        int index = findResult[0];
        int leadersNumber = findResult[1];
        int n = A.length;
        int result = 0;

        if(index == -1){
            return 0;
        }

        int leader = A[index];
        int equiCnt = 0;
        int leaderCnt = 0;

        for(int i=0; i<n; i++){
            if(A[i] == leader){
                leaderCnt++;
            }

            boolean isLeaderInFirstPart = leaderCnt > (i+1)/2;
            boolean isStillLeaderInSecondPart = (leadersNumber-leaderCnt) > (n-i-1)/2;

            if(isLeaderInFirstPart && isStillLeaderInSecondPart){
                result++;
            }
        }

        return result;
    }

    private int[] findLeaderIndex(int[] A){

        int [] result = {0, 0};

        if(A.length == 0){
            result[0] = -1;
            return result;
        } else if(A.length == 1){
            return result;
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

        if(count > n/2) {
            result[0] = index;
            result[1] = count;
            return result;
        }

        return result;

    }

}
