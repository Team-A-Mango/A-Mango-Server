package com.mango.amango.domain.product.service.impl;

import com.mango.amango.domain.product.entity.Product;
import com.mango.amango.domain.product.entity.ProductLike;
import com.mango.amango.domain.product.exception.NotFoundProductException;
import com.mango.amango.domain.product.repository.ProductLikeRepository;
import com.mango.amango.domain.product.repository.ProductRepository;
import com.mango.amango.domain.product.service.ToggleProductLikeService;
import com.mango.amango.domain.user.entity.User;
import com.mango.amango.domain.user.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class ToggleProductLikeServiceImpl implements ToggleProductLikeService {

    private final ProductRepository productRepository;
    private final ProductLikeRepository productLikeRepository;
    private final UserService userService;

    public void execute(Long productId) {
        User user = userService.getCurrentUser();

        Product product = productRepository.findById(productId)
                .orElseThrow(NotFoundProductException::new);

        ProductLike like = productLikeRepository.findByProductIdAndUserId(product.getId(), user.getId());

        if (like != null) {
            delete(like, product);
        } else {
            save(product, user);
        }

    }

    private void save (Product product, User user) {
        ProductLike like = ProductLike.builder()
                .userId(user.getId())
                .productId(product.getId())
                .build();
        productLikeRepository.save(like);
        product.increaseLikes();
    }

    private void delete (ProductLike productLike, Product product) {
        productLikeRepository.deleteById(productLike.getId());
        product.decreaseLikes();
    }
}
