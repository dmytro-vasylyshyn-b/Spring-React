package com.website.Shop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DiscountDTO {
    private Long discountId;
    private Date discountStartDate;
    private Date discountEndDate;
    private Double discountPercentage;
}
