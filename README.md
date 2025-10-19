# 1주차 덧셈 계산기

> 입력한 문자열에서 숫자를 추출하여 더하는 계산기를 구현한다.

## 기능 요구사항

- 쉼표(,) 또는 콜론(:)이 기본 구분자로 그것을 기준으로 분리한 각 숫자의 합을 반환한다.
- 문자열 앞부분의 “//”와 \n 사이에 문자열을 넣어 커스텀 구분자로 지정할 수 있다.
- 사용자가 잘못된 값을 입력한 경우 `IllegalArgumentException`을 발생시킨 후 애플리케이션은 종료가 되어야 한다.

### 추가적인 요구사항

- JDK 21버전에서 실행 가능해야 한다.
- `camp.nextstep.edu.missionutils`에서 제공하는`Console`API를 사용하여 구현해야 한다.
    - 사용자가 입력하는 값은`camp.nextstep.edu.missionutils.Console`의`readLine()`을 활용한다.

## ✈️ 구현된 기능

### **입출력**

- 출력 객체 구현(ConsoleView)
- 입력을 담당할 객체 및 인터페이스 구현 (InputInterface, ConsoleInput)
    - Console API 사용

### **덧셈**

- 계산식 인터페이스 구현 (Expression)
    - 덧셈 구체 클래스 구현 (AddExpression)
- 구분자 타입 정의 및 클래스 구현(DelimiterType, Delimiter, CustomDelimiterRule)
- 커스텀 구분자 추출 기능 (CustomDelimiterExtractor)
- 피연산자 추출 기능 (OperandExtractor)
- 문자열을 계산식으로 변환하는 기능 (ExpressionTranslator, AddExpressionTranslator)

### **기타**

- 의존 관리 객체 구현(AppConfig)

## 예외처리

### 입력 예외

- 빈 문자열 입력시 예외 발생

### 숫자 예외

- 음수 입력시 예외 발생

### 구분자 예외

- 지원하지 않은 구분자 사용시 예외 발생
- 구분자만 입력하거나, 시작에 구분자가 있을시 예외 발생
- 연속된 구분자나 구분자로 끝나는 경우 예외 발생

### 커스텀 구분자 예외

- 빈 커스텀 구분자 입력시 예외 발생
- 잘못된 형식의 커스텀 구분자 입력자 입력시 예외 발생
- 숫자로 구성된 커스텀 구분자일 경우 예외 발생