package dev.willsnow.community.exception;

/**
 * @author will
 */

public class CustomException extends RuntimeException {

    private String message;

    public CustomException(ICustomErrorCode errorCode) {
        this.message = errorCode.getMessage();
    }

    public CustomException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
