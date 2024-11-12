package by.it_academy.jd2.commonlib.exception;

public class IdNotFoundException extends RuntimeException{
    public IdNotFoundException() {
        super("Запрос содержит некорректный ID");
    }

    public IdNotFoundException(String message) {
        super(message);
    }
}
