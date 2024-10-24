package com.example.kafka.controller;

import com.example.kafka.model.Message;
import com.example.kafka.model.MessageRequest;
import com.example.kafka.service.KafkaProducerService;
import com.example.kafka.service.MessageService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/messages")
@RequiredArgsConstructor
public class MessageController {

  private final KafkaProducerService kafkaProducerService;
  private final MessageService messageService;

  @PostMapping("/send")
  public ResponseEntity<Void> sendMessage(@Validated @RequestBody MessageRequest request) {
    kafkaProducerService.sendMessage("my_topic", request.getKeys(), request.getMessages());
    return ResponseEntity.ok().build();
  }

  @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<Message>> getAllMessages() {
    return ResponseEntity.ok(messageService.getAllMessages());
  }
}
