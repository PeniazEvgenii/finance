package by.it_academy.jd2.controller;

import by.it_academy.jd2.service.api.ICurrencyService;
import by.it_academy.jd2.service.api.IOperationCategoryService;
import by.it_academy.jd2.service.dto.CurrencyReadDto;
import by.it_academy.jd2.service.dto.OperationCategoryReadDto;
import by.it_academy.jd2.service.exchange.api.IExchangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("/info")
@RestController
public class InfoController {

    private final ICurrencyService currencyService;
    private final IExchangeService exchangeService;
    private final IOperationCategoryService operationCategoryService;

    @GetMapping("/currency/{uuid}")
    public ResponseEntity<CurrencyReadDto> findCurrencyById(@PathVariable("uuid") UUID id) {
        return currencyService.findById(id)
                .map(currency -> ResponseEntity.ok().body(currency))
                .orElseGet(() -> ResponseEntity.ok().body(null));
    }

    @GetMapping("/category/{uuid}")
    public ResponseEntity<OperationCategoryReadDto> findCategoryById(@PathVariable("uuid") UUID id) {
        return operationCategoryService.findById(id)
                .map(category -> ResponseEntity.ok().body(category))
                .orElseGet(() -> ResponseEntity.ok().body(null));
    }

    @GetMapping("/exchange_rate")
    public BigDecimal getExchangeRate(@RequestParam String base,
                                      @RequestParam String target) {

        return exchangeService.getCurrencyConversionRate(base, target);
    }
}
