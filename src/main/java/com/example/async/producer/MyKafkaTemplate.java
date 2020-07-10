package com.example.async.producer;

import java.util.Map.Entry;
import java.util.Set;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

@Component
public class MyKafkaTemplate<K, V> extends KafkaTemplate<K, V> {


    /**
     * This template will publish/producer the messages asynchronously
     */
    @Autowired
    public MyKafkaTemplate(ProducerFactory kafkaProducerFactory) {
        super(kafkaProducerFactory, false);

        DefaultKafkaProducerFactory defaultKafkaProducerFactory = (DefaultKafkaProducerFactory) kafkaProducerFactory;
        Set<Entry> set  = defaultKafkaProducerFactory.getConfigurationProperties().entrySet();
        set.forEach( item ->
                System.out.println("producer config key : "+item.getKey()+"  and value is : "+item.getValue())
        );

        System.out.println("LINGER_MS_CONFIG "+defaultKafkaProducerFactory.getConfigurationProperties().get(ProducerConfig.LINGER_MS_CONFIG));
        System.out.println("BATCH_SIZE_CONFIG "+defaultKafkaProducerFactory.getConfigurationProperties().get(ProducerConfig.BATCH_SIZE_CONFIG));
    }

    @Override
    protected ListenableFuture<SendResult<K, V>> doSend(final ProducerRecord<K, V> producerRecord) {
        return super.doSend(producerRecord);
    }
}
