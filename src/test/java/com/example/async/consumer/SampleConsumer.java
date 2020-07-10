package com.example.async.consumer;

import static com.example.async.producer.Constants.ASYNC_PRODUCER_TEST_TOPIC_NAME;

import com.example.async.producer.Constants;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

public class SampleConsumer {

    @KafkaListener(topics = Constants.ASYNC_PRODUCER_TEST_TOPIC_NAME, id = "123")
    public void consumeRecord(ConsumerRecord consumerRecord){
        System.out.println(String.format(" GOt consumerRecord with key %s ", consumerRecord.key()));
    }

}
