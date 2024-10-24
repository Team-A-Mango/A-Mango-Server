package com.mango.amango.domain.product.entity;

import com.mango.amango.domain.user.entity.User;
import com.mango.amango.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product")
public class Product extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private Long price;

    @ManyToOne
    private User user;

    @Builder.Default
    private Integer view = 0;

    @Builder.Default
    private Integer likes = 0;

    private LocalDateTime expirTime;

    private Long auctionPrice;

}
