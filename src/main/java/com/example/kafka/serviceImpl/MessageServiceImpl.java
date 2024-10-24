package com.example.kafka.serviceImpl;

import com.example.kafka.model.Message;
import com.example.kafka.repository.MessageRepository;
import com.example.kafka.service.MessageService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

  private final MessageRepository messageRepository;

  @Override
  public List<Message> getAllMessages() {
    return messageRepository.findAll();
  }
}
