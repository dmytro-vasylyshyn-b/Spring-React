package com.website.Shop.Services.Implementation;

import com.website.Shop.Model.Categories;
import com.website.Shop.Repository.CategoriesRepository;
import com.website.Shop.Services.CategoriesService;
import com.website.Shop.dto.CategoriesDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoriesServiceImpl implements CategoriesService {

    @Autowired
    private CategoriesRepository categoriesRepository;

    // Отримання всіх категорій
    @Override
    public List<CategoriesDTO> findAllCategories() {
        List<Categories> categories = categoriesRepository.findAll();
        return categories.stream()
                .map(this::toDTO) // Перетворюємо кожну категорію в CategoriesDTO
                .collect(Collectors.toList());
    }

    // Створення нової категорії
    @Override
    public CategoriesDTO createCategory(CategoriesDTO categoryDTO) {
        Categories category = toEntity(categoryDTO); // Перетворюємо DTO в сутність
        Categories savedCategory = categoriesRepository.save(category);
        return toDTO(savedCategory); // Повертаємо збережену категорію у вигляді DTO
    }

    // Оновлення категорії
    @Override
    public CategoriesDTO updateCategory(Long categoryId, String categoryName) {
        Categories updatedCategory = categoriesRepository.findById(categoryId)
                .orElseThrow(() -> new NoSuchElementException("Category not found with ID: " + categoryId));

        updatedCategory.setCategoryName(categoryName);
        Categories savedCategory = categoriesRepository.save(updatedCategory);
        return toDTO(savedCategory); // Повертаємо оновлену категорію як DTO
    }

    // Видалення категорії
    @Override
    public void deleteCategory(Long categoryId) {
        categoriesRepository.deleteById(categoryId);
    }

    // Пошук категорії за ID
    @Override
    public Optional<CategoriesDTO> findCategoryById(Long categoryId) {
        Optional<Categories> category = categoriesRepository.findById(categoryId);
        return category.map(this::toDTO);
    }


    // Перетворення з Categories на CategoriesDTO
    public CategoriesDTO toDTO(Categories category) {
        return new CategoriesDTO(category.getCategoryId(), category.getCategoryName());
    }

    // Перетворення з CategoriesDTO на Categories
    public Categories toEntity(CategoriesDTO categoryDTO) {
        Categories category = new Categories();
        category.setCategoryId(categoryDTO.getCategoryId());
        category.setCategoryName(categoryDTO.getCategoryName());
        return category;
    }
}
