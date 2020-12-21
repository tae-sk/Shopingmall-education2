package com.taeseok.apis.datamodels.dto;


import com.taeseok.apis.model.Coupon;
import com.taeseok.apis.model.IssuedCoupon;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class IssuedCouponDTO {
    int issuedCouponId;
    int couponId;
    int userId;
    Date expiredAt;
    boolean used;

    public IssuedCouponDTO(IssuedCoupon issuedCoupon) {
        this.issuedCouponId = issuedCoupon.getIssuedCouponId();
        this.couponId = issuedCoupon.getCouponId();
        this.userId = issuedCoupon.getUserId();
        this.expiredAt = issuedCoupon.getExpiredAt();
    }

    @Override
    public String toString() {
        return String.format(
                "IssuedCouponDTO[issueCouponId=%d,couponId=%d,userId=%d,expiredAt='%s',used='%s']",
                this.issuedCouponId,this.couponId,this.userId,this.expiredAt,this.used
        );
    }
}
