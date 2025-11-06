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

@Table(name = "pulse_data")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DataEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "blog_name")
    private String blogName;

    @Column(name = "commit_num")
    private Integer commitNum;

    @Column(name = "pulse_date")
    private LocalDateTime date;

}
