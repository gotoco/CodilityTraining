package codilitytraining.lesson5StacksAndQueues;
/**
 A string S consisting of N characters is considered to be properly nested if any of the following conditions is true:
 S is empty;
 S has the form "(U)" or "[U]" or "{U}" where U is a properly nested string;
 S has the form "VW" where V and W are properly nested strings.
 For example, the string "{[()()]}" is properly nested but "([)()]" is not.
 Write a function:
 class Solution { public int solution(String S); }
 that, given a string S consisting of N characters, returns 1 if S is properly nested and 0 otherwise.
 For example, given S = "{[()()]}", the function should return 1 and given S = "([)()]", the function should return 0,
 as explained above.
 Assume that:
 N is an integer within the range [0..200,000];
 string S consists only of the following characters: "(", "{", "[", "]", "}" and/or ")".
 Complexity:
 expected worst-case time complexity is O(N);
 expected worst-case space complexity is O(N) (not counting the storage required for input arguments).
 */

import java.util.Stack;

public class Brackets {

    public static void main(String [] args){

        Brackets test = new Brackets();

        String testCase1 = "{[()()]}" ;

        System.out.println("First test case should return 1 : " + test.solution(testCase1));

        String testCase2 = "([)()]";

        System.out.println("Second test case should return 0 : " + test.solution(testCase2));

    }

    /*Codility 100%*/
    public int solution(String S) {
        int result = 1;
        Stack<Character> stack = new Stack<Character>();

        for(int i=0; i<S.length(); i++){
            Character ch = S.charAt(i);
            switch (ch){

                case '(' : stack.push('('); break;
                case '{' : stack.push('{'); break;
                case '[' : stack.push('['); break;

                case ')' :
                    if(stack.empty()){
                        result = 0;
                        break; }
                    if(! stack.pop().equals('(')) {
                        result = 0; }
                    break;
                case '}' :
                    if(stack.empty()){
                        result = 0;
                        break; }
                    if(! stack.pop().equals('{')) {
                    result = 0; }
                    break;
                case ']' :
                    if(stack.empty()){
                        result = 0;
                        break; }
                    if(! stack.pop().equals('[')) {
                        result = 0; }
                    break;
            }

        }

        if(! stack.empty()){
            result = 0;
        }

        return result;
    }
}
