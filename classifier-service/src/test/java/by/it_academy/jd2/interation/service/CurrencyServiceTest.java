package by.it_academy.jd2.interation.service;

import by.it_academy.jd2.commonlib.page.PageOf;
import by.it_academy.jd2.interation.IntegrationTestBase;
import by.it_academy.jd2.repository.ICurrencyRepository;
import by.it_academy.jd2.service.CurrencyService;
import by.it_academy.jd2.service.dto.CurrencyCreateDto;
import by.it_academy.jd2.service.dto.CurrencyReadDto;
import by.it_academy.jd2.service.dto.PageDto;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RequiredArgsConstructor
class CurrencyServiceTest extends IntegrationTestBase {

    private final CurrencyService currencyService;
    private final ICurrencyRepository currencyRepository;

    @Test
    void create() {
        CurrencyCreateDto dto = new CurrencyCreateDto("BYN", "белорусский рубль");
        currencyService.create(dto);
        currencyRepository.findByTitleIgnoreCase("BYN").
                ifPresent(currency -> assertEquals(dto.getTitle(), currency.getTitle()));
    }

    @Test
    void findAll() {
        PageOf<CurrencyReadDto> all = currencyService.findAll(new PageDto(0, 3));
        assertThat(all.getContent()).hasSize(3);
        assertEquals(3, all.getSize());
        assertEquals(0, all.getNumber());
        assertTrue(all.isFirst());
    }

    @Test
    void findByTitle() {
        Optional<CurrencyReadDto> usd = currencyService.findByTitle("USD");
        assertTrue(usd.isPresent());
        usd.ifPresent(u -> assertEquals(u.getDescription(), "Доллар США"));
    }

    @Test
    void findById() {
        Optional<CurrencyReadDto> byId = currencyService.findById(UUID.fromString("7eeebe68-ab98-4c71-af8e-6a2d5e0acc42"));
        assertTrue(byId.isPresent());
        byId.ifPresent(currency -> assertEquals("USD", currency.getTitle()));
    }
}