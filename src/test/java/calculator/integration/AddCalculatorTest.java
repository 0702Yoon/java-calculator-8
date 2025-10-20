package calculator.integration;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

import calculator.Application;
import calculator.domain.delimiter.Delimiter;
import calculator.domain.delimiter.DelimiterType;
import camp.nextstep.edu.missionutils.test.NsTest;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class AddCalculatorTest extends NsTest {
    @Nested
    @DisplayName("기본 구분자 테스트")
    class BasicDelimiterTests {
        @Test
        @DisplayName("기본 구분자로 여러 숫자의 합을 계산한다")
        void 기본_구분자_테스트() {
            // given
            List<Delimiter> defaultDelimiters = DelimiterType.ADD.getDefaultDelimiters();

            // when & then
            for (Delimiter delimiter : defaultDelimiters) {
                String expression = String.join(delimiter.getValue(), "1", "2", "3", "4", "5");
                assertSimpleTest(() -> {
                    run(expression);
                    assertThat(output()).contains("결과 : 15");
                });
            }
        }

        @Test
        @DisplayName("쉼표와 콜론이 혼합된 구분자로 숫자들의 합을 계산한다")
        void 혼합_기본_구분자_테스트() {
            assertSimpleTest(() -> {
                run("1,2:3,4:5,6");
                assertThat(output()).contains("결과 : 21");
            });
        }
    }

    @Nested
    @DisplayName("커스텀 구분자 테스트")
    class CustomDelimiterTests {
        @Test
        @DisplayName("한 글자 커스텀 구분자(세미콜론)로 숫자들의 합을 계산한다")
        void 한글자_커스텀_구분자_세미콜론_테스트() {
            assertSimpleTest(() -> {
                run("//;\\n1;2;3;4");
                assertThat(output()).contains("결과 : 10");
            });
        }

        @Test
        @DisplayName("두 글자 이상 커스텀 구분자(별표)로 숫자들의 합을 계산한다")
        void 두글자_커스텀_구분자_별표_테스트() {
            assertSimpleTest(() -> {
                run("//**\\n5**10**15**20");
                assertThat(output()).contains("결과 : 50");
            });
        }

        @Test
        @DisplayName("기본 구분자가 뒤에 들어간 커스텀 구분자로 숫자들의 합을 계산한다.")
        void 두글자_커스텀_구분자_테스트() {
            assertSimpleTest(() -> {
                run("//#:\\n1#:2#:3#:4#:5");
                assertThat(output()).contains("결과 : 15");
            });
        }

        @Test
        @DisplayName("기본 구분자가 앞에 들어간 커스텀 구분자로 숫자들의 합을 계산한다.")
        void 기본_구분자가_앞에_있는_커스텀_구분자_테스트() {
            assertSimpleTest(() -> {
                run("//:#\\n1:#2:#3:#4:#5");
                assertThat(output()).contains("결과 : 15");
            });
        }
    }

    @Nested
    @DisplayName("숫자 합계 계산 테스트")
    class NumberSumCalculationTests {
        @Test
        @DisplayName("0이 포함된 숫자들의 합을 계산한다")
        void 영_포함_계산_테스트() {
            assertSimpleTest(() -> {
                run("0,5,0,10");
                assertThat(output()).contains("결과 : 15");
            });
        }

        @Test
        @DisplayName("단일 숫자 0의 합을 계산한다")
        void 단일_영_계산_테스트() {
            assertSimpleTest(() -> {
                run("0");
                assertThat(output()).contains("결과 : 0");
            });
        }
    }

    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }
}
