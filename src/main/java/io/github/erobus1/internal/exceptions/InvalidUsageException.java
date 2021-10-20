package io.github.erobus1.internal.exceptions;

public class InvalidUsageException extends FrameworkException {
    public InvalidUsageException(String message) {
        super("Usage not correct - " + message);
    }
}
