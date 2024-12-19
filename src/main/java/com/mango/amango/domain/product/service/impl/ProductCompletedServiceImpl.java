package com.mango.amango.domain.product.service.impl;

import com.mango.amango.domain.order.entity.Order;
import com.mango.amango.domain.order.entity.OrderStatus;
import com.mango.amango.domain.order.repository.OrderRepository;
import com.mango.amango.domain.product.entity.Product;
import com.mango.amango.domain.product.exception.NotFoundOrderException;
import com.mango.amango.domain.product.exception.NotFoundProductException;
import com.mango.amango.domain.product.exception.OrderAlreadyTradedException;
import com.mango.amango.domain.product.repository.ProductRepository;
import com.mango.amango.domain.product.service.ProductCompletedService;
import com.mango.amango.domain.user.entity.User;
import com.mango.amango.domain.user.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductCompletedServiceImpl implements ProductCompletedService {

    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final UserService userService;

    public void execute(Long productId) {
        User user = userService.getCurrentUser();

        Product product = productRepository.findById(productId)
                .orElseThrow(NotFoundProductException::new);

        Order order = orderRepository.findByProductIdAndUserId(product.getId(), user.getId())
                .orElseThrow(NotFoundOrderException::new);

        if (order.getOrderStatus().equals(OrderStatus.FINISHED)) {
            throw new OrderAlreadyTradedException();
        } else order.updateOrderStatus(OrderStatus.FINISHED);
    }
}
