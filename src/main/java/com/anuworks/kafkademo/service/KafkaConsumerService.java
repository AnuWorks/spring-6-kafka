package com.anuworks.kafkademo.service;

import com.anuworks.kafkademo.controller.KafkaProducerController;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.springframework.kafka.annotation.KafkaListener;

import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by Anudeep Madrampalli (Anuworks)
 **/
@Service
@RequiredArgsConstructor
public class KafkaConsumerService {

    private final KafkaProducerService kafkaProducerService;


    @KafkaListener(topics = "anuworks", groupId = "my-group")
    public void listen(String message) {
        System.out.println("Received message on anuworks: " + message);
    }

    @KafkaListener(topics = "orders", groupId = "order-group")
    public void consume(ConsumerRecord<String, String> record) {
        System.out.println("Received message on orders: " + record.value());
    }


    @KafkaListener(topics = "orders", groupId = "order-group")
    public void consumeWithRetry(ConsumerRecord<String, String> record) {
        System.out.println("Order processed consumeWithRetry: " + record.value());
        try {
            processMessage(record.value());
        } catch (Exception e) {
            kafkaProducerService.sendToDLQ(record.value());
        }
    }

    @KafkaListener(topics = "orders.DLQ", groupId = "order-group")
    public void consumeWithRetryDLQ(ConsumerRecord<String, String> record) {
        try {
            System.out.println("DLQ processed: " + record.value());
            processMessage(record.value());
        } catch (Exception e) {
            kafkaProducerService.sendToDLQ(record.value());
        }
    }

    private void processMessage(String message) {
        System.out.println("Processing order: " + message);
        if (message.contains("error")) {
            throw new RuntimeException("Simulated processing error");
        }
    }


}
