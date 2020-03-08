package me.will.community.exception;

/**
 * @author will
 */

public class CustomException extends RuntimeException {

    private String message;
    private Integer code;

    public CustomException(ICustomErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
