package by.it_academy.jd2.service.exception;

public class UpdateTimeMismatchException extends RuntimeException{
    public UpdateTimeMismatchException() {
    }

    public UpdateTimeMismatchException(String message) {
        super(message);
    }
}
