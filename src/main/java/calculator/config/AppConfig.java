package calculator.config;

import calculator.application.calculator.AddCalculator;
import calculator.application.extractor.CustomDelimiterExtractor;
import calculator.application.extractor.OperandExtractor;
import calculator.application.translator.AddExpressionTranslator;
import calculator.application.translator.ExpressionTranslator;
import calculator.io.input.ConsoleInput;
import calculator.io.input.InputInterface;
import calculator.io.output.ConsoleView;

public class AppConfig {
    private static final AppConfig INSTANCE = new AppConfig();

    private AppConfig() {
    }

    public static AppConfig getInstance() {
        return INSTANCE;
    }

    public AddCalculator addCalculator() {
        return new AddCalculator(expressionTranslator(), consoleView());
    }

    private ExpressionTranslator expressionTranslator() {
        return new AddExpressionTranslator(inputInterface(), customDelimiterExtractor(), operandExtractor());
    }

    private OperandExtractor operandExtractor() {
        return new OperandExtractor();
    }

    private CustomDelimiterExtractor customDelimiterExtractor() {
        return new CustomDelimiterExtractor();
    }

    private InputInterface inputInterface() {
        return new ConsoleInput();
    }

    private ConsoleView consoleView() {
        return new ConsoleView();
    }
}
