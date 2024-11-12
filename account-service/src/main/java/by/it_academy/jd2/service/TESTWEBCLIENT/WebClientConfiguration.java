package by.it_academy.jd2.service.TESTWEBCLIENT;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;


public class WebClientConfiguration {


    public WebClient classifClient() {
        return WebClient.builder()
                //  .baseUrl("${service.classifier}")          //можно не указывать здесь url. будет 1 универс
                .build();
    }
}
