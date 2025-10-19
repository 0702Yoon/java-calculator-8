package calculator;

import calculator.application.calculator.AddCalculator;
import calculator.config.AppConfig;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        AppConfig appConfig = AppConfig.getInstance();
        AddCalculator calculator = appConfig.addCalculator();
        calculator.startCalculate();
    }
}
