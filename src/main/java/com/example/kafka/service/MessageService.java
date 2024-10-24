package com.example.kafka.service;

import com.example.kafka.model.Message;
import java.util.List;

public interface MessageService {

  List<Message> getAllMessages();
}
