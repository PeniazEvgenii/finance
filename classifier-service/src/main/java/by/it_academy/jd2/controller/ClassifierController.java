package by.it_academy.jd2.controller;

import by.it_academy.jd2.commonlib.aop.LoggingAspect;
import by.it_academy.jd2.commonlib.dto.PageDto;
import by.it_academy.jd2.commonlib.page.PageOf;
import by.it_academy.jd2.service.api.ICurrencyService;
import by.it_academy.jd2.service.api.IOperationCategoryService;
import by.it_academy.jd2.service.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@LoggingAspect
@RestController
@RequiredArgsConstructor
@RequestMapping("/classifier")
public class ClassifierController {

    private final ICurrencyService currencyService;
    private final IOperationCategoryService operationCategoryService;

    @PostMapping("/currency")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void createCurrency(@RequestBody CurrencyCreateDto currencyCreateDto) {

        currencyService.create(currencyCreateDto);
    }

    @GetMapping("/currency")
    @ResponseStatus(value = HttpStatus.OK)
    public PageOf<CurrencyReadDto> findAllCurrency(@RequestParam(defaultValue = "0") Integer page,
                                                   @RequestParam(defaultValue = "20") Integer size) {

        return currencyService.findAll(new PageDto(page, size));
    }

    @PostMapping("/operation/category")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void createOperationCategory(@RequestBody OperationCategoryCreateDto operationCategoryCreateDto) {

        operationCategoryService.create(operationCategoryCreateDto);
    }

    @GetMapping("/operation/category")
    @ResponseStatus(value = HttpStatus.OK)
    public PageOf<OperationCategoryReadDto> findAllOperationCategory(@RequestParam(defaultValue = "0") Integer page,
                                                                     @RequestParam(defaultValue = "20") Integer size) {

        return operationCategoryService.findAll(new PageDto(page, size));
    }
}
