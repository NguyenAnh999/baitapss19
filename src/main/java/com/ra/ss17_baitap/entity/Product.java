package com.ra.ss17_baitap.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "products")
public class Product {
    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer proId;
    @Column(name = "product_name")
    @NotEmpty(message = "ko dc de trong")
    private String proName;
    @Column(name = "producer")
    @NotEmpty(message = "ko dc de trong")
    private String producer;
    @Column(name = "year_making")
    @NotNull(message = "ko dc de trong")
    private Integer yearMaking;
    @Column(name = "expire_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "ko dc de trong")
    @Future(message = "ko dc nho hon ngay hien tai")
    private Date expireDate;
    @Column(name = "price")
    @NotNull(message = "ko dc de trong")
    private Double price;
}