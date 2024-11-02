package by.it_academy.jd2.commonlib.error;

public class StructuredError {
    private final String message;
    private final String field;

    public StructuredError(String message, String field) {
        this.message = message;
        this.field = field;
    }

    public String getMessage() {
        return message;
    }

    public String getField() {
        return field;
    }
}
