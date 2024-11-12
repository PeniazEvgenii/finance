package by.it_academy.jd2.service.TESTWEBCLIENT;

import by.it_academy.jd2.service.feign.CurrencyInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.UUID;


@RequiredArgsConstructor
public class TestWebClientService {

    private final WebClient classifClient;

    public ResponseEntity<CurrencyInfoDto> getCurrency(String url, UUID id) {
        return classifClient.get()
                .uri("http://" + url + "/info/currency/{id}", id)
                .retrieve()
                .toEntity(CurrencyInfoDto.class)
                .block();
    }
}
