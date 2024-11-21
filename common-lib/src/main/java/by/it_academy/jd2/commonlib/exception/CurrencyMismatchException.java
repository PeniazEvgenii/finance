package by.it_academy.jd2.commonlib.exception;

public class CurrencyMismatchException extends RuntimeException{

    private static final String DEFAULT_MESSAGE = "Указана неверная валюта";

    public CurrencyMismatchException() {
        super(DEFAULT_MESSAGE);
    }

    public CurrencyMismatchException(String message) {
        super(message);
    }
}
