package com.website.Shop.Controller;

import com.website.Shop.Services.ProductsService;
import com.website.Shop.dto.ProductsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductsController {

    private final ProductsService productsService;

    @Autowired
    public ProductsController(ProductsService productsService) {
        this.productsService = productsService;
    }

    @PostMapping(consumes = {"multipart/form-data"})
    public ProductsDTO addNewProduct(
            @RequestPart("productDTO") ProductsDTO productDTO,
            @RequestPart("file") MultipartFile file
    ) {
        return productsService.createProductWithImage(productDTO, file);
    }


    @GetMapping
    public List<ProductsDTO> getAllProducts(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice
    ) {
        return productsService.findProductsWithFilter(name, categoryId, minPrice, maxPrice);
    }


//    @PostMapping
//    public ProductsDTO addNewProduct(@Validated @RequestBody ProductsDTO productDTO) {
//        return productsService.createProduct(productDTO);
//    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long productId) {
        Optional<ProductsDTO> product = productsService.findProductById(productId);

        if (product.isPresent()) {
            productsService.deleteProduct(productId);
            return ResponseEntity.ok("Product " + product.get().getProductName() + " deleted");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ProductsDTO> updateProduct(@RequestBody ProductsDTO productDTO, @PathVariable Long productId) {
        return ResponseEntity.ok(productsService.updateProduct(productDTO, productId));
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductsDTO> getProductById(@PathVariable Long productId) {
        Optional<ProductsDTO> productDTO = productsService.findProductById(productId);

        if (productDTO.isPresent()) {
            return ResponseEntity.ok(productDTO.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
