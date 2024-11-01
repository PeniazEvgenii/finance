package by.it_academy.jd2.service.mapper;

import by.it_academy.jd2.repository.entity.CurrencyEntity;
import by.it_academy.jd2.service.dto.CurrencyCreateDto;
import by.it_academy.jd2.service.dto.CurrencyReadDto;
import by.it_academy.jd2.service.mapper.api.ICurrencyMapper;
import org.springframework.stereotype.Component;

@Component
public class CurrencyMapper implements ICurrencyMapper {

    public CurrencyEntity mapCreate(CurrencyCreateDto createDto) {
        return CurrencyEntity.builder()
                .title(createDto.getTitle())
                .description(createDto.getDescription())
                .build();
    }

    public CurrencyReadDto mapRead(CurrencyEntity entity) {
        return CurrencyReadDto.builder()
                .id(entity.getId())
                .dtCreate(entity.getDtCreate())
                .dtUpdate(entity.getDtUpdate())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .build();
    }
}
