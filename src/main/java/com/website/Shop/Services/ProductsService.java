package com.website.Shop.Services;

import com.website.Shop.Model.Products;
import com.website.Shop.dto.ProductsDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ProductsService {
    List<ProductsDTO> findAllProducts();
    ProductsDTO createProduct(ProductsDTO product);
    ProductsDTO updateProduct(ProductsDTO product, Long productId);
    void deleteProduct(Long productId);
    Optional<ProductsDTO> findProductById(Long productId);
    List<ProductsDTO> findProductsWithFilter(String name, Long categoryId, Double minPrice, Double maxPrice);
    Products convertToEntity(ProductsDTO productDTO);

    ProductsDTO createProductWithImage(ProductsDTO productDTO, MultipartFile file);
    ProductsDTO convertToDTO(Products createdProduct);
}
