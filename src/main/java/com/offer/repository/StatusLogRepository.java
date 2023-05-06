package com.offer.repository;

import com.offer.dto.StatusLogDto;
import com.offer.entity.Offer;
import com.offer.entity.StatusLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StatusLogRepository extends JpaRepository<StatusLog,Long> {
    List<StatusLog> findByOffer_Id(Long id);
}
