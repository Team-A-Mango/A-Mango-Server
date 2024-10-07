package com.mango.amango.domain.product;

import com.mango.amango.domain.user.User;
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

    private String price;

    @ManyToOne
    private User user;

    private Integer view;

    private Integer likes;

    private LocalDateTime expirTime;

    private Long auctionPrice;

}
