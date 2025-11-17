package com.devpulse.devpulse.service;

import com.devpulse.devpulse.dto.GithubEventDto;
import com.devpulse.devpulse.entity.DataEntity;
import com.devpulse.devpulse.repository.PulseRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service // -빈 등록
public class KafkaConsumerService {

    // 이 service코드의 핵심은 저장이기 때문에 PulseRepository 선언
    private final PulseRepository pulseRepository;

    // 의존성 주입
    public KafkaConsumerService(PulseRepository pulseRepository) {
        this.pulseRepository = pulseRepository;
    }

    // 'github-event'이라는 토픽만 dev-pulse-group이라는 그룹만 감시
    @KafkaListener(topics = "github-events", groupId = "dev-pulse-group")
    public void consumeGithubEvents(String message) { // Kafka가 메세지를 감지하면, 이 메서드가 실행

        ObjectMapper mapper = new ObjectMapper(); // JSON 분석 도구
        GithubEventDto[] events;
        try {
            // message를 GithubEventDto를 참고하여 자동 변환
            // 3개의 DTO 설계도 참고하여 자동 변환
            events = mapper.readValue(message, GithubEventDto[].class);
        } catch (JsonProcessingException e) { // 예외 발생 시
            log.error("✅ Kafka Consumer: JSON 파싱 실패! " + e.getMessage());
            return; // 이 메서드만 종료
        }

        // push횟수 총합을 위한 변수
        int totalPushEvents = 0;
        for (GithubEventDto event : events) { // events를 하나씩 순회하면서
            if ("PushEvent".equals(event.getType())) { // event type이 PushEvent이면
                totalPushEvents++; // 1 증가
            }
        }

        if (totalPushEvents > 0) { // 집계된 push event가 0 초과면
            DataEntity realData = new DataEntity(); // DataEntity 객체 생성
            realData.setBlogName("Github_pushEvent");
            realData.setCommitNum(totalPushEvents);
            realData.setDate(LocalDateTime.now());

            // db에 저장
            pulseRepository.save(realData);
            log.info("✅ Kafka Consumer: Github 'Push 횟수' " + totalPushEvents + "건 발견 및 저장!");
        } else {
            log.info("✅ Kafka Consumer: 새 Push 이벤트(커밋) 없음.");
        }
    }
}