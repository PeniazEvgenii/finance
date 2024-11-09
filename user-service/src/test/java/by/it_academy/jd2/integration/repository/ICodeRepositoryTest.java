package by.it_academy.jd2.integration.repository;

import by.it_academy.jd2.integration.IntegrationTestBase;
import by.it_academy.jd2.repository.ICodeRepository;
import by.it_academy.jd2.repository.entity.CodeEntity;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RequiredArgsConstructor
class ICodeRepositoryTest extends IntegrationTestBase {

    private static final String MAIL = "user111@example.com";
    private static final String CODE = "5331924";

    private final ICodeRepository repository;

    @Test
    void findByMail() {
        Optional<CodeEntity> codeEntity = repository.findByMail(MAIL);
        assertTrue(codeEntity .isPresent());
        codeEntity .ifPresent(code -> assertEquals(CODE, code.getCode()));
    }
}