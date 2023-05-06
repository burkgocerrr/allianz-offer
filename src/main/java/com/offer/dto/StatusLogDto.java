package com.offer.dto;

import com.offer.entity.Offer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Status Log Data Transfer Object")
public class StatusLogDto {

    @ApiModelProperty(value = "ID")
    private Long id;
    @ApiModelProperty(value = "Old Status")
    private Boolean oldStatus;

    @ApiModelProperty(value = "New Status")
    private Boolean newStatus;


    @ApiModelProperty(value = "Created Date")
    private Date createdDate;
}
