package com.mango.amango.domain.tag.repository;

import com.mango.amango.domain.tag.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
}
