package com.example.kafka.serviceImpl;

import com.example.kafka.model.Message;
import com.example.kafka.repository.MessageRepository;
import com.example.kafka.service.KafkaConsumerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumerServiceImpl implements KafkaConsumerService {

  private final MessageRepository messageRepository;

  @Override
  @Transactional
  @KafkaListener(topics = "my_topic", groupId = "kafka-group", concurrency = "3")
  public void listen(final ConsumerRecord<String, String> record) {
    Message msg = Message.builder()
        .key(record.key())
        .value(record.value())
        .build();

    messageRepository.save(msg);
    log.info("Message saved " + record.value());
  }
}
