package calculator.io.input;

import camp.nextstep.edu.missionutils.Console;
import java.util.NoSuchElementException;

public class ConsoleInput implements InputInterface {

    @Override
    public String readLine() {
        try {
            return Console.readLine();
        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException("입력값은 비어있을 수 없습니다.");
        }
    }
}
