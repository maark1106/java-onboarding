package onboarding;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Problem6Test {

    @Test
    public void testCase1() {
        List<List<String>> forms = List.of(
                List.of("jm@email.com", "제이엠"),
                List.of("jason@email.com", "제이슨"),
                List.of("woniee@email.com", "워니"),
                List.of("mj@email.com", "엠제이"),
                List.of("nowm@email.com", "이제엠")
        );
        List<String> result = List.of("jason@email.com", "jm@email.com", "mj@email.com");
        assertThat(Problem6.solution(forms)).isEqualTo(result);
    }

    @Test
    public void testCase2() {
        List<List<String>> forms = List.of(
                List.of("jm@email.com", "제이엠"),
                List.of("jason@email.com", "제이슨"),
                List.of("woniee@email.com", "워니"),
                List.of("mj@email.com", "엠제이"),
                List.of("nowm@email.com", "이제엠"),
                List.of("qq@email.com", "이워니")
        );
        List<String> result = List.of("jason@email.com", "jm@email.com", "mj@email.com", "qq@email.com",
                "woniee@email.com");
        assertThat(Problem6.solution(forms)).isEqualTo(result);
    }
}
