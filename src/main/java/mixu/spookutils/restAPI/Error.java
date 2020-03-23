package mixu.spookutils.restAPI;


public class Error {
    private final Integer statusCode;
    private final String exceptionMessage;

    public Error(Integer errorCode, String exceptionMessage) {
        this.statusCode = errorCode;
        this.exceptionMessage = exceptionMessage;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }
}
