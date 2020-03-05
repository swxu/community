package dev.willsnow.community.exception;

/**
 * @author will
 */


public enum CustomErrorCode implements ICustomErrorCode {
    /**
     *
     */
    QUESTION_NOT_FOUND("Question doesn't exist.");

    private String message;

    CustomErrorCode(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
