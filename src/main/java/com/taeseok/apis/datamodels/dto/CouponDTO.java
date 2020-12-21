package com.taeseok.apis.datamodels.dto;

import com.taeseok.apis.model.Coupon;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class CouponDTO {
    int couponId;
    Date expireAt;
    int avilableDays;
    int productID;
    String category;
    int discountPrice = 0;
    int discountPercentage = 0;

    public CouponDTO(Coupon coupon) {
        this.couponId = coupon.getCouponId();
        this.expireAt = coupon.getExpireAt();
        this.avilableDays = coupon.getAvailableDays();
        this.productID = coupon.getProductID();
        this.category = coupon.getCategory();
        this.discountPrice = coupon.getDiscountPrice();
        this.discountPercentage = coupon.getDiscountPercentage();
    }

    @Override
    public String toString() {
        return String.format(
                "CouponDTO[couponId=%d, expireAt='%s', productId=%d, category='%s', " +
                        "discountPrice=%d, discountPercentage=%d]",
                this.couponId, this.expireAt, this.avilableDays, this.productID,
                this.category, this.discountPrice, this.discountPercentage
        );
    }
}
