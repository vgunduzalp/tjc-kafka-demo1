package com.example.tjckafkademo1.service;

import com.example.tjckafkademo1.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserListener {

    @KafkaListener(topics = "tjc.kafka-demo-user-created.0", containerFactory = "kafkaListenerContainerFactory")
    public void consume(
            @Payload User user,
            @Header("X-AgentName") String agentName,
            @Header(KafkaHeaders.OFFSET) int offset,
            @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
            Acknowledgment ack
    ) {
        log.info("userId: {}, agentName: {}, partition: {}, offset: {}", user.getId(), agentName, partition, offset);
        ack.acknowledge();
    }
}
