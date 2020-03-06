package dev.willsnow.community.exception;

/**
 * @author will
 */


public enum CustomErrorCode implements ICustomErrorCode {
    /**
     *
     */
    QUESTION_NOT_FOUND(2001, "问题已不存在"),
    TARGET_PARAM_NOT_FOUND(2002, "未指定任何问题或评论进行回复"),
    NO_LOGIN(2003, "请登录后再评论"),
    SYS_ERROR(2004, "系统错误，稍后重试"),
    TYPE_PARAM_ERROR(2005, "评论类型错误或不存在"),
    COMMENT_NOT_FOUND(2006, "评论已不存在"),
    ;

    private String message;
    private Integer code;

    CustomErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }
}
