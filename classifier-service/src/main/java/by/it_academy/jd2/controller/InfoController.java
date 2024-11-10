package by.it_academy.jd2.controller;

import by.it_academy.jd2.service.api.ICurrencyService;
import by.it_academy.jd2.service.api.IOperationCategoryService;
import by.it_academy.jd2.service.dto.CurrencyReadDto;
import by.it_academy.jd2.service.dto.OperationCategoryReadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("/info")
@RestController
public class InfoController {

    private final ICurrencyService currencyService;
    private final IOperationCategoryService operationCategoryService;

    @GetMapping("/currency/{uuid}")
    public ResponseEntity<CurrencyReadDto> findCurrencyById(@PathVariable("uuid") UUID id) {
        return currencyService.findById(id)
                .map(currency -> ResponseEntity.ok().body(currency))
                .orElseGet(() -> ResponseEntity.ok().body(null));                               //оптимальный вариант
    }

    @GetMapping("/category/{uuid}")
    public ResponseEntity<OperationCategoryReadDto> findCategoryById(@PathVariable("uuid") UUID id) {
        return operationCategoryService.findById(id)
                .map(category -> ResponseEntity.ok().body(category))
                .orElseGet(() -> ResponseEntity.ok().body(null));
    }
}
