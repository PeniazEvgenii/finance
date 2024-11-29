package by.it_academy.jd2.schedulerservice.service.feign;

import by.it_academy.jd2.schedulerservice.service.feign.api.IClassifierService;
import by.it_academy.jd2.schedulerservice.service.feign.client.IClassifierClient;
import by.it_academy.jd2.schedulerservice.service.feign.dto.CurrencyInfoDto;
import by.it_academy.jd2.schedulerservice.service.feign.dto.OperationCategoryInfoDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClassifierService implements IClassifierService {

    private final IClassifierClient classifierClient;

    public Optional<CurrencyInfoDto> findCurrencyById(UUID id) {

            ResponseEntity<CurrencyInfoDto> currency = classifierClient.findCurrencyById(id);
            return Optional.ofNullable(currency.getBody());

    }

    public Optional<OperationCategoryInfoDto> findOperationCategoryById(UUID id) {

            ResponseEntity<OperationCategoryInfoDto> categoryById = classifierClient.findCategoryById(id);
            return Optional.ofNullable(categoryById.getBody());

    }

}
