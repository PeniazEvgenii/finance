package by.it_academy.jd2.commonlib.exception;

public class UpdateTimeMismatchException extends RuntimeException{

    private static final String DEFAULT_MESSAGE = "Запрос содержит некорректное время обновления";

    public UpdateTimeMismatchException() {
        super(DEFAULT_MESSAGE);
    }

    public UpdateTimeMismatchException(String message) {
        super(message);
    }
}
