package com.devpulse.devpulse.service;

import com.devpulse.devpulse.entity.DataEntity;
import com.devpulse.devpulse.repository.PulseRepository;
import java.time.LocalDateTime;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class PulseService {

    private final PulseRepository pulseRepository;

    public PulseService(PulseRepository pulseRepository) {
        this.pulseRepository = pulseRepository;
    }

    @Scheduled(fixedRate = 10000)
    public void collectPulseData() {
        DataEntity data = new DataEntity();
        data.setBlogName("자동 생성된 데이터");
        data.setCommitNum((int)(Math.random() * 10));
        data.setDate(LocalDateTime.now());

        pulseRepository.save(data);
    }
}
