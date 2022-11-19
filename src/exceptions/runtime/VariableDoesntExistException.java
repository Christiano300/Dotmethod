package exceptions.runtime;

public class VariableDoesntExistException extends Exception {
    public VariableDoesntExistException(String message) {
        super(message);
    }
}
