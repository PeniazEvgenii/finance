package by.it_academy.jd2.controller.handler;

import by.it_academy.jd2.commonlib.error.EError;
import by.it_academy.jd2.commonlib.error.ErrorResponse;
import by.it_academy.jd2.commonlib.error.StructuredError;
import by.it_academy.jd2.commonlib.error.StructuredErrorResponse;
import by.it_academy.jd2.service.exception.EmailSendException;
import by.it_academy.jd2.service.exception.IdNotFoundException;
import by.it_academy.jd2.service.exception.UpdateTimeMismatchException;
import by.it_academy.jd2.service.exception.VerificationException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.List;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StructuredErrorResponse> onMethodArgumentNotValidException(
            MethodArgumentNotValidException exception) {

        List<StructuredError> errors = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> new StructuredError(error.getDefaultMessage(), error.getField()))
                .toList();
        StructuredErrorResponse structuredErrorResponse = new StructuredErrorResponse(EError.STRUCTURED_ERROR, errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(structuredErrorResponse);
    }

    @ExceptionHandler(IdNotFoundException.class)
    public ResponseEntity<List<ErrorResponse>> onIdNotFoundException(
            IdNotFoundException exception) {
        ErrorResponse errorResponse = new ErrorResponse(EError.ERROR, "Запрос содержит некорректный ID. Измените запрос и отправьте его ещё раз");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(List.of(errorResponse));
    }

    @ExceptionHandler(UpdateTimeMismatchException.class)
    public ResponseEntity<List<ErrorResponse>> onUpdateTimeMismatchException(
            UpdateTimeMismatchException exception) {
        ErrorResponse errorResponse = new ErrorResponse(EError.ERROR,
                "Запрос содержит некорректное время обновления. Измените запрос и отправьте его ещё раз");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(List.of(errorResponse));
    }

    @ExceptionHandler(EmailSendException.class)
        public ResponseEntity<List<ErrorResponse>> onEmailSendException() {
        ErrorResponse errorResponse = new ErrorResponse(EError.ERROR,
                "Сервер не смог корректно обработать запрос. Попробуйте позже или обратитесь к администратору");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(List.of(errorResponse));
    }

    @ExceptionHandler(VerificationException.class)
        public ResponseEntity<List<ErrorResponse>> onVerificationException() {
        ErrorResponse errorResponse = new ErrorResponse(EError.ERROR,
                "Зарос содержит некорректные данные. Проверьте и повторите запрос");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(List.of(errorResponse));
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<List<ErrorResponse>> onNoResourceFoundException() {
        ErrorResponse errorResponse = new ErrorResponse(EError.ERROR,
                "Введен неверный URL");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(List.of(errorResponse));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<StructuredErrorResponse> onConstraintValidationException(
            ConstraintViolationException exception) {

        List<StructuredError> errors = exception.getConstraintViolations()
                .stream()
                .map(violation -> {
                    String fullPath = violation.getPropertyPath().toString();
                    String fieldName = fullPath.contains(".")
                            ? fullPath.substring(fullPath.lastIndexOf(".") + 1)
                            : fullPath;

                    return new StructuredError(violation.getMessage(), fieldName);
                })
                .toList();
        StructuredErrorResponse structuredErrorResponse = new StructuredErrorResponse(EError.STRUCTURED_ERROR, errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(structuredErrorResponse);
    }

    //HttpMessageNotReadableException     когда в теле ничего не передано

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<List<ErrorResponse>> onException() {
//        ErrorResponse errorResponse = new ErrorResponse(EError.ERROR,
//                "Сервер не смог корректно обработать запрос. Попробуйте позже или обратитесь к администратору");
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                .body(List.of(errorResponse));
//    }



}
