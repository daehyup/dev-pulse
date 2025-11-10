package com.devpulse.devpulse.service;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PulseService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    private final RestTemplate restTemplate;
    private final String githubApiUri;

    public PulseService(RestTemplate restTemplate,
        @Value("${github.api.uri}") String githubApiUri,
        KafkaTemplate<String, String> kafkaTemplate) {
        this.restTemplate = restTemplate;
        this.githubApiUri = githubApiUri;
        this.kafkaTemplate = kafkaTemplate;
    }

//    @Scheduled(fixedRate = 3600000)
    @PostConstruct
    public void collectPulseData() {
        String jsonResponse = restTemplate.getForObject(githubApiUri, String.class);

        kafkaTemplate.send("github-events", jsonResponse);
        System.out.println("Kafka Producer: 'github-events' 토픽으로 데이터 발송 성공!");
    }
}
