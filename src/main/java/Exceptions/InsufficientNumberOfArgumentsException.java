package Exceptions;

public class InsufficientNumberOfArgumentsException extends SyntaxException {
    public InsufficientNumberOfArgumentsException(String message) {
        super(message);
    }
    public InsufficientNumberOfArgumentsException() {
        super();
    }
}
