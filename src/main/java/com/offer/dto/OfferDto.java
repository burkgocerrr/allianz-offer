package com.offer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.offer.entity.OfferCategory;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.EnumSet;
import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Offer Data Transfer Object")
public class OfferDto {

    @JsonProperty(required = false)
    @ApiModelProperty(value = "Offer ID",hidden = true)
    private Long id;

    @Pattern(regexp = "^[^\\\\[\\\\]{}()\\\\^$|*+?.]+",message = "You can only start with numbers or letters!")
    @Size(min = 10,max = 50,message = "Enter between 10 and 50 characters!")
    @NotNull
    @ApiModelProperty(required = true,value = "Advert Title")
    private String advertTitle;
    @Size(min = 20,max = 200,message = "Enter between 2 and 200 characters!")
    @NotNull
    @ApiModelProperty(required = true,value = "Description")
    private String description;

    @NotNull
    @ApiModelProperty(required = true,value = "Category")
    private OfferCategory category;

    @ApiModelProperty(hidden = true,value = "Status")
    private Boolean status;

    @ApiModelProperty(hidden = true,value = "Created Date")
    private Date createdDate;

    public OfferCategory category() {
        return category;
    }

    public void setCategory(OfferCategory category) {
        Set<OfferCategory> validEnumValues = EnumSet.allOf(OfferCategory.class);
        if (!validEnumValues.contains(category)) {
            throw new IllegalArgumentException("Invalid SampleEnum value: " + category);
        }
        this.category = category;
    }


}
