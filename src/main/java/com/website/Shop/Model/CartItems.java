package com.website.Shop.Model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table(name="CartItems")
public class CartItems {

    @Id
    @Column(name = "cart_items_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long chartItemsId;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Products product;

    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;

    @Column(name = "quantity")
    private int quantity;
}
