package by.it_academy.jd2.service.test;

import by.it_academy.jd2.commonlib.exception.IdNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VerifyService {

    private final ClassifierClient classifierClient;

    public void verifyCurrencyExists(UUID currencyId) {
        boolean exists = classifierClient.currencyExists(currencyId);
        if (!exists) {
            throw new IdNotFoundException("Currency not found with ID: " + currencyId);
        }
    }

    public void verifyCategoryExists(UUID categoryId) {
        boolean exists = classifierClient.categoryExists(categoryId);
        if (!exists) {
            throw new IdNotFoundException("Category not found with ID: " + categoryId);
        }
    }
}
