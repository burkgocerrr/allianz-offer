package com.offer.api;

import com.offer.dto.OfferDto;
import com.offer.dto.StatusCountDto;
import com.offer.dto.StatusDto;
import com.offer.dto.StatusLogDto;
import com.offer.service.OfferService;
import com.offer.utility.ApiPaths;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(ApiPaths.OfferCtrl.BASE_PATH)
@Api(value = "Project APIs")
public class OfferController {

    private final OfferService offerService;

    public OfferController(OfferService offerService)
    {
        this.offerService=offerService;
    }

    @PostMapping(ApiPaths.OfferCtrl.CREATE)
    @ApiOperation(value = "Get All Offer",response =OfferDto.class)
    public ResponseEntity<OfferDto> createOffer(@Valid @RequestBody OfferDto offerDto){
        OfferDto resultOffer=offerService.createOffer(offerDto);
        return ResponseEntity.ok(resultOffer);
    }

    @GetMapping(ApiPaths.OfferCtrl.GET_ALL)
    @ApiOperation(value = "Get All Offer",response =List.class)
    public ResponseEntity<List<OfferDto>> getOffers(){
        List<OfferDto> offerDtoList=offerService.getOffers();
        return ResponseEntity.ok(offerDtoList);
    }
    @GetMapping(ApiPaths.OfferCtrl.STATUS_COUNT)
    @ApiOperation(value = "Count By Status",response =List.class)
    public ResponseEntity<List<StatusCountDto>> countByStatus(){
        List<StatusCountDto> result=offerService.countByStatus();
        return ResponseEntity.ok(result);
    }
    @PostMapping(ApiPaths.OfferCtrl.UPDATE_STATUS+"/{id}")
    public ResponseEntity<OfferDto> updateStatus(@PathVariable("id") Long id,@RequestBody StatusDto statusDto){
        OfferDto result=offerService.updateStatus(id,statusDto);
        return ResponseEntity.ok(result);
    }

    @GetMapping(ApiPaths.OfferCtrl.GET_STATUS_LOGS+"/{id}")
    public ResponseEntity<List<StatusLogDto>> getStatusLogs(@PathVariable("id") Long id){
        List<StatusLogDto> result=offerService.getStatusLogs(id);
        return ResponseEntity.ok(result);
    }

}
