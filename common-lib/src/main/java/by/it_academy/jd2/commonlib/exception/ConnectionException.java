package by.it_academy.jd2.commonlib.exception;

public class ConnectionException extends RuntimeException{
    public ConnectionException() {
        super("Ошибка во время соедениния с сервисом");
    }

    public ConnectionException(String message) {
        super(message);
    }
}
