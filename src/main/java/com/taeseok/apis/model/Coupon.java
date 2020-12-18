package com.taeseok.apis.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Getter
@NoArgsConstructor
@Entity
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int couponId;

    @Column
    Date expireAt;

    @Column
    int availableDays;

    @Column
    String productID;

    @Column
    String category;

    @Column
    int discountPrice = 0;

    @Column
    int intdiscountPercentage = 0;
}
