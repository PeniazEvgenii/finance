package by.it_academy.jd2.interation.repository;

import by.it_academy.jd2.interation.IntegrationTestBase;
import by.it_academy.jd2.interation.annotation.IT;
import by.it_academy.jd2.repository.ICurrencyRepository;
import by.it_academy.jd2.repository.entity.CurrencyEntity;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@RequiredArgsConstructor
class ICurrencyRepositoryIT extends IntegrationTestBase {

    private final ICurrencyRepository repository;

    @Test
    void findByTitleIgnoreCase() {
        Optional<CurrencyEntity> usd = repository.findByTitleIgnoreCase("USD");
        assertTrue(usd.isPresent());
        usd.ifPresent(currency -> assertEquals(UUID.fromString("7eeebe68-ab98-4c71-af8e-6a2d5e0acc42"), currency.getId()));
    }
}