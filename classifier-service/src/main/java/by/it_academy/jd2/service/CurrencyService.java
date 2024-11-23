package by.it_academy.jd2.service;

import by.it_academy.jd2.commonlib.aop.LoggingAspect;
import by.it_academy.jd2.commonlib.dto.PageDto;
import by.it_academy.jd2.commonlib.page.PageOf;
import by.it_academy.jd2.repository.ICurrencyRepository;
import by.it_academy.jd2.repository.entity.CurrencyEntity;
import by.it_academy.jd2.service.api.ICurrencyService;
import by.it_academy.jd2.service.dto.CurrencyCreateDto;
import by.it_academy.jd2.service.dto.CurrencyReadDto;
import by.it_academy.jd2.service.feign.api.IAuditService;
import by.it_academy.jd2.service.mapper.api.ICurrencyMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;
import java.util.UUID;

import static by.it_academy.jd2.commonlib.constant.Actions.AUDIT_CURRENCY_CREATE;

@LoggingAspect
@Service
@Validated
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CurrencyService implements ICurrencyService {

    private final ICurrencyRepository currencyRepository;
    private final ICurrencyMapper currencyMapper;
    private final IAuditService auditService;

    @Transactional
    @Override
    public void create(@Valid CurrencyCreateDto createDto) {
        Optional.of(createDto)
                .map(currencyMapper::mapCreate)
                .map(currencyRepository::saveAndFlush)
                .ifPresent(entity -> auditService.send(AUDIT_CURRENCY_CREATE, entity.getId()));
    }

    @Override
    public PageOf<CurrencyReadDto> findAll(@Valid PageDto pageDto) {
        Sort sortCurrency = Sort.sort(CurrencyEntity.class)
                .by(CurrencyEntity::getTitle)
                .ascending();

        PageRequest pageRequest = PageRequest.of(
                pageDto.getPage(),
                pageDto.getSize(),
                sortCurrency);

        Page<CurrencyReadDto> pageCurrency = currencyRepository
                .findAll(pageRequest)
                .map(currencyMapper::mapRead);

        return PageOf.of(pageCurrency);
    }

    @Override
    public Optional<CurrencyReadDto> findByTitle(String title) {
        return currencyRepository.findByTitleIgnoreCase(title)
                .map(currencyMapper::mapRead);
    }

    @Override
    public Optional<CurrencyReadDto> findById(UUID id) {
        return currencyRepository.findById(id)
                .map(currencyMapper::mapRead);
    }
}
