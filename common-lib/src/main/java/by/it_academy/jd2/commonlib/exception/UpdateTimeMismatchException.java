package by.it_academy.jd2.commonlib.exception;

public class UpdateTimeMismatchException extends RuntimeException{

    public UpdateTimeMismatchException() {
        super("Запрос содержит некорректное время обновления");
    }

    public UpdateTimeMismatchException(String message) {
        super(message);
    }
}
