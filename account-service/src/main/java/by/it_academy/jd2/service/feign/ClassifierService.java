package by.it_academy.jd2.service.feign;

import by.it_academy.jd2.controller.feign.ClassifierClient;
import by.it_academy.jd2.service.feign.api.IClassifierService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ClassifierService implements IClassifierService {

    private final ClassifierClient classifierClient;

    public Optional<CurrencyInfoDto> findCurrencyById(UUID id) {
        ResponseEntity<CurrencyInfoDto> currency = classifierClient.findCurrencyById(id);
        if(currency.getStatusCode().is2xxSuccessful()) {
            return Optional.ofNullable(currency.getBody());
        } else {
            throw new RuntimeException("Error connection");      //  надо на что-то поменять
        }
    }

    public Optional<OperationCategoryInfoDto> findOperationCategoryById(UUID id) {
        ResponseEntity<OperationCategoryInfoDto> categoryById = classifierClient.findCategoryById(id);
        if(categoryById.getStatusCode().is2xxSuccessful()) {
            return Optional.ofNullable(categoryById.getBody());
        } else {
            throw new RuntimeException("Error connection");           //  надо на что-то поменять и отловить с 500 статусом
        }
    }

    public boolean checkCurrency(UUID id) {
        ResponseEntity<CurrencyInfoDto> currency = classifierClient.findCurrencyById(id);
        CurrencyInfoDto body = currency.getBody();
//        currency.getStatusCode();
//        currency.getBody();
        return currency != null;
    }
}
