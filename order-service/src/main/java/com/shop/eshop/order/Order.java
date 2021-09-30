package com.shop.eshop.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@NamedEntityGraph(name = "order-with-items",
        attributeNodes = @NamedAttributeNode(value = "items", subgraph = "item-subgraph"),
        subgraphs = {
        @NamedSubgraph(
                name = "item-subgraph",
                attributeNodes = {
                        @NamedAttributeNode("product")
                }
        )
    }
)
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToMany(mappedBy = "order")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<OrderItem> items;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @CollectionTable(name="user", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "user", nullable = false)
    private Long user;
}

