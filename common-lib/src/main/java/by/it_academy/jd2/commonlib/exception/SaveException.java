package by.it_academy.jd2.commonlib.exception;

public class SaveException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "Ошибка во время сохранения объекта";


    public SaveException() {
        super(DEFAULT_MESSAGE);
    }

    public SaveException(String message) {
        super(message);
    }
}
