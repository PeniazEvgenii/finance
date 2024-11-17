package by.it_academy.jd2.commonlib.exception;

public class SaveException extends RuntimeException{
    public SaveException() {
        super("Ошибка во время сохранения объекта");
    }

    public SaveException(String message) {
        super(message);
    }
}
