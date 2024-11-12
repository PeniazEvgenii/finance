package by.it_academy.jd2.service.feign.api;

import by.it_academy.jd2.service.feign.CurrencyInfoDto;
import by.it_academy.jd2.service.feign.OperationCategoryInfoDto;

import java.util.Optional;
import java.util.UUID;

public interface IClassifierService {

    Optional<CurrencyInfoDto> findCurrencyById(UUID id);

    public Optional<OperationCategoryInfoDto> findOperationCategoryById(UUID id);

    boolean checkCurrency(UUID id);                                                                                         //удалить!!!!
}
