package com.mango.amango.domain.image.service;

import com.mango.amango.domain.product.entity.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageUploadService {
    void saveImage(Product product, List<MultipartFile> files);
}
