package by.it_academy.jd2.controller;

import by.it_academy.jd2.commonlib.page.PageOf;
import by.it_academy.jd2.service.IClassifierService;
import by.it_academy.jd2.service.dto.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/classifier")
public class ClassifierController {

    private final IClassifierService classifierService;

    @PostMapping("/currency")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void createCurrency(@RequestBody @Valid CurrencyCreateDto currencyCreateDto) {

        classifierService.createCurrency(currencyCreateDto);
    }

    @GetMapping("/currency")
    @ResponseStatus(value = HttpStatus.OK)
    public PageOf<CurrencyReadDto> findAllCurrency(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                   @RequestParam(value = "size", defaultValue = "20") Integer size) {

        return classifierService.findAllCurrency(new PageDto(page, size));
    }

    @PostMapping("/operation/category")
    @ResponseStatus(value = HttpStatus.CREATED)                                                                         //опред может валид все на сервис
    public void createOperationCategory(@RequestBody @Valid OperationCategoryCreateDto operationCategoryCreateDto) {

        classifierService.createOperationCategory(operationCategoryCreateDto);
    }

    @GetMapping("/operation/category")
    @ResponseStatus(value = HttpStatus.OK)
    public PageOf<OperationCategoryReadDto> findAllOperationCategory(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                                     @RequestParam(value = "size", defaultValue = "20") Integer size) {

        return classifierService.findAllOperationCategory(new PageDto(page, size));
    }
}
