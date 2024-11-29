package by.it_academy.jd2.schedulerservice.service;

import by.it_academy.jd2.commonlib.exception.IdNotFoundException;
import by.it_academy.jd2.commonlib.exception.SaveException;
import by.it_academy.jd2.schedulerservice.repository.IOperationRepository;
import by.it_academy.jd2.schedulerservice.repository.entity.Operation;
import by.it_academy.jd2.schedulerservice.service.api.IOperationService;
import by.it_academy.jd2.schedulerservice.service.dto.OperationDto;
import by.it_academy.jd2.schedulerservice.service.mapStruct.OperationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OperationService implements IOperationService {

    private final IOperationRepository operationRepository;
    private final OperationMapper mapper;

    @Transactional
    public Operation create(OperationDto dto) {
        return Optional.of(dto)
                .map(mapper::toEntity)
                .map(operationRepository::saveAndFlush)
                .orElseThrow(SaveException::new);
    }

    public Operation findById(UUID id) {
        return operationRepository
                .findById(id)
                .orElseThrow(IdNotFoundException::new);
    }

    @Transactional
    public Operation update(UUID id, OperationDto dto) {

        Operation operation = this.findById(id);
        return mapper.updateEntityFromDto(dto, operation);
    }
}
