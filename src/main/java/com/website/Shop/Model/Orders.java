package com.website.Shop.Model;


import com.website.Shop.Enums.OrdersStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table(name = "Orders ")
public class Orders {

    @Id
    @Column(name = "orders_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ordersId;

    @Column(name = "order_date")
    private Date orderDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status")
    private OrdersStatus ordersStatus;

    @Column(name = "order_total_amount")
    private double orderTotalAmount;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItems> orderItems;
}
