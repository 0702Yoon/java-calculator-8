package calculator.io.input;

/**
 * 한 줄 입력을 읽어오는 인터페이스
 */
public interface InputInterface {
    /**
     * 한 줄로 된 문자열 입력을 반환합니다. 비어있다면 illegalargumentexception 예외를 던진다.
     *
     * @return 입력된 문자열
     */
    String readLine();
}