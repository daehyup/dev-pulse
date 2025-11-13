package com.devpulse.devpulse.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "pulse_data") // 테이블 이름 지정
@Entity // dto가 아닌 DB테이과 1:1로  매핑되는 엔티티
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DataEntity {

    @Id // 기본키 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // id를 자동 증가
    private Long id;

    @Column(name = "blog_name")
    private String blogName; // blogName -> blog_name 매핑

    @Column(name = "commit_num")
    private Integer commitNum; // commitNum -> commit_num 매핑

    @Column(name = "pulse_date")
    private LocalDateTime date; // date -> pulse_date 매핑
}
