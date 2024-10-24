package com.mango.amango.domain.product.repository;

import com.mango.amango.domain.product.entity.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
