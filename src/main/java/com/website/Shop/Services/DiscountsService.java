package com.website.Shop.Services;

import com.website.Shop.Model.Discounts;
import com.website.Shop.dto.DiscountDTO;

import java.util.List;
import java.util.Optional;

public interface DiscountsService {
    Optional<DiscountDTO> findById(Long discountId);
    List<DiscountDTO> findAllDiscounts();
    DiscountDTO createDiscount(Discounts discount);
    void deleteDiscount(Long discountId);
    List<DiscountDTO> findDiscountsWithFilter(int number);

}
