package com.offer.service.impl;

import com.offer.dto.OfferDto;
import com.offer.entity.Offer;
import com.offer.entity.OfferCategory;
import com.offer.repository.OfferRepository;
import com.offer.repository.StatusLogRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;

import java.util.Date;

public class OfferServiceImplTest {
    private OfferServiceImpl offerServiceImpl;
    private OfferRepository offerRepository;
    private StatusLogRepository statusLogRepository;
    private ModelMapper modelMapper;




    @Before
    public void setUp() throws Exception {
        offerRepository= Mockito.mock(OfferRepository.class);
        statusLogRepository=Mockito.mock(StatusLogRepository.class);
        modelMapper=Mockito.mock(ModelMapper.class);

        offerServiceImpl = new OfferServiceImpl(offerRepository,modelMapper,statusLogRepository);

    }

    @Test
    public void whenCreateOfferCalledWithValidRequest_itShouldReturnValidOfferDto(){
        OfferDto offerDto=new OfferDto(1L,"DenemeDenemeDeneme","DenemeDenemeDenemeDeneme", OfferCategory.HS,true,new Date());
        boolean check=false;
        Mockito.when(offerRepository.existsByAdvertTitleAndCategoryAndDescription(offerDto.getAdvertTitle(),offerDto.getCategory(),offerDto.getDescription())).thenReturn(check);
        Offer offer=new Offer();
        offer.setId(1L);
        offer.setAdvertTitle("DenemeDenemeDeneme");
        offer.setDescription("DenemeDenemeDenemeDeneme");
        offer.setCategory(OfferCategory.HS);
        offer.setStatus(true);
        offer.setCreatedDate(new Date());
        Mockito.when(modelMapper.map(offerDto,Offer.class)).thenReturn(offer);
        Mockito.when(modelMapper.map(offerRepository.save(offer),OfferDto.class)).thenReturn(offerDto);

        OfferDto result= offerServiceImpl.createOffer(offerDto);
        Assert.assertEquals(result,offerDto);
        Mockito.verify(offerRepository).existsByAdvertTitleAndCategoryAndDescription(offerDto.getAdvertTitle(),offerDto.getCategory(),offerDto.getDescription());
        Mockito.verify(modelMapper).map(offerDto,Offer.class);
        Mockito.verify(modelMapper).map(offerRepository.save(offer),OfferDto.class);

    }


}