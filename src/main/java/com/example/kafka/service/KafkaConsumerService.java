package com.example.kafka.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public interface KafkaConsumerService {
  void listen(ConsumerRecord<String, String> record);
}
