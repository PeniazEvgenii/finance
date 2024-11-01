package by.it_academy.jd2.service.mapper;

import by.it_academy.jd2.repository.entity.OperationCategoryEntity;
import by.it_academy.jd2.service.dto.OperationCategoryCreateDto;
import by.it_academy.jd2.service.dto.OperationCategoryReadDto;
import by.it_academy.jd2.service.mapper.api.IOperationCategoryMapper;
import org.springframework.stereotype.Component;

@Component
public class OperationCategoryMapper implements IOperationCategoryMapper {

    public OperationCategoryEntity mapCreate(OperationCategoryCreateDto createDto){
        return OperationCategoryEntity.builder()
                .title(createDto.getTitle())
                .build();
    }

    public OperationCategoryReadDto mapRead(OperationCategoryEntity entity){
        return OperationCategoryReadDto.builder()
                .id(entity.getId())
                .dtCreate(entity.getDtCreate())
                .dtUpdate(entity.getDtUpdate())
                .title(entity.getTitle())
                .build();
    }
}
