package com.mango.amango.domain.product.service.impl;

import com.mango.amango.domain.order.entity.Order;
import com.mango.amango.domain.order.repository.OrderRepository;
import com.mango.amango.domain.product.service.StockProductService;
import com.mango.amango.domain.user.entity.User;
import com.mango.amango.domain.user.service.UserService;
import com.mango.amango.global.exception.CustomErrorCode;
import com.mango.amango.global.exception.CustomException;
import com.mango.amango.global.sms.event.SendMessageEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.mango.amango.domain.order.entity.OrderStatus.*;

@Service
@Transactional
@RequiredArgsConstructor
public class StockProductServiceImpl implements StockProductService {

    private final OrderRepository orderRepository;
    private final UserService userService;
    private final ApplicationEventPublisher publisher;

    @Override
    public void execute(Long productId) {
        User currentUser = userService.getCurrentUser();
        Order order = orderRepository.findByProductId(productId)
                .orElseThrow(() -> new CustomException(CustomErrorCode.NOT_FOUND_PRODUCT));

        if (!order.getProduct().getUser().equals(currentUser)) {
            throw new CustomException(CustomErrorCode.NOT_MATCH_USER);
        }
        if (!order.getOrderStatus().equals(PENDING)) {
            throw new CustomException(CustomErrorCode.ALREADY_ORDER_STATUS);
        }

        order.updateOrderStatus(STOCK);
        String message = "보관함에 상품이 보관되어있습니다!\n빠른시일 내에 회수해 주세요";
        publisher.publishEvent(new SendMessageEvent(order.getUser().getPhoneNumber(), message));
    }
}
