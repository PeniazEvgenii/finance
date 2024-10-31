package by.it_academy.jd2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.ConfigurableApplicationContext;

@Slf4j
@SpringBootApplication
@ConfigurationPropertiesScan
public class ApplicationRunner {
    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(ApplicationRunner.class, args);
        log.info("Количество бинов в beanDefinitions = {}",context.getBeanDefinitionCount());

    }
}
