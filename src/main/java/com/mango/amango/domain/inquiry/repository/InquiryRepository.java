package com.mango.amango.domain.inquiry.repository;

import com.mango.amango.domain.inquiry.entity.Inquiry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InquiryRepository extends JpaRepository<Inquiry, Long> {
    List<Inquiry> findAllByProductId(Long productId);
}
