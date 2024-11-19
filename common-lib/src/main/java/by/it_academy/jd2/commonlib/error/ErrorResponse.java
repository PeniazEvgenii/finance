package by.it_academy.jd2.commonlib.error;

import lombok.Data;

@Data
public class ErrorResponse {
    private final EError logref;
    private final String message;
}
