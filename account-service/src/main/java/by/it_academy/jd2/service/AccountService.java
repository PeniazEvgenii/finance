package by.it_academy.jd2.service;

import by.it_academy.jd2.commonlib.aop.LoggingAspect;
import by.it_academy.jd2.commonlib.dto.PageDto;
import by.it_academy.jd2.commonlib.exception.IdNotFoundException;
import by.it_academy.jd2.commonlib.exception.UpdateTimeMismatchException;
import by.it_academy.jd2.commonlib.page.PageOf;
import by.it_academy.jd2.repository.IAccountRepository;
import by.it_academy.jd2.repository.entity.AccountEntity;
import by.it_academy.jd2.service.api.IAccountService;
import by.it_academy.jd2.service.api.IUserHolderService;
import by.it_academy.jd2.service.dto.AccountCreateDto;
import by.it_academy.jd2.service.dto.AccountReadDto;
import by.it_academy.jd2.service.dto.AccountUpdateDto;
import by.it_academy.jd2.service.feign.api.IAuditService;
import by.it_academy.jd2.service.mapper.api.IAccountMapper;
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

import static by.it_academy.jd2.commonlib.constant.Actions.AUDIT_ACCOUNT_CREATE;
import static by.it_academy.jd2.commonlib.constant.Actions.AUDIT_ACCOUNT_UPDATE;

@LoggingAspect
@Service
@Validated
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AccountService implements IAccountService {

    private final IAccountRepository accountRepository;
    private final IUserHolderService userHolderService;
    private final IAccountMapper accountMapper;
    private final IAuditService auditService;

    @Transactional
    @Override
    public void create(@Valid AccountCreateDto createDto) {

        Optional.of(createDto)
                .map(accountMapper::mapCreate)
                .map(accountRepository::saveAndFlush)
                .ifPresent(account -> auditService.send(AUDIT_ACCOUNT_CREATE, account.getId()));
    }

    @Override
    public PageOf<AccountReadDto> findAll(@Valid PageDto pageDto) {
        UUID currentUserId = userHolderService.getUserId();

        Sort sortByTitle = Sort.sort(AccountEntity.class)
                .by(AccountEntity::getTitle)
                .ascending();

        PageRequest pageRequest = PageRequest.of(
                pageDto.getPage(),
                pageDto.getSize(),
                sortByTitle);

        Page<AccountReadDto> accounts = accountRepository
                .findAllByUserId(currentUserId, pageRequest)
                .map(accountMapper::mapRead);

        return PageOf.of(accounts);
    }

    @Override
    public AccountReadDto findById(UUID id) {
        AccountEntity account = this.findByIdAndUserId(id);
        return accountMapper.mapRead(account);
    }

    @Transactional
    @Override
    public void update(@Valid AccountCreateDto createDto,
                       @Valid AccountUpdateDto updateDto) {

        AccountEntity accountEntity = this.findByIdAndUserId(updateDto.getId());

        if (!accountEntity.getDtUpdate().equals(updateDto.getDtUpdate())) {
            throw new UpdateTimeMismatchException();
        }

        Optional.of(accountEntity)
                .map(entity -> accountMapper.mapUpdate(createDto, entity))
                .map(accountRepository::saveAndFlush)
                .ifPresent(entity -> auditService.send(AUDIT_ACCOUNT_UPDATE, entity.getId()));
    }

    @Override
    public AccountEntity findByIdAndUserId(UUID id) {
        UUID currentUserId = userHolderService.getUserId();
        return accountRepository.findByIdAndUserId(id, currentUserId)
                .orElseThrow(IdNotFoundException::new);
    }

    @Override
    public AccountEntity findEntityById(UUID id) {
        return accountRepository.findById(id)
                .orElseThrow(IdNotFoundException::new);
    }

    @Override
    public Optional<AccountReadDto> findByIdAndUserId(UUID id, UUID userId) {
        return accountRepository.findByIdAndUserId(id, userId)
                .map(accountMapper::mapRead);
    }
}
