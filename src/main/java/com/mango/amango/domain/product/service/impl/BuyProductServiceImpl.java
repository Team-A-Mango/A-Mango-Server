package com.mango.amango.domain.product.service.impl;

import com.mango.amango.domain.order.entity.Order;
import com.mango.amango.domain.order.repository.OrderRepository;
import com.mango.amango.domain.order.util.OrderConverter;
import com.mango.amango.domain.product.entity.Product;
import com.mango.amango.domain.product.exception.NotFoundProductException;
import com.mango.amango.domain.product.exception.ProductAlreadyTradedException;
import com.mango.amango.domain.product.repository.ProductRepository;
import com.mango.amango.domain.product.service.BuyProductService;
import com.mango.amango.domain.user.entity.User;
import com.mango.amango.domain.user.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class BuyProductServiceImpl implements BuyProductService {

    private final ProductRepository productRepository;
    private final UserService userService;
    private final OrderRepository orderRepository;

    public void execute(Long productId) {
        User user = userService.getCurrentUser();

        Product product = productRepository.findById(productId)
                .orElseThrow(NotFoundProductException::new);

        if (orderRepository.existsByProductId(product.getId())) {
            throw new ProductAlreadyTradedException();
        } else {
            Order order = OrderConverter.toEntity(product, user);
            orderRepository.save(order);
        }
    }
}
