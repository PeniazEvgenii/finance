package by.it_academy.jd2.service.exception;

public class AccountStatusException extends RuntimeException{

    private static final String DEFAULT_MESSAGE = "Учетная запись неактивна";

    public AccountStatusException() {
        super(DEFAULT_MESSAGE);
    }

    public AccountStatusException(String message) {
        super(message);
    }
}
