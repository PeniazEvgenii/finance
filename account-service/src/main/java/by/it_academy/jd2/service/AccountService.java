package by.it_academy.jd2.service;

import by.it_academy.jd2.commonlib.exception.IdNotFoundException;
import by.it_academy.jd2.commonlib.exception.UpdateTimeMismatchException;
import by.it_academy.jd2.commonlib.page.PageOf;
import by.it_academy.jd2.repository.IAccountRepository;
import by.it_academy.jd2.repository.entity.AccountEntity;
import by.it_academy.jd2.service.api.IAccountService;
import by.it_academy.jd2.service.dto.AccountCreateDto;
import by.it_academy.jd2.service.dto.AccountReadDto;
import by.it_academy.jd2.service.dto.AccountUpdateDto;
import by.it_academy.jd2.service.dto.PageDto;
import by.it_academy.jd2.service.mapper.IAccountMapper;
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

@Validated
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AccountService implements IAccountService {

    private final IAccountMapper accountMapper;
    private final IAccountRepository accountRepository;

    @Transactional
    @Override
    public void create(@Valid AccountCreateDto createDto) {
        Optional.of(createDto)
                .map(accountMapper::mapCreate)
                .map(accountRepository::saveAndFlush)
                .orElseThrow();                                 // свое исключение проброшу
    }

    @Override
    public PageOf<AccountReadDto> findAll(@Valid PageDto pageDto) {
        Sort sortByTitle = Sort.sort(AccountReadDto.class)
                .by(AccountReadDto::getTitle)
                .ascending();
        PageRequest pageRequest = PageRequest.of(
                pageDto.getPage(),
                pageDto.getSize(),
                sortByTitle);
        Page<AccountReadDto> accounts = accountRepository
                .findAll(pageRequest)
                .map(accountMapper::mapRead);
        return PageOf.of(accounts);
    }

    @Override
    public Optional<AccountReadDto> findById(UUID id) {
        return accountRepository.findById(id)
               .map(accountMapper::mapRead);
    }

    @Transactional
    @Override
    public void update(@Valid AccountUpdateDto updateDto) {
//        AccountEntity accountEntity = accountRepository.findById(updateDto.getId())         // наверное в аннотации бессмысленно чекать существ ли  и делать плюс запрос
//                .orElseThrow(IdNotFoundException::new);
//                                                                                            // а можно в аннотации чекнуть сразу аккаунт и время!!!
//        if(!updateDto.getDtUpdate().equals(accountEntity.getDtUpdate())) {
//            throw new UpdateTimeMismatchException();
//        }
//
//        Optional.of(accountEntity)
//                .map(entity -> accountMapper.mapUpdate(updateDto, entity))
//                .map(accountRepository::saveAndFlush)
//                .orElseThrow();                                                      // // свое исключение проброшу

        accountRepository.findById(updateDto.getId())               // компактно, не понятно время неверно или id
                .map(entity -> accountMapper.mapUpdate(updateDto, entity))
                .map(accountRepository::saveAndFlush)
                .orElseThrow();
    }

    @Override
    public Optional<AccountEntity> findEntityById(UUID id) {
        return accountRepository.findById(id);
    }
}
