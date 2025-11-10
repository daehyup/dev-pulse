package com.devpulse.devpulse.service;

import com.devpulse.devpulse.entity.DataEntity;
import com.devpulse.devpulse.repository.PulseRepository;
import java.time.LocalDateTime;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    private final PulseRepository pulseRepository;

    public KafkaConsumerService(PulseRepository pulseRepository) {
        this.pulseRepository = pulseRepository;
    }

    @KafkaListener(topics = "github-events", groupId = "dev-pulse-group")
    public void consumeGithubEvents(String message) {

        String targetWorld = "\"type\":\"PushEvent\"";
        int pushEventCount = message.split(targetWorld).length - 1;

        if (pushEventCount > 0) {
            DataEntity realData = new DataEntity();
            realData.setBlogName("Github pushEvent");
            realData.setCommitNum(pushEventCount);
            realData.setDate(LocalDateTime.now());

            pulseRepository.save(realData);
            System.out.println("✅ Kafka Consumer: Github push event " + pushEventCount + "건 발견 및 저장!");
        } else {
            System.out.println("✅ Kafka Consumer: 새 Push 이벤트 없음.");
        }
    }
}
