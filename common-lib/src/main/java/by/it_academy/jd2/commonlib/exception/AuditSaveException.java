package by.it_academy.jd2.commonlib.exception;

public class AuditSaveException extends RuntimeException{
    public AuditSaveException() {
        super("Ошибка во сохранения аудит события");
    }

    public AuditSaveException(String message) {
        super(message);
    }
}
