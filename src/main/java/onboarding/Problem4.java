package onboarding;

import static java.lang.Character.isLowerCase;
import static java.lang.Character.isUpperCase;

public class Problem4 {
    public static String solution(String word) {
        return convertToOppositeWords(word);
    }

    public static String convertToOppositeWords(String word){

        StringBuilder result = new StringBuilder();
        if(isValidateLength(word)){
            for(char ch : word.toCharArray()){
                result.append(convertWord(ch));
            }
        }

        return result.toString();
    }

    public static char convertWord(char ch){
        if(isUpperCase(ch)){
            return convertUpperCase(ch);
        }
        if(isLowerCase(ch)){
            return convertLowerCase(ch);
        }

        return ch;
    }

    public static char convertUpperCase(char ch){
        return (char)('Z' - ch + 'A');
    }

    public static char convertLowerCase(char ch){
        return (char)('z' - ch + 'a');
    }

    public static boolean isValidateLength(String word){
        return 1 <= word.length() && word.length() <= 1000;
    }

}
