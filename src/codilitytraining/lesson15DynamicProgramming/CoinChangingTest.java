package codilitytraining.lesson15DynamicProgramming;

/**
 * Example from lecture
 *
 For a given set of denominations, you are asked to find the minimum number of coins with
 which a given amount of money can be paid. Assume that you can use as many coins of
 a particular denomination as necessary. The greedy algorithmic approach is always to select
 the largest denomination not exceeding the remaining amount of money to be paid. As long
 as the remaining amount is greater than zero, the process is repeated. However, this algorithm
 may return a suboptimal result. For instance, for an amount of 6 and coins of values 1, 3, 4,
 we get 6 = 4 + 1 + 1, but the optimal solution here is 6 = 3 + 3.
 A dynamic algorithm finds solutions to this problem for all amounts not exceeding the
 given amount, and for increasing sets of denominations. For the example data, it would
 consider all the amounts from 0 to 6, and the following sets of denominations: ∅, {1}, {1, 3}
 and {1, 3, 4}. Let dp[i, j] be the minimum number of coins needed to pay the amount j if we
 use the set containing the i smallest denominations. The number of coins needed must satisfy
 the following rules:
 • no coins are needed to pay a zero amount: dp[i, 0] = 0 (for all i);
 • if there are no denominations and the amount is positive, there is no solution, so for
 convenience the result can be infinite in this case: dp[0, j] = ∞ (for all j > 0);
 • if the amount to be paid is smaller than the highest denomination c i , this denomination
 can be discarded: dp[i, j] = dp[i − 1, j] (for all i > 0 and all j such that c i > j);
 • otherwise, we should consider two options and choose the one requiring fewer coins:
 either we use a coin of the highest denomination, and a smaller amount to be paid
 remains, or we don’t use coins of the highest denomination (and the denomination can
 thus be discarded): dp[i, j] = min(dp[i, j − c i ] + 1, dp[i − 1, j]) (for all i > 0 and all j
 such that c i j).
 */
public class CoinChangingTest {


    public static void main(String [] args){


        example();


    }

    public static void example(){

        CoinChangingTest ct = new CoinChangingTest();

        System.out.println("Example for 6 and {1,3,4} should return 2 : " + ct.optimizedMemorySolution(new int [] {1,3,4}, 6));
     //   System.out.println("Example for 6 and {4} should return inf : " + ct.solution(new int [] {4}, 6));
    }

    /**
     *
     * @param C     given set of denominations
     * @param D     given amount of money
     * @return      minimum number of coins with which a given amount of money can be paid
     */
    public int solution(int [] C, int D){

        int N = C.length;
        //Create two-dimensional array with all zeros
        int [][] dp = new int[N+1][D+1];

        for(int i=1; i<D+1; i++) dp[0][i] = Integer.MAX_VALUE;

        for(int i=1; i<N+1; i++){
            for(int j=0; j<C[i-1]; j++)
                dp[i][j] = dp[i-1][j];
            for(int j=C[i-1]; j<D+1; j++)
                dp[i][j] = Math.min(dp[i][j-C[i-1]] + 1, dp[i-1][j]);
        }

        return dp[N][D];
    }

    /**
     *
     * @param C     given set of denominations
     * @param D     given amount of money
     * @return      minimum number of coins with which a given amount of money can be paid
     */
    public int optimizedMemorySolution(int [] C, int D){

        int N = C.length;
        //Create two-dimensional array with all zeros
        int [] dp = new int[D+1];

        for(int i=1; i<D+1; i++) dp[i] = Integer.MAX_VALUE;

        for(int i=1; i<N+1; i++)
            for(int j=C[i-1]; j<D+1; j++)
                dp[j] = Math.min(dp[j - C[i-1]] + 1, dp[j]);

        return dp[D];
    }
}
