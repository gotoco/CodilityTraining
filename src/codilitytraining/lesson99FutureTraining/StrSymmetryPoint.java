package codilitytraining.lesson99FutureTraining;

/**
 Write a function:
 class Solution { public int solution(String S); }
 that, given a string S, returns the index (counting from 0) of a character such that the part of the string to the left of that character is a reversal of the part of the string to its right. The function should return −1 if no such index exists.
 Note: reversing an empty string (i.e. a string whose length is zero) gives an empty string.
 For example, given a string:
 "racecar"
 the function should return 3, because the substring to the left of the character "e" at index 3 is "rac", and the one to the right is "car".
 Given a string:
 "x"
 the function should return 0, because both substrings are empty.
 Assume that:
 the length of S is within the range [0..2,000,000].
 Complexity:
 expected worst-case time complexity is O(length(S));
 expected worst-case space complexity is O(1) (not counting the storage required for input arguments).
 */
public class StrSymmetryPoint {

    public static StrSymmetryPoint that = new StrSymmetryPoint();

    public static void main(String [] args){
        example();
    }

    public static void example(){
               System.out.println("Solution for example should return 3 " + that.solution("racecar"));
               System.out.println("Solution for example should return 0 " + that.solution("x"));
        System.out.println("Solution for example should return -1 " + that.solution("car"));
    }

    public int solution(String S) {
        int N = S.length();

        if(N==0)return -1;
        if(N==1)return 0;
        if(N%2==0)return -1;

        int start = 0;
        int end = N-1;

        while(start!=end){
            if(S.charAt(start)==S.charAt(end)){
                start++; end--;
            } else {
                return -1;
            }
        }
        return start;
    }

}
