package com.mango.amango.domain.user.service.impl;

import com.mango.amango.domain.order.repository.OrderRepository;
import com.mango.amango.domain.order.util.OrderConverter;
import com.mango.amango.domain.product.entity.Product;
import com.mango.amango.domain.product.entity.ProductLike;
import com.mango.amango.domain.product.exception.NotFoundProductException;
import com.mango.amango.domain.product.repository.ProductLikeRepository;
import com.mango.amango.domain.product.repository.ProductRepository;
import com.mango.amango.domain.user.entity.User;
import com.mango.amango.domain.user.presentation.UserController;
import com.mango.amango.domain.user.presentation.dto.response.GetMyOrdersRes;
import com.mango.amango.domain.user.service.GetMyOrdersService;
import com.mango.amango.domain.user.service.UserService;
import com.mango.amango.domain.user.util.UserConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class GetMyOrdersServiceImpl implements GetMyOrdersService {

    private final UserService userService;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final ProductLikeRepository productLikeRepository;

    public GetMyOrdersRes execute() {
        User user = userService.getCurrentUser();

        List<GetMyOrdersRes.MyOrders> sale = productRepository.findAllByUserId(user.getId())
                .stream()
                .map(UserConverter::toDto)
                .toList();

        List<GetMyOrdersRes.MyOrders> purchase = orderRepository.findAllByUserId(user.getId())
                .stream()
                .map(product -> UserConverter.toDto(product.getProduct()))
                .toList();

        List<GetMyOrdersRes.MyLikes> likes = productLikeRepository.findByUserId(user.getId())
                .stream()
                .map(a -> UserConverter.toLikeDto(res(a)))
                .toList();

        return UserConverter.toDtoRes(sale, purchase, likes);
    }

    private Product res (ProductLike like) {
        return productRepository.findById(like.getProductId())
                .orElseThrow(NotFoundProductException::new);
    }
}
