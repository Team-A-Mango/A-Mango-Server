package com.mango.amango.domain.image.service.impl;

import com.mango.amango.domain.image.entity.Image;
import com.mango.amango.domain.image.repository.ImageRepository;
import com.mango.amango.domain.image.service.ImageUploadService;
import com.mango.amango.domain.product.entity.Product;
import com.mango.amango.global.exception.CustomErrorCode;
import com.mango.amango.global.exception.CustomException;
import com.mango.amango.global.file.s3.S3ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ImageUploadServiceImpl implements ImageUploadService {

    private final S3ClientService s3ClientService;
    private final ImageRepository imageRepository;

    @Override
    public void saveImage(Product product, List<MultipartFile> files) {
        try {
            for (MultipartFile file : files) {
                if (file.isEmpty()) {
                    continue;
                }

                imageRepository.save(Image.builder()
                        .imageUrl(s3ClientService.upload(file))
                        .product(product)
                        .build());
            }

        } catch (IOException e) {
            throw new CustomException(CustomErrorCode.FILE_PROCESSING_ERROR);
        }
    }

}
