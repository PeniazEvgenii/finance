package by.it_academy.jd2.commonlib.exception;

public class IdNotFoundException extends RuntimeException{

    private static final String DEFAULT_MESSAGE = "Запрос содержит некорректный ID";

    public IdNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public IdNotFoundException(String message) {
        super(message);
    }
}
