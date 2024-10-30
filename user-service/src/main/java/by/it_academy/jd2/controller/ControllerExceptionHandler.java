package by.it_academy.jd2.controller;

import by.it_academy.jd2.error.EError;
import by.it_academy.jd2.error.ErrorResponse;
import by.it_academy.jd2.error.StructuredError;
import by.it_academy.jd2.error.StructuredErrorResponse;
import by.it_academy.jd2.exception.IdNotFoundException;
import by.it_academy.jd2.exception.UpdateTimeMismatchException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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
        ErrorResponse errorResponse = new ErrorResponse(EError.ERROR, "Запрос содержит некорректное время обновления. Измените запрос и отправьте его ещё раз");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(List.of(errorResponse));
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



}
