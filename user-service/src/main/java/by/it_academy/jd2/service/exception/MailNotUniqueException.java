package by.it_academy.jd2.service.exception;

public class MailNotUniqueException extends RuntimeException{

    private static final String DEFAULT_MESSAGE = "Пользователь с таким mail уже существует";


    public MailNotUniqueException() {
        super(DEFAULT_MESSAGE);
    }

    public MailNotUniqueException(String message) {
        super(message);
    }

    public MailNotUniqueException(String message, Throwable cause) {
        super(message, cause);
    }
}
