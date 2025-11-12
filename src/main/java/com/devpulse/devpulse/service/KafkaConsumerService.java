package com.devpulse.devpulse.service;

import com.devpulse.devpulse.dto.GithubEventDto;
import com.devpulse.devpulse.entity.DataEntity;
import com.devpulse.devpulse.repository.PulseRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

        // 1. [수정] JSON 파싱(변환)을 '가장 먼저' 수행합니다.
        ObjectMapper mapper = new ObjectMapper();
        GithubEventDto[] events;
        try {
            events = mapper.readValue(message, GithubEventDto[].class);
        } catch (JsonProcessingException e) {
            // JSON 파싱 실패 시, 로그를 남기고 메서드를 종료합니다. (앱이 죽는 것을 방지)
            System.err.println("✅ Kafka Consumer: JSON 파싱 실패! " + e.getMessage());
            return; // 종료
        }

        // 2. '총 커밋 개수'를 계산합니다.
        int totalCommits = 0;
        for (GithubEventDto event : events) {
            // 3. 'PushEvent'만 필터링합니다.
            if ("PushEvent".equals(event.getType())) {

                // 4. (Null 방어) payload나 commits 리스트가 null이 아닐 때만 개수를 셉니다.
                if (event.getPayload() != null && event.getPayload().getCommits() != null) {

                    // 5. [수정] 'getPayloadDto()' -> 'getPayload()'
                    //    'Push 횟수'(split)가 아닌, '진짜 커밋 개수'(.size())를 누적합니다.
                    totalCommits += event.getPayload().getCommits().size();
                }
            }
        }

        // 6. [수정] 'totalCommits' (총 커밋 개수)를 기준으로 DB에 저장합니다.
        if (totalCommits > 0) {
            DataEntity realData = new DataEntity();
            realData.setBlogName("Github_pushEvent");
            realData.setCommitNum(totalCommits); // [수정] pushEventCount -> totalCommits
            realData.setDate(LocalDateTime.now());

            pulseRepository.save(realData);
            System.out.println("✅ Kafka Consumer: Github '진짜 커밋' " + totalCommits + "건 발견 및 저장!");
        } else {
            System.out.println("✅ Kafka Consumer: 새 Push 이벤트(커밋) 없음.");
        }
    }
}
