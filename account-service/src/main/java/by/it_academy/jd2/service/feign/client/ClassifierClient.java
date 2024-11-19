package by.it_academy.jd2.service.feign.client;

import by.it_academy.jd2.service.feign.dto.CurrencyInfoDto;
import by.it_academy.jd2.service.feign.dto.OperationCategoryInfoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "classifier", url = "${service.classifier}")
public interface ClassifierClient {

    @GetMapping("/info/currency/{uuid}")
    ResponseEntity<CurrencyInfoDto> findCurrencyById(@PathVariable("uuid") UUID id);

    @GetMapping("/info/category/{uuid}")
    ResponseEntity<OperationCategoryInfoDto> findCategoryById(@PathVariable("uuid") UUID id);
}
