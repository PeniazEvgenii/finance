package by.it_academy.jd2.service.feign;

import by.it_academy.jd2.controller.feign.ClassifierClient;
import by.it_academy.jd2.service.feign.api.IClassifierService;
import by.it_academy.jd2.service.feign.dto.CurrencyInfoDto;
import by.it_academy.jd2.service.feign.dto.OperationCategoryInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ClassifierService implements IClassifierService {

    private final ClassifierClient classifierClient;

    public Optional<CurrencyInfoDto> findCurrencyById(UUID id) {
        try{
            ResponseEntity<CurrencyInfoDto> currency = classifierClient.findCurrencyById(id);
            return Optional.ofNullable(currency.getBody());
        } catch (Exception exception) {
            throw new RuntimeException("Error connection", exception);                                    //  надо на что-то поменять
        }
    }

    public Optional<OperationCategoryInfoDto> findOperationCategoryById(UUID id) {
        try{
            ResponseEntity<OperationCategoryInfoDto> categoryById = classifierClient.findCategoryById(id);
            return Optional.ofNullable(categoryById.getBody());
        } catch (Exception exception) {
            throw new RuntimeException("Error connection", exception);                                    //  надо на что-то поменять
        }
    }

}
