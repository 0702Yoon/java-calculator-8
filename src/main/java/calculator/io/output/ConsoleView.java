package calculator.io.output;

public class ConsoleView {
    private static final String ASK_ADD_NUMBER = "덧셈할 문자열을 입력해 주세요.";

    public void printAskAddNumbers() {
        System.out.println(ASK_ADD_NUMBER);
    }

    public void printResult(String result) {
        System.out.println("결과 : " + result);
    }
}
