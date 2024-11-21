package by.it_academy.jd2.service.exception;

public class MailSendException extends RuntimeException{
    public MailSendException() {
    }

    public MailSendException(String message) {
        super(message);
    }

    public MailSendException(String message, Throwable cause) {
        super(message, cause);
    }
}
