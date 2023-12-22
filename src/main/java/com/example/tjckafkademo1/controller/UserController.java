package com.example.tjckafkademo1.controller;


import com.example.tjckafkademo1.model.User;
import lombok.AllArgsConstructor;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

@AllArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private static final String topicName = "tjc.kafka-demo-user-created.0";

    @PostMapping
    public ResponseEntity<User> create() throws ExecutionException, InterruptedException {

        var user = User.builder()
                .id(UUID.randomUUID().toString())
                .userName("vgunduzalp")
                .age(30)
                .build();


        Message<User> userMsg =
                MessageBuilder.withPayload(user)
                        .setHeader(KafkaHeaders.TOPIC, topicName)
                        // .setHeader(KafkaHeaders.KEY, user.getUserName())
                        // .setHeader(KafkaHeaders.PARTITION, 1)
                        .setHeader("X-AgentName", "kafka-demo-app")
                        .build();


        kafkaTemplate.send(userMsg).get();
        // kafkaTemplate.send(topicName, userMsg).get();

        return new ResponseEntity<>(user, HttpStatus.CREATED);

    }

}
