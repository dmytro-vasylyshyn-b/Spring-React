package com.website.Shop.Model;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Table(name = "OrderItems")
public class OrderItems {

    @Id
    @Column(name = "order_items_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderItemsId;

    @Column(name = "quantity")
    private int quantity;


    @Column(name = "price")
    private double price;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Products product;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Orders order;
}
