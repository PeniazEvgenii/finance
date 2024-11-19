package by.it_academy.jd2.service.validation;

import by.it_academy.jd2.service.api.IAccountService;
import by.it_academy.jd2.service.dto.AccountReadDto;
import by.it_academy.jd2.service.dto.AccountUpdateDto;
import by.it_academy.jd2.service.validation.annotation.AccountUpdate;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CheckAccountUpdate implements ConstraintValidator<AccountUpdate, AccountUpdateDto> {

    private final IAccountService accountService;

    @Override
    public boolean isValid(AccountUpdateDto value, ConstraintValidatorContext context) {
        AccountReadDto account = accountService.findById(value.getId());

        return account.getDtUpdate().equals(value.getDtUpdate());
    }
}
