package by.it_academy.jd2.service.test;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ClassifierClient {

    private final RestTemplate restTemplate;

    public boolean currencyExists(UUID currencyId) {
        try {
            ResponseEntity<CurrencyReadDto> response = restTemplate.getForEntity(
                    "http://192.168.100.29:8080/info/currency/{currencyId}",
                    CurrencyReadDto.class,
                    currencyId
            );
            CurrencyReadDto body = response.getBody();
            return response.getStatusCode() == HttpStatus.OK;
        } catch (HttpClientErrorException.NotFound e) {
            return false;
        }
    }

    public boolean categoryExists(UUID categoryId) {
        try {
            ResponseEntity<Void> response = restTemplate.getForEntity(
                    "http://192.168.100.29/api/v1/category/{categoryId}",
                    Void.class,
                    categoryId
            );
            return response.getStatusCode() == HttpStatus.OK;
        } catch (HttpClientErrorException.NotFound e) {
            return false;
        }
    }
}
