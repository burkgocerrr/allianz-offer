package com.offer.service;

import com.offer.dto.OfferDto;
import com.offer.dto.StatusCountDto;
import com.offer.dto.StatusDto;
import com.offer.dto.StatusLogDto;

import java.util.List;
public interface OfferService {
    OfferDto createOffer (OfferDto offerDto);
    List<OfferDto> getOffers();
    List<StatusCountDto> countByStatus();
    OfferDto updateStatus (Long id,StatusDto statusDto);
    List<StatusLogDto> getStatusLogs(Long id);
}
