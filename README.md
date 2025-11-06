```markdown
# 🚀 Dev-Pulse (개발자의 맥박)

[![Build Status](https://img.shields.io/badge/Build-Passing-brightgreen?style=for-the-badge)](https://github.com/daehyup/dev-pulse)
[![Tech Stack](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=Spring&logoColor=white)](https://spring.io/projects/spring-boot)
[![Tech Stack](https://img.shields.io/badge/Kafka-000000?style=for-the-badge&logo=ApacheKafka&logoColor=white)](https://kafka.apache.org/)
[![License](https://img.shields.io/badge/License-MIT-blue?style=for-the-badge)](https://opensource.org/licenses/MIT)

> 흩어진 나의 개발 활동(커밋, 블로그, 프로젝트)을 '맥박(Pulse)'처럼 수집하고 분석하여, 성장을 '정량화된 리포트'로 시각화하는 개인용 데이터 허브입니다.


*(프로젝트가 완성되면 이곳에 스크린샷을 추가하세요)*

---

## 💡 프로젝트 동기 (Motivation)

개발자로서 매일 커밋을 하고, 블로그를 작성하며 여러 프로젝트에 기여합니다. 하지만 이러한 활동들은 GitHub, Tistory, Velog 등 여러 플랫폼에 흩어져 있어 저의 성장 과정을 한눈에 파악하기 어려웠습니다.

`Dev-Pulse`는 이 모든 데이터를 한곳에 모아 **'개발자로서의 나의 맥박'**이 얼마나 꾸준히, 그리고 활발하게 뛰고 있는지 측정하고 시각화하기 위해 시작되었습니다. 이 리포트를 통해 스스로를 객관적으로 분석하고, 성장을 위한 동기를 부여받고자 합니다.

## 📊 주요 기능 (Features)

* **개발 활동 수집**: GitHub 커밋, 블로그(Velog, Tistory 등) 포스트를 주기적으로 수집합니다. (Scheduler)
* **데이터 파이프라인**: 수집된 대량의 데이터를 비동기 메시지 큐(Kafka)를 통해 안정적으로 처리합니다.
* **정량화 리포트**: 수집된 데이터를 분석하여 커밋 빈도, 블로그 포스팅 주기 등을 시각화된 대시보드로 제공합니다. (Thymeleaf)

## 🏛️ 타겟 아키텍처 (Architecture)

이 프로젝트는 확장성과 안정성을 고려하여 다음과 같은 데이터 파이프라인 아키텍처를 목표로 합니다.

```

```
              [GitHub API]    [Blog RSS]
                   |                |
                   v                v
              [Scheduler (Collector)]
                   |
 (Produce)         v
```

[ Apache Kafka (Topic: github-commits, blog-posts) ]
|
(Consume)         v
[Spring Boot Consumer]
|
v
[JPA / RDBMS (MySQL)]
|
v
[Spring Boot API Server]
|
v
[View (Thymeleaf)]

````

## 🛠️ 기술 스택 (Tech Stack)

**Backend:**
* `Spring Boot`
* `Spring Data JPA` (RDBMS 연동)
* `Spring Web` (API 및 리포트 제공)
* `Spring Scheduler` (데이터 수집 스케줄링)
* `Spring Kafka` (비동기 데이터 처리)

**Database:**
* `H2` (개발 및 테스트용)
* `MySQL` (운영 환경 예정)

**View:**
* `Thymeleaf`

**DevOps & Tools:**
* `Git`, `GitHub`
* `Gradle` (또는 `Maven`)
* `IntelliJ IDEA`

## 🏁 시작하기 (Getting Started)

### 1. Prerequisites
* Java 17 (혹은 11)
* Git

### 2. 프로젝트 클론
```bash
git clone [https://github.com/](https://github.com/)[본인_GitHub_ID]/dev-pulse.git
cd dev-pulse
````

### 3\. 빌드 및 실행 (Maven/Gradle)

```bash
# Gradle
./gradlew build
java -jar build/libs/dev-pulse-0.0.1-SNAPSHOT.jar

# Maven
./mvnw clean package
java -jar target/dev-pulse-0.0.1-SNAPSHOT.jar
```

### 4\. 리포트 확인

브라우저에서 `http://localhost:8080/report` 로 접속합니다.

## 🗺️ 프로젝트 로드맵 (Roadmap)

  - [x] **Phase 1: 리포트 뷰 설계**
      - [x] `Spring Web` + `Thymeleaf` MVC 구조 설정
      - [x] Mock 데이터를 이용한 가짜 리포트 화면 구현
  - [ ] **Phase 2: 데이터 영속성 확보**
      - [ ] `Spring Data JPA` 및 `H2` 연동
      - [ ] 엔티티(Entity) 및 리포지토리(Repository) 설계
      - [ ] 컨트롤러가 DB의 실제 데이터를 조회하도록 변경
  - [ ] **Phase 3: 데이터 수집 자동화**
      - [ ] `Scheduler`를 이용한 주기적인 데이터 수집 로직 구현
      - [ ] (GitHub API, 블로그 RSS 파싱 로직 구현)
  - [ ] **Phase 4: 아키텍처 고도화 (Kafka)**
      - [ ] 수집된 데이터를 Kafka로 전송(Produce)
      - [ ] Kafka로부터 데이터를 받아 DB에 저장(Consume)하는 Consumer 분리

## License

This project is licensed under the MIT License.

```
```
