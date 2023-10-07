package onboarding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Problem5 {

    enum Money{
        MONEY_UNIT_50000(50000),
        MONEY_UNIT_10000(10000),
        MONEY_UNIT_5000(5000),
        MONEY_UNIT_1000(1000),
        MONEY_UNIT_500(500),
        MONEY_UNIT_100(100),
        MONEY_UNIT_50(50),
        MONEY_UNIT_10(10),
        MONEY_UNIT_1(1);

        private final int value;
        private Money(int money){
            this.value = money;
        }

        public static List<Money> highestOrder(){
            return Arrays.stream(values())
                    .sorted((m1, m2) -> m2.value - m1.value)
                    .collect(Collectors.toList());
        }
    }
    public static List<Integer> solution(int money) {
        return countMoneyUnit(money);
    }

    public static List<Integer> countMoneyUnit(int money){
        ArrayList<Integer> result = new ArrayList<>();

        for(Money m : Money.highestOrder()){
            result.add(unitCount(money, m.value));
            money %= m.value;
        }

        return result;
    }

    public static int unitCount(int money, int moneyUnit){
        return money / moneyUnit;
    }

}
