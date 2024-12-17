package com.mango.amango.domain.product.service.impl;

import com.mango.amango.domain.image.entity.Image;
import com.mango.amango.domain.image.service.FindAllImageService;
import com.mango.amango.domain.product.entity.Product;
import com.mango.amango.domain.product.presentation.dto.response.FindAllProductRes;
import com.mango.amango.domain.product.repository.ProductRepository;
import com.mango.amango.domain.product.service.FindAllProductService;
import com.mango.amango.domain.product.util.ProductConverter;
import com.mango.amango.domain.tag.entity.Tag;
import com.mango.amango.domain.tag.service.FindAllTagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FindAllProductServiceImpl implements FindAllProductService {

    private final ProductRepository productRepository;
    private final FindAllImageService imageService;
    private final FindAllTagService findAllTagService;

    @Override
    public List<FindAllProductRes> execute() {
        List<Product> products = productRepository.findAll();
        List<Image> images = imageService.execute();
        List<Tag> tags = findAllTagService.execute();

        return products.stream().map((product) ->
                    ProductConverter.toDto(product, images, tags)).toList();
    }
}
