package com.codegym.service;

import com.codegym.model.Product;

public interface ProductService {
    Iterable<Product> findAll();
    Product findById(Integer id);
    Product save(Product product);
    Product remove(Integer id);
}
