package by.it_academy.jd2.service.testTemplate;

import by.it_academy.jd2.service.feign.CurrencyInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@RequiredArgsConstructor
public class ClassifierClientTemplate {

    private final RestTemplate restTemplate;

    public boolean currencyExists(UUID currencyId) {
        try {
            ResponseEntity<CurrencyInfoDto> response = restTemplate.getForEntity(
                    "http://192.168.100.29:8081/info/currency/{currencyId}",
                    CurrencyInfoDto.class,
                    currencyId
            );
            CurrencyInfoDto body = response.getBody();
            return response.getStatusCode() == HttpStatus.OK;
        } catch (HttpClientErrorException.NotFound e) {
            return false;
        }
    }

    public boolean categoryExists(UUID categoryId) {
        try {
            ResponseEntity<Void> response = restTemplate.getForEntity(
                    "http://192.168.100.29:8081/api/v1/category/{categoryId}",
                    Void.class,
                    categoryId
            );
            return response.getStatusCode() == HttpStatus.OK;
        } catch (HttpClientErrorException.NotFound e) {
            return false;
        }
    }
}
