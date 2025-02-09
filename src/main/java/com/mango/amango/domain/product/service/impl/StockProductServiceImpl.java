package com.mango.amango.domain.product.service.impl;

import com.mango.amango.domain.order.client.OrderClient;
import com.mango.amango.domain.order.entity.Order;
import com.mango.amango.domain.order.repository.OrderRepository;
import com.mango.amango.domain.product.presentation.dto.request.StockProductReq;
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
    private final OrderClient orderClient;

    @Override
    public void execute(Long productId, StockProductReq request) {
        User currentUser = userService.getCurrentUser();
        Order order = orderRepository.findByProductId(productId)
                .orElseThrow(() -> new CustomException(CustomErrorCode.NOT_FOUND_PRODUCT));

        if (!order.getProduct().getUser().equals(currentUser)) {
            throw new CustomException(CustomErrorCode.NOT_MATCH_USER);
        }
        if (order.getOrderStatus().equals(STOCK)) {
            throw new CustomException(CustomErrorCode.ALREADY_STOCK_ORDER_STATUS);
        }
        if (order.getOrderStatus().equals(FINISHED)) {
            throw new CustomException(CustomErrorCode.ALREADY_FINISHED_ORDER_STATUS);
        }
        if (orderRepository.existsByOrderStatusAndStorageNumber(STOCK, request.storageNumber())) {
            throw new CustomException(CustomErrorCode.STORAGE_ALREADY_OCCUPIED);
        }

        order.stockProduct(request.storageNumber());
        orderClient.postOrderIdentity(order);
        String message = request.storageNumber() + "번 보관함에 상품이 보관되어 있습니다!\n이른 시일 내에 회수해 주세요";
        publisher.publishEvent(new SendMessageEvent(order.getUser().getPhoneNumber(), message));
    }
}
