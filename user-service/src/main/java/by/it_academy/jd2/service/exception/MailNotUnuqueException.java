package by.it_academy.jd2.service.exception;

public class MailNotUnuqueException extends RuntimeException{
    public MailNotUnuqueException() {
        super("Пользователь с таким email уже существует");
    }

    public MailNotUnuqueException(String message) {
        super(message);
    }

    public MailNotUnuqueException(String message, Throwable cause) {
        super(message, cause);
    }
}
