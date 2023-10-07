package onboarding;

import java.util.stream.IntStream;

public class Problem3 {

    private static final int START_NUM = 1;

    enum GameNumber{
        THREE(3), SIX(6), NINE(9);

        private final int value;
        private GameNumber(int number){
            this.value = number;
        }
    }

    public static int solution(int number) {
        return IntStream.range(START_NUM, number + 1)
                .map(Problem3::countClap)
                .sum();
    }

    public static int countClap(int number){

        int result = 0;

        while(number != 0){
            int digit = number % 10;
            if(includeOneOf369(digit)){
                result++;
            }
            number /= 10;
        }

        return result;
    }

    public static boolean includeOneOf369(int digit){
        for(GameNumber number : GameNumber.values()){
            if(number.value == digit){
                return true;
            }
        }
        return false;
    }
}
