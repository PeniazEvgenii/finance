package by.it_academy.jd2.service.exception;

public class AccountStatusException extends RuntimeException{
    public AccountStatusException() {
        super("Учетная запись неактивна");
    }

    public AccountStatusException(String message) {
        super(message);
    }
}
