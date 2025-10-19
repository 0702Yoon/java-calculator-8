package calculator.domain.expression.add;

import calculator.domain.expression.Expression;

public class AddExpression implements Expression {
    private final long[] operandArr;

    private AddExpression(long[] operandArr) {
        this.operandArr = operandArr;
    }

    public static Expression of(long[] operandList) {
        return new AddExpression(operandList);
    }

    @Override
    public long calculate() {
        long result = 0;
        for (long e : operandArr) {
            result += e;
        }
        return result;
    }
}
