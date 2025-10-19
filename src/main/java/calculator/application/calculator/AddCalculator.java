package calculator.application.calculator;

import calculator.application.translator.ExpressionTranslator;
import calculator.domain.expression.Expression;
import calculator.io.output.ConsoleView;

public class AddCalculator {
    private final ExpressionTranslator expressionTranslator;
    private final ConsoleView consoleView;

    public AddCalculator(ExpressionTranslator expressionTranslator, ConsoleView consoleView) {
        this.expressionTranslator = expressionTranslator;
        this.consoleView = consoleView;
    }

    public void startCalculate() {
        consoleView.printAskAddNumbers();

        Expression expression = expressionTranslator.getExpression();
        long result = expression.calculate();
        
        consoleView.printResult(String.valueOf(result));
    }
}
