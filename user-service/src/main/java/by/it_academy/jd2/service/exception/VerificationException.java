package by.it_academy.jd2.service.exception;

public class VerificationException extends RuntimeException{
    public VerificationException() {
        super("Неверно введен mail или code");
    }

    public VerificationException(String message) {
        super(message);
    }
}
