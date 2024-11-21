package by.it_academy.jd2.service.exception;

public class InvalidCredentialsException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "Неверный логин или пароль";

    public InvalidCredentialsException() {
        super(DEFAULT_MESSAGE);
    }

    public InvalidCredentialsException(String message) {
        super(message);
    }
}
