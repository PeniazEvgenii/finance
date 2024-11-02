package by.it_academy.jd2.commonlib.error;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum EError {
    @JsonProperty(value = "error")
    ERROR,
    @JsonProperty(value = "structured_error")
    STRUCTURED_ERROR
}
