package onboarding;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Problem3FuncTest {

    Problem3 problem3 = new Problem3();

    @Test
    void gameTest1(){
        int number = 13;
        int result = 4;
        assertThat(Problem3.solution(number)).isEqualTo(result);
    }

    @Test
    void gameTest2() {
        int number = 33;
        int result = 14;
        assertThat(Problem3.solution(number)).isEqualTo(result);
    }

    @Test
    void gameTest3(){
        int number = 16;
        int result = 5;
        assertThat(Problem3.solution(number)).isEqualTo(result);
    }
}
