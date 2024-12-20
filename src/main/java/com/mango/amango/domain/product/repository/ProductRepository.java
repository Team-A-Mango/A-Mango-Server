package com.mango.amango.domain.product.repository;

import com.mango.amango.domain.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByUserId(UUID userId);

    @Query("SELECT p FROM Product p WHERE p.isSold = false ")
    List<Product> findByIsSoldFalse();

    List<Product> findByTitleContaining(String keyword);
}
