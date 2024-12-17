package com.mango.amango.domain.tag.service;

import com.mango.amango.domain.product.entity.Product;
import com.mango.amango.domain.tag.entity.Category;

import java.util.List;

public interface SaveTagService {
    void saveTag(Product product, List<Category> categories);
}
