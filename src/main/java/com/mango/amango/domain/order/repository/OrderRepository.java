package com.mango.amango.domain.order.repository;

import com.mango.amango.domain.order.entity.Order;
import com.mango.amango.domain.order.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, Long> {
    boolean existsByProductId(Long productId);

    Optional<Order> findByProductId(Long productId);

    List<Order> findAllByUserId(UUID userId);

    Optional<Order> findByProductIdAndUserId(Long productId, UUID userId);

    boolean existsByOrderStatusAndStorageNumber(OrderStatus orderStatus, Integer storageNumber);
}
