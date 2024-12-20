package com.website.Shop.Services.Implementation;

import com.website.Shop.Model.Categories;
import com.website.Shop.Model.Discounts;
import com.website.Shop.Model.Products;
import com.website.Shop.Repository.CategoriesRepository;
import com.website.Shop.Repository.DiscountsRepository;
import com.website.Shop.Repository.ProductsRepository;
import com.website.Shop.Services.ProductsService;
import com.website.Shop.dto.CategoriesDTO;
import com.website.Shop.dto.DiscountDTO;
import com.website.Shop.dto.ProductsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ProductsServiceImpl implements ProductsService {

    private final ProductsRepository productsRepository;
    private final DiscountsRepository discountsRepository;
    private final CategoriesRepository categoriesRepository;

    @Autowired
    public ProductsServiceImpl(ProductsRepository productsRepository,
                               DiscountsRepository discountsRepository,
                               CategoriesRepository categoriesRepository) {
        this.productsRepository = productsRepository;
        this.discountsRepository = discountsRepository;
        this.categoriesRepository = categoriesRepository;
    }

    @Override
    public List<ProductsDTO> findAllProducts() {
        List<Products> products = productsRepository.findAll();
        return products.stream().map(this::convertToDTO).toList();
    }

    private final Path imageStoragePath = Paths.get("C:\\Users\\Користувач\\Desktop\\університет\\веб\\Lab4\\Shop\\src\\main\\resources\\images");

    @Override
    public ProductsDTO createProductWithImage(ProductsDTO productDTO, MultipartFile file) {
        try {
            String filename = file.getOriginalFilename();
            Path targetPath = imageStoragePath.resolve(filename).normalize();
            Files.copy(file.getInputStream(), targetPath);

            productDTO.setPhotoUrl("/images/" + filename);
            return createProduct(productDTO);
        } catch (IOException e) {
            throw new RuntimeException("Не вдалося зберегти файл зображення", e);
        }
    }



    @Override
    public ProductsDTO createProduct(ProductsDTO productDTO) {
        Products product = convertToEntity(productDTO);
        product.setCreatedAt(LocalDate.now());
        Products savedProduct = productsRepository.save(product);
        return convertToDTO(savedProduct);
    }

    @Override
    public ProductsDTO updateProduct(ProductsDTO productDTO, Long productId) {
        Products existingProduct = productsRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (productDTO.getProductName() != null) existingProduct.setProductName(productDTO.getProductName());
        if (productDTO.getProductDescription() != null) existingProduct.setProductDescription(productDTO.getProductDescription());
        if (productDTO.getProductPrice() != null) existingProduct.setProductPrice(productDTO.getProductPrice());
        if (productDTO.getProductStock() != null) existingProduct.setProductStock(productDTO.getProductStock());
        if (productDTO.getPhotoUrl() != null) existingProduct.setPhotoUrl(productDTO.getPhotoUrl());


        if (productDTO.getProductCategory() != null) {
            Categories category = categoriesRepository.findById(productDTO.getProductCategory().getCategoryId())
                    .orElseThrow(() -> new NoSuchElementException("Category not found"));
            existingProduct.setCategory(category);
        }

        if (productDTO.getProductDiscount() != null) {
            Discounts discount = discountsRepository.findById(productDTO.getProductDiscount().getDiscountId())
                    .orElseThrow(() -> new NoSuchElementException("Discount not found"));
            existingProduct.setDiscount(discount);
        }

        Products savedProduct = productsRepository.save(existingProduct);
        return convertToDTO(savedProduct);
    }

    @Override
    public void deleteProduct(Long productId) {
        productsRepository.deleteById(productId);
    }

    @Override
    public Optional<ProductsDTO> findProductById(Long productId) {
        return productsRepository.findById(productId).map(this::convertToDTO);
    }

    @Override
    public List<ProductsDTO> findProductsWithFilter(String name, Long categoryId, Double minPrice, Double maxPrice) {
        List<Products> products=productsRepository.findAll();

        if(name!=null){
            products=products.stream()
                    .filter(product -> product.getProductName().toLowerCase().contains(name.toLowerCase()))
                    .toList();
        }

        if(categoryId!= null){
            products=products.stream()
                    .filter(product ->product.getCategory()!=null && product.getCategory().getCategoryId().equals(categoryId))
                    .toList();
        }

        if(minPrice != null){
            products=products.stream()
                    .filter(product -> product.getProductPrice() >= minPrice)
                    .toList();
        }

        if(maxPrice != null){
            products=products.stream()
                    .filter(product -> product.getProductPrice() <= maxPrice)
                    .toList();
        }

        return products.stream().map(this::convertToDTO).toList();
    }

    public ProductsDTO convertToDTO(Products product) {
        CategoriesDTO categoryDTO = null;
        if(product.getCategory()!=null){
            categoryDTO= new CategoriesDTO(
                    product.getCategory().getCategoryId(),
                    product.getCategory().getCategoryName()
            );
        }

        DiscountDTO discountDTO = null;
        if(product.getDiscount()!=null){
            discountDTO= new DiscountDTO(
                    product.getDiscount().getDiscountId(),
                    product.getDiscount().getDiscountStartDate(),
                    product.getDiscount().getDiscountEndDate(),
                    product.getDiscount().getDiscountPercentage()
            );
        }

        return new ProductsDTO(
                product.getProductId(),
                product.getProductName(),
                product.getProductDescription(),
                product.getProductPrice(),
                product.getProductStock(),
                product.getCreatedAt() != null ? product.getCreatedAt().toString() : null,
                product.getPhotoUrl() != null ? product.getPhotoUrl().toString() : null,
                categoryDTO,
                discountDTO
        );
    }

    public Products convertToEntity(ProductsDTO productDTO) {
        Products product = new Products();
        product.setProductId(productDTO.getProductId());
        product.setProductName(productDTO.getProductName());
        product.setProductDescription(productDTO.getProductDescription());
        product.setProductPrice(productDTO.getProductPrice());
        product.setProductStock(productDTO.getProductStock());
        product.setPhotoUrl(productDTO.getPhotoUrl());

        if (productDTO.getProductCategory().getCategoryId() != null) {
            Categories category = categoriesRepository.findById(productDTO.getProductCategory().getCategoryId())
                    .orElseThrow(() -> new NoSuchElementException("Category not found"));
            product.setCategory(category);
        }

        if (productDTO.getProductDiscount() != null) {
            Discounts discount = discountsRepository.findById(productDTO.getProductDiscount().getDiscountId())
                    .orElseThrow(() -> new NoSuchElementException("Discount not found"));
            product.setDiscount(discount);
        }

        return product;
    }
}
