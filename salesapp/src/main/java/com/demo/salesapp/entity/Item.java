package com.demo.salesapp.entity;

import jakarta.persistence.*;
import lombok.Data;
import com.demo.salesapp.entity.User;

@Entity
@Data
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    String name;
    Double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", nullable = false)
    User craetedBy;

}
