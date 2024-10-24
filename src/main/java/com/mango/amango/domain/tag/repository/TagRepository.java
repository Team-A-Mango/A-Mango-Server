package com.mango.amango.domain.tag.repository;

import com.mango.amango.domain.tag.entity.Tag;
import org.springframework.data.repository.CrudRepository;

public interface TagRepository extends CrudRepository<Tag, Long> {
}
