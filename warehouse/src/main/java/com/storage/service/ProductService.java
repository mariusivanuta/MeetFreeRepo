package com.storage.service;

import com.storage.entity.Product;
import com.storage.repository.ProductRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ProductService {

    @Inject
    ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAllByActive(true);
    }

    public Optional<Product> findById(Long productId) {
        return null;
        //  return productRepository.findById(productId);
    }

    @Transactional
    public Product save(Product product) {
        product.setActive(true);
        return null;
        ////return productRepository.save(product);
    }

    @Transactional
    public Product inactivate(Product product) {
        product.setActive(false);
        // return productRepository.save(product);
        return null;
    }
}
