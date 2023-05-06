package com.offer.repository;

import com.offer.dto.StatusCountDto;
import com.offer.entity.Offer;
import com.offer.entity.OfferCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Offer,Long> {
    boolean existsByAdvertTitleAndCategoryAndDescription( String advertTitle, OfferCategory category,String description);

    @Query("SELECT new com.offer.dto.StatusCountDto(o.status, COUNT(*)) FROM Offer o GROUP BY o.status")
    List<StatusCountDto> countByStatus();
}
