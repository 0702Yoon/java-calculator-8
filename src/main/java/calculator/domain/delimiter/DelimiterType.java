package calculator.domain.delimiter;

import java.util.ArrayList;
import java.util.List;

public enum DelimiterType {
    ADD(List.of(Delimiter.defaultDelimiter(":"), Delimiter.defaultDelimiter(",")), CustomDelimiterRule.ADD);

    private final List<Delimiter> defaultDelimiters;
    private final CustomDelimiterRule customRule;

    DelimiterType(List<Delimiter> defaultDelimiters, CustomDelimiterRule customRule) {
        this.defaultDelimiters = defaultDelimiters;
        this.customRule = customRule;
    }

    public List<Delimiter> getDefaultDelimiters() {
        return new ArrayList<>(defaultDelimiters);
    }

    public CustomDelimiterRule getCustomRule() {
        return customRule;
    }

    public String removeDelimiterDeclaration(Delimiter customDelimiter, String line) {
        int patternSize = customRule.patternSize(customDelimiter);
        return line.substring(patternSize);
    }
}