package by.it_academy.jd2.error;

import java.util.List;

public class StructuredErrorResponse {
    private final EError logref;
    private final List<StructuredError> errors;


    public StructuredErrorResponse(EError logref, List<StructuredError> errors) {
        this.logref = logref;
        this.errors = errors;
    }

    public EError getLogref() {
        return logref;
    }

    public List<StructuredError> getErrors() {
        return errors;
    }

}


