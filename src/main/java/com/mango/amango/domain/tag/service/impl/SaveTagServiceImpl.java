package com.mango.amango.domain.tag.service.impl;

import com.mango.amango.domain.product.entity.Product;
import com.mango.amango.domain.tag.entity.Category;
import com.mango.amango.domain.tag.entity.Tag;
import com.mango.amango.domain.tag.repository.TagRepository;
import com.mango.amango.domain.tag.service.SaveTagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SaveTagServiceImpl implements SaveTagService {

    private final TagRepository tagRepository;

    @Override
    public void saveTag(Product product, List<Category> categories) {
        categories.forEach(category -> {
            tagRepository.save(Tag.builder()
                    .category(category)
                    .product(product)
                    .build());
        });
    }
}
