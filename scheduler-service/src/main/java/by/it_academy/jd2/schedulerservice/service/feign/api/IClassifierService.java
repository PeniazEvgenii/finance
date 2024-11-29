package by.it_academy.jd2.schedulerservice.service.feign.api;


import by.it_academy.jd2.schedulerservice.service.feign.dto.CurrencyInfoDto;
import by.it_academy.jd2.schedulerservice.service.feign.dto.OperationCategoryInfoDto;

import java.util.Optional;
import java.util.UUID;

public interface IClassifierService {

    Optional<CurrencyInfoDto> findCurrencyById(UUID id);

    Optional<OperationCategoryInfoDto> findOperationCategoryById(UUID id);

}
