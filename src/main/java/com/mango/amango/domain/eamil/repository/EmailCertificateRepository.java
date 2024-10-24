package com.mango.amango.domain.eamil.repository;

import com.mango.amango.domain.eamil.entity.EmailCertificate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailCertificateRepository extends JpaRepository<EmailCertificate, Long> {
    Boolean existsByEmail(String email);

    void deleteByEmail(String email);
}
