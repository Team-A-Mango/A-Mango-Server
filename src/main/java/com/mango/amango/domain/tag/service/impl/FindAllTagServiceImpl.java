package com.mango.amango.domain.tag.service.impl;

import com.mango.amango.domain.tag.entity.Tag;
import com.mango.amango.domain.tag.repository.TagRepository;
import com.mango.amango.domain.tag.service.FindAllTagService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class FindAllTagServiceImpl implements FindAllTagService {

    private final TagRepository tagRepository;

    @Override
    public List<Tag> execute() {
        return tagRepository.findAll();
    }
}
