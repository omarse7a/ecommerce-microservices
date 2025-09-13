package com.konecta.product_service.service;

import com.konecta.product_service.dto.ProductCreationDto;
import com.konecta.product_service.entity.Product;
import com.konecta.product_service.exception.ProductAlreadyExistsException;
import com.konecta.product_service.exception.ProductNotFoundException;
import com.konecta.product_service.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository pRepository) {
        this.productRepository = pRepository;
    }

    public Product addProduct(ProductCreationDto dto) {
        if(productRepository.existsByName(dto.getName())) {
            throw new ProductAlreadyExistsException(
                    "Product Already Exists.",
                    Map.of("name", "name (" + dto.getName() + ") already exists"));
        }
        Product p = new Product();
        p.setName(dto.getName());
        p.setDescription(dto.getDescription());
        p.setPrice(dto.getPrice());
        p.setStock(dto.getStock());

        productRepository.save(p);
        return p;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(
                        "Product Not Found",
                        Map.of("id", "id (" + id + ") is not found")
                ));
    }
}
