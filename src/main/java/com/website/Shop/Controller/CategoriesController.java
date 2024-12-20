package com.website.Shop.Controller;

import com.website.Shop.Services.CategoriesService;
import com.website.Shop.dto.CategoriesDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin(origins = "http://localhost:3001")
public class CategoriesController {

    private final CategoriesService categoriesService;

    @Autowired
    public CategoriesController(CategoriesService categoriesService) {
        this.categoriesService = categoriesService;
    }

    // Отримання всіх категорій
    @GetMapping
    public ResponseEntity<List<CategoriesDTO>> getAllCategories() {
        List<CategoriesDTO> categoryDTOs = categoriesService.findAllCategories();
        return ResponseEntity.ok(categoryDTOs);
    }

    // Додавання нової категорії
    @PostMapping
    public ResponseEntity<CategoriesDTO> addCategory(@RequestBody CategoriesDTO categoryDTO) {
        CategoriesDTO savedCategoryDTO = categoriesService.createCategory(categoryDTO);
        return ResponseEntity.ok(savedCategoryDTO);
    }

    // Оновлення категорії
    @PutMapping("/{id}")
    public ResponseEntity<CategoriesDTO> updateCategory(@PathVariable Long id, @RequestBody CategoriesDTO categoryDTO) {
        CategoriesDTO updatedCategoryDTO = categoriesService.updateCategory(id, categoryDTO.getCategoryName());
        return ResponseEntity.ok(updatedCategoryDTO);
    }

    // Видалення категорії
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long categoryId) {
        categoriesService.deleteCategory(categoryId);
        return ResponseEntity.ok("Category " + categoryId + " deleted");
    }
}
