package com.example.async.producer;

import static com.example.async.producer.Constants.ASYNC_PRODUCER_TEST_TOPIC_NAME;

import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.kafka.support.SendResult;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.concurrent.ListenableFuture;

@RunWith(SpringRunner.class)
@SpringBootTest
@ComponentScan(basePackages = "com.example.async.producer")
public class KafkaAsyncProducerTest {

    @Autowired
    protected MyKafkaTemplate myKafkaTemplate;

    @Test
    public void send_message_using_async_producer() throws InterruptedException {
        String key = UUID.randomUUID().toString();

        for (int i=0; i<1; i++){
            System.out.println("Sending a message with a key "+key);
            ListenableFuture<SendResult<String, String>> future = myKafkaTemplate.send(ASYNC_PRODUCER_TEST_TOPIC_NAME, key, "Message"+i);
            //SendResult<String, String> sendResult = future.get(1, TimeUnit.MINUTES);
            //Assert.assertNotNull(sendResult.getRecordMetadata().partition());
        }

        Thread.sleep(1000000000);
    }

}
