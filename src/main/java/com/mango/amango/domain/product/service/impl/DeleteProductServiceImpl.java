package com.mango.amango.domain.product.service.impl;

import com.mango.amango.domain.product.entity.Product;
import com.mango.amango.domain.product.repository.ProductRepository;
import com.mango.amango.domain.product.service.DeleteProductService;
import com.mango.amango.domain.user.entity.User;
import com.mango.amango.domain.user.service.UserService;
import com.mango.amango.global.exception.CustomErrorCode;
import com.mango.amango.global.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class DeleteProductServiceImpl implements DeleteProductService {

    private final ProductRepository productRepository;
    private final UserService userService;

    @Override
    public void execute(Long productId) {
        User currentUser = userService.getCurrentUser();
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new CustomException(CustomErrorCode.NOT_FOUND_PRODUCT));

        if (!currentUser.equals(product.getUser())) {
            throw new CustomException(CustomErrorCode.NOT_MATCH_USER);
        }

        productRepository.delete(product);
    }
}
