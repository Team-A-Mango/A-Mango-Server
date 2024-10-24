package com.mango.amango.domain.image.service;

import com.mango.amango.domain.image.entity.Image;
import com.mango.amango.domain.image.repository.ImageRepository;
import com.mango.amango.domain.product.entity.Product;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.mango.amango.domain.image.ImageUtil.*;

@Service
@Transactional
@AllArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;

    public void saveImage(Product product, List<MultipartFile> files) {
        for (MultipartFile file : files) {
            imageRepository.save(Image.builder()
                    .image(storeImage(file))
                    .product(product)
                    .build()
            );
        }
    }
}
