package calculator.io.input;

import camp.nextstep.edu.missionutils.Console;

public class ConsoleInput implements InputInterface {

    @Override
    public String readLine() {
        String line = Console.readLine();
        if (line.isBlank()) {
            throw new IllegalArgumentException("입력값은 비어있을 수 없습니다.");

        }
        return line;
    }
}
