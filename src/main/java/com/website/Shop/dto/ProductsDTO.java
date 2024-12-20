package com.website.Shop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductsDTO {
    private Long productId;
    private String productName;
    private String productDescription;
    private Double productPrice;
    private Integer productStock;
    private String createdAt;
    private String photoUrl;

    private CategoriesDTO productCategory;

    private DiscountDTO productDiscount;
}
