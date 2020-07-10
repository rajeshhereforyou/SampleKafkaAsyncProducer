package com.example.async.producer.config;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
public class MyKafkaProducerConfig {

    @Value("${spring.application.name}")
    String appName;

    @Value("${kafka.producer.acks:all}")
    private String acksConfig;

    @Value("${kafka.producer.request.timeout.ms.config:10000}")
    private int requestTimeoutMsConfig;

    @Value("${kafka.producer.retries.config:3}")
    private int retries;

    @Value("${kafka.producer.retry.backoff.ms.config:3000}")
    private int retryBackoffMs;

    @Value("${kafka.producer.max.in.flight.requests.per.connection:1}")
    private int maxInFlightRequests;

    @Value("${kafka.producer.compression.type:none}")
    private String compressionType;

    @Value("${kafka.producer.batch.size:33554431}")
    private int batchSize;

    @Value("${kafka.producer.linger.ms:1200000}")
    private int lingerMS;

    @Value("${kafka.bootstrap.servers.config:localhost:9051}")
    private String bootstrapServersConfig;


    @Bean
    @Primary
    public ProducerFactory<String, Object> primaryProducerFactory() {
        return new DefaultKafkaProducerFactory<>(getCommonConfigs());
    }

    @PostConstruct
    public Map<String, Object> getCommonConfigs() {
        Map<String, Object> configs = new HashMap<>();

        configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServersConfig);
        configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configs.put(ProducerConfig.RETRIES_CONFIG, retries);
        configs.put(ProducerConfig.LINGER_MS_CONFIG, lingerMS);
        configs.put(ProducerConfig.ACKS_CONFIG, acksConfig);
        configs.put(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION, maxInFlightRequests);
        configs.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, compressionType);
        configs.put(ProducerConfig.BATCH_SIZE_CONFIG, batchSize);

        return configs;
    }
}


