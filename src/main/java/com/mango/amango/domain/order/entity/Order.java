package com.mango.amango.domain.order.entity;

import com.mango.amango.domain.product.entity.Product;
import com.mango.amango.domain.user.entity.User;
import com.mango.amango.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.mango.amango.domain.order.entity.OrderStatus.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private User user;

    @Enumerated(EnumType.STRING)
    private HandSign handSign;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus = PENDING;

    public void updateOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
