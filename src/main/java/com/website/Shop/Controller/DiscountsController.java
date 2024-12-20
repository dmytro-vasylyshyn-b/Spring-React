package com.website.Shop.Controller;


import com.website.Shop.Model.Discounts;
import com.website.Shop.Services.DiscountsService;
import com.website.Shop.dto.DiscountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/discounts")
public class DiscountsController {

    private final DiscountsService discountsService;

    @Autowired
    public DiscountsController(DiscountsService discountsService) {
        this.discountsService = discountsService;
    }


    @GetMapping
    private List<DiscountDTO> getDiscountsWithFilter(@RequestParam(required = false) Long discountId, @RequestParam(required = false) Integer number) {
        if (discountId != null) {
            return discountsService.findById(discountId)
                    .map(Collections::singletonList)
                    .orElse(Collections.emptyList());
        } else if (number != null){
            return discountsService.findDiscountsWithFilter(number);
        }else return discountsService.findAllDiscounts();
    }


    @PostMapping
    private DiscountDTO createDiscount(@RequestBody Discounts discount){
        return discountsService.createDiscount(discount);
    }

}
