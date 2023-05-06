package com.offer.service.impl;

import com.offer.advice.OfferNotFound;
import com.offer.dto.OfferDto;
import com.offer.dto.StatusCountDto;
import com.offer.dto.StatusDto;
import com.offer.dto.StatusLogDto;
import com.offer.entity.Offer;
import com.offer.entity.OfferCategory;
import com.offer.entity.StatusLog;
import com.offer.repository.OfferRepository;
import com.offer.repository.StatusLogRepository;
import com.offer.service.OfferService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;

    private final StatusLogRepository statusLogRepository;
    private final ModelMapper modelMapper;

    public OfferServiceImpl(OfferRepository offerRepository,ModelMapper modelMapper,StatusLogRepository statusLogRepository)
    {
        this.offerRepository = offerRepository;
        this.modelMapper=modelMapper;
        this.statusLogRepository=statusLogRepository;
    }
    @Override
    public OfferDto createOffer(OfferDto offerDto) {
        boolean check=offerRepository.existsByAdvertTitleAndCategoryAndDescription(offerDto.getAdvertTitle(),offerDto.getCategory(),offerDto.getDescription());
        if(check)
        {
            throw new IllegalArgumentException("There is an ad title and description belonging to the same category.");
        }
        else {
            if(offerDto.getCategory() != OfferCategory.HS)
            {
                offerDto.setStatus(null);
            }
            else{
                offerDto.setStatus(true);
            }
            Offer offer=modelMapper.map(offerDto,Offer.class);
            offer.setCreatedDate(new Date());
            return modelMapper.map(offerRepository.save(offer),OfferDto.class);
        }
    }

    @Override
    public List<OfferDto> getOffers() {
        List<Offer> offers=offerRepository.findAll();
        List<OfferDto> dtos=offers.stream().map(offer -> modelMapper.map(offer,OfferDto.class)).collect(Collectors.toList());
        return dtos;
    }

    @Override
    public List<StatusCountDto> countByStatus() {
        return offerRepository.countByStatus();
    }

    @Override
    public OfferDto updateStatus(Long id, StatusDto statusDto) {
        Optional<Offer> offer= offerRepository.findById(id);

        if(offer.isPresent()){

            StatusLog statusLog=new StatusLog();
            statusLog.setOldStatus(offer.get().getStatus());
            statusLog.setNewStatus(statusDto.getNewStatus());
            statusLog.setOffer(offer.get());
            statusLog.setCreatedDate(new Date());
            statusLogRepository.save(statusLog);
            offer.get().setStatus(statusDto.getNewStatus());
            return modelMapper.map(offerRepository.save(offer.get()),OfferDto.class);
        }
        throw new OfferNotFound("Offer Not Found!");
    }

    @Override
    public List<StatusLogDto> getStatusLogs(Long id) {
        Optional<Offer> offer= offerRepository.findById(id);

        if(offer.isPresent()){
            List<StatusLog> statusLog= statusLogRepository.findByOffer_Id(offer.get().getId());
            List<StatusLogDto> dtos=statusLog.stream().map(sLog -> modelMapper.map(sLog,StatusLogDto.class)).collect(Collectors.toList());
            return dtos;
        }
        throw new OfferNotFound("Offer Not Found!");


    }

}
