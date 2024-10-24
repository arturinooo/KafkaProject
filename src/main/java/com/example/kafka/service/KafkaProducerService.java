package com.example.kafka.service;

import java.util.List;

public interface KafkaProducerService {
  void sendMessage(String topic, List<String> keys, List<String> messages);
}
