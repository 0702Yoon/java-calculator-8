package calculator.domain.delimiter;


public class Delimiter {
    private final String delimiter;

    private Delimiter(String delimiter) {
        this.delimiter = delimiter;
    }

    public static Delimiter customDelimiter(String customDelimiter) {
        if (customDelimiter.chars().anyMatch(Character::isDigit)) {
            throw new IllegalArgumentException("구분자안에는 숫자가 존재하면 안됩니다.");
        }
        return new Delimiter(customDelimiter);
    }

    public static Delimiter defaultDelimiter(String delimiter) {
        return new Delimiter(delimiter);
    }

    public boolean isEqualTo(String input) {
        return this.delimiter.equals(input);
    }

    public int getLength() {
        return delimiter.length();
    }
}
