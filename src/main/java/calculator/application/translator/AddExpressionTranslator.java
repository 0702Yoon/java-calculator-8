package calculator.application.translator;

import calculator.application.extractor.CustomDelimiterExtractor;
import calculator.application.extractor.OperandExtractor;
import calculator.domain.delimiter.Delimiter;
import calculator.domain.delimiter.DelimiterType;
import calculator.domain.expression.Expression;
import calculator.domain.expression.add.AddExpression;
import calculator.io.input.InputInterface;
import java.util.List;
import java.util.Optional;

public class AddExpressionTranslator implements ExpressionTranslator {
    private final InputInterface inputInterface;
    private final CustomDelimiterExtractor customDelimiterExtractor;
    private final OperandExtractor operandExtractor;

    private final DelimiterType delimiterType = DelimiterType.ADD;

    public AddExpressionTranslator(InputInterface inputInterface, CustomDelimiterExtractor customDelimiterExtractor,
                                   OperandExtractor operandExtractor) {
        this.inputInterface = inputInterface;
        this.customDelimiterExtractor = customDelimiterExtractor;
        this.operandExtractor = operandExtractor;
    }

    @Override
    public Expression getExpression() {
        String line = inputInterface.readLine();
        List<Delimiter> delimiters = delimiterType.getDefaultDelimiters();

        Optional<Delimiter> customDelimiter = customDelimiterExtractor.getCustomDelimiter(line,
                delimiterType.getCustomRule());

        if (customDelimiter.isPresent()) {
            delimiters.add(customDelimiter.get());
            line = delimiterType.removeDelimiterDeclaration(customDelimiter.get(), line);
        }
        long[] operandList = operandExtractor.extractOperand(line, delimiters);
        return AddExpression.of(operandList);
    }
}
