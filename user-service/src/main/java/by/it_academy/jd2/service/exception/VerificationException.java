package by.it_academy.jd2.service.exception;

public class VerificationException extends RuntimeException{

    private static final String DEFAULT_MESSAGE = "Неверно введен mail или code";


    public VerificationException() {
        super(DEFAULT_MESSAGE);
    }

    public VerificationException(String message) {
        super(message);
    }
}
