package by.it_academy.jd2.service.testTemplate;

import by.it_academy.jd2.commonlib.exception.IdNotFoundException;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class VerifyService {

    private final ClassifierClientTemplate classifierClientTemplate;

    public void verifyCurrencyExists(UUID currencyId) {
        boolean exists = classifierClientTemplate.currencyExists(currencyId);
        if (!exists) {
            throw new IdNotFoundException("Currency not found with ID: " + currencyId);
        }
    }

    public void verifyCategoryExists(UUID categoryId) {
        boolean exists = classifierClientTemplate.categoryExists(categoryId);
        if (!exists) {
            throw new IdNotFoundException("Category not found with ID: " + categoryId);
        }
    }
}
