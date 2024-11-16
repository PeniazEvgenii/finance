package by.it_academy.jd2.controller.handler;

import by.it_academy.jd2.commonlib.error.EError;
import by.it_academy.jd2.commonlib.error.ErrorResponse;
import by.it_academy.jd2.commonlib.error.StructuredError;
import by.it_academy.jd2.commonlib.error.StructuredErrorResponse;
import by.it_academy.jd2.commonlib.exception.IdNotFoundException;
import by.it_academy.jd2.commonlib.exception.UpdateTimeMismatchException;
import by.it_academy.jd2.service.exception.EmailSendException;
import by.it_academy.jd2.service.exception.VerificationException;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
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
    public ResponseEntity<List<ErrorResponse>> onIdNotFoundException(IdNotFoundException exception) {
        ErrorResponse errorResponse = new ErrorResponse(EError.ERROR, exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(List.of(errorResponse));
    }

    @ExceptionHandler(UpdateTimeMismatchException.class)
    public ResponseEntity<List<ErrorResponse>> onUpdateTimeMismatchException(UpdateTimeMismatchException exception) {
        ErrorResponse errorResponse = new ErrorResponse(EError.ERROR, exception.getMessage());
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
        public ResponseEntity<List<ErrorResponse>> onVerificationException(VerificationException exception) {
        ErrorResponse errorResponse = new ErrorResponse(EError.ERROR, exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(List.of(errorResponse));
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<List<ErrorResponse>> onNoResourceFoundException(NoResourceFoundException exception) {
        ErrorResponse errorResponse = new ErrorResponse(EError.ERROR, exception.getMessage());
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

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<List<ErrorResponse>> onHttpMessageNotReadableException (HttpMessageNotReadableException exception) {
        ErrorResponse errorResponse = new ErrorResponse(EError.ERROR,
                "Тип данных, переданных в запросе не соответствует требованиям");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(List.of(errorResponse));
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<List<ErrorResponse>> onHttpRequestMethodNotSupportedException(
            HttpRequestMethodNotSupportedException exception) {
        ErrorResponse errorResponse = new ErrorResponse(EError.ERROR, exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(List.of(errorResponse));
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<List<ErrorResponse>> onValidationException(
            ValidationException exception) {
        ErrorResponse errorResponse = new ErrorResponse(EError.ERROR,
                "Запрос содержит некорректные данные. Измените запрос и отправьте его ещё раз");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(List.of(errorResponse));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<List<ErrorResponse>> onException(Exception exception) {
        ErrorResponse errorResponse = new ErrorResponse(EError.ERROR,
                "Сервер не смог корректно обработать запрос. Попробуйте позже или обратитесь к администратору");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(List.of(errorResponse));
    }

}
