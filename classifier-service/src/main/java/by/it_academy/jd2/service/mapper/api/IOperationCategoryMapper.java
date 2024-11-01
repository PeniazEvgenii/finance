package by.it_academy.jd2.service.mapper.api;

import by.it_academy.jd2.repository.entity.OperationCategoryEntity;
import by.it_academy.jd2.service.dto.OperationCategoryCreateDto;
import by.it_academy.jd2.service.dto.OperationCategoryReadDto;

public interface IOperationCategoryMapper {

    OperationCategoryEntity mapCreate(OperationCategoryCreateDto createDto);

    OperationCategoryReadDto mapRead(OperationCategoryEntity entity);
}
