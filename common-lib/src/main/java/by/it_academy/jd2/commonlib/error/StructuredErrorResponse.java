package by.it_academy.jd2.commonlib.error;

import lombok.Data;

import java.util.List;

@Data
public class StructuredErrorResponse {
    private final EError logref;
    private final List<StructuredError> errors;
}


