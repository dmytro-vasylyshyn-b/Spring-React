package com.website.Shop.Services.Implementation;

import com.website.Shop.Model.Discounts;
import com.website.Shop.Repository.DiscountsRepository;
import com.website.Shop.Services.DiscountsService;
import com.website.Shop.dto.DiscountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiscountsServiceImpl implements DiscountsService {

    @Autowired
    private DiscountsRepository discountsRepository;

    @Override
    public Optional<DiscountDTO> findById(Long discountId) {
        return discountsRepository.findById(discountId)
                .map(this::convertToDTO);
    }

    @Override
    public List<DiscountDTO> findAllDiscounts() {
        return discountsRepository.findAll().stream().map(this::convertToDTO).toList();
    }


    @Override
    public DiscountDTO createDiscount(Discounts discount) {
        discountsRepository.save(discount);
        return convertToDTO(discount);
    }

    @Override
    public void deleteDiscount(Long discountId) {
        if(discountId != null){
            discountsRepository.deleteById(discountId);
        }
    }

    @Override
    public List<DiscountDTO> findDiscountsWithFilter(int number) {
        List<Discounts> discounts= discountsRepository.findAll();

        discounts.stream().
                filter(discount -> discount.getDiscountPercentage() > 60).limit(number)
                .toList();
        return discounts.stream().map(this::convertToDTO).toList();
    }

    public  Discounts convertToEntity(DiscountDTO discountDTO){
        Discounts discounts=new Discounts(
                discountDTO.getDiscountId(),
                discountDTO.getDiscountPercentage(),
                discountDTO.getDiscountStartDate(),
                discountDTO.getDiscountEndDate()
        );
        return discounts;
    }

    public DiscountDTO convertToDTO(Discounts discount){

        return new DiscountDTO(
                discount.getDiscountId(),
                discount.getDiscountStartDate(),
                discount.getDiscountEndDate(),
                discount.getDiscountPercentage()
        );
    }
}
