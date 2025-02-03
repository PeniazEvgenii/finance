package by.it_academy.jd2.service.util;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class CodeGeneratorTest {

    private static final Integer MIN_RANGE = 1_000_000;
    private static final Integer MAX_RANGE = 9_999_999;

    @RepeatedTest(10)
    void generateNumericCode() {
        String result = CodeGenerator.generateNumericCode();

        assertNotNull(result);
        assertThat(Integer.parseInt(result)).isBetween(MIN_RANGE, MAX_RANGE);
    }

    @Test
    void generateAlphaNumericCode() {
        String result = CodeGenerator.generateAlphaNumericCode();

        assertNotNull(result);
    }
}