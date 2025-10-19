package calculator.application.extractor;

import calculator.domain.delimiter.CustomDelimiterRule;
import calculator.domain.delimiter.Delimiter;
import java.util.Optional;

public class CustomDelimiterExtractor {
    public Optional<Delimiter> getCustomDelimiter(String line, CustomDelimiterRule rule) {
        if (isStartNumber(line)) {
            return Optional.empty();
        }
        String customDelimiter = rule.extractCustomDelimiter(line);
        return Optional.of(Delimiter.customDelimiter(customDelimiter));
    }

    private boolean isStartNumber(String line) {
        return Character.isDigit(line.charAt(0));
    }
}
