package by.it_academy.jd2.error;

public class ErrorResponse {
    private final EError logref;
    private final String message;

    public ErrorResponse(EError logref, String message) {
        this.logref = logref;
        this.message = message;
    }

    public EError getLogref() {
        return logref;
    }

    public String getMessage() {
        return message;
    }
}
