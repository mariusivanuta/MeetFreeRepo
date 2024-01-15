package com.storage.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_gen")
    @SequenceGenerator(name = "order_gen", sequenceName = "order_seq", initialValue = 1)
    Long id;
    String orderName;

    @OneToMany
    List<OrderItem> orderItemList;
}
