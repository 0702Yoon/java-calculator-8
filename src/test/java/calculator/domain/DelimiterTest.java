package calculator.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import calculator.domain.delimiter.CustomDelimiterRule;
import calculator.domain.delimiter.Delimiter;
import calculator.domain.delimiter.DelimiterType;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class DelimiterTest {

    @Nested
    @DisplayName("Delimiter() 테스트")
    class CustomDelimiterTests {

        @Test
        @DisplayName("커스텀 구분자안에 숫자가 존재하면 예외를 던진다.")
        void 숫자_구분자_예외() {
            assertThatThrownBy(() ->
                Delimiter.customDelimiter("s24d"))
                .isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        @DisplayName("문자 구분자는 정상적으로 생성된다.")
        void 문자_구분자_생성() {
            Delimiter delimiter = Delimiter.customDelimiter("asd");
            assertThat(delimiter).isNotNull();
        }

        @Test
        @DisplayName("특수문자 구분자는 정상적으로 생성된다.")
        void 특수문자_구분자_생성() {
            Delimiter delimiter = Delimiter.customDelimiter("***@#$");
            assertThat(delimiter).isNotNull();
        }

        @Test
        @DisplayName("커스텀 구분자 패턴을 맞추지 않으면 예외를 던진다.")
        void 잘못된_문자열_구분자_추출() {
            for (CustomDelimiterRule rule : CustomDelimiterRule.values()) {
                assertThatThrownBy(() ->
                    rule.extractCustomDelimiter("\\nasd//")
                ).isInstanceOf(IllegalArgumentException.class);
            }
        }

        @Test
        @DisplayName("길이에 상관없이 패턴이 맞지 않으면 예외를 던진다.")
        void 짧은_문자열_구분자_추출() {
            for (CustomDelimiterRule rule : CustomDelimiterRule.values()) {
                assertThatThrownBy(() ->
                    rule.extractCustomDelimiter("a")
                ).isInstanceOf(IllegalArgumentException.class);
            }
        }

        @Test
        @DisplayName("커스텀 구분자가 비어있으면 예외를 던진다.")
        void 커스텀_구분자_공백() {
            for (CustomDelimiterRule rule : CustomDelimiterRule.values()) {
                assertThatThrownBy(() ->
                    rule.extractCustomDelimiter("")
                ).isInstanceOf(IllegalArgumentException.class);
            }
        }
    }

    @Nested
    @DisplayName("기본 생성자 테스트")
    class DefaultDelimiterTests {

        @Test
        @DisplayName("기본 생성자로 구분자를 만들 수 있다")
        void 기본_구분자_생성() {
            for (DelimiterType delimiterType : DelimiterType.values()) {
                List<Delimiter> defaultDelimiters = delimiterType.getDefaultDelimiters();
                for (Delimiter delimiter : defaultDelimiters) {
                    Delimiter.defaultDelimiter(delimiter.toString());
                }
            }
        }
    }

    @Nested
    @DisplayName("isEqualTo() 테스트")
    class CompareTests {

        @Test
        @DisplayName("구분자와 같은 문자열이면 true를 반환한다")
        void 동일_문자열() {
            Delimiter delimiter = Delimiter.customDelimiter("s");
            assertThat(delimiter.isEqualTo("s")).isTrue();
        }

        @Test
        @DisplayName("구분자와 다른 문자열이면 false를 반환한다")
        void 다른_문자열() {
            Delimiter delimiter = Delimiter.defaultDelimiter("s");
            assertThat(delimiter.isEqualTo(",")).isFalse();
        }
    }
}