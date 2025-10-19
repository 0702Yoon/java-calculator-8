package calculator.domain.delimiter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum CustomDelimiterRule {
    ADD("//", "\\n");

    private final String leftPattern;
    private final String rightPattern;
    private final Pattern pattern;

    CustomDelimiterRule(String leftPattern, String rightPattern) {
        this.leftPattern = leftPattern;
        this.rightPattern = rightPattern;
        this.pattern = Pattern.compile(
                "^" + Pattern.quote(leftPattern) + "(.*)" + Pattern.quote(rightPattern)
        );
    }

    public String extractCustomDelimiter(String line) {
        Matcher matcher = pattern.matcher(line);
        if (matcher.find()) {
            String delimiter = matcher.group(1);
            validateNotEmptyDelimiter(delimiter);
            return delimiter;
        }
        throw new IllegalArgumentException("커스텀 구분자는 " + leftPattern + "와 " + rightPattern + " 사이에 와야 합니다.");
    }

    private static void validateNotEmptyDelimiter(String delimiter) {
        if (delimiter.isEmpty()) {
            throw new IllegalArgumentException("커스텀 구분자가 비어 있습니다.");
        }
    }

    public int patternSize(Delimiter customDelimiter) {
        return leftPattern.length() + rightPattern.length() + customDelimiter.getLength();
    }
}
