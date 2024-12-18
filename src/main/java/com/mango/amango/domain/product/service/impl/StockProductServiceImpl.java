package com.mango.amango.domain.product.service.impl;

import com.mango.amango.domain.order.entity.Order;
import com.mango.amango.domain.order.entity.OrderStatus;
import com.mango.amango.domain.order.repository.OrderRepository;
import com.mango.amango.domain.product.entity.Product;
import com.mango.amango.domain.product.repository.ProductRepository;
import com.mango.amango.domain.product.service.StockProductService;
import com.mango.amango.domain.user.entity.User;
import com.mango.amango.domain.user.service.UserService;
import com.mango.amango.global.exception.CustomErrorCode;
import com.mango.amango.global.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.mango.amango.domain.order.entity.OrderStatus.*;

@Service
@Transactional
@RequiredArgsConstructor
public class StockProductServiceImpl implements StockProductService {

    private final OrderRepository orderRepository;
    private final UserService userService;

    @Override
    public void execute(Long productId) {
        User user = userService.getCurrentUser();
        Order order = orderRepository.findByProductId(productId)
                .orElseThrow(() -> new CustomException(CustomErrorCode.NOT_FOUND_PRODUCT));

        if (!order.getUser().equals(user)) {
            throw new CustomException(CustomErrorCode.NOT_MATCH_USER);
        }
        if (!order.getOrderStatus().equals(PENDING)) {
            throw new CustomException(CustomErrorCode.ALREADY_ORDER_STATUS);
        }

        order.updateOrderStatus(STOCK);
    }
}
