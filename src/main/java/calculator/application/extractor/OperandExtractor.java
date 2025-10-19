package calculator.application.extractor;

import calculator.domain.delimiter.Delimiter;
import java.util.ArrayList;
import java.util.List;

public class OperandExtractor {
    private static final int NUMBER_MIN_VALUE_CONDITION = 0;
    private static final char ASCII_ZERO = '0';
    private static final String ERROR_NOT_DIGIT = "피연산자는 양수여야 합니다.";
    private static final String ERROR_INVALID_DELIMITER = "없는 구분자입니다.";
    private static final String ERROR_MISPLACED_DELIMITER = "구분자 이전에 숫자가 있어야 합니다.";
    private static final String ERROR_LINE_END_DELIMITER = "잘못된 구분자 입력으로 끝났습니다.";


    /**
     * 주어진 문자열을 숫자와 구분자로 분리하여 long 배열로 반환합니다.
     *
     * <p>동작:
     * <ol>
     *   <li>문자열을 순회하며 숫자와 구분자를 식별합니다.</li>
     *   <li>연속된 숫자는 하나의 operand 묶어 처리합니다.</li>
     *   <li>구분자가 delimiters 목록에 포함되어 있는지 확인합니다.</li>
     *   <li>숫자와 구분자가 올바르지 않으면 예외를 발생시킵니다.</li>
     * </ol>
     * </p>
     *
     * @param line       연산 문자열 (예: "1,2:3")
     * @param delimiters 허용된 구분자 리스트
     * @return 문자열에서 추출한 숫자를 담은 long 배열
     * @throws IllegalArgumentException - 숫자 대신 잘못된 문자가 들어온 경우 - 구분자가 잘못된 위치에 있는 경우 - 문자열 끝에 잘못된 구분자가 있는 경우
     */
    public long[] extractOperand(String line, List<Delimiter> delimiters) {
        StringBuilder numberBuilder = new StringBuilder();
        StringBuilder delimiterBuilder = new StringBuilder();
        List<Long> operands = new ArrayList<>();

        int i = 0;
        while (i < line.length()) {
            char c = line.charAt(i);

            if (Character.isDigit(c)) {
                if (!isValidNumber(c)) {
                    throw new IllegalArgumentException(ERROR_NOT_DIGIT);
                }

                if (!delimiterBuilder.isEmpty()) {
                    validateDelimiter(delimiterBuilder.toString(), delimiters);
                    delimiterBuilder.setLength(0);
                }
                numberBuilder.append(c);
                i++;
                continue;
            }

            delimiterBuilder.append(c);

            boolean matched = delimiters.stream()
                .anyMatch(d -> d.isEqualTo(delimiterBuilder.toString()));

            if (matched) {
                if (numberBuilder.isEmpty()) {
                    throw new IllegalArgumentException(ERROR_MISPLACED_DELIMITER);
                }
                flushOperand(numberBuilder, operands);
                delimiterBuilder.setLength(0);
            }
            i++;
        }

        if (!numberBuilder.isEmpty()) {
            flushOperand(numberBuilder, operands);
        }

        if (!delimiterBuilder.isEmpty()) {
            throw new IllegalArgumentException(ERROR_LINE_END_DELIMITER);
        }

        return operands.stream().mapToLong(Long::longValue).toArray();
    }

    private void flushOperand(StringBuilder sb, List<Long> operands) {
        long value = Long.parseLong(sb.toString());
        operands.add(value);
        sb.setLength(0);
    }

    private boolean isValidNumber(char oneChar) {
        return (oneChar - ASCII_ZERO) >= NUMBER_MIN_VALUE_CONDITION;
    }

    private void validateDelimiter(String delimiterValue, List<Delimiter> delimiters) {
        boolean valid = delimiters.stream()
            .anyMatch(delimiter -> delimiter.isEqualTo(delimiterValue));
        if (!valid) {
            throw new IllegalArgumentException(ERROR_INVALID_DELIMITER);
        }
    }
}