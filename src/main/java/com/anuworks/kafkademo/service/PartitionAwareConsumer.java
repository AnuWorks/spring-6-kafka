package com.anuworks.kafkademo.service;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Arrays;
import java.util.Map;

/**
 * Created by Anudeep Madrampalli (Anuworks)
 **/

@EnableKafka
@Component
public class PartitionAwareConsumer {


}
