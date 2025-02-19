package by.it_academy.jd2.configuration;

import by.it_academy.jd2.configuration.properties.KafkaTopicNameProperties;
import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.kafka.transaction.KafkaTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;

import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class KafkaConfiguration {

    private final KafkaTopicNameProperties topicNames;

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootStrapServers;
    @Value("${spring.kafka.producer.acks}")
    private String acks;
    @Value("${spring.kafka.producer.properties.delivery.timeout.ms}")
    private String deliveryTimeout;
    @Value("${spring.kafka.producer.properties.linger.ms}")
    private String linger;
    @Value("${spring.kafka.producer.properties.request.timeout.ms}")
    private String requestTimeout;
    @Value("${spring.kafka.producer.properties.enable.idempotence}")
    private boolean idempotence;
    @Value("${spring.kafka.producer.properties.max.in.flight.requests.per.connection}")
    private int inflightRequests;
    @Value("${spring.kafka.producer.transaction-id-prefix}")
    private String transactionalIdPrefix;

    private Map<String, Object> producerConfigs() {
        return Map.of(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootStrapServers,
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class,
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class,
                ProducerConfig.ACKS_CONFIG, acks,
                ProducerConfig.DELIVERY_TIMEOUT_MS_CONFIG, deliveryTimeout,
                ProducerConfig.LINGER_MS_CONFIG, linger,
                ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, requestTimeout,
                ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION, inflightRequests,

                ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, idempotence,
                ProducerConfig.TRANSACTIONAL_ID_CONFIG, transactionalIdPrefix
        );
    }

    @Bean
    public ProducerFactory<String, Object> producerFactory() {
        producerConfigs().entrySet().forEach(System.out::println);
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    public KafkaTemplate<String, Object> kafkaTemplate2(ProducerFactory<String, Object> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }

    @Bean
    public KafkaTransactionManager<String, Object> kafkaTransactionManager(ProducerFactory<String, Object> producerFactory) {
        return new KafkaTransactionManager<>(producerFactory);
    }

    @Bean
    @Primary
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    @Bean
    public NewTopic createCodeTopic() {
        return TopicBuilder
                .name(topicNames.getCodeCreatedTopic())
                .partitions(3)
                .replicas(3)
                .configs(Map.of("min.insync.replicas","2"))
                .build();
    }

    @Bean
    public NewTopic createRegisterTopic() {
        return TopicBuilder
                .name(topicNames.getRegisterCompletedTopic())
                .partitions(3)
                .replicas(3)
                .configs(Map.of("min.insync.replicas","2"))
                .build();
    }
}
