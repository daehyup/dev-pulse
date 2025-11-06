package com.devpulse.devpulse.repository;

import com.devpulse.devpulse.entity.DataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PulseRepository extends JpaRepository<DataEntity, Long> {

}
