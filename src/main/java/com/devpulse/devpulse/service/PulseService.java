// 역할 : '생산자'이자 '스케줄러'
// 하는 일 : 정해진 시간마다, RestTemplate으로 GitHub API를 수집해서, KafkaTemplate으로 Kafka '우체통'에 '발송'
// 핵심 : 이 서비스는 PulseRepository를 주입받지 않는다. 발송만 책임지는 '느슨한 결합'

package com.devpulse.devpulse.service;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

// @Service : 비즈니스 로직을 담당하는 두뇌이다.
@Slf4j
@Service // -> Bean 등록
public class PulseService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    private final RestTemplate restTemplate;
    private final String githubApiUri;

    // 생성자 의존성 주입
    public PulseService(RestTemplate restTemplate,
        @Value("${github.api.uri}") String githubApiUri, // Application.properties에서 그 값을 주입
        KafkaTemplate<String, String> kafkaTemplate) {

        this.restTemplate = restTemplate;
        this.githubApiUri = githubApiUri;
        this.kafkaTemplate = kafkaTemplate;
    }

//    @Scheduled(fixedRate = 3600000) -> 자동 실행(1시간마다)
    @PostConstruct // 단 한먼 메서드 실행
    public void collectPulseData() {
        // 주입받은 'githubApiUri' 필드(URL)를 사용하고,
        // 주입받은 'restTemplate' 도구를 사용해서
        // GitHub API를 호출하고, 응답 원본을 'String(문자열)'으로 받습니다.
        String jsonResponse = restTemplate.getForObject(githubApiUri, String.class);

        // 디버깅용 로그
        log.info("======= GitHub API 응답 원본 =======");
        log.info("Response: {}", jsonResponse);

        // kafkaTemplate 도구를 이용해서 github-events토픽으로 API응답 원본 전송
        kafkaTemplate.send("github-events", jsonResponse);
        log.info("Kafka Producer: 'github-events' 토픽으로 데이터 발송 성공!");
    }
}
