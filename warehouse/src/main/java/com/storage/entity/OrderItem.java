package com.storage.entity;

import javax.persistence.*;

@Entity
@Table(name = "orderitem")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orderitem_gen")
    @SequenceGenerator(name = "orderitem_gen", sequenceName = "orderitem_seq", initialValue = 1)
    Long id;
    
    String itemName;

    @ManyToOne
    Order order;
}
