package by.it_academy.jd2.service.exchange.exception;

public class ExchangeRateException extends RuntimeException {
    public ExchangeRateException() {
    }

    public ExchangeRateException(String message) {
        super(message);
    }
}
