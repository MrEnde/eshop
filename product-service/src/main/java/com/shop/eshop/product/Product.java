package com.shop.eshop.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "product")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Column(name = "name", nullable = false)
    private String name;

    @CollectionTable(name="category", joinColumns = @JoinColumn(name = "category_id"))
    @Column(name = "category", nullable = false)
    private Long category;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
