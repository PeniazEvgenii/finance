package by.it_academy.jd2.commonlib.exception;

public class ConnectionException extends RuntimeException{

    private static final String DEFAULT_MESSAGE = "Ошибка во время соедениния с сервисом";

    public ConnectionException() {
        super(DEFAULT_MESSAGE);
    }

    public ConnectionException(String message) {
        super(message);
    }

    public ConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
