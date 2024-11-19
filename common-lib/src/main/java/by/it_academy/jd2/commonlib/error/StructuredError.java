package by.it_academy.jd2.commonlib.error;

import lombok.Data;

@Data
public class StructuredError {
    private final String message;
    private final String field;
}
