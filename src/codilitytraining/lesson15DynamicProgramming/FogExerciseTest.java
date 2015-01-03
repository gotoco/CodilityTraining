package codilitytraining.lesson15DynamicProgramming;

/**
 Problem: A small frog wants to get from position 0 to k (1 <= k <= 10 000). The frog can
 jump over any one of n fixed distances s 0 , s 1 , . . . , s n−1 (1 <= s_i <= k). The goal is to count the
 number of different ways in which the frog can jump to position k. It is sufficient to return
 the result modulo q.
 We assume that two patterns of jumps are different if, in one pattern, the frog visits
 a position which is not visited in the other pattern.
 Solution O(n · k): The task can be solved by using dynamic programming. Let’s create an
 array dp consisting of k elements, such that dp[j] will be the number of ways in which the
 frog can jump to position j.
 */
public class FogExerciseTest {

    public static FogExerciseTest that = new FogExerciseTest();

    public static void main(String [] args){

        example();

    }

    public static void example(){

        System.out.println("Example test for dist {1,3,4} and position should return 9 " + that.solution(new int [] {1,3,4}, 6));

    }

    /**
     *
     * @param S     given set of fixed distances
     * @param k     given end position
     * @return      counted number of different ways in which the frog can jump to position k
     */
    public int solution(int [] S, int k){
        int N = S.length;
        int [] jumps = new int[k+1];
        jumps[0] = 1;

        for(int i=1; i<k+1; i++){
            for(int j=0; j<N; j++){
                if(S[j]<=i){
                    jumps[i] += jumps[i-S[j]];
                }
            }
        }

        return jumps[k];
    }

    public int codilityFrogSolution(int [] S, int k){
        int N = S.length;
        int [] dp = new int [k+1];
        dp[0]=1;

        for(int j=1; j<k+1; j++){
            for(int i=0; i<N; i++){
                if(S[i] <= j)
                    dp[j] = dp[j] + dp[j - S[i]];
            }
        }
        return dp[k];
    }
}
