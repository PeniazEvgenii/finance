package by.it_academy.jd2.service.exception;

public class IdNotFoundException extends RuntimeException{
    public IdNotFoundException() {
    }

    public IdNotFoundException(String message) {
        super(message);
    }
}
