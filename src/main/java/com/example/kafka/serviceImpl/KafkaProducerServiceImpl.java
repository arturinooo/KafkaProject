package com.example.kafka.serviceImpl;

import com.example.kafka.service.KafkaProducerService;
import jakarta.annotation.PreDestroy;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaProducerServiceImpl implements KafkaProducerService {

  private final KafkaTemplate<String, String> kafkaTemplate;

  private final int NUM_THREADS = 10;
  private final ExecutorService executorService = Executors.newFixedThreadPool(NUM_THREADS);

  @Override
  public void sendMessage(final String topic, final List<String> keys, final List<String> messages) {
    for (int i = 0; i < messages.size(); i++) {
      String key = keys.get(i);
      String message = messages.get(i);

      executorService.submit(() -> {
        kafkaTemplate.send(topic, key, message);
        log.info("Sent message: " + message + " with key: " + key);
      });
    }
  }

  @PreDestroy
  private void shutdownExecutorService() {
    log.info("Shutting down ExecutorService");
    executorService.shutdown();
    try {
      if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
        executorService.shutdownNow();
      }
    } catch (InterruptedException e) {
      executorService.shutdownNow();
    }
  }
}
