package onboarding;

public class EnumTest {

        enum GameNumber {
            THREE(3), SIX(6), NINE(9);

            private final int gameNumber;

            private GameNumber(int number){
                this.gameNumber = number;
            }
        }


    public static void main(String[] args) {
        GameNumber g1 = GameNumber.valueOf("THREE");
        System.out.println("g1 = " + g1.gameNumber);
    }
}
