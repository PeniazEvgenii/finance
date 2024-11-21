package by.it_academy.jd2.service.exception;

public class EmailSendException extends RuntimeException{
    public EmailSendException() {
    }

    public EmailSendException(String message) {
        super(message);
    }

    public EmailSendException(String message, Throwable cause) {
        super(message, cause);
    }
}
