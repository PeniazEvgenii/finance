package by.it_academy.jd2.commonlib.exception;

public class CurrencyMismatchException extends RuntimeException{
    public CurrencyMismatchException() {
    }

    public CurrencyMismatchException(String message) {
        super(message);
    }
}
