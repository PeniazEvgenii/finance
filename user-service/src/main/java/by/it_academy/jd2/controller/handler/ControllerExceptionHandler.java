package by.it_academy.jd2.controller.handler;

import by.it_academy.jd2.commonlib.error.EError;
import by.it_academy.jd2.commonlib.error.ErrorResponse;
import by.it_academy.jd2.commonlib.error.StructuredError;
import by.it_academy.jd2.commonlib.error.StructuredErrorResponse;
import by.it_academy.jd2.commonlib.exception.IdNotFoundException;
import by.it_academy.jd2.commonlib.exception.SaveException;
import by.it_academy.jd2.commonlib.exception.UpdateTimeMismatchException;
import by.it_academy.jd2.service.exception.*;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.List;

@Slf4j
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
        log.error("Argument annotated with fails. Errors: {}", errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(structuredErrorResponse);
    }

    @ExceptionHandler(IdNotFoundException.class)
    public ResponseEntity<List<ErrorResponse>> onIdNotFoundException(IdNotFoundException exception) {
        ErrorResponse errorResponse = new ErrorResponse(EError.ERROR, exception.getMessage());
        log.error("Object with id not found");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(List.of(errorResponse));
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<List<ErrorResponse>> onInvalidCredentialsException(InvalidCredentialsException exception) {
        ErrorResponse errorResponse = new ErrorResponse(EError.ERROR, exception.getMessage());
        log.error("invalid password or mail for login");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(List.of(errorResponse));
    }

    @ExceptionHandler(AccountStatusException.class)
    public ResponseEntity<List<ErrorResponse>> onAccountStatusException(AccountStatusException exception) {
        ErrorResponse errorResponse = new ErrorResponse(EError.ERROR, exception.getMessage());
        log.error("try login to inactive account: {}", exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(List.of(errorResponse));
    }

    @ExceptionHandler(UpdateTimeMismatchException.class)
    public ResponseEntity<List<ErrorResponse>> onUpdateTimeMismatchException(UpdateTimeMismatchException exception) {
        ErrorResponse errorResponse = new ErrorResponse(EError.ERROR, exception.getMessage());
        log.error("TimeUpdate argument mismatch");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(List.of(errorResponse));
    }

    @ExceptionHandler(MailNotUniqueException.class)
    public ResponseEntity<List<ErrorResponse>> onMailNotUniqueException(MailNotUniqueException exception) {
        ErrorResponse errorResponse = new ErrorResponse(EError.ERROR, exception.getMessage());
        log.error("Mail is not unique");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(List.of(errorResponse));
    }

    @ExceptionHandler(MailSendException.class)
        public ResponseEntity<List<ErrorResponse>> onEmailSendException() {
        ErrorResponse errorResponse = new ErrorResponse(EError.ERROR,
                "Сервер не смог корректно обработать запрос. Попробуйте позже или обратитесь к администратору");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(List.of(errorResponse));
    }

    @ExceptionHandler(VerificationException.class)
        public ResponseEntity<List<ErrorResponse>> onVerificationException(VerificationException exception) {
        ErrorResponse errorResponse = new ErrorResponse(EError.ERROR, exception.getMessage());
        log.error("Verification exception: {}", exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(List.of(errorResponse));
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<List<ErrorResponse>> onNoResourceFoundException(NoResourceFoundException exception) {
        ErrorResponse errorResponse = new ErrorResponse(EError.ERROR, exception.getMessage());
        log.error("ResourceHttpRequestHandler can not find a resource: {}", exception.getMessage());
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
        log.error("Argument annotated with fails.Errors: {}", errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(structuredErrorResponse);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<List<ErrorResponse>> onHttpMessageNotReadableException (HttpMessageNotReadableException exception) {
        ErrorResponse errorResponse = new ErrorResponse(EError.ERROR,
                "Тип данных, переданных в запросе не соответствует требованиям");
        log.error("The HttpMessageConverter. read method fails: {}", exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(List.of(errorResponse));
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<List<ErrorResponse>> onHttpRequestMethodNotSupportedException(
            HttpRequestMethodNotSupportedException exception) {
        ErrorResponse errorResponse = new ErrorResponse(EError.ERROR, exception.getMessage());
        log.error("Request handler does not support a specific request method, error: {}", exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(List.of(errorResponse));
    }

    @ExceptionHandler(SaveException.class)
    public ResponseEntity<List<ErrorResponse>> onSaveException(SaveException exception) {
        ErrorResponse errorResponse = new ErrorResponse(EError.ERROR,
                "Сервер не смог корректно обработать запрос. Попробуйте позже или обратитесь к администратору");
        log.error("SaveException. Error saving object");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(List.of(errorResponse));
    }

    @ExceptionHandler(OptimisticLockingFailureException.class)
    public ResponseEntity<List<ErrorResponse>> onOptimisticLockingFailureException(OptimisticLockingFailureException exception) {
        ErrorResponse errorResponse = new ErrorResponse(EError.ERROR,
                "Объект был изменен другим пользователем. Попробуйте еще раз или обратитесь к администратору");
        log.error("Optimistic locking violation, error: {}", exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(List.of(errorResponse));
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<List<ErrorResponse>> onValidationException(
            ValidationException exception) {
        ErrorResponse errorResponse = new ErrorResponse(EError.ERROR,
                "Запрос содержит некорректные данные. Измените запрос и отправьте его ещё раз");
        log.error("ValidationException. Errors: {}", exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(List.of(errorResponse));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<List<ErrorResponse>> onException(Exception exception) {
        ErrorResponse errorResponse = new ErrorResponse(EError.ERROR,
                "Сервер не смог корректно обработать запрос. Попробуйте позже или обратитесь к администратору");
        log.error("Exception. Error: {}", exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(List.of(errorResponse));
    }

}
