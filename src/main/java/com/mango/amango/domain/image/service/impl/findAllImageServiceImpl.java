package com.mango.amango.domain.image.service.impl;

import com.mango.amango.domain.image.entity.Image;
import com.mango.amango.domain.image.repository.ImageRepository;
import com.mango.amango.domain.image.service.FindAllImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class findAllImageServiceImpl implements FindAllImageService {

    private final ImageRepository imageRepository;

    @Override
    public List<Image> execute() {
        return imageRepository.findAll();
    }
}
