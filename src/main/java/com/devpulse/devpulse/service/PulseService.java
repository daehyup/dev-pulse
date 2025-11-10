package com.devpulse.devpulse.service;

import com.devpulse.devpulse.entity.DataEntity;
import com.devpulse.devpulse.repository.PulseRepository;
import jakarta.annotation.PostConstruct;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PulseService {

    private final PulseRepository pulseRepository;
    private final RestTemplate restTemplate;
    private final String githubApiUri;

    public PulseService(PulseRepository pulseRepository,
        RestTemplate restTemplate,
        @Value("${github.api.uri}") String githubApiUri) {
        this.pulseRepository = pulseRepository;
        this.restTemplate = restTemplate;
        this.githubApiUri = githubApiUri;
    }

//    @Scheduled(fixedRate = 3600000)
    @PostConstruct
    public void collectPulseData() {
        String jsonResponse = restTemplate.getForObject(githubApiUri, String.class);

        System.out.println("======= GitHub API 응답 원본 =======");
        System.out.println(jsonResponse);
        System.out.println("===================================");

        String targetWord = "\"type\":\"PushEvent\"";
        int pushEventCount = jsonResponse.split(targetWord).length - 1;

        if (pushEventCount > 0) {
            DataEntity realData = new DataEntity();
            realData.setBlogName("Github pushEvent");
            realData.setCommitNum(pushEventCount);
            realData.setDate(LocalDateTime.now());

            pulseRepository.save(realData);

            System.out.println("스케줄러 실행: Github push event " + pushEventCount + "건 발견 및 저장!");
        } else {
            System.out.println("스케줄러 실행: 새 Push 이벤트 없음.");
        }
    }
}
