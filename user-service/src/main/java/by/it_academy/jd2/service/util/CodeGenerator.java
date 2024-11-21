package by.it_academy.jd2.service.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.keygen.KeyGenerators;

import java.util.concurrent.ThreadLocalRandom;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CodeGenerator {

    private static final Integer MIN_RANGE = 1_000_000;
    private static final Integer MAX_RANGE = 9_999_999;

    public static String generateNumericCode() {
        ThreadLocalRandom current = ThreadLocalRandom.current();
        return String.valueOf(current.nextInt(MIN_RANGE, MAX_RANGE));
    }

    public static String generateAlphaNumericCode() {
        return KeyGenerators.string().generateKey();
    }
}
