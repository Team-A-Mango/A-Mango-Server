package com.mango.amango.domain.image.repository;

import com.mango.amango.domain.image.entity.Image;
import org.springframework.data.repository.CrudRepository;

public interface ImageRepository extends CrudRepository<Image, Long> {
}
