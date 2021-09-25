package net.spookelton.spookutils.restAPI;


public class Error {
    private final Integer code;
    private final String message;

    public Error(Integer errorCode, String exceptionMessage) {
        this.code = errorCode;
        this.message = exceptionMessage;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
