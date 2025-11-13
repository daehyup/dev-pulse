package com.devpulse.devpulse.repository;

import com.devpulse.devpulse.entity.DataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // 빈등록
// JPA가 DB 오류를 스프링이 이해할 수 있는 '스프링 예외'로 번역 해주는 기능
public interface PulseRepository extends JpaRepository<DataEntity, Long> {
    // 'DataEntity' 만 관리
    // 'DataEntity'의 @ID( 기본키 ) 타입은 Long

}
