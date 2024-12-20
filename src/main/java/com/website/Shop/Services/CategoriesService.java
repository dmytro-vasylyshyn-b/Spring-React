package com.website.Shop.Services;

import com.website.Shop.dto.CategoriesDTO;

import java.util.List;
import java.util.Optional;

public interface CategoriesService {
    List<CategoriesDTO> findAllCategories();
    CategoriesDTO createCategory(CategoriesDTO categoryDTO);
    CategoriesDTO updateCategory(Long categoryId, String categoryName);
    void deleteCategory(Long categoryId);
    Optional<CategoriesDTO> findCategoryById(Long categoryId);
}
