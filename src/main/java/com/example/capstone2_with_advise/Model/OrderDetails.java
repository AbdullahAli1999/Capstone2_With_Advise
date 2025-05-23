package com.example.capstone2_with_advise.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(columnDefinition = "int not null")
    private Integer order_id;
    @Column(columnDefinition = "int not null")
    private Integer product_id;
    @Column(columnDefinition = "int not null")
    private Integer quantity;
    @Column(columnDefinition = "int not null")
    private Integer price;
}