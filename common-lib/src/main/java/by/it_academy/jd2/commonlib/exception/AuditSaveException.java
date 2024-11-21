package by.it_academy.jd2.commonlib.exception;

public class AuditSaveException extends RuntimeException{

    private static final String DEFAULT_MESSAGE = "Ошибка во время сохранения аудит события";

    public AuditSaveException() {
        super(DEFAULT_MESSAGE);
    }

    public AuditSaveException(String message) {
        super(message);
    }
}
