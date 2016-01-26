package com.grandland.glits.ms.exception;

/**
 * FieldEmptyException
 *
 * @author Allen Jin
 * @date 2016/01/26
 */
public class FieldEmptyException extends Exception {

    public FieldEmptyException() {
    }

    public FieldEmptyException(String message) {
        super(message);
    }

    public FieldEmptyException(Throwable cause) {
        super(cause);
    }

    public FieldEmptyException(String message, Throwable cause) {
        super(message, cause);
    }
}
