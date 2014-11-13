package codilitytraining.lesson5StacksAndQueues;
/**
 A string S consisting of N characters is called properly nested if:
 S is empty;
 S has the form "(U)" where U is a properly nested string;
 S has the form "VW" where V and W are properly nested strings.
 For example, string "(()(())())" is properly nested but string "())" isn't.
 Write a function:
 class Solution { public int solution(String S); }
 that, given a string S consisting of N characters, returns 1 if string S is properly nested and 0 otherwise.
 For example, given S = "(()(())())", the function should return 1 and given S = "())", the function should return 0,
 as explained above.

 Assume that:
 N is an integer within the range [0..1,000,000];
 string S consists only of the characters "(" and/or ")".

 Complexity:
 expected worst-case time complexity is O(N);
 expected worst-case space complexity is O(1) (not counting the storage required for input arguments).
 */
import java.util.Stack;

public class Nesting {

    public static void main(String [] args){

        Nesting test = new Nesting();

        String testCase1 = "(()(())())";

        System.out.println("First test case should return :1 - " + test.solution(testCase1));

        String testCase2 = "())";

        System.out.println("2 test case should return :0 - " + test.solution(testCase2));

        String testCase3 = "";

        System.out.println("3 test case should return :1 - " + test.solution(testCase3));
    }

    /*Codility 100% */
    public int solution(String S) {
        int result = 1;

        if(S== null || S.length() == 0){
            return result;
        }

        Stack<Character> stack = new Stack<Character>();

        for(int i=0; i<S.length(); i++){

            if(S.charAt(i) == '('){
                stack.push('(');
            } else {
                if(stack.empty()){
                    result = 0;
                    break;
                } else {
                    stack.pop();
                }
            }
        }


        if(stack.size() != 0){
            result = 0;
        }

        return result;
    }


}
