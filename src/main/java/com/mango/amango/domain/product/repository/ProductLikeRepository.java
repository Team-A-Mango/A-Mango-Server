package com.mango.amango.domain.product.repository;

import com.mango.amango.domain.product.entity.ProductLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProductLikeRepository extends JpaRepository<ProductLike, Long> {
    ProductLike findByProductIdAndUserId(Long productId, UUID userId);
}
