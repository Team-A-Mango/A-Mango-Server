package com.mango.amango.domain.product.service.impl;

import com.mango.amango.domain.order.client.OrderClient;
import com.mango.amango.domain.order.entity.Order;
import com.mango.amango.domain.order.repository.OrderRepository;
import com.mango.amango.domain.order.util.OrderConverter;
import com.mango.amango.domain.product.entity.Product;
import com.mango.amango.domain.product.exception.NotFoundProductException;
import com.mango.amango.domain.product.exception.ProductAlreadyTradedException;
import com.mango.amango.domain.product.presentation.dto.request.OrderProductReq;
import com.mango.amango.domain.product.repository.ProductRepository;
import com.mango.amango.domain.product.service.BuyProductService;
import com.mango.amango.domain.user.entity.User;
import com.mango.amango.domain.user.service.UserService;
import com.mango.amango.global.exception.CustomErrorCode;
import com.mango.amango.global.exception.CustomException;
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
    private final OrderClient orderClient;

    public void execute(Long productId, OrderProductReq request) {
        User currentUser = userService.getCurrentUser();

        Product product = productRepository.findById(productId)
                .orElseThrow(NotFoundProductException::new);

        if (product.getUser().equals(currentUser)) {
            throw new CustomException(CustomErrorCode.MATCH_PRODUCT_SUBMIT_USER_AND_BUY_USER);
        }
        if (orderRepository.existsByProductId(product.getId())) {
            throw new ProductAlreadyTradedException();
        }

        product.markAsSold();
        Order order = OrderConverter.toEntity(product, currentUser, request.handSign());
        orderRepository.save(order);
        orderClient.postOrderIdentity(request.handSign().getValue());
    }
}
