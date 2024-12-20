package com.website.Shop.Repository;

import com.website.Shop.Model.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductsRepository extends JpaRepository<Products, Long> {
    @Query("SELECT p FROM Products p WHERE LOWER(p.productName) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Products> findByNameContaining(@Param("name") String name);

    List<Products> findByCategory_CategoryId(Long categoryId);

    @Query("SELECT p FROM Products p WHERE p.productPrice BETWEEN :minPrice AND :maxPrice")
    List<Products> findByPriceRange(@Param("minPrice") double minPrice, @Param("maxPrice") double maxPrice);
}
