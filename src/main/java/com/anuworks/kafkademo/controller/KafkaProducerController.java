package com.anuworks.kafkademo.controller;

import com.anuworks.kafkademo.service.KafkaProducerService;
import com.anuworks.kafkademo.service.PartitionAwareConsumer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Anudeep Madrampalli (Anuworks)
 **/
@RestController
@RequestMapping("/api/v1/kafka")
@RequiredArgsConstructor
public class KafkaProducerController {

    private final KafkaProducerService kafkaProducerService;
    private final PartitionAwareConsumer partitionAwareConsumer;

    @PostMapping("/send")
    public String sendMessage(@RequestParam String message) {
        kafkaProducerService.sendMessage(message);
        kafkaProducerService.sendMessage("orders", message);
        kafkaProducerService.sendMessage("orders.DLQ", message);
        return "Message sent to Kafka: " + message;
    }

}
