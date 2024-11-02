package by.it_academy.jd2.controller;

import by.it_academy.jd2.commonlib.exception.IdNotFoundException;
import by.it_academy.jd2.service.IClassifierService;
import by.it_academy.jd2.service.dto.CurrencyReadDto;
import by.it_academy.jd2.service.dto.OperationCategoryReadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("/info")
@RestController
public class InfoController {

    private final IClassifierService classifierService;

    @GetMapping("/currency/{uuid}")
    public CurrencyReadDto findCurrencyById(@PathVariable("uuid") UUID id) {
        return classifierService.findCurrencyById(id)
                  .orElseThrow(IdNotFoundException::new);          // это внутри  между  сервисами, может так что возвращать. или
               // .orElse(null);                                                                   // либо просто null
    }

    @GetMapping("/category/{uuid}")
    public OperationCategoryReadDto findCategoryById(@PathVariable("uuid") UUID id) {
        return classifierService.findCategoryById(id)
                .orElseThrow(IdNotFoundException::new);           // это внутри  между  сервисами, может так что возвращать. или
             // .orElse(null);                                                                   // либо просто null
    }
}
