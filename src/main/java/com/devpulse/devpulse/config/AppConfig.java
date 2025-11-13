package com.devpulse.devpulse.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

// 애플리케이션 전체의 설정을 정의하는 '설정 파일' -> 스프링이 우선 탐색
@Configuration
public class AppConfig {

    // @Bean: 이 메서드가 반환(return)하는 객체를 스프링의 '공용 도구함'에 등록
    // RestTemplate :
    // - 외부 API를 호출할 사용하는 'HTTP' 통신 도구
    // - 여기서 new로 딱 한번 생성해서 '싱글톤'으로 관리
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

// 싱글톤 : 이 객체는 우리 시스템 전체에서 '단 하나만' 만들어서 , 모두가 공유하게 하겠다는 설계 방식