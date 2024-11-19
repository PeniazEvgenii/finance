package by.it_academy.jd2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;

@Slf4j
@SpringBootApplication
@EnableFeignClients
@ConfigurationPropertiesScan
public class ApplicationClassifierRunner {
    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(ApplicationClassifierRunner.class, args);
        log.info("Количество бинов в beanDefinitions = {}",context.getBeanDefinitionCount());

    }
}
