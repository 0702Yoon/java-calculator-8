package calculator.integration;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import calculator.Application;
import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ExceptionTest extends NsTest {
    @Test
    @DisplayName("빈 문자열 입력시 IllegalArgumentException을 발생시킨다")
    void 빈_문자열_예외_테스트() {
        assertSimpleTest(() ->
            assertThatThrownBy(() -> runException(""))
                .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    @DisplayName("음수 입력시 IllegalArgumentException을 발생시킨다")
    void 음수_예외_테스트() {
        assertSimpleTest(() ->
            assertThatThrownBy(() -> runException("-1,2,3"))
                .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    @DisplayName("숫자가 아닌 문자 입력시 IllegalArgumentException을 발생시킨다")
    void 숫자가_아닌_문자_예외_테스트() {
        assertSimpleTest(() ->
            assertThatThrownBy(() -> runException("a,b,c"))
                .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    @DisplayName("잘못된 구분자 사용시 IllegalArgumentException을 발생시킨다")
    void 잘못된_구분자_예외_테스트() {
        assertSimpleTest(() ->
            assertThatThrownBy(() -> runException("1&2&3"))
                .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    @DisplayName("두 글자 커스텀 구분자와 숫자가 아닌 문자가 함께 입력시 IllegalArgumentException을 발생시킨다")
    void 두글자_커스텀_구분자_숫자가_아닌_문자_예외_테스트() {
        assertSimpleTest(() ->
            assertThatThrownBy(() -> runException("//bb\\n1bbXbb3"))
                .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    @DisplayName("구분자만 입력시 IllegalArgumentException을 발생시킨다")
    void 구분자만_입력_예외_테스트() {
        assertSimpleTest(() ->
            assertThatThrownBy(() -> runException(","))
                .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    @DisplayName("시작에 구분자가 있는 경우 IllegalArgumentException을 발생시킨다")
    void 시작에_구분자_예외_테스트() {
        assertSimpleTest(() ->
            assertThatThrownBy(() -> runException(",1,2"))
                .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    @DisplayName("연속된 구분자 입력시 IllegalArgumentException을 발생시킨다")
    void 연속된_구분자_예외_테스트() {
        assertSimpleTest(() ->
            assertThatThrownBy(() -> runException("1,,2"))
                .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    @DisplayName("빈 커스텀 구분자 형식 입력시 IllegalArgumentException을 발생시킨다")
    void 빈_커스텀_구분자_예외_테스트() {
        assertSimpleTest(() ->
            assertThatThrownBy(() -> runException("//\\n1,2,3"))
                .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    @DisplayName("잘못된 커스텀 구분자 형식 입력시 IllegalArgumentException을 발생시킨다")
    void 잘못된_커스텀_구분자_형식_예외_테스트() {
        assertSimpleTest(() ->
            assertThatThrownBy(() -> runException("/;\\n1;2;3"))
                .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }
}
