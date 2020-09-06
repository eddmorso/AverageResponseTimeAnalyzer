package Exceptions;

public class SyntaxException extends AnalyzerException {
    public SyntaxException(String line) {
        super(line);
    }

    public SyntaxException() {

    }
}
