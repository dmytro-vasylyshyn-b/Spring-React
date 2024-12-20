package com.website.Shop.Repository;

import com.website.Shop.Model.Discounts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscountsRepository extends JpaRepository<Discounts, Long> {
}
