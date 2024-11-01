package by.it_academy.jd2.service.mapper.api;

import by.it_academy.jd2.repository.entity.CurrencyEntity;
import by.it_academy.jd2.service.dto.CurrencyCreateDto;
import by.it_academy.jd2.service.dto.CurrencyReadDto;

public interface ICurrencyMapper {

    CurrencyEntity mapCreate(CurrencyCreateDto createDto);

    CurrencyReadDto mapRead(CurrencyEntity entity);
}
